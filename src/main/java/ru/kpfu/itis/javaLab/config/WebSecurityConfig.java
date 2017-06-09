package ru.kpfu.itis.javaLab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.kpfu.itis.javaLab.model.enums.UserRole;

import javax.servlet.Filter;

/**
 * Created by Safin Ramil on 07.06.17
 * Safin.Ramil@ordotrans.ru
 * <p>
 * Custom Web Spring Security configuration
 */

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"ru.kpfu.itis.javaLab.security"})

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final OAuth2ClientContext oAuth2ClientContext;

    @Autowired
    public WebSecurityConfig(
        @Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
        @Qualifier("oauth2ClientContext") OAuth2ClientContext oAuth2ClientContext
    ) {
        this.userDetailsService = userDetailsService;
        this.oAuth2ClientContext = oAuth2ClientContext;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login**", "/auth/**", "/signup/**").permitAll()
                .antMatchers("/admin/**").hasAuthority(UserRole.ROLE_ADMIN.name())
                .antMatchers("/images/**", "/js/**", "/css/**").permitAll()
                .antMatchers("/fonts/**", "/favicon.ico", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", false)
            .and()
            .logout()
                .logoutSuccessUrl("/login?logout")
                .logoutUrl("/logout").permitAll()
            .and()
            .rememberMe()
                .tokenValiditySeconds(32 * 60 * 60)
                .alwaysRemember(true)
            .and()
            .csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }


    @Bean
    @Autowired
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }


    // Sign in via Google filter
    private Filter ssoFilter() {

        // google
        OAuth2ClientAuthenticationProcessingFilter googleFilter =
            new OAuth2ClientAuthenticationProcessingFilter("/auth/google");

        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);

        googleFilter.setRestTemplate(googleTemplate);

        UserInfoTokenServices tokenServices = new UserInfoTokenServices(googleResource().getUserInfoUri(),
            googleResource().getClientId());

        tokenServices.setRestTemplate(googleTemplate);

        googleFilter.setTokenServices(tokenServices);

        return googleFilter;
    }


    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }


    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

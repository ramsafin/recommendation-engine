package ru.kpfu.itis.javaLab.web.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kpfu.itis.javaLab.web.validators.annotations.PasswordsMatch;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Safin Ramil on 07.06.17
 * RamilSafNab1996@gmail.com
 */

@PasswordsMatch
public class RegistrationForm implements Serializable {

    private String email;

    private String password;

    private String passwordRepeat;

    private String name;

    private String surname;

    public RegistrationForm() {

    }

    @Size(min = 6, max = 64, message = "Email should be within 6 and 64")
    public String getEmail() {
        return email;
    }

    @Size(min = 8, max = 64, message = "Password length should be within 8 and 64")
    public String getPassword() {
        return password;
    }

    @JsonProperty(value = "password_repeat")
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

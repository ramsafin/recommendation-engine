package ru.kpfu.itis.javaLab.web.listenters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */

@WebListener
public class TestHttpSessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(TestHttpSessionListener.class);

    private final AtomicLong counter = new AtomicLong(0); // online users counter

    private final String COUNTER_PARAM = "visitors";


    @Override
    public void sessionCreated(HttpSessionEvent se) {

        ServletContext servletContext = se.getSession().getServletContext();

        servletContext.setAttribute(COUNTER_PARAM, counter.incrementAndGet());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        ServletContext servletContext = se.getSession().getServletContext();

        servletContext.setAttribute(COUNTER_PARAM, counter.decrementAndGet());
    }
}

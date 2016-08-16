package ru.devjava.averchenkov.staff.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Класс конфигурирования.
 *
 * @author Averchenkov R.A.
 */
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = {"ru.devjava.averchenkov.staff"})
public class WebConfig extends WebMvcConfigurerAdapter {
    /*
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/
}

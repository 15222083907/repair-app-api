package com.toec.market.repair;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.toec.market.repair.convert.DateConverter;


@Configuration
 public class WebMvcConfig extends WebMvcConfigurationSupport {
     @Override
     public void addFormatters(FormatterRegistry registry) {
         registry.addConverter(new DateConverter());
     }

}
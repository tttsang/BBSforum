package com.jking.website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {
  @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry)
  {
      resourceHandlerRegistry.addResourceHandler("/images/Post/**").addResourceLocations("file:D:/Post/");
      resourceHandlerRegistry.addResourceHandler("/images/Reply/**").addResourceLocations("file:D:/Reply/");
      super.addResourceHandlers(resourceHandlerRegistry);
  }
}

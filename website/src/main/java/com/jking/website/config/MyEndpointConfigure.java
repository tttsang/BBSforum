package com.jking.website.config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpointConfig;

@Component
public class MyEndpointConfigure extends ServerEndpointConfig.Configurator implements ApplicationContextAware{
    private static volatile BeanFactory  beanFactory;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return beanFactory.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyEndpointConfigure.beanFactory= applicationContext;
    }
}

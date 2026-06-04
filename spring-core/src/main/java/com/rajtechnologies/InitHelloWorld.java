package com.rajtechnologies;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class InitHelloWorld implements BeanPostProcessor {

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       System.out.println("bean name after initialization "+beanName);
        return bean;
    }

    @Override
    public @Nullable Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean name before initialization "+beanName);
        return bean;
    }
}

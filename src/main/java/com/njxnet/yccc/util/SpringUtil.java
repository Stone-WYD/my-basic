package com.njxnet.yccc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware, EnvironmentAware {

    private static ApplicationContext context;
    private static Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }

    public static Environment getEnvironment() {
       return environment;
    }

    /**
     * 获取bean
     *
     */
    public static <T> T getBean(Class<T> bean) {
        return context.getBean(bean);
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 获取配置
     *
     */
    public static String getConfig(String key) {
        return environment.getProperty(key);
    }

    /**
     * 发布事件消息
     *
     */
    public static void publishEvent(ApplicationEvent event) {
        context.publishEvent(event);
    }
}

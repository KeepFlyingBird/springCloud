package cn.freeFly.springcloud.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * IOC容器有beanFactory 和ApplicationContext.通常建议使用后者，因为它包含了前者的功能。Spring的核心是ApplicationContext.它负责管理 beans 的完整生命周期。
 * 我们可以从applicationContext里通过bean名称获取安装的bean.进行某种操作。不能直接使用applicationContext.而需要借助applicationContextAware
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public ApplicationContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext != null?applicationContext.getBean(beanName):null;
    }
}

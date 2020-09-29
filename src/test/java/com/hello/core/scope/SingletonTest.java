package com.hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(singletonBean.class);
        singletonBean singletonBean1 = ac.getBean(singletonBean.class);
        singletonBean singletonBean2 = ac.getBean(singletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class singletonBean{

        @PostConstruct
        public void init(){
            System.out.println("NetworkClient.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("NetworkClient.close");
        }
    }
}

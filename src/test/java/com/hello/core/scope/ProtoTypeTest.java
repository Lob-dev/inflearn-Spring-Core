package com.hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ProtoTypeTest {

    @Test
    void protoTypeBeanFind() {
        //AnnotationConfigApplicationContext(대상 클래스) 대상 클래스에 Configuration이나 Component Scan이 없더라도
        //대상을 직접 지정하여 가져오는 것이기 때문에 상관이 없다.

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(protoTypeBean.class);

        //2개가 생성이 된다.
        System.out.println("Find protoTypeBean1");
        protoTypeBean protoTypeBean1 = ac.getBean(protoTypeBean.class);
        System.out.println("Find protoTypeBean2");
        protoTypeBean protoTypeBean2 = ac.getBean(protoTypeBean.class);

        //즉 다른 Spring Bean이다.
        System.out.println("protoTypeBean1 = " + protoTypeBean1);
        System.out.println("protoTypeBean2 = " + protoTypeBean2);

        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class protoTypeBean{

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("prototypeBean.close");
        }
    }
}

package com.hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(clientBean.class, PrototypeBean.class);

        clientBean clientBean1 = ac.getBean(clientBean.class);

        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        clientBean clientBean2 = ac.getBean(clientBean.class);

        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);


    }

    // Singleton Bean인 ClientBean 위에 prototypeBean 을 불러낸다. (이때 스프링 컨테이너에서 생성하고 던져준다.)
    // 이 사용법의 경우 prototype bean의 사용 이유가 없어진다. (상태가 공유되기 때문에?)
    @Scope("singleton")
    static class clientBean {
        //private final PrototypeBean prototypeBean; // 생성 시점에서 주입. 계속 같은 것을 사용하게 됨.

//      @Autowired
//      ApplicationContext applicationContext; // 주입 시에 prototype Bean을 생성하는 것이 아니라.

        //지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공하는 것이 바로 ObjectProvider이다.
        //ObjectFactory 를 상속받아 편의 기능을 추가한 녀석이다. 스프링에 의존적이다. 상속, 옵션, 스트림 처리등 편의 기능이 많다.
/*      @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;*/

        //JSR-330 표준의 Provider 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워진다
        //get() 이라는 기능하나만을 가지고 있다. 스프링에 의존적이지 않다.
        //javax.inject:javax.inject:1 라이브러리를 추가해야 사용이 가능하다.
        //lazy retrieval or Optional retrieval and 순환 참조가 발생할 때 사용하면 된다.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


//      @Autowired
//      public clientBean(PrototypeBean prototypeBean) {
//          this.prototypeBean = prototypeBean;
//      }

        public int logic() {
            // 로직이 호출될 때마다 prototype bean을 요청하고 Spring Container가 새로 생성해준다.
            //PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);

            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}



package com.hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("사용자 A와 사용자 B의 요청이 겹쳤을 경우")
    void stateServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 만원을 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadA : B사용자가 만원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("userA" + userAPrice);

        assertThat(userAPrice).isEqualTo(10000);

    }

    @Configuration
    static class TestConfig {

        @Bean
        public  StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
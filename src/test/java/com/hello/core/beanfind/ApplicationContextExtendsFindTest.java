package com.hello.core.beanfind;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);\
        DiscountPolicy reteDiscountPolicy = ac.getBean("reteDiscountPolicy", DiscountPolicy.class);
        assertThat(reteDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입(구현체)으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }


    // 테스트 케이스에선 System.out.println 같은 건 안쓰는 것이 좋다.
    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beanOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beanOfType.size()).isEqualTo(2);
        for (String key : beanOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beanOfType);
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회 - Object ")
    void findAllBeanByObjectType() {
        Map<String, Object> beanOfType = ac.getBeansOfType(Object.class);
        for (String key : beanOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beanOfType);
        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy reteDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}

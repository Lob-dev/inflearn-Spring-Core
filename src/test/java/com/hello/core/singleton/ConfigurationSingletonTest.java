package com.hello.core.singleton;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.member.MemoryMemberRepository;
import com.hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 3개의 Bean이 동일하다. AppConfig 에서 3번의 new memberRepository가 생성될 것 같았는데 그러지 않았다.
        System.out.println("memberService -> memberRepository1 = "+memberRepository1);
        System.out.println("orderService -> memberRepository2 = " +memberRepository2);
        System.out.println("memberRepository = " +memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = "+bean.getClass());
        // 순수한 클래스라면 class com.hello.core.AppConfig 까지만 나와야함
        // AppConfig$$EnhancerBySpringCGLIB$$bc20a6a8?
        // CGLIB ? : Bytecode 를 조작하는 라이브러리이다.
        // AppConfig 를 상속받는 임의의 클래스를 만들고 그 다른 클래스를 빈으로 등록하는 것이다.
        // 그 임의의 다른 클래스가 싱글톤이 보장되도록 해준다. AppConfigCGLIB()
        // 스프링 빈이 없으면 생성해서 빈으로 등록하고 빈이 있으면 빈을 반환하는 코드가 동적으로 생성된다. (예상)
    }
}

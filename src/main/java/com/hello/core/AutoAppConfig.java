package com.hello.core;

import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(// @Component 어노테이션이 붙은 모든 클래스를 찾아서 자동으로 스프링 빈으로 등록한다.
        // 어노테이션 타입을 스캔하는데, Configuration.class 를 제외한다. (AppConfig.class, TestConfig.class 등을 제외함)
        // basePackages 를 주지 않는다면 기본적으로 자신의 위치를 기준으로 하위 패키지까지 스캔한다. 모두 스캔하려면 시작 루트에 두어야한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //Overriding bean definition for bean 'memoryMemberRepository' with a different definition:
    //수동 등록 빈이 자동 등록 빈보다 우선권을 가진다.
    //수동 빈이 자동 빈을 오버라이딩 해버린다. (원래는..)
    //최근 버전의 스프링 부트는 수동 빈 등록과 자동 등록 빈이 충돌이 나면 에러를 발생시킨다.
    //관련 설정 spring.main.allow-bean-definition-overriding=false
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}

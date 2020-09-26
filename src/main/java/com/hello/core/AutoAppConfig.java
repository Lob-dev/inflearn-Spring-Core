package com.hello.core;

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
}

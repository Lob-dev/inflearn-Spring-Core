package com.hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String s) {
        this.url = s;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

/*  설정 정보를 통한 생애주기 콜백. (해당 @Bean 설정에서 (initMethod = "init", destroyMethod = "close") 사용)
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }*/

    /*
     어노테이션 설정을 통한 생애주기 콜백.
     최신 스프링에서 권장하는 방법이다. + JSR-250 자바 표준 기술이다. 단점으로는 외부 라이브러리에 적용할 수 없다.
     외부 라이브러리에 적용하려면 설정 정보를 통하여 적용하자. (내부 어노테이션, 외부 Bean 설정)
    */
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

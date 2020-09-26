package com.hello.core.singleton;

public class SingletonService {

    //Static은 런타임 시점에서 한번 생성되어서 유지된다. 즉 해당 메서드는 한번만 요청되고 다시 요청되지 않는다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서의 생성을 막기위해 작성
    private SingletonService() {
    }

    public void logic() {
        System.out.println("Singleton 객체 로직 호출 : do something..");
    }

}

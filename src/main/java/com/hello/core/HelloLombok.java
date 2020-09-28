package com.hello.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor // final이 붙은 객체를 넣어주는 생성자가 만들어짐
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("SAdasd");
        String a = helloLombok.getName();

        System.out.println("a = " + a);

    }
}

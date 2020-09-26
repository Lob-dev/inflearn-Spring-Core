package com.hello.core.singleton;

public class StatefulService {

    // 공유 상태 인자를 필드에 저장하고 반환하는 방식 X
    // private int price; 이런 공유 필드는 만들면 안된다.
    // 이문제가 아니여도 멀티쓰레드 간의 문제로 발생할 수 있다.


    // 무상태로 설계하기 : 지역변수, 파라미터를 사용해라.
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; 여기가 문제
        return price;
    }




//    필드 값이 없으니 제거
//    public int getPrice() {
//        return price;
//    }

}

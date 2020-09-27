package com.hello.core.order;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.member.Member;
import com.hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //할인 정책을 변경하려면 클라이언트인 ServiceImpl의 코드를 고쳐야한다. 클라이언트가 구현체를 의존한다.
    //인터페이스 의존 DiscountPolicy , 구체 클래스 의존 FixDiscountPolicy, RateDiscountPolicy
    //두개의 부류를 함께 의존하고 있다. DIP 위반 (추상화(인터페이스)만 의존해야 한다.)


    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    // 테스트 용도
    public  MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

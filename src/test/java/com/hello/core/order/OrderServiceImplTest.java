package com.hello.core.order;

import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        //given
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());

        //when
        Order order = orderService.createOrder(1L, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

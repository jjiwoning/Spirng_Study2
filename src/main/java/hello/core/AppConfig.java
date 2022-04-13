package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정 정보 (구성 정보)를 나타낸다. 어플리케이션의 어떻게 구성되는지를 담당하는 코드
@Configuration
public class AppConfig {

    // 이렇게 Bean으로 등록해두면 스프링 컨테이너에 등록이 된다.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
        //생성자를 통해서 구현체를 외부에서 주입한다. 생성자 주입
        //리팩터링 : 커맨드 옵션 m
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy(){

        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

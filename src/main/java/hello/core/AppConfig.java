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
    // @Bean -> memberService() -> new MemoryMemberRepository() 호출하게 된다.
    // @Bean -> orderService() -> 이놈도 MemoryMemberRepository() 호출한다.
    // -> 2번이 호출 되네? 싱글톤이 깨지지 않나? Test 코드로 확인해보자!!

    //1. call AppConfig.memberService
    //2. call AppConfig.memberRepository
    //3. call AppConfig.memberRepository
    //4. call AppConfig.orderService
    //5. call AppConfig.memberRepository
    // 실행 순서는 보장이 안되지만 이 갯수대로 출력이 될 것이다. 근데 실제로 코드를 돌려보면 각각 1개씩 출력이 된다. -> 총 3개 실행됨.
    // 왤까?
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService"); // -> soutm 단축키로 바로 호출 가능
        return new MemberServiceImpl(memberRepository());
        //생성자를 통해서 구현체를 외부에서 주입한다. 생성자 주입
        //리팩터링 : 커맨드 옵션 m
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
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

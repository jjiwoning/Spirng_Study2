package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // -> 우리가 앞서 만든 생성자를 그대로 만들어준다. final이 붙은 객체 변수들을 파라미터로 받는 생성자 만들어주는 역할
public class OrderServiceImpl implements OrderService{

    // 생성자 주입을 사용해야 final 키워드를 사용할 수 있다.
    private final MemberRepository memberRepository;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -> DIP , OCP 위반 -> 인터페이스에만 의존하게 다시 설계해야된다.
    private final DiscountPolicy discountPolicy; // 이렇게 변경을 해주면 된다.
    // 구현체가 없어서 실제로 실행하면 null pointer exception 발생 -> 누군가 구현 객체 주입 해줘야된다.
    // final -> 객체에 값이 무조건 있어야 된다 라는 의미. -> 생성자에 값을 무조건 넣어줘라

//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Autowired // 생성자 위에다 하면 된다. + 생성자가 1개만 있다면 Autowired를 생략해도 자동 주입이 된다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 정보 먼저 검색
        Member member = memberRepository.findById(memberId);
        // 할인 정책은 discount 한테 넘김 -> 할인쪽이 고장나면 할인만 고치면 된다. 주문은 냅둬도 된다.
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일 책임 원칙을 잘 지킨 예시

        return new Order(memberId, itemName, itemPrice, discountPrice); //최종적으로 할인된 가격

    }

    //테스트 용도 코드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}

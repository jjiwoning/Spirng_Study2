package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -> DIP , OCP 위반 -> 인터페이스에만 의존하게 다시 설계해야된다.
    private final DiscountPolicy discountPolicy; // 이렇게 변경을 해주면 된다.
    // 구현체가 없어서 실제로 실행하면 null pointer exception 발생 -> 누군가 구현 객체 주입 해줘야된다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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

}

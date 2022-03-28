package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 정보 먼저 검색
        Member member = memberRepository.findById(memberId);
        // 할인 정책은 discount 한테 넘김 -> 할인쪽이 고장나면 할인만 고치면 된다. 주문은 냅둬도 된다.
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일 책임 원칙을 잘 지킨 예시

        return new Order(memberId, itemName, itemPrice, discountPrice); //최종적으로 할인된 가격

    }

}

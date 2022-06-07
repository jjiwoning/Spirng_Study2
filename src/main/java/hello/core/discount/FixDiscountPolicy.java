package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;


public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 고정 할인

    @Override
    public int discount(Member member, int price) {
        // enum 자료형은 == 으로 비교 가능
        if (member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}

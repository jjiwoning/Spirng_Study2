package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 인터페이스 뿐만 아니라 구현체도 의존하게 된다. -> DIP(의존관계 역전 원칙)를 위반하고 있다.
    private final MemberRepository memberRepository;

    //component로 스프링 빈이 등록이 되면 의존 관계 주입에 문제가 발생한다. 이 때 의존관계를 자동으로 넣어주는 @Autowired가 필요하다.
    @Autowired // ac.getBean(MemberRepository.class)와 같다.
    //이를 적어주면 스프링에서 MemberRepository type에 맞는 걸 찾아와서 의존관계 주입을 자동으로 해준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도 코드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

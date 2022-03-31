package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 인터페이스 뿐만 아니라 구현체도 의존하게 된다. -> DIP(의존관계 역전 원칙)를 위반하고 있다.
    private final MemberRepository memberRepository;

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
}

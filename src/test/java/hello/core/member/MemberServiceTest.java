package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    // 테스트 코드를 잘 작성하는 것이 상당히 중요하다!!
    @Test // 이 방법을 통해 콘솔 창 확인 없이 테스트가 잘 되었는지 확인 가능하다.
    void join(){
        //given -> 이러한 환경이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when -> 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then -> 이렇게 된다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }

}

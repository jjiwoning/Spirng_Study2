package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    // 순수한 자바 코드로 테스트 개발 -> 한계가 있다.
    // junit을 쓰는게 좋다
    public static void main(String[] args) {
        //AppConfig를 사용하는 버전
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        //스프링을 사용하는 버전
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링은 항상 이걸로 시작한다. -> 스프링 컨테이너라고 보면 된다. -> 모든 객체들을 관리해주는 역할 수행
        // AppConfig.class -> 클래스의 인스턴스가 아닌 타입 자체를 넘겨야되기 때문에
        // new -> 초기화해서 클래스 내 멤버들 접근 가능
        // .class -> 클래스 타입으로 접근하여 클래스의 속성, 멤버에 대한 정보에 접근 가능 -> 리플렉션이라고 한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // 첫 매개변수는 꺼내려고 하는 메서드의 이름, 두 번째 매개변수는 반환 타입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

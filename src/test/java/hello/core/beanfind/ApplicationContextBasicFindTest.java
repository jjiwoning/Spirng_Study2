package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*; // -> static import : 메서드나 변수를 패키지,클래스명 없이 접근 가능하게 해준다.
import static org.junit.jupiter.api.Assertions.*;
// Assertion 의 assertj 사용

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    // 인터페이스로 조회를 하면 인터페이스의 구현체가 대상이 된다.
    @Test
    @DisplayName("빈 타입으로만 조회")
    void findBeanByType(){
        //type으로 조회할때는 같은 타입이 있을 때 조금 곤란해진다.
        MemberService memberService = ac.getBean(MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //구체적인 타입으로도 스프링 빈을 조회할 수 있다. -> 구현에 의존한거라 좋은 방식은 아니다. (역할과 구현의 분리에서 역할에 의존해야되기 때문)
        // 유연성이 떨어진다.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //항상 테스트는 실패 테스트도 만들어야 된다.
    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        //ac.getBean("xxxx", MemberService.class); -> 이런거는 없다.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }
}

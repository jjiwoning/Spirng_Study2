package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        //셋이 모두 똑같음을 알 수 있다.

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class); // AppConfig도 spring bean으로 생성할 수 있다.

        System.out.println("bean.getClass() = " + bean.getClass()); // getClass()는 class 타입을 확인시켜준다.
        // bean.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$aa31659a
        // -> 원래는 AppConfig까지 나와야 하는데 뭔가 더 추가된걸 볼 수 있다.
        // 이 클래스는 내가 만든 클래스가 아닌 스프링이 CGLIB이라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클레스를
        // 상속받는 임의의 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것 이다.
    }
}

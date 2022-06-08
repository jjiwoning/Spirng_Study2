package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core" ,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 뺄거 지정해주는 거 -> Configuration 어노테이션 붙은 클래스 뺄거다. -> 지금까지 만든 예제와 충돌 가능성 때문에 넣음
        // 실제로는 스캔 대상에서 제외하지 않는다.
) // 이 컴포넌트 스캔은 @Component 가 붙은 클래스를 찾아서 스프링 빈에 등록시켜준다.
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}

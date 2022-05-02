package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){ // junit 5 버전부터는 public 안적어줘도 된다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { // iter + tab -> 리스트, 배열이 있을 때 for문 자동 생성해준다.
            Object bean = ac.getBean(beanDefinitionName); // type 지정을 안해줘서 object가 나온다.
            System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean); // soutv + tab

        }
    }
    //모든 빈이 아닌 어플리케이션 빈만 보고싶다.
    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean(){ // junit 5 버전부터는 public 안적어줘도 된다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { // iter + tab -> 리스트, 배열이 있을 때 for문 자동 생성해준다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//bean에 대한 메타데이터 정보

            //Role ROLE_APPLICATION : 직접 등록한 어플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName); // type 지정을 안해줘서 object가 나온다.
                System.out.println("beanDefinitionName = " + beanDefinitionName + "object = " + bean); // soutv + tab
            }
        }
    }


}

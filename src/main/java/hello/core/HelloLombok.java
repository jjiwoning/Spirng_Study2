package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(11);
        helloLombok.setName("TomTom");

        System.out.println("helloLombok = " + helloLombok);
        // lombok이 getter setter를 자동으로 만들어준다
    }
}

package hello.core.singleton;

public class SingletonService {
    // final class -> inheritance No!
    // static 영역에 객체를 1개 생성해두고
    private static final SingletonService instance = new SingletonService();

    // 객체 인스턴스가 필요하면 public static 매서드를 이용해서 조회하도록 한다.
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private으로 설정하여 외부에서 new로 생성을 못하게 한다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
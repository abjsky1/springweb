package example.day039_260831_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

public class Spring_exam3 {
    public static void main(String[] args) {
        
    //  [1]
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    // 
    }
}  // class END



//  [1] 전통 방식으로 객체 생성
class SampleDao{

    void method(){System.out.println("메소드 실행");}

}

class SampleController1{

    void method(){
    
    //  1. 다른 클래스 안에 메소드 호출하는 방법

    //      1) 인스턴스 생성(주체)
    
            SampleDao sampleDao = new SampleDao();

    //      2) 해당 인스턴스(주체)가 메소드를 호출함.
            sampleDao.method();

    }

}

//  [2] 전통 방식으로 싱글톤 생성 , dao 인스턴스 생성 불가능
class SampleDao2{

    private SampleDao2(){}
    private static final SampleDao2 instance = new SampleDao2();
    public static SampleDao2 getInstance(){return instance;}

    public void method(){}

}

class SampleController2{

    SampleDao2 dao2 = SampleDao2.getInstance();

    void method(){

        // 2. 다른 클래스 안에 메소드 호출하는 방법
        dao2.method();

    }
    
}

//  [3] 스프링 IOC/DI
@Component
class SampleDao3{

    // 싱글톤 대신 @Component 이용하여 IOC(자동객체관리) 규칙으로 스프링 컨테이너에 자동 빈(객체) 등록
    void method(){}

}

class SampleController3{
    

//  1. 스프링 컨테이너(메모리/저장소)에 등록된 빈(객체) 가져와서 대입

//  의존성주입
    @Autowired  
    SampleDao3 dao3;

    void method(){

        dao3.method();

    }

}

class SampleController4{

//  2. 스프링에서 권장하는 방식
    private final SampleDao3 dao3;

    @Autowired
    public SampleController4(SampleDao3 dao3){
        this.dao3 = dao3;
    }

    void method(){}

}

@RequiredArgsConstructor
class SampleController5{

//  3. 롬복 이용한 방식 , final 초기화 이후 수정 불가능.
    private final SampleDao3 dao3;

}


/*

    정리

    다른 클래스의 메소드 호출하기 위해서는 객체가 필요함.

    1. [전통] new 클래스명().메소드명();
    2. [프로젝트] 클래스명().getInstance().메소드명();
    3. [스프링] @Component 등록 , @Autowired 호출

    @Component 등록되는 시점.  ==  SpringBootApplication 안에 ( @ComponentScan )

    ==> 스프링이 켜지면서 폴더 안에 @Component 다 찾아내서 스프링 컨테이너에 등록하는 구조

    주의할 점 : AppStart 는 최상위에 위치하게 만든다.

*/
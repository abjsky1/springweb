package example.day045_260909_spring.spring_exam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;


public class Spring_exam1 {
    public static void main(String[] args) {
        
    //  서로 다른 클래스의 메소드를 호출하는 방법

    //  메소드 - 상호작용( 2개 이상의 개체 주고(인수/매개변수:M) 받음(리턴:1) )

    //  1. 인스턴스 생성하여 메소드 호출 
        TestService1 testService = new TestService1();
        int result1 = testService.plus(3, 5);

    //  2. 싱글톤 생성
        TestService2 testService2 = TestService2.getInstance();
        int result2 = testService.plus(4, 8);
    
    //  3. 스태틱(static) 메소드
        int result3 = TestService3.plus(5, 10);
    
    //  4. 스프링방식
    //  @RequiredArgsConstructor 
    //  private final TestService4 testService4;
    //  or
    //  @Autowired private TestService4 testService4;
    
    //  인스턴스(주체O) vs static(주체X)

    //  5. 사칙연산( 연산은 항상 하나의 값 반환 )

    // 
    // 

        ClassRoom c1 = new ClassRoom();
        c1.개별호출().밥먹기();



    }
}


class TestService1{

    int plus(int x, int y){
        return x+y;
    }

}

class TestService2{

    private TestService2(){}
    private static final TestService2 instance = new TestService2();
    public static TestService2 getInstance(){return instance;}

    int plus(int x, int y){
        return x+y;
    }

}

class TestService3{

    static int plus(int x, int y){
        return x+y;
    }
}

class TestService4{

    int plus(int x, int y){
        return x+y;
    }
}

class ClassRoom{
    List<Student> list = new ArrayList<>();
    ClassRoom(){
        list.add( new Student("김길리") );
        list.add( new Student("홍길동") );
    }
    Student 개별호출(){return list.get(1);}
}

class Student{
    String name;
    Student(String name){this.name = name;}
    void 밥먹기(){
        System.out.println(this.name + " 밥 먹음");
    }
}





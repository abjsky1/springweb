package example.day039_260831_spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Spring_exam1 {

    public static void main(String[] args) {
        
        SubClass subClass = new SubClass();

    //  부모 메소드가 아닌 오버라이드 메소드가 실행됨.
        subClass.method1();

        subClass.method2();  // 실행은 가능하지만 권장은 안 함.


    //  1. [리플렉션] 클래스 정보 반환
        
        Class<TestClass> clazz = TestClass.class;

    //  2. 특정 메소드 반환

        try{    

            Method method = clazz.getMethod("method3");

    //      3. 특정 메소드의 어노테이션 확인
            MyAnnotaion annotaion = method.getAnnotation(MyAnnotaion.class);

    //      4. 특정 어노테이션의 속성 확인
            System.out.println(annotaion.value());
            System.out.println(annotaion.data());

    //      5. 동적 로딩
            TestClass testClass = clazz.getDeclaredConstructor().newInstance();
            method.invoke(testClass);

        }catch(Exception e){System.out.println(e);}


    }  // Main END

}  // class END


/*

    사용자 정의 어노테이션 만들기
    어노테이션을 정의할 때는 반드시 유지 기간(@Retention)과 적용 위치(@Target)를 설정해야 합니다.

    1] @Retention: 어노테이션이 언제까지 유지될 것인가?

    RetentionPolicy.SOURCE: 컴파일 전(소스 코드 파일)까지만 유지됩니다. 컴파일 후 .class 파일에서는 사라집니다. (예: 롬복 @Getter)

    RetentionPolicy.CLASS: 컴파일 후 .class 파일에는 포함되지만, JVM이 실행될 때(Runtime)는 메모리에 올라가지 않습니다. (기본값)

    RetentionPolicy.RUNTIME: 프로그램 실행 중(Runtime)에도 유지됩니다. 리플렉션을 통해 런타임에 어노테이션 정보를 읽어 동작을 제어할 수 있습니다. (예: 스프링 어노테이션 대부분)

    2] @Target: 어노테이션을 어디에 붙일 것인가?

    ElementType.TYPE: 클래스, 인터페이스, 열거형(Enum)에 사용

    ElementType.METHOD: 메소드에 사용

    ElementType.FIELD: 멤버 변수(필드)에 사용

    ElementType.PARAMETER: 메소드의 파라미터에 사용

    ElementType.CONSTRUCTOR: 생성자에 사용

*/


// 상위클래스
class SuperClass{
    void method1(){}
}  // class END

// 하위클래스
class SubClass extends SuperClass{

//  [1] 어노테이션( 컴파일(실행) 중에 해당 메소드를 사용하는 방법을 명시함. )

    @Override  
    void method1() {
        // TODO Auto-generated method stub
        super.method1();
    }

/*
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
*/


//  [2] 어노테이션 : (더 이상 사용 안 함.)  
    @Deprecated 
    void method2(){}

/*
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
*/

}  // class END

//  [3] 어노테이션 만들기 : @interface 어노테이션명{}
@Retention( RetentionPolicy.RUNTIME ) // 해당 어노테이션 생명주기 설정 : 실행 중 유지
@Target( ElementType.METHOD ) // 해당 어노테이션 사용처 설정 : 메소드
@interface MyAnnotaion{

    // 추상메소드 형식의 어노테이션 속성
    String value();
    int data() default 1;

}

//  [4] 어노테이션 주입(사용)
class TestClass{

    @MyAnnotaion( value = "안녕하세요" , data = 10 )  // @내가만든어노테이션()
    public void method3(){System.out.println("메소드3 실행");}

    @MyAnnotaion( value = "반갑습니다" )
    public void method4(){System.out.println("메소드4 실행");}

}  // class END
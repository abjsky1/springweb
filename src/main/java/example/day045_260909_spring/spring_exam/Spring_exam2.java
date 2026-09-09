package example.day045_260909_spring.spring_exam;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 추상메소드
interface Calculator{ int plus( int x, int y ); }

public class Spring_exam2 {
    public static void main(String[] args) {
        
    //  1. 구현체

    //      - 추상메소드(interface)를 구현한 객체

    //  2. 익명구현체

    //      - 추상메소드 구현한 클래스 없이 직접(1회성) 구현

    //      - 인터페이스 변수명 = new 인터페이스(){ 추상메소드구현 }

            Calculator calculator1 = new Calculator(){ @Override public int plus( int x, int y ){ return x+y; } };

            int result1 = calculator1.plus(3,5);

    //  3. 람다표현식
    
            Calculator calculator2 = ( x, y ) -> { return  x + y; };

            int result2 = calculator2.plus(3, 5);

            Calculator calculator3 = ( x, y ) -> x + y;

            int result3 = calculator3.plus(3, 5);

    //  4. 람다표현식 사용하는 인터페이스

    //      - Function< 인수타입 , 반환타입 > , .apply( 인수 ) , 매개변수 O / 반환 O

            Function< Integer, Integer > function = ( x ) -> { return x * 2; };

            int result4 = function.apply(3);

    //      - Supplier< 반환타입 > , .get( ) , 매개변수 X / 반환 O

            Supplier< Integer > supplier = ( ) -> { return 2; };

            int result5 = supplier.get();

    //      - Consumer< 인수타입 > , .accept( 인수 ) , 매개변수 O / 반환 X

            Consumer< String > consumer = ( str ) -> { System.out.println( str ); };

            consumer.accept( "김길리" );

    //      - Predicate< 인수타입 > , test( 인수 ) , 매개변수 O / 반환(true/false)

            Predicate< Integer > predicate = ( x ) -> { return x % 2 == 0; };
            
            boolean result6 = predicate.test(3);

    //  활용처 : 위 4가지 직접적인 사용이 아닌 API(다른 사람이 만든 클래스/메소드) 에서 활용 가능

    //      - filter( Predicate ) , map( Function ) , forEach( Consumer )

    //      스트림(Stream) API 에서 주로 사용됨. 스프링시큐리티API

    //      
    // 





    }


}

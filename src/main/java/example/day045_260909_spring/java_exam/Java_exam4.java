package example.day045_260909_spring.java_exam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Java_exam4 {
    public static void main(String[] args) {
        
    //  [*] 메소드 레퍼런스 API : 람다식이 단순히 기존메소드만 호출하는 경우 간결하게 표현하는 방법

    //  1. 

    //  Integer.parseInt("문자"); // 문자 --> 정수 변환 함수  
            
            Function< String , Integer > function1 = ( x ) -> { return Integer.parseInt( x ); };
            
            System.out.println(function1.apply("10"));

            Function< String , Integer > function2 = Integer::parseInt;

            System.out.println(function2.apply("20"));


    //  2.

            List<String> names = List.of("김길리", "홍길동", "프로틴", "삼다수", "마우스");
    
    //      2-1.
                for(int i = 0 ; i <= names.size()-1 ; i++){
                    System.out.println( names.get(i) );
                }

    //      2-2.

                for(String name : names ){ System.out.println(name); }

    //      2-3.

                names.stream().forEach( (name) -> { System.out.println(name); } );

    //      2-4. 메소드참조는 메소드명 명시하고 ()소괄호 작성하지 않는다.

                names.stream().forEach( System.out::println );


    //  3. 이름들의 글자수 출력하세요.

    //      3-1. 전통방식

            for(int i = 0 ; i <= names.size()-1 ; i++){ System.out.println(names.get(i).length()); }

    //      3-2. 스트림 방식
    
            names.stream().map( ( name ) -> { return name.length(); } ).forEach( ( result ) -> { System.out.println( result ); } );

    //      3-3. 메소드 레퍼런스 방식 ( 클래스명::함수명 )

            names.stream().map( String::length ).forEach( System.out::println );


    //  4. names 리스트 안에 문자열/이름들을 각각 대입하여 Student 객체 만드시오.

    //      4-1. 전통방식
    
            List<Student> list1 = new ArrayList<>();
            for(int i = 0 ; i <= names.size()-1 ; i++){ Student student = new Student( names.get(i) ); list1.add(student); }
            
    //      4-2. 스트림 API
            
            List<Student> list2 = names.stream().map( (name) -> { return new Student(name); } ).toList();

    //      4-3. 메소드참조(레퍼런스)

            List<Student> list3 = names.stream().map( Student::new ).toList();


    /*
                
        [*] 유형

        1. 클래스명::static메소드명     ex) MemberDto::from    ,  DTO 에서 변환할 때 사용하면 좋음.
    
        2. 인스턴스명::메소드명

        3. 클래스명::new

        JPA Service 구조 : Entity --> Dto 변환  (조회)

        1.
        List<MemberDto> list1 = entityList.stream().map( ( entity ) -> { return MemberDto.from( entity ); } ).toList();

        2.
        List<MemberDto> list2 = entityList.stream().map( MemberDto::from ).toList();

    */
    
        
    // 
    // 
    // 
    // 


    }

}

// Dto 역할
class Student{

    private String name;

    public Student( String name ){ this.name = name; }
}

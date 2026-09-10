package example.day045_260909_spring.java_exam;

import java.util.Comparator;
import java.util.List;

public class Java_exam3 {
    public static void main(String[] args) {
        
    //  [1] 리스트 생성

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

    //      1. 일반 for문
          
            for(int i = 0 ; i >= numbers.size()-1 ; i++){
                System.out.println(numbers.get(i));
            }
          
    //      2. 향상된 for문
          
            for( Integer data : numbers ){
                System.out.println(data);
            }
          
    //      3. forEach
          
            numbers.forEach( ( data ) -> { System.out.println( data ); } );
          

    //  [2] 스트림 API , 리스트객체.stream().중간연산1().중간연산2().최종연산();

    //      1. stream().forEach( ( 순회변수명 ) -> { 실행문 } );  ,  return 없는 단순 순회 제공
    
            numbers.stream().forEach( ( data ) -> { System.out.println(data); } );

    //      2. stream().map( ( 순회변수명 ) -> { return 값; } ).최종연산();  ,  return 있는 순회 제공
    
            List<Integer> newList1 = numbers.stream().map( ( data ) -> { return data; } ).toList();

    //      3. stream().filter( ( 순회변수명 ) -> { return 조건식; } ).최종연산();  ,  if문 역할

            List<Integer> newList2 = numbers.stream().filter( ( data ) -> { return data % 2 == 0; } ).toList();
    
    //      4-1. stream().sorted().최종연산(); 오름차순 정렬
    //      4-2. stream().sorted( Comparator.reverseOrder() ).최종연산(); 내림차순 정렬

            List<Integer> newList3 = numbers.stream().sorted( Comparator.reverseOrder() ).toList();

    //      5. stream().distinct().최종연산();  ,  중복제거

            List<Integer> newList4 = numbers.stream().distinct().toList();

    //      6. stream().limit( 개수 ).최종연산;  ,  개수만큼 반환

            List<Integer> newList5 = numbers.stream().limit(5).toList();

    //      7. stream().skip( 개수 ).최종연산;  ,  개수만큼 건너뛰기

            List<Integer> newList6 = numbers.stream().skip(3).toList();

    //      8. 중간연산은 여러번 가능 , 최종연산은 1번만 가능

            List<Integer> newList7 = numbers.stream()
                                            .distinct()                             // 중간연산
                                            .filter( (x) -> { return x % 2 == 0;} ) // 중간연산
                                            .map( (x) -> { return x; } )            // 중간연산
                                            .sorted( Comparator.reverseOrder() )    // 중간연산
                                            .limit(3)                       // 중간연산
                                            .toList();                              // 최종연산
    //      ==>>  [10 ,8 ,6]

    //      JPA 에서 Entity <--> DTO 서로 변환하는 과정에서 많이 사용됨. 


    //  스트림 : 컬렉션(리스트) 인스턴스를 흐름단위로 중간연산과 최종연산 제공 API(미리만든클래스/메소드)
    
    //  메소드레퍼런스 API : 미리 만들어진 메소드를 간편하게 호출하는 방법 제공 API()  

    //  이중콜론연산자 ::
            
    //      System.out.println("메소드 호출의 정석");  ==>>  System.out::println("메소드 호출의 레퍼런스 방식");


    }



}

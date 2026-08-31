package example.day039_260831_spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Spring_exam2 {
    public static void main(String[] args) {
        
    //  1. @NoArgsConstructor
        Student s1 = new Student();

    //  2. @AllArgsConstructor
        Student s2 = new Student("김길리", 100, 90);

    //  3. @Setter
        s1.setKor(88);

    //  4. @Getter 
        System.out.println(s2.getMath());

    //  5. @ToString
        System.out.println(s2.toString());

    //  6. 빌터 패턴을 이용한 객체 생성

    //  - .builder() 빌더 패턴 시작
    //  - .build() 빌더 패턴 끝
    //  - 장점 : 순서 무관 , 선택적 대입 , 유연한 객체 생성
    
        Student s3 = Student.builder()
                            .name("람보르길리")
                            .kor(93)
                            .math(99)
                            .build();

    //  클래스명.메소드명() : static 일 때 가능

    //  new 생성자명() : static 없음.
    // 
    // 
    // 
    // 

    }
}

@NoArgsConstructor // 매개변수 없는 생성자 (자동)생성
@AllArgsConstructor // 전체 매개변수 생성자 (자동)생성
// @RequiredArgsConstructor // final 멤버변수의 생성자 (자동)생성
@Getter @Setter // private 멤버변수의 getter/setter 메소드 (자동)생성
@ToString // 멤버변수 문자열 출력 메소드 (자동)생성
@EqualsAndHashCode // 객체 안에 멤버변수의 값 모두 비교 메소드 (자동)생성
@Data // getter + setter + RequiredArgsConstructor + toString + EqualsAndHashCode 묶음.
@Builder // 빌더 패턴 지원
class Student{

    // 멤버변수
    private String name; // 이름
    private int kor;     // 국어점수
    private int math;    // 수학점수

    // 2. 생성자 -> 롬복 어노테이션으로 대체
    // 3. getter sctter -> 롬복 어노테이션 대체
    // 4. toSting -> 롬복 어노테이션 대체
}
package example.day042_260904_spring;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class Spring_exam1 {
    public static void main(String[] args) {
        
    //  [1] 리터럴/상수 = 기본값
        int a = 3;  // a변수가 3 참조한다. 
        int b = 3;  // a변수가 3 참조한다.
    //  두 변수가 참조하는 값은 총 몇개인가요? 1개

    //  [2] 참조 : 어떠한 값이 위치 , 인스턴스(객체) 1개당 참조값 1개
        String c = new String("김길리");
        String d = new String("홍길동");
    //  두 변수가 참조하는 값은 총 몇개인가요? 2개

    //  [3] 
        Test t = new Test();
        t.name = new String("유재석");
    //  변수 t 가 참조하는 값은 몇개인가요? 1개
    //  t -> Test(101번지) -> name(201번지)
    
    
    //  [4] 자바 참조
    
    //  1. '자유' 카테고리 등록

        Category c1 = new Category(1 , "자유" , new ArrayList<>());

    //  c1 참조 몇개하고 있나요? 1개
    //  c1 -> category(101번지)
    
    //  2. '자유' 카테고리에 게시물 작성
        
        Board b1 = new Board(1 , "제목1" , c1);
    //  b1 참조 몇개하고 있나요? 1개
    //  b1 -> Board(102번지) -> Category(201번지)
    
    //  단방향 참조
    //  * b1 을 통해 c1 알 수 있나요?(참조하고 있나요?) Yes
    //  JPA 개발자는 이 구조를 보고 DB의 연관관계(Join) 기능처럼 사용 가능하겠다고 판단.

    //  양방향 참조
    //  * c1 을 통해 b1 알 수 있나요?(참조하고 있나요?) No
    //  JPA 개발자는 

    //  3. category 에 board 넣어주자
        c1.getList().add(b1);
    //  c1 -> category -> list(board)
    //  * c1 을 통해 b1 알 수 있나요?(참조하고 있나요?) Yes
    //  * JPA 서로 참조 가능한 구조

    //  ! 문제발생
        System.out.println(b1);
    //  b1 -> c1 -> b1 -> c1 -> b1 -> c1 -> ~ 무한참조 , <순환참조>

    //  toString() : Object(슈퍼클래스)클래스의 객체 주소값 반환 함수. 
    //  + 오버라이딩 : 객체 주소값 대신에 문자열로 반환하는 함수
    //  @ToString.Exclude
    //  private List<Board> list = new ArrayList<>();

    //  데이터베이스는 단방향 : 참조(FK)테이블에 PK테이블의 PK값을 저장한다. 
    //  데이터베이스는 양방향 : X , 참조/매핑 테이블 (실무적으로 권장X) , Join 기능 있기 때문에 교집합 쉽게 가능.
        

    






    }

}

class Test{
    String name;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Board{ // 하위클래스
    private int bno;
    private String btitle;

//  카테고리를 보드에 참조
    private Category category;  // 참조 FK
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Category{ // 상위클래스
    private int cno;
    private String cname;
    @ToString.Exclude
    private List<Board> list = new ArrayList<>();
}
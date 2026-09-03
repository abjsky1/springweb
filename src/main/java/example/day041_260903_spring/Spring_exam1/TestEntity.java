package example.day041_260903_spring.Spring_exam1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    // 제약조건 설정
    @Column( name = "name" , nullable = true , length = 100 , unique = true )
    private String name;

    @Column( columnDefinition = "varchar(100) not null default '제품설명'")
    private String descri;

    @Column( insertable = true , updatable =  true )
    private Integer price;


/*
    제품 등록일과 제품 수정일은 모든 테이블에 들어가야 하므로 BaseTime 을 따로 만들어 모든 엔티티에 상속시킴 
*/

}

/*

    @Column( name = "필드명" ) : 필드명 생략시 자동으로 멤버변수명으로 지정

    @Column( nullable = true ) : Not Null

    @Column( length = 문자열길이 ) : 최대 255까지 가능 

    @Column( unique = true ) : 중복 불가능 - true , 중복 가능 - false

    @Column( columnDefinition = "DB DDL 구문 직접 작성" ) : SQL 문법을 직접 작성

    @Column( insertable = true ) : 해당 컬럼을 SQL에 insert 적용 여부 

    @Column( updatable = true ) : 해당 컬럼을 SQL에 update 적용 여부

*/

/*

    src/main/resources/sql/v1_260903.sql

    C:\KDT\springweb
    │
    ├─ src
    │   └─ main
    │       ├─ java
    │       │   └─ example
    │       │       └─ day041_260903_spring
    │       │           └─ AppStart.java
    │       │
    │       └─ resources
    │           ├─ application.properties
    │           │
    │           └─ sql
    │               └─ v1_260903.sql

*/
package example.day040_260902_spring.Spring_exam1;

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
@Table( name = "exam" )  // 데이터베이스에서 매핑할 테이블 이름 정의 , 생략 시 클래스 명으로 자동 매핑.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamEntity {

//  엔티티 무조건 1개 이상의 PK 갖는다.

//  Primary Key 지정
    @Id  
//  auto_increment 지원 = mysql
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer eno;
    private String ename;
//  
//  
//  
//  
//  
//  
//  
//  
}

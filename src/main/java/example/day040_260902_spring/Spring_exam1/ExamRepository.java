package example.day040_260902_spring.Spring_exam1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository< ExamEntity , Integer >{
    
//  구현체 : 해당 인터페이스(추상) 구현한 객체

//  <> 제네릭타입 : 해당 클래스 안에 사용할 매개 타입 (8가지 기본타입 제공 안함. 래퍼클래스 사용)

//  JpaRepository< 조작할 엔티티명 , 엔티티 PK타입 >

// 
// 
// 

}

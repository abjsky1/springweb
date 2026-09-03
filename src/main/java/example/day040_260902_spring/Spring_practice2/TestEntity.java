package example.day040_260902_spring.Spring_practice2;

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
public class TestEntity {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private Integer no;
    private String content;
    private String writer;
    
}

/*
    JPA 영속성 : 자바의 데이터가 사라지지 않게 데이터베이스에 저장
    자바프로그램은 휘발성 영구저장 불가
    데이터베이스는 비휘발성 영구저장 가능
*/
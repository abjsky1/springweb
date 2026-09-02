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

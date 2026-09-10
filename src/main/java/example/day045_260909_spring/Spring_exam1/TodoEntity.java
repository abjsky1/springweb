package example.day045_260909_spring.Spring_exam1;

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
@Table (name = "todo")
@AllArgsConstructor 
@NoArgsConstructor 
@Data 
@Builder 
public class TodoEntity {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private Boolean done;

    
}

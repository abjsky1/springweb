package example.day044_260908_spring.spring_practice5_test.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table(name = "board")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class BoardEntity extends BassTime{

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @Column 
    private String author;

    @Column 
    private String password;

    @Column    
    private String content;

    @OneToMany (mappedBy = "boardEntity" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();


}

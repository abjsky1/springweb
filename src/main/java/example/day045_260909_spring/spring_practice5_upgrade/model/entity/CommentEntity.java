package example.day045_260909_spring.spring_practice5_upgrade.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "comment")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class CommentEntity extends BaseTime{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column 
    private String author;

    @Column 
    private String password;

    @Column
    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "board_id")
    private BoardEntity boardEntity;


}

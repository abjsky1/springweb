package example.day045_260909_spring.spring_practice5_upgrade.model.dto;

import example.day045_260909_spring.spring_practice5_upgrade.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class CommentDto {

    private Integer id;

    private Integer boardId;

    private String author;

    private String password;

    private String content;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
            .author(author)
            .password(password)
            .content(content)
        //  .boardEntity 는 CommentService 에서 작업
            .build();

    }

    public static CommentDto from(CommentEntity commentEntity){
        return CommentDto.builder()
            .id(commentEntity.getCommentId())
            .author(commentEntity.getAuthor())
            .password(commentEntity.getPassword())
            .content(commentEntity.getContent())
            .boardId(commentEntity.getBoardEntity().getId())
            .build();

    }

}

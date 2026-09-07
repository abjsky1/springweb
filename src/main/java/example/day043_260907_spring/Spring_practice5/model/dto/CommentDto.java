package example.day043_260907_spring.Spring_practice5.model.dto;

import java.time.LocalDateTime;

import example.day043_260907_spring.Spring_practice5.model.entity.BoardEntity;
import example.day043_260907_spring.Spring_practice5.model.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data 
@Builder 
public class CommentDto {

    private Integer boardId;

    private Integer commentId;

    private String author;

    private String password;

    private String content;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    public CommentEntity toEntity(){

        return CommentEntity.builder()
            .commentId(this.commentId)
            .author(this.author)
            .password(this.password)
            .content(this.content)
            .build();
    }

    public static CommentDto from(CommentEntity commentEntity){

        return CommentDto.builder()
            .author(commentEntity.getAuthor())
            .password(commentEntity.getPassword())
            .content(commentEntity.getContent())
            .commentId(commentEntity.getCommentId())
            .boardId(commentEntity.getBoardEntity().getBoardId())
            .createdAt(commentEntity.getCreatedAt())
            .updatedAt(commentEntity.getUpdatedAt())
            .build();
    }


}

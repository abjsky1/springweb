package example.day044_260908_spring.spring_practice5_test.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import example.day044_260908_spring.spring_practice5_test.model.entity.CommentEntity;
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

    private String author;

    private String password;

    private String content;

    private Integer boardId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
            .author(this.author)
            .password(this.password)
            .content(this.content)
            .build();
    }

    public static CommentDto from(CommentEntity commentEntity){
        return CommentDto.builder()
            .id(commentEntity.getCommentId())
            .author(commentEntity.getAuthor())
            .password(commentEntity.getPassword())
            .content(commentEntity.getContent())
            .createdAt(commentEntity.getCreatedAt())
            .updatedAt(commentEntity.getUpdatedAt())
            .build();
    }

}

package example.day045_260909_spring.spring_practice5_upgrade.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.day045_260909_spring.spring_practice5_upgrade.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class BoardDto {

    private Integer id;

    private String author;

    private String password;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder.Default
    private List<CommentDto> comments = new ArrayList<>();

    public BoardEntity toEntity(){
        return BoardEntity.builder()
            .author(author)
            .password(password)
            .content(content)
            .build();
    }

    public static BoardDto from(BoardEntity boardEntity){
        return BoardDto.builder()
            .id(boardEntity.getId())
            .author(boardEntity.getAuthor())
            .password(boardEntity.getPassword())
            .content(boardEntity.getContent())
            .createdAt(boardEntity.getCreatedAt())
            .updatedAt(boardEntity.getUpdatedAt())
            .comments(boardEntity.getCommentEntities().stream().map( (commentEntity) -> {return CommentDto.from(commentEntity); } ).toList())
            .build();
    }

}

package example.day043_260907_spring.Spring_practice5.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.day043_260907_spring.Spring_practice5.model.entity.BoardEntity;
import example.day043_260907_spring.Spring_practice5.model.entity.CommentEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor 
@Data 
@Builder 
public class BoardDto {

    private Integer boardId;

    private String author;

    private String password;

    private String content;

    @Builder.Default
    private List<CommentDto> commentDtos = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BoardEntity toEntity(){

        return BoardEntity.builder()
            .author(this.author)
            .password(this.password)
            .content(content)
            .build();
    }

    public static BoardDto from(BoardEntity boardEntity){

        return BoardDto.builder()
            .boardId(boardEntity.getBoardId())
            .author(boardEntity.getAuthor())
            .password(boardEntity.getPassword())
            .content(boardEntity.getContent())
            .createdAt(boardEntity.getCreatedAt())
            .updatedAt(boardEntity.getUpdatedAt())
            .build();
    }
}

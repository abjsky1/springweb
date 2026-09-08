package example.day044_260908_spring.spring_practice5_test.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import example.day044_260908_spring.spring_practice5_test.model.entity.BoardEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
            .author(this.author)
            .password(this.password)
            .content(this.content)
            .build();
    }

    public static BoardDto from(BoardEntity boardEntity){
        return BoardDto.builder()
            .id(boardEntity.getBoardId())
            .author(boardEntity.getAuthor())
            .password(boardEntity.getPassword())
            .content(boardEntity.getContent())
            .createdAt(boardEntity.getCreatedAt())
            .updatedAt(boardEntity.getUpdatedAt())
            .build();
    }

}

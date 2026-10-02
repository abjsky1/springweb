package example.day060_261002_spring;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardDto {

    private Long id;
    private String title;
    private String content;

//  DTO : 클라이언트와 요청/응답 값들을 자바형식으로 구성
//  파일 업로드 (파일은 문자가 아닌 바이트 , 따라서 특정한 인터페이스 사용) (String 아님)

//  파일 업로드용 - 등록용
    private MultipartFile file;
//  여러개의 파일이면 List<MultipartFile> 로 생성

//  파일 이름 : 업로드된 파일명 - 출력용 
    private String fileName;

    private LocalDateTime createDate; // 프론트에 전달할 작성일자
    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .build();
    }
    public static BoardDto fromEntity(BoardEntity entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .fileName(entity.getFileName())
                .createDate(entity.getCreateDate())
                .build();
    }

}

package example.day062_261007_spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class MessageDto {

//  메세지 형식 , TALK 메세지 or ENTER 접속
    private String type;

//  방 번호
    private String roomId;

//  보낸 사람
    private String sender;

//  보낸 내용
    private String content;

//  보낸 시간
    private String date;

}

package example.day044_260908_spring.spring_practice5_test.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import example.day044_260908_spring.spring_practice5_test.model.dto.CommentDto;
import example.day044_260908_spring.spring_practice5_test.model.entity.BoardEntity;
import example.day044_260908_spring.spring_practice5_test.model.entity.CommentEntity;
import example.day044_260908_spring.spring_practice5_test.model.repository.BoardRepository;
import example.day044_260908_spring.spring_practice5_test.model.repository.CommnetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class CommentService {

    private final CommnetRepository commnetRepository;
    private final BoardRepository boardRepository;

    public boolean save(CommentDto commentDto){

        CommentEntity commentEntity = commentDto.toEntity();

        Optional<BoardEntity> optional = boardRepository.findById(commentDto.getBoardId());

        if (optional.isPresent()) {
            
            BoardEntity boardEntity = optional.get();

            commentEntity.setBoardEntity(boardEntity);

        }
        
        CommentEntity savedCommentEntity = commnetRepository.save(commentEntity);

        if(savedCommentEntity.getCommentId() >= 1){return true;}
        return false;
    }


    public boolean delete(Integer commentId , String password){

        Optional<CommentEntity> optional = commnetRepository.findById(commentId);

        if(optional.isPresent()){
            CommentEntity commentEntity = optional.get();
            if(password.equals(commentEntity.getPassword())){
                commnetRepository.delete(commentEntity);
                return true;
            }
        }
        return false;
    }


}

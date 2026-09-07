package example.day043_260907_spring.Spring_practice5.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import example.day043_260907_spring.Spring_practice5.model.dto.CommentDto;
import example.day043_260907_spring.Spring_practice5.model.entity.BoardEntity;
import example.day043_260907_spring.Spring_practice5.model.entity.CommentEntity;
import example.day043_260907_spring.Spring_practice5.model.repository.BoardRepository;
import example.day043_260907_spring.Spring_practice5.model.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


//  [댓글 등록 기능]
    public boolean save(CommentDto commentDto){

        CommentEntity commentEntity = commentDto.toEntity();

        Optional<BoardEntity> optional = boardRepository.findById(commentDto.getBoardId());

        if (optional.isPresent()) {
            
            BoardEntity boardEntity = optional.get();

            commentEntity.setBoardEntity(boardEntity);

            CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

            if(savedCommentEntity.getCommentId() >= 1){return true;}
            
        }
        return false;

    }


//  [댓글 삭제 기능]
    public boolean delete(Integer commentId, String password){
        
        Optional<CommentEntity> optional = commentRepository.findById(commentId);

        if (optional.isPresent()) {
            
            CommentEntity commentEntity = optional.get();

            if(commentEntity.getCommentId()==commentId && commentEntity.getPassword().equals(password)){

                commentRepository.delete(commentEntity);

                return true;
            }

        }
        return false;
    
    }




    
}

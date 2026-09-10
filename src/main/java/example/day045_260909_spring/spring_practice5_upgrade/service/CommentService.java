package example.day045_260909_spring.spring_practice5_upgrade.service;

import java.util.Optional;

import org.springframework.stereotype.Service;


import example.day045_260909_spring.spring_practice5_upgrade.model.dto.CommentDto;
import example.day045_260909_spring.spring_practice5_upgrade.model.entity.BoardEntity;
import example.day045_260909_spring.spring_practice5_upgrade.model.entity.CommentEntity;
import example.day045_260909_spring.spring_practice5_upgrade.model.repository.BoardRepository;
import example.day045_260909_spring.spring_practice5_upgrade.model.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

//  댓글 등록 기능
    public boolean save(CommentDto commentDto){

        CommentEntity commentEntity = commentDto.toEntity();

        Optional<BoardEntity> optional = boardRepository.findById(commentDto.getBoardId());

        if (optional.isPresent()) {
            
            BoardEntity boardEntity = optional.get();

            commentEntity.setBoardEntity(boardEntity);

        }

        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

        if(savedCommentEntity.getCommentId() >= 1){return true;}

        return false;
        
    //  Optional<CommentEntity> optional = commentRepository.findById(commentDto.getId());
    
    //  if(optional.isPresent()){
    //      CommentEntity commentEntity2 = optional.get();
    //      BoardEntity boardEntity = commentEntity2.getBoardEntity();
    //      commentEntity1.setBoardEntity(boardEntity);
    //      CommentEntity savedCommentEntity = commentRepository.save(commentEntity1);
    //      if(savedCommentEntity.getId() >= 1){return true;}
    //  }

    }

//  댓글 삭제 기능
    public boolean delete(Integer commentId , String password){

        if (commentRepository.findById(commentId).isPresent()) {
            if(password.equals(commentRepository.findById(commentId).get().getPassword())){
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }

}

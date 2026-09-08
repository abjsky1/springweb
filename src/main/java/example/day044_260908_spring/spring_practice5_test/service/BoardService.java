package example.day044_260908_spring.spring_practice5_test.service;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import example.day044_260908_spring.spring_practice5_test.model.dto.BoardDto;
import example.day044_260908_spring.spring_practice5_test.model.dto.CommentDto;
import example.day044_260908_spring.spring_practice5_test.model.entity.BoardEntity;
import example.day044_260908_spring.spring_practice5_test.model.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class BoardService {

    private final BoardRepository boardRepository;


    public boolean save(BoardDto boardDto){

        BoardEntity boardEntity = boardDto.toEntity();

        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);

        if(savedBoardEntity.getBoardId() >= 1){return true;}
        return false;
    }


    public List<BoardDto> findAll(){

        List<BoardEntity> boardEntities = boardRepository.findAll();

        List<BoardDto> boardDtos = new ArrayList<>();

        boardEntities.forEach((boardEntity)->{

            BoardDto boardDto = BoardDto.from(boardEntity);

            boardEntity.getCommentEntities().forEach((commentEntity)->{

                CommentDto commentDto = CommentDto.from(commentEntity);

                boardDto.getComments().add(commentDto);

            });
            boardDtos.add(boardDto);
        });
        
        return boardDtos;

    }

    public boolean delete( Integer id , String password){

        Optional<BoardEntity> optional = boardRepository.findById(id);

        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            if(password.equals(boardEntity.getPassword())){
                boardRepository.delete(boardEntity);
                return true;
            }
        }
        return false;
    }


}

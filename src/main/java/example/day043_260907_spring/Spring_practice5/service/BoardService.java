package example.day043_260907_spring.Spring_practice5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import example.day043_260907_spring.Spring_practice5.model.dto.BoardDto;
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
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    //  [게시글 등록 기능]
    public boolean save(BoardDto boardDto){

        BoardEntity boardEntity = boardDto.toEntity();

        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);

        if(savedBoardEntity.getBoardId() >= 1){return true;}
        return false;
    }

    //  [게시글 목록 조회 기능]
    public List<BoardDto> findAll(){

        // board 엔티티 전체 불러오기
        List<BoardEntity> boardEntities = boardRepository.findAll();

        // Dto 로 변환해야 하니까 최종 반환할 Dto 리스트 생성
        List<BoardDto> boardDtos = new ArrayList<>();

        // Dto 리스트에 집어넣을 항목 정의 시작
        // 전체 불러온 board 엔티티에서 하나씩 꺼내기
        boardEntities.forEach((boardEntity) -> {
        
        // boardDto 라는 곳에 꺼낸 Entity Dto로 변환 (아직 commentEntity 리스트는 안 들어옴.)
            BoardDto boardDto = BoardDto.from(boardEntity);

        // boardEntity 안에 commentEntity 에서 하나씩 꺼내기    
            boardEntity.getCommentEntities().forEach((comment)->{

        // 꺼낸 CommentEntity 를 Dto로 변환 작업
        // 주의 : Dto 에 리스트는 Dto 타입으로 생성할 것.
                CommentDto commentDto = CommentDto.from(comment);

        // 변환한 거 Dto 에 만들어져있는 List 에 집어넣기
                boardDto.getCommentDtos().add(commentDto);

            });
        
            boardDtos.add(boardDto);

        });
        return boardDtos;
        
    }

    //  [게시글 삭제]
    public boolean delete(Integer boardId , String password){

        Optional<BoardEntity> optional = boardRepository.findById(boardId);

        if (optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            if (boardEntity.getPassword().equals(password)){
                boardRepository.delete(boardEntity);
                return true;
            }
        }
        return false;
    }







}

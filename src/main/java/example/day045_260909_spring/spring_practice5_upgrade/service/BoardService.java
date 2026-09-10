package example.day045_260909_spring.spring_practice5_upgrade.service;

import example.day045_260909_spring.spring_practice5_upgrade.model.repository.CommentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import example.day045_260909_spring.spring_practice5_upgrade.model.dto.BoardDto;
import example.day045_260909_spring.spring_practice5_upgrade.model.entity.BoardEntity;
import example.day045_260909_spring.spring_practice5_upgrade.model.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class BoardService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


//  게시글 등록 기능
    public boolean save(BoardDto boardDto){
    
        BoardEntity savedBoardEntity = boardRepository.save(boardDto.toEntity());

        if(savedBoardEntity.getId() >= 1){return true;}
        return false;
        
    }

//  게시글 목록 조회 기능
    public List<Map<String,Object>> findAll(){

    //  List<BoardEntity> boardEntities = boardRepository.findAll();

    //  BoardDto 다 가져오기. 
    //  but comments 가 빠져있음. 
        List<Map<String,Object>> boardDtoMaps = boardRepository.boardFindAllQuery();
        List<Map<String,Object>> newBoard = boardDtoMaps.stream().map( (map)-> {return map;}).toList();

    //  BoardDto 에 comments 찾아서 넣어주기
    //  맵 하나씩 가져오기 
        return newBoard.stream().map((map)->{
            
            Map<String, Object> modifiableMap = new HashMap<>(map);

            List<Map<String,Object>> commentDtoMaps = boardRepository.commentQuery( map.get("id") );

            modifiableMap.put("comments", commentDtoMaps);
            
            return modifiableMap;

        }).toList();
    }

//  게시글 삭제 기능
    public boolean delete(Integer id , String password){
        
        if(boardRepository.findById(id).isPresent()){
            if(password.equals(boardRepository.findById(id).get().getPassword())){
                boardRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

// +게시물 목록 개별 조회 기능
    public Map<String,Object> findDetail(Integer id){

        Map<String,Object> boardDtoMap = boardRepository.boardFindDetailQuery(id);

        Map<String,Object> modifiableMap = new HashMap<>(boardDtoMap);
        
        List<Map<String,Object>> commentDtoMaps = boardRepository.commentQuery( boardDtoMap.get("id") );
        
        modifiableMap.put("comments", commentDtoMaps);

        return modifiableMap;
    }


}

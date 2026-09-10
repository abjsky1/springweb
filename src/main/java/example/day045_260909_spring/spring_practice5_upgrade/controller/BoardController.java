package example.day045_260909_spring.spring_practice5_upgrade.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day045_260909_spring.spring_practice5_upgrade.model.dto.BoardDto;
import example.day045_260909_spring.spring_practice5_upgrade.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/board")
@RequiredArgsConstructor 
public class BoardController {

    private final BoardService boardService;


//  게시글 등록 기능
    @PostMapping ("")
    public boolean save(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }

//  게시글 목록 조회 기능
    @GetMapping ("")
    public List<Map<String,Object>> findAll(){
        return boardService.findAll();
    }

//  게시글 삭제 기능
    @DeleteMapping ("")
    public boolean delete(@RequestParam (name = "id") Integer id , @RequestParam (name = "password") String password){
        return boardService.delete(id, password);
    }


// +게시물 목록 개별 조회 기능
    @GetMapping ("/detail")
    public Map<String,Object> findDetail(@RequestParam(name = "id") Integer id){
        return boardService.findDetail(id);
    }
}

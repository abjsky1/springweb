package example.day043_260907_spring.Spring_practice5.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260907_spring.Spring_practice5.model.dto.BoardDto;
import example.day043_260907_spring.Spring_practice5.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    
//  [게시글 등록 기능]
    @PostMapping("")
    public boolean save(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }

//  [게시글 목록 조회 기능]
    @GetMapping("")
    public List<BoardDto> findAll(){
        return boardService.findAll();
    }

//  [게시글 삭제 기능]
    @DeleteMapping("")
    public boolean delete(@RequestParam(name = "boardId") Integer boardId , @RequestParam (name = "password") String password){
        return boardService.delete(boardId, password);
    }




}

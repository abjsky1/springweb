package example.day044_260908_spring.spring_practice5_test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day044_260908_spring.spring_practice5_test.model.dto.BoardDto;
import example.day044_260908_spring.spring_practice5_test.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/board")
public class BoardController {

    private final BoardService boardService;


    @PostMapping("")
    public boolean save(@RequestBody BoardDto boardDto){
        return boardService.save(boardDto);
    }

    @GetMapping("")
    public List<BoardDto> findAll(){
        return boardService.findAll();
    }

    @DeleteMapping("")
    public boolean delete(@RequestParam (name = "id") Integer id, @RequestParam (name = "password") String password){
        return boardService.delete(id,password);
    }
}

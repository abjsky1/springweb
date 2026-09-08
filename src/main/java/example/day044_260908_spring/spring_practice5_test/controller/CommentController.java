package example.day044_260908_spring.spring_practice5_test.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day044_260908_spring.spring_practice5_test.model.dto.CommentDto;
import example.day044_260908_spring.spring_practice5_test.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/board/comments")
public class CommentController {

    private final CommentService commentService;


    @PostMapping ("")
    public boolean save(@RequestBody CommentDto commentDto){
        return commentService.save(commentDto);
    }


    @DeleteMapping ("")
    public boolean delete(@RequestParam (name = "commentId") Integer commentId , @RequestParam (name = "password") String password){
        return commentService.delete(commentId, password);
    }


}

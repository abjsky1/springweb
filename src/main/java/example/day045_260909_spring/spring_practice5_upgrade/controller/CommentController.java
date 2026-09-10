package example.day045_260909_spring.spring_practice5_upgrade.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day045_260909_spring.spring_practice5_upgrade.model.dto.CommentDto;
import example.day045_260909_spring.spring_practice5_upgrade.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/board/comments")
@RequiredArgsConstructor 
public class CommentController {

    private final CommentService commentService;


//  댓글 등록 기능
    @PostMapping ("")
    public boolean save(@RequestBody CommentDto commentDto){
        return commentService.save(commentDto);
    }

//  댓글 삭제 기능
    @DeleteMapping ("")
    public boolean delete(@RequestParam (name = "commentId") Integer commentId ,@RequestParam (name = "password") String password){
        return commentService.delete(commentId, password);
    }



}

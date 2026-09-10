package example.day045_260909_spring.Spring_exam1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/day045/todo")
@RequiredArgsConstructor 
public class TodoController {

    private final TodoRepository todoRepository;
    

}

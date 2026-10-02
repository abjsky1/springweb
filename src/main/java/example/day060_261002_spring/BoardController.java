package example.day060_261002_spring;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin (origins = "http://localhost:5173") // React 로컬 포트 허용
@RestController 
@RequiredArgsConstructor  
@RequestMapping ("/api/board")
public class BoardController {

    private final BoardService boardService;
    // 등록
    // 컨트롤러에서 DTO 매핑시 @RequestBody 사용하지 않음.
    // application/json     ==> @RequestBody
    // multipart/form-data  ==> ModelAttribute  or  생략
    @PostMapping ("/write")
    public boolean write(@ModelAttribute /*생략가능*/ BoardDto dto) {
        return boardService.boardWrite(dto);
    }

    // 전체 조회
    @GetMapping ("/list")
    public List<BoardDto> list() {
        return boardService.boardFindAll();
    }

    // 개별 조회
    @GetMapping("/view")
    public BoardDto view(@RequestParam ( name = "id") Long id) {
        return boardService.boardFindById(id);
    }

}

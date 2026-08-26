package example.day036_260826_spring_talendTester.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;

import example.day036_260826_spring_talendTester.model.dao.WaitingDao;
import example.day036_260826_spring_talendTester.model.dto.WaitingDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;






/*
    컨트롤러에 서블릿( HTTP 프로토콜 사용 가능하게 기능/방법( Get/Post/Put/Delete ) 제공하는 클래스 ) 기능 달기

        * 레거시(과거) 코드는 상속받아 서블릿 구현

        * 스프링은 @Controller 안에 서블릿이 포함됨.

            1. 웹기술을 포함할 컨트롤러 클래스 위에 @Controller ( @ : 어노테이션 )

            2. 반환타입이 JSON 이면 @RestController

                * HTTP content type : 
                    
                    - http 를 통해 데이터 받은 곳에서 데이터 사용하는 타입/규칙/가이드 안내만 해줌. ( 기술 X )

                    - http 전송 데이터 타입
                    
                    - text/html , application/json(@RestController) , form 등등

                    - ( DTO 반환 타입은 없다. - JAVA 이기 때문 )
            
            3. 해당 메소드 마다 URL 정의

                * URL 정의시 http://127.0.0.1:8080(도메인) 이후 경로(path/url) 정의 , 중복없이 아무거나.

                    1. PostMapping("/URL") : HTTP 메소드 중에 POST 메소드 매핑/연결/대응 어노테이션

            4. Rest API Test

                * Method = Post

                * Scheme = http://127.0.0.1:8080/board/save?content=안녕하세요&writer=김길리

                * 

*/

@RestController
public class WaitingController {

//  private BoardController(){}
//  private static final BoardController instance = new BoardController();
//  public static BoardController getInstance(){return instance;}

    private WaitingDao wd = WaitingDao.getInstance();

//  [1] 등록

//      1. PostMapping("/URL") : HTTP 메소드 중에 POST 메소드 매핑/연결/대응 어노테이션

        @PostMapping("/waiting/save")
        public boolean save( WaitingDto waitingDto ){

        //  view에게 전달받은 매개변수을 dao에게전달
        
                boolean result = wd.save(waitingDto);
        
        //  dao에게 받은 결과을 view 반환

                return result;

        }


//  [2] 전체조회

        @GetMapping("/waiting/findall")
        public ArrayList<WaitingDto> findAll(){

            ArrayList<WaitingDto> result = wd.findAll();

            return result;

        }


// [3] 수정 Controller 
    @PutMapping("/waiting/update")
    public boolean update( WaitingDto waitingDto ){
        return wd.update( waitingDto );
    }


// [4] 개별삭제 Controller
    @DeleteMapping("/waiting/delete")
    public boolean delete( String phoneNumber ){
        return wd.delete( phoneNumber );
    }


//
//
//
//
//
//
//
//
//

}

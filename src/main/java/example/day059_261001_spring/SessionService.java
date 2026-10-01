// package example.day059_261001_spring;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import jakarta.servlet.http.HttpSession;
// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;

// @Service 
// @RequiredArgsConstructor 
// @Transactional 
// public class SessionService {


//*** Controller ***//
//  [1] 쿼리스트링으로 전달받은 data 값을 세션(HttpSession)에 누적 저장
//  @GetMapping ("/add")
//  public List<String> add( @RequestParam (name = "data") String data , HttpSession session ){
//      // HttpSession session = request.getSession();
//      // boolean result = sessionService.add(data, session);
//      // if(result){return "세션 저장 성공";}
//      // return "세션 저장 실패";
//  }


// //  [1] 쿼리스트링으로 전달받은 data 값을 세션(HttpSession)에 누적 저장
//     public boolean add(String data , HttpSession session){

//         Object obj = session.getAttribute("dataList");

//         List<String> dataList;

//         if(obj == null){ dataList = new ArrayList<>(); }
//         else { dataList = (List<String>) obj; }

//         dataList.add(data);

//         session.setAttribute("dataList", dataList);

//         System.out.println("현재 세션 데이터 : " + dataList);

//         return true;

//     }

// }

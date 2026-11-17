package example.day064_261012_spring;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LLMService {

/*
    LLM : 대규모 언어 모델 = AI 모델
    LLM API : 각 LLM 회사에서 API 제공 , API (유료/무료)
*/

//  1. 서비스키 불러오기
    @Value ("${api.gemini-key}")
    public String geminiApiKey;

//  2. api 호출 라이브러리
    private final WebClient webClient = WebClient.builder().build();

//  3. 직렬화 라이브러리
    private ObjectMapper objectMapper = new ObjectMapper();


//  LLM Call
    public String LLMCall( String prompt){

    //  1. 프롬프트 구성
        Map<String , Object> requestBody = Map.of("contents",
                                                  List.of( Map.of ("parts" , 
                                                                   List.of(Map.of("text", prompt )))));

        try {

    //  2. WebClient 이용하여 LLM API 모델 호출
    //  https://generativelanguage.googleapis.com/v1beta/모델명
        Map<String,Object> response = webClient.post()
            .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent?key={key}",geminiApiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

        System.out.println( response );

    //  MAP  ==>  JSON
        JsonNode root = objectMapper.valueToTree( response ); 

    //  AI 응답 본문 추출
        JsonNode textNode = root.at("/candidates/0/content/parts/0/text");

    //  응답 자료 안에 AI 응답 본문만 반환
        return textNode.asText();
        
        } catch (WebClientResponseException e) {
        System.out.println("에러내용: " + e.getResponseBodyAsString());
        return "호출 실패: " + e.getStatusCode();
        }

    }


//  [2] 프롬프트 요청하여 응답받기
    public String chat1( String prompt ){
        
        return LLMCall( prompt );
        
    }


//  [3] 데이터베이스 안에 자료 + 프롬프트 조합
    private final DeliveryLogRepository deliveryLogRepository;
    public String chat2( ){

    //  1. 로그 엔티티 모두 꺼내기
        List<DeliveryLog> logList = deliveryLogRepository.findAll();

    
        try{

    //  2. 엔티티 -> JSON 변환
        String jsonContext = objectMapper.writeValueAsString(logList);

    //  3. 프롬프트 작성
    //  String """ 문자열 """ + db 자료
        String prompt = """
                배송 및 반품 로그 데이터이며 데이터들을 분석하여 아래와 같이 보고서 작성하기
                1. 배송 일수가 길거나 반품률이 높은 지점 식별
                2. 파손, 배송 지연, 단순 변심 등 반품 패턴 분석
                3. 배송사별 배송 통계(개수, 평균, 반품비율) 

                [데이터]
                """ + jsonContext;

    //  조합된 컨텐츠 전달
        return LLMCall( prompt );
    
        }catch(Exception e){System.err.println(e);}
    
        return null;
    
    }


//  [3] 프롬프트 연결 ( 질문 -> 답 -> 질문 -> 답 -> 질문 -> 답 ... )
    public String chat3( String topic ){

    //  1. 프롬프트1 작성
        String prompt1 = String.format("""
            고객 배송 불만 접수 내용 : %s
            위 내용에서 1. 귀책 사유 , 2. 상품 피해 심각도(상/중/하) 를 판단해서 3줄 요약해줘.
            """, topic);

        String result1 = LLMCall(prompt1);

    //  2. 프롬프트2 작성
        String prompt2 = String.format("""
            접수 내용 : %s
            1차 분석 결과 :%s
            위 분석을 토대로 배송팀이 구체적인 조치 방안 제안해줘.

            """, topic , result1 );

        String result2 = LLMCall(prompt2);

    //  3. 프롬프트3 작성
        String prompt3 = String.format("""
            1차 분석 : %s
            2차 분석 : %s
            위 내용으로 고객에게 보낼 친절한 메세지 초안 작성
            """, result1 , result2 );

        String result3 = LLMCall(prompt3);



        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        return result3;

    }


//  프롬프트 의도를 판단하여 기능 라우팅 + AI에이전트

//  프롬프트 : 명령어

//  LLM : AI모델

//  API : AI모델을 사용하기위한 기능 REST 로 지원

//  AI에이전트 : LLM + 외부도구  =>  자율 실행

//  프롬프트 잘 작성하는 방법 : 자료에대한 이해도 , 질문/키워드 


    public String chat4( String message ){

    //  [처리할 방법] + [결과 형식] 포함
        String prompt = String.format("""
            배송 CS 로그 서비스이며, 고객의 요청을 분석하여 다음 4가지 action_type 중 하나를 선택한다.
            JSON 형식만을 반환한다.
            [선택 가능한 ACTION_TYPE]
            - TRACK_DELIVERY : 배송 위치 추적, 도착 예정일, 어디쯤 왔는지 확인
            - RETURN_REQUEST : 반품신청, 단순 변심 회수, 교환 문의
            - CLAIM_COMPENSATION : 배송지연 보상, 상품 파손 보상, 사고 배상 문의
            - NONE : 위 3가지 해당하지 않거나 알 수 없는 경우
            [JSON 출력 형식]
            { "action_type" : "TRACK_DELIVERY" , "order_id" : "추출한 주문번호(예:ORD-202601-001, 없으면 빈 문자열)" , "reason" : "분류이유" }
            
            고객의 요청 :%s
            """, message);

    //  프롬프트 전달하여 결과 받기
        String response = LLMCall( prompt );

        try{
    //  Map -> JSON 변환
        JsonNode root = objectMapper.readTree(response);
        
    //  JSON 안에 "action_type" 추출
    //  root.path("속성명") : 속성명에 따른 속성값 추출
        String action_type = root.path("action_type").asText("NONE");

    //  JSON 안에 "order_id" 추출
        String orderId = root.path("order_id").asText("");

    //  action_type 과 order_id 가지고 각종 서비스 라우팅/연결분기
        if(action_type.equals("TRACK_DELIVERY")){ return processOrder( orderId , "고객이 배송 상태 조회를 요청했습니다. 위 주문 기록을 확인하고 현재 위치와 배송 예정 상태를 친절하게 안내해줘."); }
        else if(action_type.equals("RETURN_REQUEST")){ return processOrder( orderId , "고객이 반품을 요청 , 위 주문 기록과 사유를 확인하고 반품 수거 절차를 안내해줘."); }
        else if(action_type.equals("CLAIM_COMPENSATION")){ return processOrder( orderId , "고객이 배송지연 및 파손에 대한 보상청구 요청, 위 주문 기록의 배송일수를 근거로 보상 기준 해당 여부와 접수 절차 안내해줘."); }
        else{ return "요청을 파악하지 못했습니다. 다시 요청해주세요"; }

        }catch(Exception e){System.out.println(e);}

        return null;

    }

    public String processOrder( String order_id , String prompt ){

        DeliveryLog deliveryLog = deliveryLogRepository.findById(order_id).orElse(null);

        if (deliveryLog == null) { return "주문번호가 없습니다."; }

        try{

        String jsonlog = objectMapper.writeValueAsString(deliveryLog);

        String requestPrompt = String.format("""
            [주문 정보] %s
            [요청 사항] %s
            """, jsonlog, prompt);

        return LLMCall( requestPrompt );

        }catch(Exception e){System.out.println(e);}

        return null;
        
    }


}

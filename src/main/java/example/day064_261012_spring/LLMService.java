package example.day064_261012_spring;

import java.util.List;
import java.util.Map;

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


//  
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





}

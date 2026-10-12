package example.day064_261012_spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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




    




}

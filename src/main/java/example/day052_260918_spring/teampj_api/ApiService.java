package example.day052_260918_spring.teampj_api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service 
public class ApiService {

//  1. JSON
    //  WebClient 객체 빌더패턴 생성
    private WebClient webClient = WebClient.builder().build();

    //  
    @Value ("${api.public-data.service-key}")
    private String serviceKey;


    public Map<String,Object> findAll(){

    //  API 주소(공공데이터 신청한 api 요청 url)    
        String url = "https://apis.data.go.kr/1192000/service/OceansEnvImpactService1/getOceanEnvImpactInfo1";
    
        url += "?serviceKey="+serviceKey;

        url += "&pageNo="+1;

        url += "&numOfRows="+10;

        url += "&ACP_YEAR="+2013;
    
        //  WebClient 객체 빌더패턴 생성
        //  WebClient webClient = WebClient.builder().build();
    
        //  WebClient 객체 이용한 api 요청하고 응답받기
        String response = webClient.get()      // .http메소드명
                                                .uri(url)                                  // uri 는 http 주소상에 자원(쿼리스트링)까지 포함
                                                .retrieve()                                // 요청 결과 반환 결과 수신
                                                .bodyToMono(String.class)       // 응답 결과 content-type 직렬화/변환
                                                .block();                                  // 동기화

        //  String 타입 -> xml 타입 변환
        //  xml 매퍼 객체 생성
            XmlMapper xmlMapper = new XmlMapper();

        //  Map<String , Object> map = xmlMapper.readValue(xml문자열, 타입명.class); + 일반예외
        
            try{
            Map<String , Object> map = xmlMapper.readValue(response, Map.class);
            return map;
            }catch( Exception e ){ System.out.println(e);}

            return null;
    }



    public Map<String,Object> test1(){
        String url="https://apis.data.go.kr/6260000/BusanTblDpstcRsdService/getTblDpstcRsdInfo";
        url += "?serviceKey="+"e2adc7127dbecdfb8fc874af822ca2369bb0b66c704fb68cd8c74e5f96c4cc0c";
        url += "&pageNo="+1;
        url += "&numOfRows="+10;
        String response = webClient.get( ).uri( url ).retrieve()
                .bodyToMono(String.class) 
                .block();
        XmlMapper xmlMapper = new XmlMapper(); 
        try{
            Map<String,Object> map = xmlMapper.readValue( response , Map.class );
            return  map;
        }catch( Exception e ){ System.out.println( e ); }
        return  null;
    }


    public Map<String,Object> Koo( ){

        String url = "https://api.odcloud.kr/api/15087697/v1/uddi:b06dcf2d-3b77-4d88-a50f-ede85eb74c3c";
        url += "?page=" + 1;
        url += "&perPage=" + 10;
        url += "&serviceKey=" + "ae3ef9800f628783781804c737f4e98abb3bd5c189f5a9500df88acae4789c0d";

        Map<String,Object> response = webClient.get()
            .uri(url)
            .retrieve( )
            .bodyToMono(Map.class)
            .block( );
        
        return response;
    }

    public Map<String, Object> practice() {

        String url = "https://apis.data.go.kr/1220000/nitemtrade/getNitemtradeList";

        url += "?serviceKey=" + serviceKey;
        url += "&strtYymm=202601";
        url += "&endYymm=202601";
        url += "&cntyCd=US";
        url += "&hsSgn=0101219000";

        System.out.println("요청 URL = " + url);

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("공공데이터 응답 = " + response);

        XmlMapper xmlMapper = new XmlMapper();

        try {

            Map<String, Object> map =
                    xmlMapper.readValue(response, Map.class);

            return map;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    
}

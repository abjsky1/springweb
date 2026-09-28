package example.day052_260918_spring.spring_api;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service 
public class ApiService {

//  서비스키는 안전하게 application.properties 에서 관리
//  프로젝트간 api 키는 github push 하지 말 것.
//  notion or excel 에서 공유

//  WebClient 객체 빌더패턴 생성
    private WebClient webClient = WebClient.builder().build();

//  
    @Value ("${api.public-data.service-key}")
    private String serviceKey;

//  1. JSON
    public Map<String, Object> test1(){

    //  API 주소(공공데이터 신청한 api 요청 url)    
        String url = "https://api.odcloud.kr/api/15052602/v1/uddi:855807e2-fe8a-4e47-8a5a-ce1894e410d7_201909031553";
    
        url += "?page="+1;

        url += "&perPage="+10;

        url += "&serviceKey="+serviceKey;
    
        //  WebClient 객체 빌더패턴 생성
        //  WebClient webClient = WebClient.builder().build();
    
        //  WebClient 객체 이용한 api 요청하고 응답받기
        Map<String, Object> response = webClient.get()      // .http메소드명
                                                .uri(url)                                  // uri 는 http 주소상에 자원(쿼리스트링)까지 포함
                                                .retrieve()                                // 요청 결과 반환 결과 수신
                                                .bodyToMono(Map.class)       // 응답 결과 content-type 직렬화/변환
                                                .block();                                  // 동기화

        return response;

    }
    


//  2. XML
    public Map<String, Object> test2(){

        String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";

        url += "?serviceKey="+serviceKey;

        url += "&pageNo="+1;

        url += "&numOfRows="+10;

        String response = webClient.get()
                                   .uri(url)
                                   .retrieve()
/* XML 타입은 Map 직렬화 불가능 */    .bodyToMono(String.class)
                                   .block();

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


//  3. CSV
    public List<Map<String,Object>> test3(){
        
//      .csv 파일 경로 , resources 이하 폴더
        String fileName = "static/day052_260918_csv/중소벤처기업부_벤처기업명단_20260521.csv";

//      ClassPathResource 객체 이용하여 해당 경로 안에 파일 가져오기 [파일객체]
        ClassPathResource resource = new ClassPathResource(fileName);


//      대용량 파일들을 바이트로 읽어와서 바이트 배열에 저장
        List<Map<String,Object>> list = new ArrayList<>();

        try{
            byte[] bytes = resource.getInputStream().readAllBytes();

//          한글 인코딩 적용 : EUC-KR , CP949 , UTF-8  등
            InputStreamReader reader = new InputStreamReader(new java.io.ByteArrayInputStream(bytes) , Charset.forName("UTF-8"));

//          OpenCSV 이용하여 바이트들을 대입
            CSVReader csvReader = new CSVReaderBuilder(reader).build();

//          주로 첫 행은 제목 : 한 줄 읽어오기 : (Key/속성명 으로 사용할 예정)
            String[] headers = csvReader.readNext();

//          나머지 행들은 반복문 이용하여 가져오기            
            String[] values;



//          무한루프
            while( true ){

                values = csvReader.readNext();

                if(values == null){break;}

//              반복문 이용해서 map 만들기

                Map<String,Object> row = new LinkedHashMap<>();

                for(int i = 0 ; i < headers.length ; i++ ){
                    
                    row.put( headers[i] , values[i] );
                }

                list.add( row );
            }

        }catch( Exception e ){ System.out.println(e);}

        return list;


    }





}


/*
    컬렉션프레임워크 : List , Set , Map

        - List : 여러개 자료들을 인덱스로 구분하여 하나의 자료에 저장 (중복가능)

            => [ 값1 , 값2 , 값3 ]

        - Set : 여러개 자료들을 인덱스 없이 하나의 자료에 저장 (중복불가)

            => ( 값1 , 값2 , 값3 )

        - Map : Key 와 Value 를 한쌍으로 구성된 Entry 여러개를 하나의 자료에 저장

            => { 속성명1 : 값1 , 속성명2 : 값2 , 속성명3 : 값3 }


    URI : 쿼리스트링 포함 

    URL : 쿼리스트링 전까지

    WebClient 객체 : 스프링에서 외부 API 요청 라이브러리

        - 객체 : WebClient webClient = WebClient.builder().build();

    클래스명.class : 리플렉션 ( 특정/)

    
    대부분의 공공자료 : JSON , XML , CSV

        - JSON(자바스크립트객체) : { 속성명 : 속성값 , 속성명 : 속성값 }

        - XML(마크업) : <속성명>속성값</속성명>

        - CSV(,쉼표구분) : 값,값,값,값,값

    * List<Map<String,Object>>
    * [ { } , { } , { } ]

*/
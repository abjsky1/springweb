package example.day065_261013_spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/*
    크롤링 도구/라이브러리 선택
    [1] 정적페이지(모든 자료가 한번에 표시) vs 동적페이지(구분별 자료들이 순차적으로 표시)

        정적페이지 : JSOUP                  동적페이지 : playwright

    [2] 도메인 robots.txt 검색하여 allow , Disllow 판단

    [3] 크롤링할 선택자/식별자(html-class) 찾기 

        크롤링 하고 싶은 위치를 선택하여 개발자도구에서 해당 html 을 찾고 크롤링 대상 설정

    [4] IP 차단 방지 목적 , 
    
*/

@Service 
public class CrawlingService {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/154.0.0.0 Safari/537.36";

//  [1] 정적페이지 크롤링1
    public List<String> craw1( ){

    //  1. 크롤링 페이지 지정
        String url = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";

    //  2. JSOUP 객체 생성
    try{
    //  Jsoup.connect( 크롤링할 주소 ).userAgent( 브라우저 정보 ).get( )
        Document document = Jsoup.connect( url ).userAgent( USER_AGENT ).get( );

    //  3. 가져온 html 안에서 식별자 (class , id , 마크업 , 자식선택자 , 자손선택자 등) 안에 값만 추출
    //  요소 1개 추출 = Element , 요소 2개 이상 추출 = Elements
    //  document.select( 선택자 )
        Elements elements = document.select("#section-list .titles > a");

    //  4. 가져온 것들을 반복문 이용하여 리스트에 담기
        List<String> list = new ArrayList<>();

        for( Element element : elements ){

        //  5. 마크업 사이에 텍스트 추출 : .text()
            list.add( element.text() );

        }

        return list;
    
    }catch(Exception e){System.out.println(e);}
   
        return null;

    }
    

//  [2] 정적페이지 크롤링2
    public List<Map<String,Object>> craw2(){

    //  1. 크롤링 페이지 지정
        String url = "https://www.yes24.com/product/category/daybestseller?categoryNumber=001&pageNumber=1&pageSize=24&type=day";

    //  2. JSOUP 객체 생성
    try{
    //  Jsoup.connect( 크롤링할 주소 ).userAgent( 브라우저 정보 ).get( )
        Document document = Jsoup.connect( url ).userAgent( USER_AGENT ).get( );

    //  3. 가져온 html 안에서 식별자 (class , id , 마크업 , 자식선택자 , 자손선택자 등) 안에 값만 추출
    //  요소 1개 추출 = Element , 요소 2개 이상 추출 = Elements
    //  document.select( 선택자 )
        Elements nameList = document.select(".gd_name");

        Elements priceList = document.select(".txt_num > .yes_b");

        Elements imgList = document.select(".img_bdr img");

    //  4. 가져온 것들을 반복문 이용하여 리스트에 담기
        List<Map<String,Object>> list = new ArrayList<>();

        for( int i = 0 ; i <= nameList.size()-1 ; i++ ){

        //  마크업 사이에 텍스트 추출 : .text()
        //  특정속성명의 속성값 추출 : .attr("특정속성명")
            Map<String , Object> map = new HashMap<>();
            map.put("name", nameList.get(i).text() );
            map.put("price", priceList.get(i).text() );
            map.put("image", imgList.get(i).attr("data-original") );

            list.add(map);

        }

        return list;
    
    }catch(Exception e){System.out.println(e);}
   
        return null;

    }



}

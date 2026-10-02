package example.day060_261002_spring;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class FileService {

//  [1] 업로드 경로 설정

//  1. 현재 프로젝트의 최상위 폴더 찾기
    private String baseDir = System.getProperty("user.dir");

//  2. 최상위 폴더 이후로 build 폴더로 업로드할 경로 지정
//  src 폴더 : 배포 전(서버에 업로드 전) 폴더로 개발자가 코드 작성하는 폴더
//  build 폴더 : 실행 후(서버에 업로드 된) 폴더로 개발자가 작성한 코드 실행(컴파일)한 결과물 폴더
//  * 일반 사용자들은 업로드할 경우에 개발자폴더(src) 가 아닌 서버 폴더(build)에 업로드 해야 함
//  * 추후에 AWS(클라우드)의 경우에는 클라우드 IP 를 넣어줄 것
    private String uploadPath = baseDir + "/build/resources/main/static/upload/";


//  [2] 업로드 함수
    public String fildUpload( MultipartFile multipartFile ){

    //  1. 업로드할 파일의 MultipartFile 인터페이스 가져오기

    //  2. 만약에 업로드 파일이 없으면 취소
        if(multipartFile == null || multipartFile.isEmpty()){ return null; }

    //  3. 만약에 업로드 폴더가 없으면 폴더 생성

    //  3-1. 설정한 경로 File 객체에 대입
        File dir = new File(uploadPath);

    //  3-2. 설정한 경로에 폴더가 없으면 폴더 생성
        if( !dir.exists() ){ dir.mkdir(); }

    //  4. 업로드할 파일명 중복 방지 : UUID/업로드날짜시간/PK 등 고유식별번호 추가

    //  4-1. UUID
    //  replaceAll("기존문자" , "치환할문자") : 문자열 안에 기존문자들을 새로운 문자로 치환/교환 하는 함수
        String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename()
                                                                            .replaceAll("_", "-");
    
    //  5. 업로드
    //  .transferTo( 업로드할 file 객체 );   + 예외처리
        try{
            multipartFile.transferTo( new File(uploadPath) );
            return fileName;
        }catch(Exception e){System.out.println(e);}

        return null;

    }


//  [3] 다운로드 함수

//  0. C드라이브 파일   -- FileInput -->>   JAVA   -- ServletOut -->>   브라우저
    public void fileDownload( String fileName , HttpServletResponse response ){

    //  1. 다운로드할 파일명과 HTTP 응답객체 받기

    //  2. 다운로드할 파일명과 업로드 경로 조합

    //  2-1. 업로드 경로 + 파일명;
        String downloadPath = uploadPath + fileName;

    //  3. 만약 해당 경로에 파일이 없으면 취소
        File file = new File(downloadPath);
        if( !file.exists() ){ return; }

    //  4. 해당 경로에 파일이 있으면 파일 읽어오기 
    
    //  4-1. FileInputStream  ,  예외발생
        try{

        //  파일명 (바이트) 용량 확인
            long fileSize = file.length(); 

        //  파일 용량만큼 바이트 배열 생성
            byte[] bytes = new byte[ (int)fileSize ];

        //  파일 입력 객체 생성
            FileInputStream fin = new FileInputStream(downloadPath);

        //  파일 입력 객체가 읽어온 바이트들을 바이트 배열에 저장
            fin.read(bytes);

        //  스트림(이동)간에 안전하게 스트림 직접 닫기
            fin.close();

        //  6. 다운로드 형식 지정 : 브라우저 마다 상이

        //  6-1. 실제 파일명으로 찾기 , UUID_XXX.jpg  -->> XXX.jpg

        //  _언더바 기준으로 쪼개서 2번째 인덱스 값 가져오기
            String realFileName = fileName.split("_")[1];

        //  HTTP 헤더에 다운로드 형식 지정
            response.setHeader("Content-Disposition", "attactment;filename=" + URLEncoder.encode(realFileName,"UTF-8"));

        //  5. 서버로 가져온 파일(바이트들)을 HTTP 응답하기

        //  5-1. 현재 다운로드 요청한 서블릿의 출력스트림 가져오기
            ServletOutputStream fout = response.getOutputStream();

        //  5-2. 서블릿출력스트림 객체로 앞전에 읽어온 파일을 바이트배열로 내보내기
            fout.write(bytes);

        //  5-3. 
            fout.close();
            
        }catch(Exception e){System.out.println(e);}

    }


//  [4] 파일 삭제 함수
    public boolean fileDelete( String fileName ){

    //  1. 삭제할 파일명과 경로 조합
        String deleteFilePath = uploadPath+fileName;

    //  2. 파일의 경로가 담긴 객체 생성
        File file = new File(deleteFilePath);

    //  3. 만약 경로에 파일이 존재하면 삭제
        if(file.exists()){
        
    //  3-1. 해당 경로에 파일 삭제 함수
            file.delete();
            return true;
        }
        else{ return false; }

    }


}

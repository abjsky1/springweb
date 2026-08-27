// *

console.log("INDEX.JS 열림")

//  [1] 전체조회 , funtion 함수명(매개변수명){}

    async function boardFindAll(){
        
        console.log("boardFindAll 열림");

//      1. 어디에 :

        let tbody = document.querySelector('.boardList');

//      2. 무엇을 (HTTP 통신(AXIOS) 이용한 백엔드에게 요청) : 

//      - await axios.HTTP메소드명( "HTTP주소" )

//      - await 함수 사용할 때, 함수명 앞에 async 추가해줘야 함.  ex) async function boardFindAll(){

//      - 동기화 하는 이유 : 해당 통신(응답결과) 이후에 아래 (출력)코드 실행하기 위해

//      - 비동기화( 요청 후 응답 대기 없음 ) , 동기화( 요청 후 응답 대기 )

        let html = "";

        const 응답결과 = await axios.get( "http://127.0.0.1:8080/board/findall" );

        console.log(응답결과);

//      { header:~~ , data:~~ , config:~~ }

        const 게시물리스트 = 응답결과.data;

        console.log(게시물리스트)

        for(let i = 0 ; i <= 게시물리스트.length-1 ; i++){
            
            const 게시물객체 = 게시물리스트[i];

            html += `<tr>

                        <td>${게시물객체.no}</td>
                        <td>${게시물객체.writer}</td>
                        <td>${게시물객체.content}</td>
                        <td>
                            <button onclick="boardUpdate(${게시물객체.no})">수정</button>
                            <button onclick="boardDelete(${게시물객체.no})">삭제</button>
                        </td>

                    </tr>`
        }

//      3. 출력 :

        tbody.innerHTML = html;

//      *. 한 줄 정리 : document.querySelector('.boardList').innerHTML = html;

    }

//  HTML(JS포함) 열릴 때, 최초 한 번 실행

    boardFindAll();


//  [2] 등록

    async function boardSave(){

//      1. 입력받은 값 가져오기

//      - .value : 입력상자에 입력된 값 반환 속성

        const content = document.querySelector('.content').value;
        const writer = document.querySelector('.writer').value;

//      2. 저장 : axios 이용하여 백엔드에게 저장 요청하고 응답받기

//      await axios.http메소드( "주소" ) , 도메인 생략가능

        const response = await axios.post(`/board/save?content=${content}&writer=${writer}`);

//      3. 결과 출력

        if(response.data == true){

            alert('저장 성공'); 

//          저장 성공하면 조회함수 재실행
            boardFindAll();

        }
        else{ alert('저장 실패'); }

    }


//  [3] 수정
    async function boardUpdate( no ){

//      1. 수정할 내용 입력받기 prompt

        const content = prompt('수정할 내용 : ');

//      2. 수정 처리 : axios 이용하여 백엔드에게 수정 요청/응답

        const response = await axios.put(`/board/update?no=${no}&content=${content}`)

//      3. 결과 출력

        if(response.data == true){

            alert('수정 성공');

            boardFindAll();

        }
        else{ alert('수정 실패'); }

    }


//  [4] 삭제
    async function boardDelete( no ){

//      *. no = 삭제할 게시물 번호 / 클릭한 게시물 번호

//      1. 삭제 처리 : axios 이용하여 백엔드에게 삭제 요청/응답

        const response = await axios.delete(`/board/delete?no=${no}`);

//      2. 결과

        if(response.data == true){

            alert('삭제 성공');

            boardFindAll();

        }
        else{ alert('삭제 실패'); }

    }

//  정리 : axios : 비동기 통신을 기본값으로 제공하는 HTTP 기반의 통신 도구

//  1. 설치 방법 :

//      - HTML 에 추가 : <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

//  2. axios.HTTP메소드명(URL)

//      - await axios.get( "http://127.0.0.1:8080/board/findall" );

//      - await 함수 사용할 때, 함수명 앞에 async 추가

/*

    async function boardSave(){

        const response = await axios.HTTP메소드명( URL );
        const data = response.data

    }
        
*/
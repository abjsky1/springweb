//  .js 파일은 .html 파일 안에 <script src="index.js"></script> 코드로 포함된다.

//  [1] 변수와 상수

//  1. 변수 선언 키워드 : let
    let count1 = 10; 
        count1 = 3;     // 수정 가능

//  2. 상수 선언 키워드 : const
    const count2 = 20;
//        count2 = 12;  // 수정 불가능

//  [2] 문자열 템플릿  `` 백틱

//  1.
    console.log(`hello ${count1}`)

//  2.
    let html = `<div> hello ${count2} </div>`
    console.log(html)

//  [3] 조건문

//  1. if
    const point = 85;
    if(point >= 90){console.log('A학점')}
    else if(point >= 80){console.log("B학점")}
    else{console.log("C학점")}

//  2. 삼항연산자
    console.log( point >= 90 ? "A학점" : point >= 80 ? "B학점" : "C학점")

//  3. 단축평가 , 조건 && 참이면결과
    console.log(point >= 90 && "A학점")  // 만약에 90점 이상이면 "A학점" , 아니면 false
    console.log(point >= 90 || "A학점")  // 만약에 90점 이상이면  true  , 아니면 "A학점"

//  4. null 체크 연산자  ,  조건 ?? null이면 결과
    const nickName = null;
    console.log( nickName ?? "익명사용자")

//  [4] 반복문

//  1. 일반 for문
    const array = [10, 20, 30, 40, 50]
    for(let i = 0 ; i <= array.length-1 ; i++){console.log(array[i]);}

//  2. 향상된 for문
    for(let i in array){console.log(array[i])}   // in 인덱스 하나씩 순회
    for(let value of array){console.log(value)}  // of 값 하나씩 순회

//  3. forEach  ,  단순 순회
    array.forEach( (value) => {console.log(value);})

//  4. map  ,  순회 + 반환
    const newArray1 = array.map( (value) => {return value} )

//  5. filter  ,  순회 + 조건(논리)
    const newArray2 = array.filter( (value) => {return value >= 20;})

//  [5] 함수

//  1. 선언적 함수
    function func1( 매개변수1 , 매개변수2 ){ }
    func1( 4, 10 )

//  2. 익명 함수  ,  주로 변수/상수에 저장
    const func2 = function( 매개변수1 , 매개변수2){ }
    func2( 3 , 20 )

//  3. 화살표/람다식 함수  ,  주로 변수/상수에 저장
    const func3 = ( 매개변수1 , 매개변수2 ) => { }
    func3( 10 , { name : "김길리" } )

//  4. 매개변수에 기본값 대입 가능 , 만일 인수가 없을 때 기본값 대입
    const func4 = ( 매개변수1 , 매개변수2 , 매개변수3 = 'student' ) => { console.log(매개변수3)}
    func4( 2 , 5 )


//  [6] 객체 : 여러개의 값을 가진 하나의 값 , 주로 변수/상수에 저장

//  1. { 속성명(key) : 속성값(value) }  ,  값(value)에는 객체/배열/함수 도 저장 가능
    const obj1 = { name : '김길리' , age : 23 , func1 : (param)=>{} }
    console.log( obj1.func1 )   // 속성명으로 속성값 호출

//  2. [ 값 , 값 , 값 ]
    const obj2 = [ '김길리' , 25 , (param)=>{ } ]
    console.log( obj2[2]() );   // 인덱스로 속성값 호출

//  [7] 스프레드 연산자 ... 배열 또는 객체를 복사할 때 사용, 사용처: 주소값 변경 목적 (리액트/플러터)

//  1.
    const obj3 = { ...obj1 , phone : "010" }   // { ...기존객체 , 새로운속성명 : 새로운속성값 }
    console.log( obj3 )

//  2.
    const obj4 = [ "010" , ...obj2 ]    // [ ...기존배열 , 새로운값 ]
    console.log( obj4 )

//  [8] 구조분해 할당 : 배열 또는 객체에서 값을 분해

//  1.
    const { name , age } = obj1 ;   // 오른쪽 객체 안에 왼쪽에 각 속성값들을 변수/상수에 값 대입
    console.log(name)
    console.log(age)

//  2. 
    const [ name2 , ...array2 ] = obj2;   // 오른쪽 배열 안에 순서대로 값들을 변수/상수에 대입
    console.log(name2)
    console.log(array2)  // 나머지(그외) 


//  [9] 콜백함수 : 함수를 전달해서 나중에 함수를 실행하는 것  ,  ex) 메소드레퍼런스

//  1. 
    function printSuccess(message){console.log("성공", message)}
    function printScore(score , onSuccess , onError){
        if(score >= 80){ onSuccess("합격") }
        else{ onError("불합격") }
    }
    
//  2. 주의 : 콜백함수 방식으로 함수 호출 , 주의 : 인수에 함수 전달시 함수실행 X , 함수정의 O
    printScore(50 , printSuccess , (message)=>{console.log('실패'+message)})

//  [10] 

//  1. 동기식: 먼저 호출한 함수/기능의 결과가 올 때까지 대기 상태  ,  동기화

//      1-1. 선언 함수 앞에 async 
//      1-2. axios 앞에 await
        const backLoad = async ( ) => { const responce = await axios( ); }

//  2. 비동기: 먼저 호출한 함수/기능의 결과는 순서 상관 없이 반환  ,  axios
    

    






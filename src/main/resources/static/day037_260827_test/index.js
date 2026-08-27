
console.log("js 연결 성공")


// 최초 조회 1회 실행
waitingFindAll()

// 조회
async function waitingFindAll(){

    console.log("waitingSave 함수 실행 성공")

    let html = "";

    const response = await axios.get( "http://127.0.0.1:8080/waiting/findall" );

    console.log(response)
    console.log(response.data)

    for(let i = 0 ; i <= response.data.length-1 ; i++){

        html += `<tr>
                    <td>${response.data[i].no}</td>
                    <td>${response.data[i].phoneNumber}</td>
                    <td>${response.data[i].people}명</td>
                    <td>
                        <button onclick='waitingUpdate("${response.data[i].phoneNumber}")'>수정</button>
                        <button onclick='waitingDelete("${response.data[i].phoneNumber}")'>삭제</button>
                    </td>
                </tr>`

    }

    document.querySelector("#tbody").innerHTML = html

}

// 등록
async function waitingSave(){

    let phoneNumber = document.querySelector("#phoneNumber").value;

    let people = document.querySelector("#people").value;

    const response = await axios.post(`http://127.0.0.1:8080/waiting/save?phoneNumber=${phoneNumber}&people=${people}`);

    if(response.data == true){
        alert('저장성공');
        waitingFindAll();
    }
    else{alert('저장실패');}

}

// 수정
async function waitingUpdate(phoneNumber){
    console.log("waitingUpdate 함수 실행")
    console.log(phoneNumber)
    let people = prompt("수정할 인원수 입력 : ")

    const response = await axios.put(`http://127.0.0.1:8080/waiting/update?phoneNumber=${phoneNumber}&people=${people}`);

    console.log(response);
    console.log(response.data);

    if(response.data == true){
        alert('수정성공');
        waitingFindAll();
    }
    else{alert('수정실패');}
}

// 삭제
async function waitingDelete(phoneNumber){
    console.log("waitingDelete 함수 실행")

    const response = await axios.delete(`http://127.0.0.1:8080/waiting/delete?phoneNumber=${phoneNumber}`)

    if(response.data == true){
        alert('삭제성공');
        waitingFindAll();
    }
    else{alert('삭제실패');}

}
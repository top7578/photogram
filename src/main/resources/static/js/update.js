// (1) 회원정보 수정
function update(userId, event) {

    event.preventDefault();     //폼태크 액션 막기
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type:"put",
        url:`/api/user/${userId}`,
        data: data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json" //응답타입
    }).done(res => {    //json데이터를 js데이터로 파싱해서 res에 받는다
                        //HttpStatus 상태코드 200번대
        console.log("update 성공", res);
        location.href = `/user/${userId}`
    }).fail(err => {    //HttpStatus 상태코드 200번대 아닐 때
        if (err.data == null) {
            alert(err.responseJSON.message);
        } else {
            alert(JSON.stringify(err.responseJSON.data));
        }
    });
}
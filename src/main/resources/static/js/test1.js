$(function () {

    // form 데이터를 json형태로 변경해주는 jQuery 커스텀 함수
    $.fn.serializeObject = function() {
        var obj = null;
        try {
            if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
                var arr = this.serializeArray();
                if(arr){ obj = {};
                    jQuery.each(arr, function() {
                        obj[this.name] = this.value; });
                }
            }
        }catch(e) {
            alert(e.message);
        }finally {}
        return obj;
    }

    // 테이블 서치
    searchTable();

});

// Daum 주소 찾기 api
function findAddress(){
    new daum.Postcode({
        oncomplete: function(data) {

            console.log(data);

            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            var zipcode = data.zonecode;
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $('input[name="zipcode"]').val(zipcode);
            if(roadAddr !== ''){
                $('input[name="address"]').val(roadAddr);
            }
            else if(jibunAddr !== ''){
                $('input[name="address"]').val(jibunAddr);
            }
        }
    }).open();
}


// POST/PUT 메서드의 ajax 작업
function ajaxPostPut(formId,url,type,redirectUrl,workTitle){
    var formData = $('#' + formId).serializeObject();
    alert(JSON.stringify(formData));
    $.ajax({
        url: ''+ url,
        type: ''+ type,
        dataType:'json',
        data : JSON.stringify(formData),
        contentType:'application/json;charset=utf-8',
        mimeType:'application/json;charset=utf-8',
        error:function(xhr,status,msg){
            alert("에러 상태 : " + status + "에러 내용 : " + msg);
        },
        success:function(response){
            if(response.result==true||response.nullCheckResult==true){
                alert(workTitle + "성공");
                if(null!=redirectUrl||""!=redirectUrl){
                    $(location).attr('href', redirectUrl);
                }
            } else {
                alert(workTitle + "실패");
            }
        }
    });

}




// GET/DELETE 메서드의 ajax 작업
function ajaxGetDelete(url,type,redirectUrl,workTitle){
    $.ajax({
        url: ''+ url,
        type: ''+ type,
        dataType:'json',
        contentType:'application/json;charset=utf-8',
        error:function(xhr,status,msg){
            alert("에러 상태 : " + status + "에러 내용 : " + msg);
        },
        success:function(response){
            if(response.result==true||response.nullCheckResult==true){
                alert(workTitle + "성공");
                if(null!=redirectUrl||""!=redirectUrl){
                    $(location).attr('href', redirectUrl);
                }
            } else {
                alert(workTitle + "실패");
            }
        }
    });

}

// 회원 전체 목록 조회
function loadMemberList(){
    $('tbody').empty();
    let url = 'members';
    let type = 'get';
    $.ajax({
        url: url,
        type: type,
        contentType : 'application/json;charset=utf-8',
        dataType : 'json',
        error:function(xhr,status,msg){
            alert("에러 상태 : " + status + "에러 내용 : " + msg);
        },
        success:function(response){
            if(response.result=true){
                var memberList = response.memberList;
                for (i = 0;i<memberList.length;i++) {
                    /*alert(response.boardList[i].no);*/
                    /*alert(response.boardList[i].writer);*/
                    /*alert(response.boardList[i].subject);*/

                    $('<tr>')
                        .append($('<td>').html(response.memberList[i].no))
                        .append($('<td>').html(response.memberList[i].id))
                        .append($('<td>').html(response.memberList[i].pw))
                        .append($('<td>').html(response.memberList[i].name))
                        .append($('<td>').html(response.memberList[i].email))
                        .append($('<td>').html(response.memberList[i].gender))
                        .append($('<td>').html(response.memberList[i].zipcode))
                        .append($('<td>').html(response.memberList[i].address))
                        .append($('<td>').html(response.memberList[i].detailAddress))
                        .append($('<td>').html(response.memberList[i].regDate))
                        /*.append($('<td>').html(response.boardList[i].subject))*/
                        /*                            .append($('<td>').html('<a id="subjectLink" name="subjectLink" href="/boards/'+response.boardList[i].no+'">'+response.boardList[i].subject+'</a>'))*/
                        /*.append($('<td>').html(response.boardList[i].content))*/
                        /*.append($('<td>').html('<button id=\'btnSelect\'>조회</button>'))*/
                        /*.append($('<td>').html('<button id=\'btnDelete\'>삭제</button>'))*/
                        /*.append($('<input type=\'hidden\' id=\'hidden_userId\'>').val(item.userId))*/
                        .appendTo('tbody');
                }
            } else {
                alert("컨트롤러에서 false 반환");
            }
        }
    });
}






// 로그인 메서드
function logIn(){
    var id = $('input[name="id"]').val();
    var pw = $('input[name="pw"]').val();
    var url = "members/"+id+"/"+pw;
    var type = "GET";
    var redirectUrl = "/";
    var workTitle = "로그인";
    alert(workTitle + "버튼 클릭");
    ajaxGetDelete(url, type, redirectUrl, workTitle);
}

// 로그아웃 메서드
function logOut(){
    var url = "memberLogOut";
    var type = "GET";
    var redirectUrl = "/";
    var workTitle = "로그아웃";
    alert(workTitle + "버튼 클릭");
    ajaxGetDelete(url, type, redirectUrl, workTitle);
}

// 회원가입 메서드
function join(){
    var redirectUrl = '/';
    var workTitle = "회원가입";
    var formId = "form1";
    var url = "members";
    var type = "post";
    alert(workTitle + "버튼 클릭");
    ajaxPostPut(formId,url,type,redirectUrl,workTitle);
}

// 테이블내용을 filter기능으로 조회
function searchTable(){
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
}
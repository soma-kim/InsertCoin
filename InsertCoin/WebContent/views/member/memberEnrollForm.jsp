<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.insertcoin.member.model.vo.Member" %>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>회원가입</title>

    <!-- CSS 스타일시트 -->
    <link href="resources/css/MemberEnrollForm.css" rel="stylesheet">
    <link href="resources/css/MainCss.css" rel="stylesheet">
    <link href="resources/css/MainContentCss.css" rel="stylesheet">

    <!-- jQuery 라이브러리 연결 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <!-- Bootstrap 프레임워크 연결 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

        <!-------------------------------------- 컨텐츠 영역 -------------------------------------->

        <!-- 컨텐츠 영역 -->
        <div class="content_container">

            <!-- 회원가입 창 -->
            <form id="enroll_form" action="<%= contextPath %>/enroll.me" method="post">

                <!-- 로고, 이메일, 인증번호, 비밀번호/확인, 닉네임 입력 영역 -->
                <div class="enroll_content">
                    <!-- 회원가입 창 로고 -->
                    <div style="text-align:center;">
                        <img src="resources/image/logo/insertcoin_logo.png" alt="insert_coin_logo" id="enroll_logo">
                    </div>
                    <!-- 회원가입 글자 -->
                    <div id="enroll_text"><h3>회원가입</h3><hr></div>

                    <!-- 로그인 정보 입력 폼 -->
                    <table align="center" id="enroll_table">
                        
                        <tr>
                            <th>이메일</th>
                        </tr>
                        <tr>
                            <th colspan="4">
                                <input type="email" name="memEmail" maxlength="30" required> <br>
                            </th>
                            <td colspan="2"><button class="table_button" onclick="emailCheck();">중복확인</button></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <div><label id="emailCheckOutput"></label></div>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="4">
                                <input type="text" name="certification_num" placeholder="인증번호를 입력하세요." required><br>
                                <div class="superscript">인증번호는 최대 10분간만 유효합니다.</div>
                                <td><button class="table_button_cert">요청</button></td>
                                <td><button class="table_button_cert">확인</button></td>
                            </th>
                            
                        </tr>
                        <tr>
                            <th colspan="6">비밀번호</th>
                        </tr>
                        <tr>
                            <th colspan="6">
                                <input type="password" name="memPwd" required>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="6">비밀번호 확인</th>
                        </tr>
                        <tr>
                            <th colspan="6">
                                <input type="password" name="memPwd_confirm" required>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="6">닉네임</th>
                        </tr>
                        <tr>
                            <th colspan="4">
                                <input type="text" name="memNickname" minlength="2" maxlength="10" required>
                            </th>
                            <td colspan="2"><button class="table_button" onclick="nicknameCheck();">중복확인</button></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <div><label id="nicknameCheckOutput"></label></div>
                            </td>
                        </tr>
                        <tr>
                            <!-- 가입하기 버튼 버튼 -->
                            <th colspan="6"><button type="submit" id="enroll_button">가입하기</button></th>
                        </tr>
                    </table>
                </div>
            </form>
        </div>

        <script>
        	function emailCheck() {
        		var $memEmail = $("#enroll_table input[name=memEmail]");
        		
        		$.ajax({
        			url : "emailCheck.me",
        			data : {checkEmail : $memEmail.val()},
        			success : function(result) {
        				
        				// result 값은 "possible" 또는 "impossible"
        				if(result == "impossible") {
        					
        					$("#emailCheckOutput").text("이미 존재하는 아이디이므로 사용할 수 없습니다.").css("color","red").css("font-size","12px");
        					$memEmail.val("");
        					$memEmail.focus();
        					
        				} else {
        					
        					$("#emailCheckOutput").text("사용 가능한 아이디입니다!").css("color","yellow").css("font-size","12px");
        					
        				}
        				
        			},
        			error : function () {
        				console.log("이메일 중복 체크용 ajax 통신 실패!");
        			}

        		});
        	}
        	
        	function nicknameCheck() {
        		var $memNickname = $("#enroll_table input[name=memNickname]");
        		
        		$.ajax({
        			url : "nicknameCheck.me",
        			data : {checkNickname : $memNickname.val()},
        			success : function(result) {
        				
        				// result 값은 "possible" 또는 "impossible"
        				if(result == "impossible") {
        					
        					$("#nicknameCheckOutput").text("이미 존재하는 닉네임이므로 사용할 수 없습니다.").css("color","red").css("font-size","12px");
        					$memNickname.val("");
        					$memNickname.focus();
        					
        				} else {
        					
        					$("#nicknameCheckOutput").text("사용 가능한 닉네임입니다!").css("color","yellow").css("font-size","12px");
        					
        				}
        				
        			},
        			error : function () {
        				console.log("닉네임 중복 체크용 ajax 통신 실패!");
        			}

        		});
        	}
        </script>

</body>
</html>
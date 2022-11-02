<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.insertcoin.member.model.vo.Member" %>
<%
	String contextPath = request.getContextPath();

	// 쿠키 불러오기
	// => request.getCookies() 메소드 => Cookie 타입의 배열로 리턴
	Cookie[] cookies = request.getCookies();
	
	// 배열에 담긴 여러 개의 쿠키 세트들 중에 내가 원하는 쿠키만 골라내는 작업 진행
	String saveEmail = "";
	if(cookies != null) {
		
		for(int i = 0; i < cookies.length; i++) {
			
			// System.out.println(i + " : " + cookies[i].getName() + " / " + cookies[i].getValue());
			// 서버는 기본적으로 JSSESIONID라는 쿠키를 만들어 줌
			// 쿠키로부터 name(키값)을 뽑아내려면 getName(), value(밸류값)을 뽑아내려면 getValue() 메소드 이용
			
			if(cookies[i].getName().equals("saveEmail")) {
				saveEmail = cookies[i].getValue();
				break; // 안 걸어도 상관없지만 쿠키가 많은 경우 해당 쿠키를 찾은 이후로는 굳이 계속 반복 돌면서 낭비할 필요가 없음!
			}
		}
	}
	
	// 이 시점 기준으로 "saveId"라는 키값을 가진 쿠키가 있었다면 String 타입으로 saveId라는 변수에 해당 아이디값 자체가 담겨 있을 것!
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>로그인 화면</title>

    <!-- CSS 스타일시트 -->
    <link href="resources/css/memberLoginForm.css" rel="stylesheet">
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
            <!-- 로그인 창 -->
            <form id="login_form" action="<%= contextPath %>/login.me" method="post">

                <!-- 로고, 이메일, 비밀번호 입력 영역 -->
                <div class="login_content">
                    <!-- 로그인 창 로고 -->
                    <div style="text-align:center;">
                        <img src="resources/image/common/main-logo.png" alt="insert_coin_logo" id="login_logo">
                    </div>
                    <!-- 로그인 글자 -->
                    <div id="login_text"><h2>로그인</h2><hr></div>

                    <!-- 로그인 정보 입력 폼 -->
                    <table align="center" id="login_table">
                        
                        <tr>
                            <th>이메일</th>
                        </tr>
                        <tr>
                            <th>
                                <input type="email" name="memEmail" required>
                            </th>
                        </tr>
                        <tr>
                            <th>비밀번호</th>

                        </tr>
                        <tr>
                            <th>
                                <input type="password" name="memPwd" required>
                            </th>
                        </tr>
                        <tr>
                        	<th colspan="2">
                        		<div id="saveEmail" style="display:inline-block;">
									<input type="checkbox" name="saveEmail" value="y">
									<label for="saveEmail" style="font-size:13px;">이메일 저장</label>
								</div>
                        	</th>
                        </tr>
                        <tr>
                            <!-- 로그인 버튼 -->
                            <th><button type="submit">로그인</button></th>
                        </tr>
                    </table>
                    <br>
                    <!-- 회원가입, 비밀번호 찾기 -->
                    <div align="center" id="etc">
                        <a onclick="enrollPage();">회원가입</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;
                        <a href="">비밀번호 찾기</a>
                    </div>
                </div>
            </form>
        </div>
        
        <%@ include file = "../../views/common/footer.jsp" %>
        
        
        <script>
			// 모든 요소들이 화면에 다 로딩된 후 saveEmail이라는 자바 변수에 저장된 값을 불러옴
			// 이메일 입력창에 value 속성으로 설정해 둘 것 & 아이디 저장하기 체크박스에 체크 수행
			$(function () {
			
				var saveEmail = "<%= saveEmail %>";
				
				if(saveEmail != "") { // 쿠키가 있다면
					
					$("#login_form input[name=memEmail]").val(saveEmail);
					$("#saveEmail").attr("checked", true);
				}
				
			});
			
    		function enrollPage() {
    			location.href = "<%=contextPath %>/enrollForm.me";
    		}
	    </script>

</body>
</html>
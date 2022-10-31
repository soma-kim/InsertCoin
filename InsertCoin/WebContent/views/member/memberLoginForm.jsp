<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.insertcoin.member.model.vo.Member" %>
<%
	String contextPath = request.getContextPath();
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
                        <img src="resources/image/logo/insertcoin_logo.png" alt="insert_coin_logo" id="login_logo">
                    </div>
                    <!-- 로그인 글자 -->
                    <div id="login_text"><h2>로그인</h2><hr></div>

                    <!-- 로그인 정보 입력 폼 -->
                    <table align="center" id="login_table">
                        
                        <tr>
                            <th>이메일</th>
                        </tr>
                        <tr>
                            <th colspan="3">
                                <input type="email" name="memEmail" required>
                            </th>
                        </tr>
                        <tr>
                            <th>비밀번호</th>

                            </tr>
                        <tr>
                            <th colspan="3">
                                <input type="password" name="memPwd" required>
                            </th>
                        </tr>
                        <tr>
                            <!-- 로그인 버튼 -->
                            <th colspan="3"><button type="submit">로그인</button></th>
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
    		function enrollPage() {
    			location.href = "<%=contextPath %>/enrollForm.me";
    		}
	    </script>

</body>
</html>
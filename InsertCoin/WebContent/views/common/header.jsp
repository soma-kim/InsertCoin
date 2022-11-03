<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.insertcoin.member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	String contextPath = request.getContextPath();
	
	String alertMsg = (String)session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>메인 화면</title>

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
	<script>
		var msg = "<%= alertMsg %>";
		
		if(msg != "null") {
			alert(msg);
			
			<% session.removeAttribute("alertMsg"); %>
		}
	</script>

    <!-------------------------------------- 헤더 영역 -------------------------------------->

        <!-- 헤더 영역 -->
        <div class="header_container">

            <!-- 헤더 로고 -->
            <div class="header_logo_container">
                <a href=""><img id="header_logo" src="resources/image/common/main-logo.png" width="100%"></a>
            </div>

            <!-- 헤더 메뉴바 -->
            <div id="header_menu">
                <ul>
                    <li><a href="" class="main_menu">게임</a></li>
                    <li>
                        <a href="" class="main_menu">커뮤니티</a>
                        <ul>
                            <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;개발자 게시판</a></li>
                            <li><a onclick="genBoardPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자유 게시판</a></li>
                        </ul>
                    </li>
                    <li>
                        <a onclick="noticePage();" class="main_menu">고객센터</a>
                        <ul>
                            <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공지사항</a></li>
                            <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FAQ</a></li>
                        </ul>
                    </li>
                    <% if (loginUser == null) { %>
	                    <li>
	                        <a id="login_menu" onclick="loginPage();">로그인</a>
	                        <ul>
	                            <li><a onclick="loginPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;로그인</a></li>
	                            <li><a onclick="enrollPolicyPage();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;회원가입</a></li>
	                        </ul>
	                    </li>
                    <% } else { %>
   	                    <li>
                        <a href="" class="main_menu">
                            <img src="resources/image/profile/profile_default.png" id="header_profile">
                            InsertCoin
                        </a>
                        <ul>
                            <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;마이페이지</a></li>
                            <li><a href="<%= contextPath %>/logout.me">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;로그아웃</a></li>
                        </ul>
                    </li>
                    <% } %>
                </ul>
            </div>

            <!-- 헤더 검색 -->
            <div class="header_search_container">
                <form action="" class="header_search-bar">
                    <input type="search" name="search">
                    <button type="submit" id="header_search">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                        </svg>
                    </button>
                </form>
            </div>

        </div>
    
    <script>
    	function loginPage() {
    		location.href = "<%= contextPath %>/loginForm.me";
    	}
    	
    	function enrollPolicyPage() {
    		location.href = "<%=contextPath %>/enrollPolicy.me";
    	}
    	
    	function genBoardPage() {
    		location.href= "<%= contextPath %>/list.bo?currentPage=1";
    	}
    	
    	function noticePage() {
    		location.href = "<%= contextPath %>/list.no?currentPage=1";
    	}
    </script>

</body>
</html>
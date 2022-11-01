<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.insertcoin.game.model.vo.Game" %>
<%
	// 게임 리스트 먼저 뽑기
	ArrayList<Game> list = (ArrayList<Game>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 작성</title>
    <!-- CSS 스타일시트 -->
    <link href="resources/css/boardEnrollForm.css" rel="stylesheet">
</head>
<body>

    <!-- 전체 영역 -->
    <div class="wrap">
    
		<%@ include file = "../../views/common/header.jsp" %>

	        <!-- 컨텐츠 영역 -->
	        <div class="content_container">
	
	            <div class="outer">
	                <div id="title">자유게시판</div>
	                	<form id="enroll-form" action="<%= contextPath %>/insert.bo" method="post" enctype="multipart/form-data">
		                    <input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">
		                    <table align="center" class="table-area">
		                            <tr>
		                                <td colspan="2">
		                                    <!--<input type="text" name="gameName" class="input_text" placeholder=" 게임 태그를 입력해 주세요">   -->
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>
		                                    <select name="genCategory" id="select_category">
		                                        <option value="none">카테고리 선택</option>
		                                        <option value="잡담">잡담</option>
		                                        <option value="질문">질문</option>
		                                        <option value="공략">공략</option>
		                                    </select>
		                                </td>
		                                <td>
		                                    <input id="input_title" type="text" name="genTitle" placeholder="게시글 제목을 입력해 주세요">
		                                </td>
		                            </tr>
		                            <tr>
		                                <td colspan="2">
		                                    <textarea class="input_text" name="genContent" placeholder=" 내용을 입력해 주세요"></textarea>
		                                </td>
		                            </tr>
		                            <tr>
		                            	<th>첨부파일</th>
		                            	<td><input type="file" name="upfile"></td>
		                            </tr>
		                    </table>
		
		                    <br><br>
		                    <div align="center">
		                        <button id="reset_button">취소</button>
		                        <button id="submit_button">등록</button>
		                    </div>
	         		 	</form>
	               </div>
       		 </div>
        
        <%@ include file = "../../views/common/footer.jsp" %>
        
	</div>

</body>
</html>
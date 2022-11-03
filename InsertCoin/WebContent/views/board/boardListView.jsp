<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.insertcoin.common.model.vo.PageInfo, java.util.ArrayList, com.insertcoin.board.model.vo.Board" %>
<%
	// 필요한 데이터 뽑기
	PageInfo pi = (PageInfo)request.getAttribute("pi"); // 페이징바 만들기
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); // 조회된 내용물 출력
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>자유게시판 메인</title>

    <!-- CSS 스타일시트 -->
    <link href="resources/css/boardListView.css" rel="stylesheet">

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

	    <!-- 전체 영역 -->
    <div class="wrap">
    
		<%@ include file = "../../views/common/header.jsp" %>
	
		<div class="outer">
	        <!-- 컨텐츠 영역 -->
	        <div class="content_container">
	
	            <div class="outer">
	                <div id="title">자유게시판</div>
	                    <!-- 조회할 게시물이 보여질 자리 -->
	                    <table align="center" class="list-area">
	                        <thead>
	                            <tr id="board_title">
	                                <!-- th[width=]*6 + Enter -->
	                                <th width="100">글번호</th>
	                                <th width="100">카테고리</th>
	                                <th width="700">제목</th>
	                                <th width="150">작성자</th>
	                                <th width="70">조회수</th>
	                                <th width="150">작성일</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        	<% if(list.isEmpty()) { %>
	                        		<tr>
	                        			<td colspan="6">조회된 리스트가 없습니다.</td>
	                        		</tr>
	                        	<% } else { %>
	                        		<% for(Board b : list) { %>
			                            <tr class="line">
			                                <td><%= b.getGenNo() %></td>
			                                <td><%= b.getGenCategory() %></td>
			                                <td><%= b.getGenTitle() %></td>
			                                <td><%= b.getMemNo() %></td>
			                                <td><%= b.getGenViews() %></td>
			                                <td><%= b.getGenRegister() %></td>
			                            </tr>
			                        <% } %>
	                            <% } %>
	                        </tbody>
	                    </table>
	                    <br>
	                    	<!-- 댓글 쓰기는 로그인한 유저만 할 수 있도록 -->
		                    <% if(loginUser != null) { %>
		                    	<br>
			                    <div class="board_search board_left">
			                        <input type="button" value="글쓰기" class="board_button" onclick="enrollPage();">
			                    </div>
		                    <% } %>
	                		<form id="search-form" action="<%= contextPath %>/list.bo?currnetPage=1" method="get" name="searchForm">
			                    <div class="board_search board-right">
			                        <select name="searchCategory" class="board_search">
			                            <option value="searchAll" selected>제목+내용</option>
			                            <option value="searchTitle">제목</option>
			                            <option value="searchContent">내용</option>
			                            <option value="searchWriter">작성자</option>
										<option value="searchComment" >댓글</option>
			                            <!-- <option value="50">게임명</option> -->
			                        </select>
			                        <input type="text" name="searchWord" id="searchContent"  placeholder="검색어를 입력하세요.">
			                        <button type="submit" class="board_button">검색</button>
			                        <input type="hidden" name="currentPage" value="1">
			                    </div>
            				</form>
	                    <br><br>
	                    <!-- 페이징바 -->
	                    <div class="pagingBar">
		                    <button onclick="location.href='<%= contextPath%>/list.bo?currentPage=<%= currentPage -1 %>';">&lt;</button>
		                    <% for(int p = startPage; p <= endPage; p++) { %>
		                    	
		                    	<% if(p != currentPage) { %>
		                    		
		                    		<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= p %>';"><%= p %></button>
		                    	
		                    	<% } else { %> <!-- 현재 내가 보고 있는  페이지는 클릭 안 되게 -->
		                    		
		                    		<button disabled><%= p %></button>
		                    		
		                    	<% } %>
		                    	
		                    <% } %>
		                    
		                    <% if(currentPage != maxPage) { %>
		                   
		                   		<button onclick="location.href='<%= contextPath%>/list.bo?currentPage=<%= currentPage +1 %>';">&gt;</button>
		                   <% } %>
					</div>
	                </div>
	        </div>
		</div>
        
        <%@ include file = "../../views/common/footer.jsp" %>
        
	</div>
	
	<script>
		function enrollPage() {
			location.href = "<%= contextPath %>/enrollForm.bo";
		}
		
		// 게시글에 클릭 버튼 걸기
		$(function() {
			$(".list-area>tbody>tr").click(function() {
				location.href = "<%= contextPath %>/detail.bo?genNo=" + $(this).children().eq(0).text();
			});
		});

	</script>
	

</body>
</html>
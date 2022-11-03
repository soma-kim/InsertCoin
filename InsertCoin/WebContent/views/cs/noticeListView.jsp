<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.insertcoin.common.model.vo.PageInfo, com.insertcoin.cs.model.vo.*" %>
<%
	// request 에 담았던 list 키 값의 값을 뽑아오기 (== 공지사항 전체 리스트 조회한 결과물)
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

	// 페이징 바 만들기 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>

    <style>
    
    /* 공지사항 제목 */
    .notice_title {
        margin: auto;
        width: 50%;
        margin-top: 50px;
    }
    .write_button {
        margin: auto;
        width: 50%;
    }

    h2 {
        color: white;
        margin : 100px 0px 20px 0px;
        font-size: 30px;
        font-weight: 700;
    }

    .table>tbody>tr:hover { 
        background-color: rgba(232, 183, 34, 0.8);
        cursor: pointer;
    }


    /* 공지사항 게시판 테이블 */
    .table {
        margin: auto;
        margin-top: 30px;
        margin-bottom: 30px;
        border-bottom: 1px solid white;
        text-align: center;
    }
    th.title { text-align: center;}

    /* 검색 필터, 검색창 */
    .notice_search {
        margin: auto;
        height : 37px;
        border : 10px;
        border-radius : 3px;
        vertical-align: middle;
    }
    .btn_search {height: 37px;}

    div>select {
        margin-bottom : 4px;
        border-width : 2px;
    }

    .notice_right {
        margin-left : 600px;
        margin-top: -55px;
    }
    </style>
</head>

<body>

    <!-- 전체 영역 -->
    <div class="wrap">
    
	<%@ include file = "/views/common/header.jsp" %>
	
        <!-- 컨텐츠 영역 -->
        <div class="content_container">

            <div align="left" class="notice_title"><h2>공지사항</h2></div>

            <!-- 공지사항 게시판 리스트 -->
            <table style="color:white; width:50%" align="center" class="table">
                <thead>
                    <tr id="notice_title">
                        <th width="15%">글번호</th>
                        <th width="50%">제목</th>
                        <th width="20%">작성일</th>
                        <th width="15%">조회수</th>
                    </tr>
                </thead>
                <tbody>
                <% if(list.isEmpty()) { %>
                <!-- 리스트가 비어있을 때 : 조회된 공지사항이 없음 -->
                    <tr>
                        <td colspan="4">존재하는 공지사항이 없습니다.</td>
                    </tr>
                <% } else { %>
                    <!-- 리스트가 비어있지 않을 경우 : 조회된 공지사항이 적어도 한 건이라도 있을 경우 -->
                    <% for(Notice n : list) { %> <!-- 향상된 for 문 -->                  
                    <tr class="notice_line">
                        <td><%= n.getNoticeNo() %></td>
                        <td style="text-align:left;"><%= n.getNoticeTitle() %></td>
                        <td><%= n.getNoticeRegisterDate() %></td>
                        <td><%= n.getNoticeViews() %></td>
                    </tr>
                <% } %>
                <% } %>
                </tbody>
            </table>

            <!-- 글 작성 버튼 -->
            <!-- 공지사항은 관리자만 작성 가능하므로 -->
            <% if(loginUser != null && loginUser.getMemEmail().equals("admin@insertcoin.com")) { %>
                <div align="left" class="write_button">
                    <a class="btn btn-sm btn-secondary" href="<%= contextPath %>/enrollForm.no">글 작성</a>
                </div>
            <% } %>
            
            <br>
            
            <!-- 검색 -->
            <form action="<%= contextPath %>/list.no?currentPage=1" method="get">
                <table align="center" class="search">
                    <tr>
                        <td>
                            <select class="form-control notice_search" name="searchCategory">
                                <option value="">전체</option>
                                <option value="searchAll">제목+내용</option>
                                <option value="searchTitle">제목</option>
                                <option value="searchContent">내용</option>
                            </select>
                        </td>
                        <td><input type="text" name="searchWord" class="notice_search form-control" placeholder="검색어를 입력하세요"></td>
                        <td><button type="submit" class="btn btn btn-secondary btn_search">검색</button></td>
                        <input type="hidden" name="currentPage" value="1">
                    </tr>
                </table>
            </form>
            <br>

            <!-- 페이징바 -->
            <div align="center" class="paging-area">
                
                <% if(currentPage != 1) { %>
                    <button class="btn btn-warning btn-sm" onclick="location.href='list.no?currentPage=<%= currentPage -1 %>';">&lt;</button>
                <% } %>
                
                <% for(int p = startPage; p <= endPage; p++) { %>
                    <% if(p != currentPage) { %>
                        <button class="btn btn-secondary btn-sm" onclick="location.href='list.no?currentPage=<%= p %>';"><%= p %></button>
                    <% } else { %>
                        <!-- 현재 내가 보고있는 페이지일 경우 버튼이 클릭되지 않도록 막아두기 -->  
                        <button disabled><%= p %></button>              		
                    <% } %>
                <% } %>
                
                <% if(currentPage != maxPage) { %>
                        <button class="btn btn-warning btn-sm" onclick="location.href='list.no?currentPage=<%= currentPage + 1 %>';">&gt;</button>
                <% } %>
            </div>        
        </div>
        
        <!-- 제목 눌렀을 때 상세보기 페이지로 넘기기 -->
        <script>
        
            $(function() {
                
                $(".notice_line").click(function() {
                
                    location.href = "detail.no?nno=" + $(this).children().eq(0).text();
                });
            });
        
        </script>


	<%@ include file = "/views/common/footer.jsp" %>

    </div>

</body>
</html>
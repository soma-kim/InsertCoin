<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.insertcoin.board.model.vo.Board, com.insertcoin.common.model.vo.Attachment" %>
<%
	// 필요한 데이터 먼저 뽑기
	Board b = (Board)request.getAttribute("b");
	// 게시글 번호, 카테고리명, 글 제목, 글 내용, 작성자 아이디, 작성일, 신고 수
	
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 상세 게시글</title>

    <!-- CSS 스타일시트 -->
    <link href="resources/css/boardDetailView.css" rel="stylesheet">

</head>
<body>

    <!-- 전체 영역 -->
    <div class="wrap">
    
		<%@ include file = "../../views/common/header.jsp" %>

        <!-- 컨텐츠 영역 -->
        <div class="content_container">

            <div class="outer">
                <div id="title">자유게시판</div>
                    <!-- 조회할 게시물이 보여질 자리 -->
                    <table align="center" class="list-area">
                        <thead>
                            <tr id="board_title" class="line">
                                <th width="1000">
                                    <div class="board_left">[<%= b.getGenCategory() %>] <%= b.getGenTitle() %></div>
                                </th>
                                <td>
                                    <div class="board_right"><%= b.getGenRegister() %></div>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="line">
                                <td>
                                    <div class="board_left">작성자: <b><%= b.getMemNo() %></b></div>
                                </td>
                                <td>
                                    <div class="board_right">조회수 <%= b.getGenViews() %>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;신고 0</div>
                                </td>
                            </tr>
                            <tr class="line">
                                <td colspan="3">
                                    <div id="board_text">
                                        <%= b.getGenContent() %>
                                    </div>
                                    </td>
                            </tr>
                            <tr class="line">
                            	<th>첨부파일</th>
                            	<td colspan="3">
                            		<% if(at == null) { %>
                            			첨부파일이 없습니다.
                            		<% } else { %>
                            			<a download="<%= at.getAttachmentName() %>" href="<%= contextPath %>/<%= at.getAttachmentPath() + at.getAttachmentRename() %>">
                            				<%= at.getAttachmentName() %>
                            			</a>
                            		<% } %>
                            	</td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div>
										<% if(loginUser != null && loginUser.getMemNickname().equals(b.getMemNo())) { %>
											<button class="table_button">수정</button>
	                                        <button class="table_button">삭제</button>
	                                        <button type="button" id="red_board" class="btn btn-primary" data-toggle="modal" data-target="#myReport">신고</button>
	                                    <% } %>
                                        <button class="table_button" onclick="goToList();">목록</button>
                                        
                                        <!--------------------------------------------- 신고 모달창 ------------------------------------------------>
                                        <!-- The Modal -->
                                        <div class="modal fade" id="myReport">
                                            <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                            
                                                <!-- Modal Header -->
                                                <div class="modal-header">
                                                <h5 class="modal-title">게시글 신고</h5>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                
                                                <!-- Modal body -->
                                                <div class="modal-body">
                                                    <b>신고사유</b><br>
                                                    <input type="checkbox" id="1" value="1"><label for="1">&nbsp;스팸</label><br>
                                                    <input type="checkbox" id="2" value="2"><label for="2">&nbsp;음란성</label><br>
                                                    <input type="checkbox" id="3" value="3"><label for="3">&nbsp;증오심 표현</label><br>
                                                    <input type="checkbox" id="4" value="4"><label for="4">&nbsp;잘못된 정보</label><br>
                                                    <input type="checkbox" id="5" value="5"><label for="5">&nbsp;기타</label><br>
                                                    <input type="text" placeholder="내용을 입력해 주세요" style="width:300px;">
                                                </div>
                                                
                                                <!-- Modal footer -->
                                                <div class="modal-footer">
                                                <button class="modal_button button2" type="submit" data-dismiss="modal" onclick="report();">신청</button>
                                                </div>
                                                
                                            </div>
                                            </div>
                                        </div>
                                        <!--------------------------------------------- 신고 모달창 ------------------------------------------------>

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div id="comment_count">댓글 2개</div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table class="comment_table">
                        <tr>
                            <td rowspan="2" width="5%">
                                <img src="resources/image/profile/profile_default.png" id="profile_photo">
                            </td>
                            <td>
                                <div class="comment_info1">
                                    <div>내닉넴짱짱</div>
                                </div>
                            </td>
                            <td>
                                <div class="comment_info2">
                                    <div>2022.10.23 12:02</div>
                                </div>
                            </td>
                            <td>
                                <button id="comment_info3" class="btn btn-primary" data-toggle="modal" data-target="#myReport">신고</button>
                            </td>
                        </tr>
                        <tr class="line">
                            <td>어디보자... 점점 구색을 갖추는듯하나 아직 고칠 게 많아 보입니다 ^^</td>
                        </tr>
                        <tr>
                            <td  rowspan="2" width="5%">
                                <img src="resources/image/profile/profile_default.png" id="profile_photo">
                            </td>
                            <td>
                                <div class="comment_info1">
                                    <div>하지만만약내닉네임이길어진다면</div>
                                </div>
                            </td>
                            <td>
                                <div class="comment_info2">
                                    <div>2022.10.23 12:28</div>
                                </div>
                            </td>
                            <td>
                                <button id="comment_info3" class="btn btn-primary" data-toggle="modal" data-target="#myReport">신고</button>
                            </td>
                        </tr>
                        <tr class="line">
                            <td colspan="3">제가 볼때는 별로같은데요... 일단 이 작업을 하고 나서 글씨 크기부터 좀 줄여야겠어요 전혀 분간이 안되네요 가독성 구려요 그리고 댓글이 더 길어지면 어떻게 되는지도 봐야겠어요</td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <div class=comment_container> 댓글쓰기 <br>
                                    <textarea class="comment_textarea" placeholder="명예훼손, 개인정보 유출, 분쟁 유발, 허위사실 유포 등의 글은 이용약관에 의해 제재는 물론 법률에 의해 처벌받을 수 있습니다. 건전한 커뮤니티를 위해 자제를 당부드립니다."></textarea>
                                    <button id="comment_submit">등록</button>
                                </div>    
                                
                            </td>
                        </tr>
                    </table>

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
                            <tr class="line">
                                <td>15</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>14</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>13</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>12</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>11</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>10</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>9</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>8</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>7</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>6</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>5</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>4</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>3</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>2</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                            <tr class="line">
                                <td>1</td>
                                <td>게임</td>
                                <td>게시글 제목이다</td>
                                <td>user02</td>
                                <td>10</td>
                                <td>2022-05-01</td>
                            </tr>
                        </tbody>
                    </table>

                    <br>
                    <div class="board_search board_left">
                        <input type="button" value="글쓰기" class="board_button">
                    </div>
        
                    <div class="board_search board-right">
                        <select class="board_search">
                            <option selected>제목+내용</option>
                            <option value="10">제목</option>
                            <option value="20">내용</option>
                            <option value="30">작성자</option>
                            <option value="40">댓글</option>
                            <option value="50">게임명</option>
                        </select>
                        <input type="text">
                        <button class="board_button">검색</button>
                    </div>
            
                    <br><br>
                    <!-- 페이징바 -->
                    <div align="center" class="paging-area">
                    <button>1</button>
                    <button>2</button>
                    <button>3</button>
                    <button>4</button>
                    <button>5</button>
                    <button>6</button>
                    <button>7</button>
                    <button>8</button>
                    <button>9</button>
                    <button>10</button>
                    </div>
                </div>
            
        </div>

    </div>
    
    <script>
    	function goToList() {
    		location.href = "<%=contextPath %>/list.bo?currentPage=1";
    	}
    	
    	function report() {
    		alert("신고가 성공적으로 접수되었습니다.");
    	}
    </script>

</body>

</body>
</html>
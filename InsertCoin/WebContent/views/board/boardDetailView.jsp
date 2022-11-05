<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.insertcoin.board.model.vo.Board, com.insertcoin.common.model.vo.Attachment, java.util.ArrayList, com.insertcoin.board.model.vo.GenComment" %>
<%
	// 필요한 데이터 먼저 뽑기
	Board b = (Board)request.getAttribute("b");
	// 게시글 번호, 카테고리명, 게임명, 작성자 닉네임, 글 제목, 글 내용, 작성일, 조회수
	// GEN_NO(int), GEN_CATEGORY(string), GAME_NAME(string), MEM_NICKNAME(string), GEN_TITLE(string), GEN_CONTENT(string), GEN_REGISTER_DATE(date), GEN_VIEWS(int)
	
	Attachment at = (Attachment)request.getAttribute("at");
	
	ArrayList<GenComment> list = (ArrayList<GenComment>)request.getAttribute("list");
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
                                    <div class="board_left">작성자: <b><%= b.getMemNickname() %></b></div>
                                </td>
                                <td>
                                    <div class="board_right">조회수 <%= b.getGenViews() %></div>
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
										<% if(loginUser != null && loginUser.getMemNo() == b.getMemNo()) { %>
											<button class="table_button" onclick="location.href='<%= contextPath %>/updateForm.bo?genNo=<%= b.getGenNo() %>'">수정</button>
	                                        <button class="table_button" onclick="location.href='<%= contextPath %>/delete.bo?genNo=<%= b.getGenNo() %>'">삭제</button>
	                                    <% } else if(loginUser != null && loginUser.getMemNo() != b.getMemNo()) { %>
        		                            <button type="button" id="red_board" class="btn btn-primary" data-toggle="modal" data-target="#myReport">신고</button>
	                                    <% } %>
	                                    
                                        <button class="table_button" onclick="goToList();">목록</button>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div id="comment_count"></div>
                                </td>
                            </tr>
                           
                        </tbody>
                    </table>
                    <table class="comment_table">

                        <thead>
                        </thead>
                        
                       <tbody>
	                        <% if(loginUser != null) { %>
	                        <!-- 로그인이 되어 있을 경우 -->
	                        <tr>
	                            <td colspan="4">
	                                <div class=comment_container> 댓글쓰기 <br>
	                                    <textarea class="comment_textarea" id="genCommentContent" placeholder="명예훼손, 개인정보 유출, 분쟁 유발, 허위사실 유포 등의 글은 이용약관에 의해 제재는 물론 법률에 의해 처벌받을 수 있습니다. 건전한 커뮤니티를 위해 자제를 당부드립니다."></textarea>
	                                    <button id="comment_submit" onclick="insertGenComment();">등록</button>
	                        		<input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>"> <!-- 신고한 사람 -->
	         						<input type="hidden" name="genNo" value="<%= b.getGenNo() %>"> <!-- 게시글 번호 -->
	         						<input type="hidden" name="reportedMemNo" value="<%= b.getMemNo() %>">
	         						
	                                </div>    
	                                
	                            </td>
	                        </tr>
	                        <% } else { %>
	                        <!-- 로그인이 되어 있지 않다면 -->
	                        <tr>
	                            <td colspan="4">
	                                <div class=comment_container> 댓글쓰기 <br>
	                                    <textarea class="comment_textarea" readonly>로그인 후 이용해 주세요.</textarea>
	                                    <button id="comment_submit" disabled>등록</button>
	                                </div>    
	                                
	                            </td>
	                        </tr>
	                        <% } %>
						</tbody> 
                </table>
                	<form id="report-form" action="<%= contextPath %>/reportBoard.bo" method="post" name="reportForm">
                 <!--------------------------------------------- 신고 모달창 ------------------------------------------------>
					          <!-- The Modal -->
					          <div class="modal fade" id="myReport">
					              <div class="modal-dialog modal-dialog-centered">
					              <div class="modal-content">
					              
					                  <!-- Modal Header -->
					                  <div class="modal-header">
					                  <h5 class="modal-title">게시글 신고</h5>
					                  <button type="button" class="close" data-dismiss="modal">&times;</button>
					                  <input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>"> <!-- 신고한 사람 -->
	         						<input type="hidden" name="genNo" value="<%= b.getGenNo() %>"> <!-- 게시글 번호 -->
	         						<input type="hidden" name="reportedMemNo" value="<%= b.getMemNo() %>">
					                  </div>
					                  
					                  <!-- Modal body -->
					                  <div class="modal-body">
					                      <b>신고사유</b><br>
					                      <select name="reportReason">
					                       <option value="스팸" selected>스팸</option>
					                       <option value="음란성">음란성</option>
					                       <option value="증오심 표현">증오심 표현</option>
					                       <option value="잘못된 정보">잘못된 정보</option>
					                       <option value="기타">기타</option>
					                      </select>
					                      
					                  </div>
					                  
					                  <!-- Modal footer -->
					                  <div class="modal-footer">
					                  <button id="reportSubmitButton" class="modal_button button2" type="submit" data-dismiss="modal">신청</button>
					                  </div>
					                  
					                  <script>
					                  
					                  
						                  $("#reportButton").on("click", function() {
							                    $("#myReport").modal();
							                });
								            
								            $("#reportSubmitButton").on("click", function() {
								            	
								            	document.getElementById("report-form").submit();
								            });
					                  </script>
					                  
					              </div>
					              </div>
					          </div>
					          </form>
          <!--------------------------------------------- 신고 모달창 ------------------------------------------------>
                    
                    <script>
                    
                	function goToList() {
                		location.href = "<%=contextPath %>/list.bo?currentPage=1";
                	}
                    
                    	$(function() {
                    		selectGenCommentList();
                    		
                    		// 1초 간격마다 selectGenCommentList 함수 실행
                    		setInterval(selectGenCommentList, 1000);

                    	});
                    	
                    	function insertGenComment() {
                    		$.ajax({
                    			url : "gcinsert.bo",
                    			data : {content : $("#genCommentContent").val(),
                    					genNo :	<%= b.getGenNo() %>
                    			},
                    			type : "post",
                    			success : function(result) {
                    				
                    				// result에 댓글 작성 성공 시 1, 실패 시 0
                    				if(result > 0) {
                    					// 갱신된 댓글 리스트 조회
                    					selectGenCommentList();
                    					
                    					// textarea 새로고침하지 않아도 초기화 효과
                    					$("#genCommentContent").val("");
                    				} else {
                    					alert("댓글 작성에 실패했습니다.")
                    				}
                    				
                    			},
                    			erroe : function() {
                    				console.log("댓글 작성용 ajax 통신 실패!");
                    			}
                    		});
                    	}
                    	
                    	function selectGenCommentList() {
                    		
                    		$.ajax({
                    			url : "gclist.bo",
                    			data : {genNo : <%= b.getGenNo() %>},
                    			success : function(list) {
                    				
                    				//console.log(list);
                    				//2:{genCommentNo: 15, genCommentContent: '흠', genNo: 41, memNo: '닉네임1', genCommentRegister: '22/11/04 01:18:55'}
                    				
                    				// 최신 댓글이 아래로 가게 하고 싶어서 추가함
                    				list = list.reverse();
                    				
                    				var result1 = "";
                    				for (var i in list) { // i: 0, 1, 2, 3, ..., 마지막 인덱스값
                    					                    					
                    					result1 += "<div class='commentStart'><tr>"
                   							+ 	"<td class='commentWidth'><div class='comment_info1'>" + list[i].memNo + "</div></td>"
                   							+ 	"<td><div class='comment_info2'>" + list[i].genCommentRegister + "</div></td>"
                   							+ 	"<tr><td><div class='comment_info4'>" + list[i].genCommentContent + "</div></td>"
                   							// + 	"<td><button class='comment_info3 btn btn-danger' data-toggle='modal' data-target='#myReport'>신고</button></td></tr>"
                   							+   "<tr><td colspan='4' class='line'></td></tr></div>";
                   							
                    					// 찍히긴 하는데... 1초마다 우루루... NumberFormatException: null
                    					//console.log(list[i].genCommentNo); // 13 찍힘
                    					//console.log(list[i].genNo); // 41 찍힘
                    					//console.log(list[i].memNo); // 닉네임 1 찍힘
                    					
                    					//console.log(loginUser.getMemNo()); // 오류 뜨는디
                    					
                    				}
                    				
                    				// console.log(list[0].genCommentNo);
                    				
                    				$(".comment_table thead").html(result1);
                    				
                    				var result2 = list.length;
                    				
                    				$("#comment_count").text("댓글  " + result2 + "개");
                    				
                    				
                    				
                    			},
                    			error : function() {
                    				console.log("댓글 리스트 조회용 ajax 통신 실패");
                    			}
                    		});
                    		
                    	}
                    	
                    </script>
                    
                </div>
                
        </div>

    </div>
    
    <%@ include file = "../../views/common/footer.jsp" %>

</body>

</html>
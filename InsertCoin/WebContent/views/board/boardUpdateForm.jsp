<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.insertcoin.board.model.vo.Board, com.insertcoin.common.model.vo.Attachment, com.insertcoin.game.model.vo.Game" %>
<% 
	// 필요한 데이터 먼저 뽑기
	ArrayList<Game> list = (ArrayList<Game>)request.getAttribute("list");

	Board b = (Board)request.getAttribute("b");
	
	Attachment at = (Attachment)request.getAttribute("at");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 게시글 수정</title>
    <!-- CSS 스타일시트 -->
    <link href="resources/css/boardUpdateForm.css" rel="stylesheet">
    
     <!-- 텍스트 에디터 -->
    <script src="https://cdn.tiny.cloud/1/f4ak5s88o2ixuuek5wihr4xiq28lytq1x5dhui4azyajwzxa/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script> 
</head>
<body>

	<script>
	    <!-- 에디터  -->
	        tinymce.init({
	          selector: 'textarea',
	          plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect',
	          toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
	          tinycomments_mode: 'embedded',
	          tinycomments_author: 'Author name',
	          mergetags_list: [
	            { value: 'First.Name', title: 'First Name' },
	            { value: 'Email', title: 'Email' },
	          ],
	          toolbar : false,
	          width : 935,
	          height : 198
	        });
	</script>

    <!-- 전체 영역 -->
    <div class="wrap">
    
		<%@ include file = "../../views/common/header.jsp" %>

	        <!-- 컨텐츠 영역 -->
	        <div class="content_container">
	
	            <div class="outer">
	                <div id="title">자유게시판</div>
	                	<form id="update-form" action="<%= contextPath %>/update.bo" method="post" enctype="multipart/form-data" name="updateForm">
		                    <input type="hidden" name="genNo" value="<%= b.getGenNo() %>">
		                    <table align="center" class="table-area">
		                            <tr>
		                                <td colspan="2">
		                                    <!--<input type="text" name="gameName" class="input_text" placeholder=" 게임 태그를 입력해 주세요">   -->
		                                </td>
		                            </tr>
		                            <tr>
		                                <td>
		                                    <select name="genCategory" id="select_category" required>
			                                    <option value="" selected disabled>카테고리 선택</option>
			                                    <option value="잡담">잡담</option>
			                                    <option value="질문">질문</option>
			                                    <option value="공략">공략</option>
		                                    </select>
		                                    
		                                    <script>
		                                    	$(function() {
		                                    		
		                                    		$("#update-form option").each(function() {
		                                    			
		                                    			console.log($(this));
		                                    			
		                                    			/*
		                                    			if($(this).text().equals(<%= b.getGenCategory() %>)) {
		                                    				$(this).attr("selected", true);
		                                    			}
		                                    			*/
		                                    		});
		                                    		
		                                    	});
		                                    </script>
		                                </td>
		                                <td>
		                                    <input id="input_title" type="text" name="genTitle" value="<%= b.getGenTitle() %>" required>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td colspan="2">
		                                    <textarea class="input_text" name="genContent" required><%= b.getGenContent() %></textarea>
		                                </td>
		                            </tr>
		                            <tr>
		                            	<th colspan="2">
			                            	<div id="attachment_content"> &nbsp;첨부파일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                            	<% if(at != null) { %>
			                            			<%= at.getAttachmentName() %>
			                            			
			                            			<!-- 기존의 파일번호와 수정명을 hidden으로 추가적으로 넘기기 => 찾아서 삭제해야 하니까 -->
			                            			<input type="hidden" name="originFileNo" value="<%= at.getAttachmentNo() %>">
			                            			<input type="hidden" name="originFileName" value="<%= at.getAttachmentRename() %>">
			                            			
			                            		<% } %>
			                            	<input type="file" name="reUpfile"></div>
		                            	</th>
		                            </tr>
		                    </table>
		
		                    <br><br>
		                    <div align="center">
		                        <button id="reset_button">취소</button>
		                        <button id="submit_button" onclick="categoryCheck();">수정</button>
		                    </div>
	         		 	</form>
	               </div>
       		 </div>
        
        <%@ include file = "../../views/common/footer.jsp" %>
        
	</div>
	
	<script>
		function categoryCheck() {
			
			var updateForm = document.updateForm;
			
			if(updateForm.genCategory.value=="") {
				alert("카테고리를 선택하셔야 게시글을 등록할 수 있습니다.");
				updateForm.genCategory.focus();
				return false;
			}

	    }
	</script>

</body>
</html>
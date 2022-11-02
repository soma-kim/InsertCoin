<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입_동의</title>
    <!-- CSS 스타일시트 -->
    <link href="resources/css/memberEnrollPolicy.css" rel="stylesheet">
</head>
<body>

    <!-- 전체 영역 -->
    <div class="wrap">
    
		<%@ include file = "../../views/common/header.jsp" %>

		<!-- 컨텐츠 영역 -->
        <div class="content_container">

            <!-- 회원가입 창 -->
            <form id="enroll_form" action="<%= contextPath %>/enrollForm.me" method="post">
 
                <!-- 로고, 개인정보 수집 및 이용 동의 체크, 버튼 영역 -->
                <div class="enroll_content">
                    <!-- 회원가입 창 로고 -->
                    <div style="text-align:center;">
                        <img src="resources/image/common/main-logo.png" alt="insert_coin_logo" id="enroll_logo">
                    </div>
                    <!-- 회원가입 글자 -->
                    <div id="enroll_text"><h3>회원가입</h3><hr></div>

                    <!-- 회원가입 개인정보 동의 폼 -->
                    <table align="center" id="enroll_table">
                        
                        <tr>
                            <th>개인정보 수집 및 이용 동의</th>
                            <td>(필수)</td>
                        </tr>
                        <tr>
                            <th colspan="2">
                                <textarea cols="40" rows="5" style="resize:none;" readonly>
INSERT COIN은 이용자들의 개인정보보호를 매우 중요시하며, 이용자가 회사의 서비스를 이용함과 동시에 온라인상에서 회사에 제공한 개인정보가 보호 받을 수 있도록 최선을 다하고 있습니다. 이에 INSERT COIN은 통신비밀보호법, 전기통신사업법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률 등 정보통신서비스제공자가 준수하여야 할 관련 법규상의 개인정보보호 규정 및 정보통신부가 제정한 개인정보보호지침을 준수하고 있습니다. INSERT COIN은 개인정보 보호정책을 통하여 이용자들이 제공하는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려 드립니다</textarea>
                            </th>
                        </tr>
                        <tr>
                            <th colspan="2">
                                <input type="checkbox" id="agreeCheck" name="agreeCheck" checked><label for="agree">&nbsp;개인정보 수집 및 이용에 동의합니다.</label>
                            </th>
                        </tr>
                    </table>
                    <br>
                    <div>
                     	<input type="button" id="reset_button" class="buttons" value="취소" onclick="goToHome();">
                     	<input type="button" id="submit_button" class="buttons" name="checkButton" onclick="enrollPage();" value="확인">
                    </div>
                       
                </div>
            </form>
        </div>
        
        <%@ include file = "../../views/common/footer.jsp" %>
     </div>
     
     <script>
     	function goToHome() {
     		location.href = "<%= contextPath %>";
     	}
     	
     	// 약관 동의 시 확인 버튼 클릭할 수 있게
     	$(function() {
     		
     		$("#agreeCheck").change (function () {
     			
     			var st = this.checked;
     			
     			if(st) {
     				$("input[name=checkButton]").prop("disabled", false);
     			} else {
     				$("input[name=checkButton]").prop("disabled", true);
     			}
     			
     		});
     		
     	});
     	
     	function enrollPage() {
     		location.href = "<%= contextPath %>/enrollForm.me";
     	}
     </script>

</body>
</html>
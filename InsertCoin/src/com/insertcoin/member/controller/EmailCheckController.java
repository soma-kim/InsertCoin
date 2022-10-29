package com.insertcoin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.member.model.service.MemberService;

/**
 * Servlet implementation class EmailCheckController
 */
@WebServlet("/emailCheck.me")
public class EmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // checkEmail이라는 변수에 든 value값 뽑기
	    String checkEmail = request.getParameter("checkEmail");
	    
	    // 전달값인 밸류를 서비스로 넘겨서 요청 처리 후 결과 받기
        // 응답 데이터는 1개만 넘기기 때문에 JSON 필요 없이 Ajax로 해결 가능
        // 넘겨 주기
	    int count = new MemberService().emailCheck(checkEmail);
	    
	    // 응답 데이터에 한글이 있다면 깨질 수도 있으니 문서 타입과 인코딩 방식 알려 주기
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // DB까지 다녀온 count에 중복 이메일 값이 있다면 1, 없다면 0이 담겨 있을 것 
	    if(count > 0) {
	        response.getWriter().print("impossible");
	    } else {
	        response.getWriter().print("possible");
	    }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

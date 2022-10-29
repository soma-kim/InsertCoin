package com.insertcoin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.insertcoin.member.model.service.MemberService;
import com.insertcoin.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/enroll.me")
public class MemberEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // 인코딩 변경 -> 회원가입 정보는 노출되면 안 되므로 post방식
	    request.setCharacterEncoding("UTF-8");
	    
	    // 뽑아야 할 값
	    // memEmail: 이메일 필수 입력 // MEM_EMAIL VARCHAR2(30) NOT NULL UNIQUE,
	    String memEmail = request.getParameter("memEmail");
	    // memPwd: 비밀번호 필수 입력 // MEM_PASSWORD VARCHAR2(20) NOT NULL,  
	    String memPwd = request.getParameter("memPwd");
	    // memNickname: 닉네임 필수 입력// MEM_NICKNAME VARCHAR2(30) NOT NULL UNIQUE,
	    String memNickname = request.getParameter("memNickname");
	   
	    Member m = new Member(memEmail, memPwd, memNickname);
	    // 뽑을 값만 멤버 변수에 담았는데 생성자가 없어서 추가해 줌!
	    
	    // 전달값을 Service에 전달하면서 요청 처리
	    int result = new MemberService().insertMember(m);
	    
	    if(result > 0) {
	        
	        HttpSession session = request.getSession();
	        session.setAttribute("alertMsg", "회원가입 성공! " + memNickname + "님, 동전 준비하세요!");
	        
	        // 회원가입 성공했으니 다시 메인 페이지로
	        response.sendRedirect(request.getContextPath());
	        
	    } else {
	        
	        // 에러 페이지
	        request.setAttribute("errorMsg", "회원가입에 실패했습니다. 다시 시도해 주세요.");  
	        request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
	      
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

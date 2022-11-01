package com.insertcoin.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.insertcoin.member.model.service.MemberService;
import com.insertcoin.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // 로그인 정보는 POST 방식으로 처리해야 함 => 인코딩 처리
	    request.setCharacterEncoding("UTF-8");
	    
	    // 요청 시 전달값을 꺼내기
	    // memEmail: 아이디 값(이메일)
	    String memEmail = request.getParameter("memEmail");
	    // memPwd: 비밀번호 값
	    String memPwd = request.getParameter("memPwd");
	    // memNickname: 닉네임값 (성공 메시지 표출 때 보여 주고자 함)
	    String memNickname = request.getParameter("memNickname");
	    // 이메일 저장
	    String saveEmail = request.getParameter("saveEmail"); // 체크를 했다면 "y", 하지 않았다면 null 값이 넘어옴
        
        if(saveEmail != null && saveEmail.equals("y")) {
            
            // 아이디를 저장하겠다
            // => saveEmail라는 키값으로 넘겨받았던 아이디값을 쿠키로 저장
            
            Cookie cookie = new Cookie("saveEmail", memEmail); // "saveId" - "user01"
            
            //                     1분          ==        60초
            //        1시간 ==      60분          ==      60 * 60초
            // 하루 == 24시간 == 1 * 24 * 60분 == 1 * 24 * 60 * 60초
            // 이틀 == 48시간 == 2 * 24 * 60분 == 2 * 24 * 60 * 60초
            cookie.setMaxAge(1 * 24 * 60 * 60); // 만료기간 1일 (초단위 작성)
            
            // 쿠키를 브라우저로 넘기기 => 응답 정보에 첨부함 (response 객체 사용)
            response.addCookie(cookie);
            
        } else {
            
            // 아이디를 저장하지 않겠다
            // => 아이디를 저장하고 있었던 쿠키 자체를 삭제
            // (쿠키를 삭제시키지 않으면 만료일까지 계속 살아 있기 때문)
            
            Cookie cookie = new Cookie("saveEmail", memEmail);
            cookie.setMaxAge(0); // 0초
            response.addCookie(cookie);
            
        }
	    
	    // 요청 시 전달값들을 VO 객체로 가공
	    Member m = new Member();
	    m.setMemEmail(memEmail);
	    m.setMemPwd(memPwd);
	    
	    // 가공한 VO 객체를 해당 요청을 처리하는 서비스 클래스의 메소드로 넘기기
	    Member loginUser = new MemberService().loginMember(m);
	    
	    // 처리된 결과 응답 뷰 지정
	    if(loginUser == null) { // 로그인 실패 => 에러 문구를 담아서 에러 페이지로 응답하기
	        
	        request.setAttribute("errorMsg", "로그인에 실패했습니다.");
	        RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
	        view.forward(request, response);
	       
	    } else { // 로그인 성공 => 응답 페이지에 loginUser 데이터 전달, 메인 페이지로 응답
	        
	        HttpSession session = request.getSession();
	        session.setAttribute("loginUser", loginUser);
	        
	        session.setAttribute("alertMsg", loginUser.getMemNickname() + "님! 오늘도 즐거운 게임 하세요!");
	        response.sendRedirect(request.getContextPath());
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

package com.insertcoin.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.board.model.service.BoardService;

/**
 * Servlet implementation class GenCommentReportController
 */
@WebServlet("/gcReport.bo")
public class GenCommentReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCommentReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int gcNo = Integer.parseInt(request.getParameter("gcNo")); // 댓글 번호
		int memNo = Integer.parseInt(request.getParameter("memNo")); // 로그인 한 사람
		String reportReason = request.getParameter("reportReason"); // 신고 내용
		int reportedMemNo = Integer.parseInt(request.getParameter("reportedMemNo")); // 게시글 쓴 사람
		int genNo = Integer.parseInt(request.getParameter("genNo")); // 게시글 번호
		
		//System.out.println("댓글 번호: " + gcNo + ", 신고한(로그인한) 사람: " + memNo + ", 신고 내용: " + reportReason + ", 게시글 쓴 사람: " + reportedMemNo);
		
		int result = new BoardService().reportGenCommentCount(gcNo, memNo, reportReason, reportedMemNo);
		
		if(result > 0) {
			
			request.getSession().setAttribute("alertMsg", "신고 접수되었습니다. 확인 후 조치하겠습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.bo?genNo=" + genNo);
			
		} else {
			
			request.getSession().setAttribute("alertMsg", "게시글 신고 실패하였습니다. 관리자에게 문의해 주세요.");
			response.sendRedirect(request.getContextPath() + "/detail.bo?genNo=" + genNo);
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

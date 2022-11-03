package com.insertcoin.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.board.model.service.BoardService;

/**
 * Servlet implementation class GenCommentDeleteController
 */
@WebServlet("/gcDelete.bo")
public class GenCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCommentDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 보낼 값은 댓글 번호 1개이므로 가공 필요 없음
		int gcNo = Integer.parseInt(request.getParameter("gcNo"));
		
		System.out.println("gcNo: " + gcNo);
		
		// 댓글 삭제 후 게시글을 다시 띄워 주기 위해 url에 쓰려고 가지고 온 게시글 번호
		int genNo = Integer.parseInt(request.getParameter("genNo"));
		
		System.out.println("genNo: " + genNo);
		
		int result = new BoardService().deleteComment(gcNo);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "댓글이 성공적으로 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.bo?genNo=" + genNo);
		} else {
			request.setAttribute("errorMsg", "댓글 삭제 실패");
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

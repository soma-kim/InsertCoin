package com.insertcoin.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.board.model.service.BoardService;
import com.insertcoin.board.model.vo.GenComment;
import com.insertcoin.member.model.vo.Member;

/**
 * Servlet implementation class GenCommentInsertController
 */
@WebServlet("/gcinsert.bo")
public class GenCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCommentInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 시 전달값 뽑기
		// content: 댓글 내용
		String genCommentContent = request.getParameter("content");
		// genNo: 참조 게시글 번호
		int genNo = Integer.parseInt(request.getParameter("genNo"));
		
		// 추가적으로 필요한 데이터: 댓글 작성자(지금 로그인한 회원)의 회원 번호
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		// GenComment 타입으로 가공
		GenComment gc = new GenComment();
		gc.setGenCommentContent(genCommentContent);
		gc.setGenNo(genNo);
		gc.setMemNo(String.valueOf(memNo));
		
		int result = new BoardService().insertGenComment(gc); // 댓글 작성 성공 시 1, 실패 시 0
		
		// 응답 데이터 보내기
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

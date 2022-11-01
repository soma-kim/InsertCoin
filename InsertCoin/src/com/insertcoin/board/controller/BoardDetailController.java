package com.insertcoin.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.board.model.service.BoardService;
import com.insertcoin.board.model.vo.Board;
import com.insertcoin.common.model.vo.Attachment;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 해당 게시글 먼저 뽑기
		int genNo = Integer.parseInt(request.getParameter("genNo"));
		
		// 조회수 증가 / 게시글 조회(Board) / 첨부파일 조회(Attachment)
		// => 서비스로 요청 3번을 보내야 함
		BoardService bService = new BoardService();
		
		// 조회 수 증가 요청
		int result = bService.increaseCount(genNo);
		
		if(result > 0) { // 조회수 증가에 성공
			
			// 게시글 조회, 첨부파일 조회
			Board b = bService.selectBoard(genNo);
			Attachment at = bService.selectAttachment(genNo);
			
			// 게시글 정보 보내기
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			// 게시글 상세 조회 페이지로 포워딩
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
			
		} else { // 조회수 증가에 실패했다면
			
			// 에러 문구 담아서 에러 페이지로 포워딩
			// 키값 오타 시 null
			request.setAttribute("errorMsg", "게시글 상세 조회 실패");
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

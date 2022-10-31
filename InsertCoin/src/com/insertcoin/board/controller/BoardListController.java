package com.insertcoin.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.board.model.service.BoardService;
import com.insertcoin.board.model.vo.Board;
import com.insertcoin.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징 처리
		int listCount; 	 // 현재 총 게시글 개수
		int currentPage; // 현재 요청한 페이지(= 사용자 요청 페이지)
		int pageLimit; 	 // 10개 == 페이지 하단에 보여질 페이징 바의 페이지 최대 개수
		int boardLimit;  // 10개 == 한 페이지에 보여질 게시글 최대 개수
		
		int maxPage; 	 // 가장 마지막 페이지가 몇 번 페이지인지
		int startPage;   // 페이지 하단에 보여질 페이징바의 시작 수(1, 11, ...)
		int endPage;     // 페이지 하단에 보여질 페이징바의 끝 수(10, 20, ...)
		
		// 총 게시글 개수
		listCount = new BoardService().selectListCount();
		
		// 현재 페이지
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// 하단에 보여질 페이징 바와 한 페이지의 게시글의 최대 개수
		pageLimit = 10;
		boardLimit = 10;
		
		// 가장 마지막 페이지는 몇 번 페이지?
		// => (게시글 / 10)에 대한 값을 무조건 올림
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// 페이지 하단에 보일 페이징바의 시작수 (1, 11, ...)
		startPage = (currentPage - 1) / pageLimit * pageLimit +1;
		
		// 페이지 하단에 보여질 페이징바의 끝수 (10, 20, ...)
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		} // 만약 게시글의 총합이 10 단위로 떨어지지 않는 수라면, maxPage를 페이징바의 끝수로
		
		// pageInfo 객체로 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit
								 , maxPage, startPage, endPage);
		
		// pi를 서비스로 넘기기
		// list에는 해당 페이지에서 보여져야 할 게시글의 목록들
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

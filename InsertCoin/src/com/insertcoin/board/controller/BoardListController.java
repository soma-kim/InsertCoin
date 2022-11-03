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
import com.insertcoin.cs.model.service.NoticeService;

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
		
		String searchCategory = request.getParameter("searchCategory");
		String searchWord = request.getParameter("searchWord");
		
		// 페이징 처리
		int listCount; 	 // 현재 총 게시글 개수
		int currentPage; // 현재 요청한 페이지(= 사용자 요청 페이지)
		int pageLimit; 	 // 10개 == 페이지 하단에 보여질 페이징 바의 페이지 최대 개수
		int boardLimit;  // 10개 == 한 페이지에 보여질 게시글 최대 개수
		
		int maxPage; 	 // 가장 마지막 페이지가 몇 번 페이지인지
		int startPage;   // 페이지 하단에 보여질 페이징바의 시작 수(1, 11, ...)
		int endPage;     // 페이지 하단에 보여질 페이징바의 끝 수(10, 20, ...)
		
		// listcount : 총 게시글 개수
				// 검색 카테고리 선택, 게시글 개수 조회용 
				if(searchCategory != null && searchCategory.equals("searchAll")) { // 제목+내용 으로 검색할 때 
					listCount = new BoardService().selectListCountAll(searchWord);
				} else if(searchCategory != null && searchCategory.equals("searchTitle")) { // 제목으로 검색할 때 
					listCount = new BoardService().selectListCountTitle(searchWord);
				} else if(searchCategory != null && searchCategory.equals("searchContent")) { // 내용으로 검색할 때 
					listCount = new BoardService().selectListCountContent(searchWord);
				} else if(searchCategory != null && searchCategory.equals("searchWriter")) { // 작성자로 검색할 때 
					listCount = new BoardService().selectListCountWriter(searchWord);
				} else if(searchCategory != null && searchCategory.equals("searchComment")) { // 댓글로 검색할 때 
					listCount = new BoardService().selectListCountComment(searchWord);
				} else { // 전체 조회 기본 페이지 
					listCount = new BoardService().selectListCount();
				}
		
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
		ArrayList<Board> list = new BoardService().selectList(pi, searchCategory, searchWord);
		
		// 응답페이지에서 필요로 하는 데이터를 request 영역에 담기 
		
		// 게시글 조회용 
		if(searchCategory != null && searchWord != null) {
			list = new BoardService().selectList(pi, searchCategory, searchWord);
		} else {
			list = new BoardService().selectList(pi);
		}
		
		if(list != null) {
			request.setAttribute("searchCategory", searchCategory);
			request.setAttribute("searchWord", searchWord);
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			
			// 공지사항 리스트 페이지를 포워딩 
			request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
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

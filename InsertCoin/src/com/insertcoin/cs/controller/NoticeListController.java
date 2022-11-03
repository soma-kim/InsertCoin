package com.insertcoin.cs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insertcoin.common.model.vo.PageInfo;
import com.insertcoin.cs.model.service.NoticeService;
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.cs.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchCategory = request.getParameter("searchCategory");
		String searchWord = request.getParameter("searchWord");
		System.out.println(searchCategory);
		System.out.println(searchWord);
		

		// -------------------- 페이징 처리 -------------------------
		// 총 7개의 변수 필요 => 기본적으로 알아내야하는 변수 4개, 계산해서 구해야하는 변수 3개 
		int listCount; // 현재 총 게시글 개수 
		int currentPage; // 현재 요청한 페이지 (즉, 사용자가 요청한 페이지 수)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수 
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 (몇 개 단위의 리스트가 보여질건지)
		
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지 (총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수 
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수 
		
		// listcount : 총 게시글 개수
		// 검색 카테고리 선택, 게시글 개수 조회용 
		if(searchCategory != null && searchCategory.equals("searchAll")) { // 제목+내용 으로 검색할 때 
			listCount = new NoticeService().selectListCountAll(searchWord);
		} else if(searchCategory != null && searchCategory.equals("searchTitle")) { // 제목 으로 검색할 때 
			listCount = new NoticeService().selectListCountTitle(searchWord);
		} else if(searchCategory != null && searchCategory.equals("searchContent")) { // 내용 으로 검색할 때 
			listCount = new NoticeService().selectListCountContent(searchWord);
			
		} else { // 전체 조회 기본 페이지 
			listCount = new NoticeService().selectListCount();
		}
		
		
		// currentPage : 현재 페이지(즉, 사용자가 요청한 페이지)
		// => 쿼리스트링으로 대놓고 넘김! 
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 개수 
		// 			   (페이지 목록들이 몇 개 단위씩 보여질건지) 
		pageLimit = 10;
		
		// boardLimit : 한 페이지에 보여질 게시글의 최대 개수 
		// 				(게시글이 몇 개 단위씩 보여질건지) 
		boardLimit = 10;
		
		// maxPage : 가장 마지막 페이지가 몇 번 페이지인지 (총 페이지 수) 
		maxPage = (int)Math.ceil((double)listCount /boardLimit);
		
		// startPage :  페이지 하단에 보일 페이징바의 시작 수 
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// endPage : 페이지 하단에 보여질 페이징바의 끝 수 
		endPage = startPage + pageLimit - 1;
		
		// 이 때, maxPage 가 13까지밖에 안된다면? 
		// => endPage 를 maxPage 로 변경 
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징 정보들 (7개의 변수)을 하나의 공간에 담아서 보내기 
		// => 페이징 정보들을 담을 VO 클래스를 하나 더 만들것! 
		//    (여러 게시판에서도 쓰일거니까 common 패키지에 만들기)
		
		// 1. PageInfo 객체로 가공 (조회할 때, 페이징바 만들 때 필요)
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// list 에는 해당 페이지에서 보여져야 할 게시글들의 목록들 (목록 만들 때 필요)
		ArrayList<Notice> list = new NoticeService().selectNoticeList(pi, searchCategory, searchWord);
//		request.setAttribute("pi", pi);
		
		// 응답페이지에서 필요로 하는 데이터를 request 영역에 담기 
		
		// 게시글 조회용 
//		list = new NoticeService().selectNoticeList(pi, searchCategory, searchWord);
		if(searchCategory != null && searchWord != null) {
			list = new NoticeService().selectNoticeList(pi, searchCategory, searchWord);
		} else {
			list = new NoticeService().selectNoticeList(pi);
		}
		
		if(list != null) {
			request.setAttribute("searchCategory", searchCategory);
			request.setAttribute("searchWord", searchWord);
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			
			// 공지사항 리스트 페이지를 포워딩 
			request.getRequestDispatcher("views/cs/noticeListView.jsp").forward(request, response);
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

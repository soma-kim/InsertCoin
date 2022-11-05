package com.insertcoin.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.insertcoin.board.model.service.BoardService;
import com.insertcoin.board.model.vo.Board;
import com.insertcoin.common.MyFileRenamePolicy;
import com.insertcoin.common.model.vo.Attachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 일반 게시글 추가 기능
		// 1. Board 테이블에 insert
		// 2. 만약 첨부파일이 있다면 Attachment 테이블도 insert
		
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// memNo: 작성자 번호
		// genCategory: 카테고리 번호(현재 String으로 받아 왔음)
		// gameNo: 게임 번호
		// genTitle: 게시글 제목
		// genContent: 게시글 내용
		// upfile: 첨부파일
		
		// multipart/form-data 형식인지 검사
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 전송된 파일을 처리할 작업 내용
			// 용량 제한
			int maxSize = 10 * 1024 * 1024;
			
			// 전달된 파일을 저장할 서버의 실 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/image/board/gen/post/");
			
			// 전달된 파일명 수정 및 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// DB에 기록할 데이터를 뽑아서 VO 객체에 담기
			//String memNickname = multiRequest.getParameter("memNickname"); // insert문에는 조인 못 해서 memNo로 닉네임 가지고 옴
			int memNo = Integer.parseInt(multiRequest.getParameter("memNo"));
			String genCategory = multiRequest.getParameter("genCategory");
			String genTitle = multiRequest.getParameter("genTitle");
			String genContent = multiRequest.getParameter("genContent");
			
			Board b = new Board();
			b.setMemNo(memNo);
			b.setGenCategory(genCategory);
			//b.setMemNickname(memNickname);
			b.setGenTitle(genTitle);
			b.setGenContent(genContent);
			
			// 만약 첨부파일이 있다면 첨부파일에 대한 정보도 뽑아서 VO 객체에 담기
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new Attachment();
				at.setAttachmentName(multiRequest.getOriginalFileName("upfile")); // 원본명
				at.setAttachmentRename(multiRequest.getFilesystemName("upfile")); // 수정명, 실제 서버에 업로드된 파일명
				at.setAttachmentPath("resources/image/board/gen/post/");
				
			}
			
			// 이 시점 기준으로 첨부파일이 없다면 at == null
			// 서비스 요청
			int result = new BoardService().insertBoard(b, at);
			
			// 결과에 따른 응답 페이지 지정
			if(result > 0) { // 성공 /jsp/list.bo?currentPage=1 요청 (가장 최신글)
				
				request.getSession().setAttribute("alertMsg", "게시글이 성공적으로 작성되었습니다.");
				response.sendRedirect(request.getContextPath() + "/list.bo?currentPage=1");

			} else { // 실패
				
				// 첨부파일이 있었을 경우 이미 업로드된 첨부파일을 굳이 서버에 보관할 필요가 없음
				if(at != null) {
					
					new File(savePath + at.getAttachmentRename()).delete();
					 
				}
				
				request.setAttribute("errorMsg", "게시글 작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
						
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

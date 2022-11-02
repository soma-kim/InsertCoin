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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) { // 만약 multipart/form-data 형식으로 요청 들어왔다면
			
			int maxSize = 10 * 1024 * 1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/image/board/gen/post/");
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			int genNo = Integer.parseInt(multiRequest.getParameter("genNo"));
			String genCategory = multiRequest.getParameter("genCategory");
			String gameNo = multiRequest.getParameter("gameNo");
			String genTitle = multiRequest.getParameter("genTitle");
			String genContent = multiRequest.getParameter("genContent");
			
			Board b = new Board();
			b.setGenNo(genNo);
			b.setGenCategory(genCategory);
			b.setGameNo(gameNo);
			b.setGenTitle(genTitle);
			b.setGenContent(genContent);
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("reUpfile") != null) { // 새로 첨부한 파일이 있다면
				at = new Attachment();
				at.setAttachmentName(multiRequest.getOriginalFileName("reUpfile"));
				at.setAttachmentRename(multiRequest.getFilesystemName("reUpfile"));
				
				at.setAttachmentPath("resources/image/board/gen/post/");
				
				// 기존 파일이 있을 경우
				if(multiRequest.getParameter("originFileNo") != null) {
					
					at.setAttachmentNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
				} else {
					
					at.setGenNo(genNo);
					
				}

			}
			
			System.out.println(at);
			
			int result = new BoardService().updateBoard(b, at);
			
			if(result > 0) {
				
				// 만약 기존 첨부파일이 있고 새로운 첨부파일도 있을 경우 서버에 있던 기존 첨부파일을 삭제
				if(multiRequest.getParameter("originFileName") != null && multiRequest.getOriginalFileName("reUpfile") != null) {
					
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
					
				}
				
				request.getSession().setAttribute("alertMsg", "게시글이 성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath() + "/detail.bo?genNo=" + genNo); // URL 요청
					
			} else { // 수정 실패
				
				request.setAttribute("errorMsg", "게시글 수정 실패");
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

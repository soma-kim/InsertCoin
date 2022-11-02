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
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.game.model.vo.Game;

/**
 * Servlet implementation class BoardUpdateFormController
 */
@WebServlet("/updateForm.bo")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게임 정보들 전체 조회, 해당 게시글 정보 조회, 게시글에 딸린 첨부파일 정보 조회
		BoardService bService = new BoardService();
		
		int genNo = Integer.parseInt(request.getParameter("genNo"));
		
		ArrayList<Game> list = bService.selectGameList();
		
		Board b = bService.selectBoard(genNo);
		
		Attachment at = bService.selectAttachment(genNo);
		
		request.setAttribute("list", list);
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

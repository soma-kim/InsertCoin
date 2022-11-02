package com.insertcoin.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.insertcoin.board.model.service.BoardService;
import com.insertcoin.board.model.vo.GenComment;

/**
 * Servlet implementation class GenCommentListController
 */
@WebServlet("/gclist.bo")
public class GenCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCommentListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게시글 번호 뽑기
		int genNo = Integer.parseInt(request.getParameter("genNo"));
		
		// 게시글 번호를 서비스를 전달하면서 요청 처리 후 결과 받기
		ArrayList<GenComment> list = new BoardService().selectGenCommentList(genNo);
		
		// GSON을 이용해서 응답 => ArrayList를 자바 스크립트 배열 형태로 변환
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter()); // 응답할 데이터, 통로 객체를 차례로 넘김
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

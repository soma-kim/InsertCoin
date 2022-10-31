package com.insertcoin.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.insertcoin.board.model.dao.BoardDao;
import com.insertcoin.board.model.vo.Board;
import com.insertcoin.common.model.vo.PageInfo;
import static com.insertcoin.common.JDBCTemplate.*;

public class BoardService {
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
		
	}

}

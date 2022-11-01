package com.insertcoin.board.model.service;

import static com.insertcoin.common.JDBCTemplate.close;
import static com.insertcoin.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.insertcoin.board.model.dao.BoardDao;
import com.insertcoin.board.model.vo.Board;
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.common.model.vo.PageInfo;
import com.insertcoin.game.model.vo.Game;
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
	
	public ArrayList<Game> selectGameList() {
		
		Connection conn = getConnection();
		ArrayList<Game> list = new BoardDao().selectGameList(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		// Dao의 메소드 1개 = 쿼리문 1개
		// 첨부파일이 있든 없든 간에 무조건 일어나야 하는 BOARD 테이블 INSERT 요청 먼저
		int result1 = new BoardDao().insertBoard(conn, b); // 성공 1, 실패 0
		
		// 한 개의 트랜잭션에 테이블의 변동이 있는 DML이 두 번 실행
		// 두 번의 DML 중 하나라도 실패한다면 전부 rollback / 두 번 모두 성공해야 commit
		
		// 만약 첨부파일이 있다면 Attachment 테이블에 insert 요청 보내기
		// 두 번째 요청에 대한 결과값을 담을 수 있는 변수 세팅
		// 기본값 0으로 설정하면 첨부파일 없을 때는 무조건 실패이니 기본값 1
		int result2 = 1;
		
		if(at != null) { // 첨부파일이 있다면
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		// 트랜잭션 처리는 result1 > 0 %% result2 > 0 일 때만 커밋
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		// 하나라도 실패해서 0이 나오면 실패여야 하기 때문에 곱셈 결과로 보냄 
		return result1 * result2;

	}
	
	// 조회수 증가용 서비스
	public int increaseCount(int genNo) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount()
		
	}

	

}
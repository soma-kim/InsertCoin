package com.insertcoin.board.model.service;

import static com.insertcoin.common.JDBCTemplate.close;
import static com.insertcoin.common.JDBCTemplate.commit;
import static com.insertcoin.common.JDBCTemplate.getConnection;
import static com.insertcoin.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.insertcoin.board.model.dao.BoardDao;
import com.insertcoin.board.model.vo.Board;
import com.insertcoin.board.model.vo.GenComment;
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.common.model.vo.PageInfo;
import com.insertcoin.cs.model.dao.NoticeDao;
import com.insertcoin.cs.model.vo.Notice;
import com.insertcoin.game.model.vo.Game;

public class BoardService {
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return listCount;
	}
	
	// 제목+내용 검색결과 개수 조회용 
	public int selectListCountAll(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCountAll(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 제목 검색결과 개수 조회용 
	public int selectListCountTitle(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCountTitle(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 내용 검색결과 개수 조회용 
	public int selectListCountContent(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCountContent(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 작성자 검색결과 개수 조회용 
	public int selectListCountWriter(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCountWriter(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 댓글 검색결과 개수 조회용 
	public int selectListCountComment(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCountComment(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 게시판 전체 조회용
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
		
	}
	
	// 게시판 검색 조회용 
	public ArrayList<Board> selectList(PageInfo pi, String searchCategory, String searchWord) {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi, searchCategory, searchWord);
		
		// 트랜잭션 처리 패스 
		
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
		
		int result = new BoardDao().increaseCount(conn, genNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// Board 정보를 가지고 올 서비스
	public Board selectBoard(int genNo) {
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoard(conn, genNo);
		
		close(conn);
		
		return b;
	}
	
	// Attachment 정보를 가지고 올 서비스
	public Attachment selectAttachment(int genNo) {
		Connection conn = getConnection();
		Attachment at = new BoardDao().selectAttachment(conn, genNo);
		
		close(conn);
		return at;
	}
	
	// 게시글 수정 서비스
	public int updateBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		// 3가지 경우 모두 공통적으로 실행해야 하는 GEN_BOARD UPDATE 구문
		int result1 = new BoardDao().updateBoard(conn, b);
		
		// 0으로 설정 시 기존 첨부파일 없다면 무조건 false이기 때문에 기본값을 1로 세팅 
		int result2 = 1;
		
		if(at != null) { // 새롭게 첨부된 파일이 있는 경우
			
			if(at.getAttachmentNo() != 0) { // 기존 첨부파일도 있다면

				// Attachment 테이블에 update
				result2 = new BoardDao().updateAttachment(conn, at);
				
			} else { // 기존 첨부파일은 없다면
				
				// Attachment 테이블에 insert
				result2 = new BoardDao().insertNewAttachment(conn, at); 
			}
			
		}
		
		// 모든 요청이 다 성공했을 경우에만 commit
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	// 게시글 삭제용 서비스
	public int deleteBoard(int genNo) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, genNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
		
	}
	
	public ArrayList<GenComment> selectGenCommentList(int genNo) {
		
		Connection conn = getConnection();
		ArrayList<GenComment> list = new BoardDao().selectGenCommentList(conn, genNo);
		
		close(conn);
		
		return list;
		
	}
	
	// 댓글 작성용
	public int insertGenComment(GenComment gc) {
		Connection conn = getConnection();
		int result = new BoardDao().insertGenComment(conn, gc);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	// 댓글 삭제용
	public int deleteComment(int gcNo) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteComment(conn, gcNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
	
	// 게시글 신고용
	public int reportGenBoardCount(int genNo, int memNo, String reportReason, int reportedMemNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().reportGenBoardCount(conn, genNo, memNo, reportReason, reportedMemNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
	
	
	// 댓글 신고용
	public int reportGenCommentCount(int gcNo, int memNo, String reportReason, int reportedMemNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().reportGenCommentCount(conn, gcNo, memNo, reportReason, reportedMemNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
	
	

}
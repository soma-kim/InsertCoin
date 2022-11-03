package com.insertcoin.cs.model.service;

import static com.insertcoin.common.JDBCTemplate.close;
import static com.insertcoin.common.JDBCTemplate.commit;
import static com.insertcoin.common.JDBCTemplate.getConnection;
import static com.insertcoin.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.insertcoin.common.model.vo.PageInfo;
import com.insertcoin.cs.model.dao.NoticeDao;
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.cs.model.vo.Notice;

public class NoticeService {
	
	// 총 게시글 개수 조회용 
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	// 제목+내용 검색결과 개수 조회용 
	public int selectListCountAll(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCountAll(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 제목 검색결과 개수 조회용 
	public int selectListCountTitle(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCountTitle(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 내용 검색결과 개수 조회용 
	public int selectListCountContent(String searchWord) {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCountContent(conn, searchWord);
		
		close(conn);
		
		return listCount;
	}
	
	// 공지사항 전체 조회용 
	public ArrayList<Notice> selectNoticeList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, pi);
		
		// 트랜잭션 처리 패스 
		
		close(conn);
		
		return list;
	}
	
	// 공지사항 검색 조회용 
	public ArrayList<Notice> selectNoticeList(PageInfo pi, String searchCategory, String searchWord) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, pi, searchCategory, searchWord);
		
		// 트랜잭션 처리 패스 
		
		close(conn);
		
		return list;
	}
	
	
	// 공지사항 작성용 
	public int insertNotice(Notice n, Attachment at) {
		
		Connection conn = getConnection();
		
		// 주의사항 => DAO 의 메소드 1개 == 쿼리문 1개 
		
		// NOTICE 테이블 INSERT 요청 먼저 (첨부파일이 있든 없든 간에 무조건 일어나야 함)  
		int result1 = new NoticeDao().insertNotice(conn, n); // 성공 : 1, 실패 : 0 
		
		// 한 개의 트랜잭션에 테이블의 변동이 있는 DML 이 두 번 실행 
		// => 그 두 번의 DML 중 하나라도 실패한다면 전부 롤백처리해야함!
		// => 그 두 번의 DML이 모두 성공해야지만 커밋! 
		
		// 만약 첨부파일이 있다면 ATTACHMENT 테이블에 INSERT 요청 보내기 
		
		// 두 번째 요청에 대한 결과값을 담을 수 있는 변수 먼저 세팅 
		int result2 = 1;
		
		if(at != null) { // 만약 첨부파일이 있다면 
			result2 = new NoticeDao().insertAttachment(conn, at);
		}
		
		// 트랜잭션 처리 
		// result > 0 && result2 > 0 => 둘 다 성공일 경우 
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		
		close(conn);
		
		return result1 * result2;
	}
	
	// 공지사항 조회수 증가용 서비스 
	public int increaseViews(int noticeNo) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseViews(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	// 공지사항 게시글 상세조회용 서비스 
	public Notice selectNotice(int noticeNo) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		
		return n;
	}
	
	// 공지사항 게시글 첨부파일 조회용 서비스 
	public Attachment selectAttachment(int noticeNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new NoticeDao().selectAttachment(conn, noticeNo);
		
		close(conn);
		
		return at;
	}
	
	// 공지사항 수정용 서비스 
	public int updateNotice(Notice n, Attachment at) {
		
		Connection conn = getConnection();
		
		// 두 가지를 모두 공통적으로 실행해야 하는 NOTICE UPDATE 구문 요청 
		int result1 = new NoticeDao().updateNotice(conn, n);
		
		// 두 번째 요청결과를 받을 변수 세팅 
		int result2 = 1;
		
		if(at != null) { // 새롭게 첨부된 파일이 있을 경우 => Attachment 테이블에 Update 또는 Insert
			
			if(at.getAttachmentNo() != 0) { // 기존 첨부파일이 있는 경우 => Attachment UPDATE 요청 
				
				result2 = new NoticeDao().updateAttachment(conn, at);
			
			} else { // 기존 첨부파일이 없는 경우 => Attachment INSERT 요청 
				
				// 기존에 우리가 만들어놨던 insertAttachment 재활용 불가!! (쿼리문이 다르기 때문에)
				result2 = new NoticeDao().insertNewAttachment(conn, at);
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
	
	// 공지사항 삭제용 서비스 
	public int deleteNotice(int noticeNo, int attachmentNo) {
		
		Connection conn = getConnection();
		
		// 두 가지를 모두 공통적으로 실행해야 하는 NOTICE UPDATE 구문 요청 
		int result1 = new NoticeDao().deleteNotice(conn, noticeNo);
		
		// 두 번째 요청결과를 받을 변수 세팅 
		int result2 = 1;
		
		if(attachmentNo != 0) { // 첨부파일이 있을 경우
			
				result2 = new NoticeDao().deleteAttachment(conn, attachmentNo);
	
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
	
}

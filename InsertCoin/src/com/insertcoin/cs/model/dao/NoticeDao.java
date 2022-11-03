package com.insertcoin.cs.model.dao;

import static com.insertcoin.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.insertcoin.common.model.vo.PageInfo;
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.cs.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String fileName = NoticeDao.class.getResource("/sql/cs/notice-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 총 게시글 개수 조회 
	public int selectListCount(Connection conn) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회) 
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("NOTICE_VIEWS");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 제목+내용 검색결과 개수 조회용 
	public int selectListCountAll(Connection conn, String searchWord) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchWord);
			pstmt.setString(2, searchWord);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("NOTICE_VIEWS");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 제목 검색결과 개수 조회용 
	public int selectListCountTitle(Connection conn, String searchWord) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountTitle");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchWord);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("NOTICE_VIEWS");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 내용 검색결과 개수 조회용
	public int selectListCountContent(Connection conn, String searchWord) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountContent");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchWord);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("NOTICE_VIEWS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 공지사항 전체 조회용 
	public ArrayList<Notice> selectNoticeList(Connection conn, PageInfo pi) {
		
		// SELECT 문 => ResultSet 객체 (여러 행 조회)
		
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage()	- 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Notice(rset.getInt("NOTICE_NO"),
								    rset.getString("NOTICE_TITLE"),
								    rset.getDate("NOTICE_REGISTER_DATE"),
								    rset.getInt("NOTICE_VIEWS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} 
		return list;
	}
	
	// 공지사항 검색 조회용
	public ArrayList<Notice> selectNoticeList(Connection conn, PageInfo pi, String searchCategory, String searchWord) {
		
		// SELECT 문 => ResultSet 객체 (여러 행 조회)
		
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
		
		if(searchCategory != null && searchCategory.equals("searchAll")) { // 제목+내용 검색 
			sql = prop.getProperty("selectNoticeListAll");
		} else if(searchCategory != null && searchCategory.equals("searchTitle")) { // 제목 검색 
			sql = prop.getProperty("selectNoticeListTitle");
		} else if(searchCategory != null && searchCategory.equals("searchContent")) { // 내용 검색 
			sql = prop.getProperty("selectNoticeListContent");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage()	- 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			if(searchCategory != null && searchCategory.equals("searchAll")) {
				pstmt.setString(1, searchWord);
				pstmt.setString(2, searchWord);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				
			} else if(searchCategory != null && searchCategory.equals("searchTitle") || (searchCategory != null && searchCategory.equals("searchContent"))) {
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Notice(rset.getInt("NOTICE_NO"),
								    rset.getString("NOTICE_TITLE"),
								    rset.getDate("NOTICE_REGISTER_DATE"),
								    rset.getInt("NOTICE_VIEWS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} 
		return list;
	}
	
	// 공지사항 작성용 
	public int insertNotice(Connection conn, Notice n) {
		
		// INSERT 문 => int (처리된 행의 개수)
		
		int result1 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			
			result1 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result1;
	}
	
	// 공지사항 작성 시 첨부파일 등록 
	public int insertAttachment(Connection conn, Attachment at) {
		
		// INSERT 문 => int (처리된 행의 개수) 
		
		int result2 = 0;
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getAttachmentName());
			pstmt.setString(2, at.getAttachmentRename());
			pstmt.setString(3, at.getAttachmentPath());
			
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result2;
	}
	
	// 공지사항 조회수 증가용 
	public int increaseViews(Connection conn, int noticeNo) {
		
		// UPDATE 문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseViews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 공지사항 게시글 상세조회용 
	public Notice selectNotice(Connection conn, int noticeNo) {
		
		// SELECT 문 => ResultSet객체 (한 행 조회) 
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				n = new Notice(rset.getInt("NOTICE_NO")
						     , rset.getString("NOTICE_TITLE")
						     , rset.getString("NOTICE_CONTENT")
						     , rset.getDate("NOTICE_REGISTER_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}
	
	// 공지사항 게시글 첨부파일 조회용
	public Attachment selectAttachment(Connection conn, int noticeNo) {
		
		// SELECT 문 => ResultSet 객체 (한 행 조회)
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				at = new Attachment();
				at.setAttachmentNo(rset.getInt("ATTACHMENT_NO"));
				at.setAttachmentName(rset.getString("ATTACHMENT_NAME"));
				at.setAttachmentRename(rset.getString("ATTACHMENT_RENAME"));
				at.setAttachmentPath(rset.getString("ATTACHMENT_PATH"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}
	
	// 공지사항 수정용 
	public int updateNotice(Connection conn, Notice n) {
		
		// UPDATE 문 => int (처리된 행의 개수)
		
		int result1 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result1 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result1;
	}
	
	// 공지사항 수정 시 첨부파일 
	public int updateAttachment(Connection conn, Attachment at) {
		
		// UPDATE 문 => int (처리된 행의 개수) 
		
		int result2 = 0;
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getAttachmentName());
			pstmt.setString(2, at.getAttachmentRename());
			pstmt.setInt(3, at.getAttachmentNo());
			
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result2;
	}
	
	// 공지사항 수정 시 새로운 첨부파일 등록  
	public int insertNewAttachment(Connection conn, Attachment at) {
		
		// INSERT 문 => int (처리된 행의 개수)
		
		int result2 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, at.getNoticeNo());
			pstmt.setString(2, at.getAttachmentName());
			pstmt.setString(3, at.getAttachmentRename());
			pstmt.setString(4, at.getAttachmentPath());
			
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result2;
	}
	
	
	// 공지사항 삭제용 

	public int deleteNotice(Connection conn, int noticeNo) {
		
		// UPDATE 문 => int (처리된 행의 개수)
		
		int result1 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result1 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result1;
	}
	
	// 공지사항 삭제 시 첨부파일 
	public int deleteAttachment(Connection conn, int attachmentNo) {
		
		// UPDATE 문 => int (처리된 행의 개수) 
		
		int result2 = 0;
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, attachmentNo);
			
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result2;
	}
}

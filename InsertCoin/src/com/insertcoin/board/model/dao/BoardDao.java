package com.insertcoin.board.model.dao;

import static com.insertcoin.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.insertcoin.board.model.vo.Board;
import com.insertcoin.board.model.vo.GenComment;
import com.insertcoin.common.model.vo.Attachment;
import com.insertcoin.common.model.vo.PageInfo;
import com.insertcoin.cs.model.vo.Notice;
import com.insertcoin.game.model.vo.Game;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		
		// SELECT문 => ResultSet 객체
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			// 1행 조회하므로  while 쓸 필요 없이 if로 가능!
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
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
				listCount = rset.getInt("COUNT");
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
				listCount = rset.getInt("COUNT");
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
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 작성자 검색결과 개수 조회용
	public int selectListCountWriter(Connection conn, String searchWord) {
		
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
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 댓글 검색결과 개수 조회용
	public int selectListCountComment(Connection conn, String searchWord) {
		
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
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	// 게시글 전체 조회용
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		
		// SELECT문 => ResultSet 객체 (여러 행 조회)
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("GEN_NO")
								 , rset.getString("GEN_CATEGORY")
								 , rset.getString("GAME_NO")
								 , rset.getString("MEM_NICKNAME")
								 , rset.getString("GEN_TITLE")
								 , rset.getDate("GEN_REGISTER_DATE")
								 , rset.getInt("GEN_VIEWS")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	// 게시글 검색 조회용
	public ArrayList<Board> selectList(Connection conn, PageInfo pi, String searchCategory, String searchWord) {
		
		// SELECT 문 => ResultSet 객체 (여러 행 조회)
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		if(searchCategory != null && searchCategory.equals("searchAll")) { // 제목+내용 검색 
			sql = prop.getProperty("selectBoardListAll");
		} else if(searchCategory != null && searchCategory.equals("searchTitle")) { // 제목 검색 
			sql = prop.getProperty("selectBoardListTitle");
		} else if(searchCategory != null && searchCategory.equals("searchContent")) { // 내용 검색 
			sql = prop.getProperty("selectBoardListContent");
		} else if(searchCategory != null && searchCategory.equals("searchWriter")) { // 내용 검색 
			sql = prop.getProperty("selectBoardListWriter");
		} else if(searchCategory != null && searchCategory.equals("searchComment")) { // 내용 검색 
			sql = prop.getProperty("selectBoardListComment");
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
				
			} else if(searchCategory != null && searchCategory.equals("searchTitle") || (searchCategory != null && searchCategory.equals("searchContent")) || (searchCategory != null && searchCategory.equals("searchWriter")) || (searchCategory != null && searchCategory.equals("searchComment"))) {
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("GEN_NO")
						 , rset.getString("GEN_CATEGORY")
						 , rset.getString("GAME_NO")
						 , rset.getString("MEM_NICKNAME")
						 , rset.getString("GEN_TITLE")
						 , rset.getDate("GEN_REGISTER_DATE")
						 , rset.getInt("GEN_VIEWS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		} 
		
		return list;
	}
	
	public ArrayList<Game> selectGameList(Connection conn) {
		
		// SELECT문 => ResultSet 객체 (여러 행 조회)
		ArrayList<Game> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectGameList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Game(rset.getInt("GAME_NO"),
								  rset.getString("GAME_NAME")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertBoard(Connection conn, Board b) {
		
		// insert문 => int (처리된 행 개수)
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// GEN_CATEGORY / GAME_NO / MEM_NO / GEN_TITLE / GEN_CONTENT
			// genCategory / gemeNo / memNo / genTitle / genContent
			pstmt.setString(1, b.getGenCategory());
			// pstmt.setInt(2, b.getGameNo());
			pstmt.setInt(2, Integer.parseInt(b.getMemNo()));
			pstmt.setString(3, b.getGenTitle());
			pstmt.setString(4, b.getGenContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		
		// INSERT문 => int 처리된 행의 개수
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// private String attachmentName; // 원본 파일명
			// private String attachmentRename; // 수정 파일명
			// private String attachmentPath; // 파일경로
			
			pstmt.setString(1, at.getAttachmentName());
			pstmt.setString(2, at.getAttachmentRename());
			pstmt.setString(3, at.getAttachmentPath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		System.out.println(result);
		
		return result;

	}
	
	// 조회수 증가용 Dao
	 public int increaseCount(Connection conn, int genNo) {
		 
		 // UPDATE문 => int (처리된 행의 개수)
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String sql = prop.getProperty("increaseCount");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, genNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		 return result;
		 
	 }
	 
	 // Board 정보용  DAO
	 public Board selectBoard(Connection conn, int genNo) {
		 
		 // SELECT문 => ResultSet 객체 (많아도 1행)
		 
		 Board b = null;
		 PreparedStatement pstmt = null;
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectBoard");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, genNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				b = new Board(rset.getInt("GEN_NO")
							, rset.getString("GEN_CATEGORY")
							, rset.getString("GAME_NAME") // 일단 게임 태그 제외
							, rset.getString("MEM_NICKNAME")
							, rset.getString("GEN_TITLE")
							, rset.getString("GEN_CONTENT")
							, rset.getDate("GEN_REGISTER_DATE")
							, rset.getInt("GEN_VIEWS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		 return b;
		 
	 }
	 
	 public Attachment selectAttachment(Connection conn, int genNo) {
		 
		 // SELECT문 => ResultSet 객체 (1행 조회)
		 Attachment at = null;
		 PreparedStatement pstmt = null;
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectAttachment");
		 
		 try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, genNo);
			
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
	 
	 public int updateBoard(Connection conn, Board b) {
		 
		 // UPDATE 문 => int(처리된 행의 개수)
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String sql = prop.getProperty("updateBoard");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			 pstmt.setString(1, b.getGenCategory());
			 pstmt.setString(2, b.getGenTitle());
			 pstmt.setString(3, b.getGenContent());
			 pstmt.setString(4, b.getGameNo());
			 pstmt.setInt(5, b.getGenNo());
			 
			 result = pstmt.executeUpdate();
			 			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 return result;
		
	 }
	 
	 public int updateAttachment(Connection conn, Attachment at) {
		 
		 // UPDATE => int
		 
		 int result = 0;
		 PreparedStatement pstmt = null;
		 String sql = prop.getProperty("updateAttachment");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getAttachmentName());
			pstmt.setString(2, at.getAttachmentRename());
			pstmt.setInt(3, at.getAttachmentNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		 return result;
		 
	 }
	 
	 public int insertNewAttachment(Connection conn, Attachment at) {
		 
		 // INSERT => int
		 
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String sql = prop.getProperty("insertNewAttachment");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, at.getGenNo());
			pstmt.setString(2, at.getAttachmentName());
			pstmt.setString(3, at.getAttachmentRename());
			pstmt.setString(4, at.getAttachmentPath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 return result;
	 }
	 
	 public int deleteBoard(Connection conn, int genNo) {
		 
		 // UPDATE => int
		 
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String sql = prop.getProperty("deleteBoard");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, genNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		 System.out.println(result);
		 
		 return result;
	 }
	 
	 public ArrayList<GenComment> selectGenCommentList(Connection conn, int genNo) {
		 
		 // SELECT문 => ResultSet 객체 (여러 행 조회)
		 
		 ArrayList<GenComment> list = new ArrayList<>();
		 PreparedStatement pstmt = null;
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectGenCommentList");
		 
		 try {
			pstmt = conn.prepareStatement(sql);	
			
			pstmt.setInt(1, genNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new GenComment(rset.getInt("GEN_COMMENT_NO"),
										rset.getInt("GEN_NO"),
										rset.getString("GEN_COMMENT_CONTENT"),
										rset.getString("MEM_NICKNAME"),
										rset.getString("GEN_COMMENT_REGISTER_DATE")));
			}                                                                                                                                                       
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		 
		 return list;
	 }
	  
	 // 댓글 작성용
	 public int insertGenComment(Connection conn, GenComment gc) {
		 
		 // INSERT문 => int (처리된 행의 개수)
		 
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String sql = prop.getProperty("insertGenComment");	
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,gc.getGenCommentContent());
			pstmt.setInt(2, gc.getGenNo());
			pstmt.setInt(3, Integer.parseInt(gc.getMemNo()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		 return result;
	 }
	 
	 // 댓글 삭제용
	 public int deleteComment(Connection conn, int gcNo) {
		 
		// UPDATE => int
		 
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String sql = prop.getProperty("deleteComment");
		 
		 try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, gcNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		 
		 return result;
		 
	 }

}

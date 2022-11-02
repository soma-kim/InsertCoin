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
										rset.getString("MEM_NO"),
										rset.getString("GEN_COMMENT_CONTENT"),
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
	
	
}

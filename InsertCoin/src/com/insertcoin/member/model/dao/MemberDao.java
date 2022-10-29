package com.insertcoin.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.insertcoin.common.JDBCTemplate;
import com.insertcoin.member.model.vo.Member;

public class MemberDao {
    
    private Properties prop = new Properties();
    
    public MemberDao() {
        
        String fileName = Member.class.getResource("/sql/member/member-mapper.xml").getPath();
        
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public Member loginMember(Connection conn, Member m) {
        
        // SELECT문 => ResultSet 객체 (1건)
        
        // 필요한 변수 세팅
        Member loginUser = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        // 실행할 쿼리문 선택
        String sql = prop.getProperty("loginMember");
        
        try {
            
            // 쿼리문 실행에 필요한 PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            
            // 미완성 쿼리문 완성
            // WHERE MEM_EMAIL = ?
            // AND MEM_PASSWORD = ?
            pstmt.setString(1, m.getMemEmail());
            pstmt.setString(2, m.getMemPwd());
            
            // 쿼리문 실행 후 결과 받기
            rset = pstmt.executeQuery();
            
            // rset에서 커서를 움직여가며 결과값 뽑기
            // MEM_NO NUMBER PRIMARY KEY,               
            // MEM_ACTIVE CHAR(1) DEFAULT 'Y' NOT NULL CHECK (MEM_ACTIVE IN('Y', 'B', 'N')),
            // MEM_EMAIL VARCHAR2(30) NOT NULL UNIQUE,   
            // MEM_PASSWORD VARCHAR2(20) NOT NULL,  
            // MEM_NICKNAME VARCHAR2(30) NOT NULL UNIQUE,
            // RENAME_DATE DATE,
            // REGISTER_DATE DATE DEFAULT SYSDATE NOT NULL);
            if(rset.next()) {                
                loginUser = new Member(rset.getInt("MEM_NO"),
                                       rset.getString("MEM_ACTIVE"),
                                       rset.getString("MEM_EMAIL"),
                                       rset.getString("MEM_PASSWORD"),
                                       rset.getString("MEM_NICKNAME"),
                                       rset.getDate("RENAME_DATE"),
                                       rset.getDate("REGISTER_DATE"));    
            }
            // 일치하는 회원이 있다면 loginUser에는 해당 회원의 정보가 모두 담겨 있음
            // 일치하는 회원이 없다면 loginUser에는 null 값이 담겨 있음
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }
       
        
        return loginUser;
        
    }
    
    public int insertMember(Connection conn, Member m) {
        
        // 필요한 변수 세팅
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("insertMember");
        
        try {
            // pstmt 객체 생성
            pstmt = conn.prepareStatement(sql);
            
            // 미완성 쿼리문
            pstmt.setString(1, m.getMemEmail());
            pstmt.setString(2, m.getMemPwd());
            pstmt.setString(3, m.getMemNickname());
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            JDBCTemplate.close(pstmt);
            
        }
        
        return result;
        
    }
    
    public int emailCheck(Connection conn, String checkEmail) {
        
        // SELECT문 => ResultSet 객체 (숫자 1개)
        
        // 필요한 변수 세팅
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("emailCheck");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, checkEmail);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                count = rset.getInt("COUNT(*)");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
            
        }
        
        return count;
         
    }
    
    public int nicknameCheck(Connection conn, String checkNickname) {
        
        // SELECT문 => ResultSet 객체 (숫자 1개)
        
        // 필요한 변수 세팅
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("nicknameCheck");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, checkNickname);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                count = rset.getInt("COUNT(*)");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
            
        }
        
        return count;
        
    }

}

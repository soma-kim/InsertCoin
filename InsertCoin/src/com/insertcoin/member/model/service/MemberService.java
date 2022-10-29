package com.insertcoin.member.model.service;

import java.sql.Connection;

import com.insertcoin.common.JDBCTemplate;
import com.insertcoin.member.model.dao.MemberDao;
import com.insertcoin.member.model.vo.Member;

public class MemberService {
    
    // 로그인 요청 서비스
    public Member loginMember(Member m) {
        
        Connection conn = JDBCTemplate.getConnection();
        
        Member loginUser = new MemberDao().loginMember(conn, m);
        
        JDBCTemplate.close(conn);
        
        return loginUser;
        
    }
    
    // 회원 가입 요청 서비스
    public int insertMember(Member m) {
        
        Connection conn = JDBCTemplate.getConnection();
        
        int result = new MemberDao().insertMember(conn, m);
        
        if(result > 0) {
            JDBCTemplate.commit(conn);
        } else {
            JDBCTemplate.rollback(conn);
        }
        
        return result;
    }
    
    // 이메일 중복체크용 서비스
    public int emailCheck(String emailCheck) {
        
        Connection conn = JDBCTemplate.getConnection();
        
        int count = new MemberDao().emailCheck(conn, emailCheck);
        
        JDBCTemplate.close(conn);
        
        return count;
        
    }
    
    // 닉네임 중복체크용 서비스
    public int nicknameCheck(String nicknameCheck) {
        
        Connection conn = JDBCTemplate.getConnection();
        
        int count = new MemberDao().nicknameCheck(conn, nicknameCheck);
        
        JDBCTemplate.close(conn);
        
        return count;
        
    }

}

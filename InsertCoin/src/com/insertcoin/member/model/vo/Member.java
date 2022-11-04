package com.insertcoin.member.model.vo;

import java.sql.Date;

public class Member {
    
    // 필드부
    private int memNo;          // MEM_NO NUMBER PRIMARY KEY,               
    private String memActive;   // MEM_ACTIVE CHAR(1) DEFAULT 'Y' NOT NULL CHECK (MEM_ACTIVE IN('Y', 'B', 'N')),
    private String memEmail;    // MEM_EMAIL VARCHAR2(30) NOT NULL UNIQUE,   
    private String memPwd;      // MEM_PASSWORD VARCHAR2(20) NOT NULL,  
    private String memNickname; // MEM_NICKNAME VARCHAR2(30) NOT NULL UNIQUE,
    private Date renameDate;    // RENAME_DATE DATE,
    private Date registerDate;  // REGISTER_DATE DATE DEFAULT SYSDATE NOT NULL);
    
    // 생성자부
    public Member() {
        super();
    }

    public Member(int memNo, String memActive, String memEmail, String memPwd, String memNickname, Date renameDate,
            Date registerDate) {
        super();
        this.memNo = memNo;
        this.memActive = memActive;
        this.memEmail = memEmail;
        this.memPwd = memPwd;
        this.memNickname = memNickname;
        this.renameDate = renameDate;
        this.registerDate = registerDate;
    }
    
    // 회원가입용 생성자
    public Member(String memEmail, String memPwd, String memNickname) {
        super();
        this.memEmail = memEmail;
        this.memPwd = memPwd;
        this.memNickname = memNickname;
    }
    
    // 메소드부
    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;

    }

    public String getMemActive() {
        return memActive;
    }

    public void setMemActive(String memActive) {
        this.memActive = memActive;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemNickname() {
        return memNickname;
    }

    public void setMemNickname(String memNickname) {
        this.memNickname = memNickname;
    }

    public Date getRenameDate() {
        return renameDate;
    }

    public void setRenameDate(Date renameDate) {
        this.renameDate = renameDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

	@Override
    public String toString() {
        return "Member [memNo=" + memNo + ", memActive=" + memActive + ", memEmail=" + memEmail + ", memPwd=" + memPwd
                + ", memNickname=" + memNickname + ", renameDate=" + renameDate + ", registerDate=" + registerDate
                + "]";
    }

}
package com.insertcoin.board.model.vo;

import java.sql.Date;

public class Board {
	
	// 필드부
	private int genNo; // GEN_NO NUMBER PRIMARY KEY,
	private String genCategory; // GEN_CATEGORY VARCHAR2(10) NOT NULL,
	private String gameNo; // GAME_NO NUMBER REFERENCES GAME(GAME_NO),
	private String memNo; // MEM_NO NUMBER NOT NULL REFERENCES MEMBER(MEM_NO),
	private String genTitle; // GEN_TITLE VARCHAR2(100) NOT NULL,
	private String genContent; // GEN_CONTENT VARCHAR2(4000) NOT NULL,
	private Date genRegister; // GEN_REGISTER_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int genViews; // GEN_VIEWS NUMBER DEFAULT 0,
	private String genShow; // GEN_SHOW CHAR(1) DEFAULT 'Y' NOT NULL);
	
	// 생성자부
	public Board() {
		super();
	}

	public Board(int genNo, String genCategory, String gameNo, String memNo, String genTitle, String genContent,
			Date genRegister, int genViews, String genShow) {
		super();
		this.genNo = genNo;
		this.genCategory = genCategory;
		this.gameNo = gameNo;
		this.memNo = memNo;
		this.genTitle = genTitle;
		this.genContent = genContent;
		this.genRegister = genRegister;
		this.genViews = genViews;
		this.genShow = genShow;
	}
	
	// 게시글 메인용 생성자
	public Board(int genNo, String genCategory, String gameNo, String memNo, String genTitle, Date genRegister,
			int genViews) {
		super();
		this.genNo = genNo;
		this.genCategory = genCategory;
		this.gameNo = gameNo;
		this.memNo = memNo;
		this.genTitle = genTitle;
		this.genRegister = genRegister;
		this.genViews = genViews;
	}
	
	// 일반게시글 상세 조회용 생성자
	public Board(int genNo, String genCategory, String gameNo, String memNo, String genTitle, String genContent,
			Date genRegister, int genViews) {
		super();
		this.genNo = genNo;
		this.genCategory = genCategory;
		this.gameNo = gameNo;
		this.memNo = memNo;
		this.genTitle = genTitle;
		this.genContent = genContent;
		this.genRegister = genRegister;
		this.genViews = genViews;
	}

	// 메소드부
	public int getGenNo() {
		return genNo;
	}


	public void setGenNo(int genNo) {
		this.genNo = genNo;
	}

	public String getGenCategory() {
		return genCategory;
	}

	public void setGenCategory(String genCategory) {
		this.genCategory = genCategory;
	}

	public String getGameNo() {
		return gameNo;
	}

	public void setGameNo(String gameNo) {
		this.gameNo = gameNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getGenTitle() {
		return genTitle;
	}

	public void setGenTitle(String genTitle) {
		this.genTitle = genTitle;
	}

	public String getGenContent() {
		return genContent;
	}

	public void setGenContent(String genContent) {
		this.genContent = genContent;
	}

	public Date getGenRegister() {
		return genRegister;
	}

	public void setGenRegister(Date genRegister) {
		this.genRegister = genRegister;
	}

	public int getGenViews() {
		return genViews;
	}

	public void setGenViews(int genViews) {
		this.genViews = genViews;
	}

	public String getGenShow() {
		return genShow;
	}

	public void setGenShow(String genShow) {
		this.genShow = genShow;
	}

	@Override
	public String toString() {
		return "Board [genNo=" + genNo + ", genCategory=" + genCategory + ", gameNo=" + gameNo + ", memNo=" + memNo
				+ ", genTitle=" + genTitle + ", genContent=" + genContent + ", genRegister=" + genRegister
				+ ", genViews=" + genViews + ", genShow=" + genShow + "]";
	}

}

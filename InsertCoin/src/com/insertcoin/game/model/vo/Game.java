package com.insertcoin.game.model.vo;

import java.sql.Date;

public class Game {
	
	
	// 필드부 
	private int gameNo; //	GAME_NO	NUMBER
	private String gameName; //	GAME_NAME	VARCHAR2(100 BYTE)
	private int memNo; //	MEM_NO	NUMBER
	private String gameGenre; //	GAME_GENRE	VARCHAR2(20 BYTE)
	private String gameOs; //	GAME_OS	VARCHAR2(10 BYTE)
	private String gameContent; //	GAME_CONTENT	VARCHAR2(4000 BYTE)
	private int gameDonateDefault; //	GAME_DONATE_DEFAULT	NUMBER
	private String gameFreeDownload; //	GAME_FREE_DOWNLOAD	CHAR(1 BYTE)
	private Date gameRegisterDate; //	GAME_REGISTER_DATE	DATE
	private String gameShow; //	GAME_SHOW	CHAR(1 BYTE)
	private String gamePageColor; //	GAME_PAGE_COLOR	VARCHAR2(10 BYTE)
	


	// 생성자부
	public Game() {
		super();
	}
	
	
	public Game(int gameNo, String gameName, int memNo, String gameGenre, String gameOs, String gameContent,
			int gameDonateDefault, String gameFreeDownload, Date gameRegisterDate, String gameShow,
			String gamePageColor) {
		super();
		this.gameNo = gameNo;
		this.gameName = gameName;
		this.memNo = memNo;
		this.gameGenre = gameGenre;
		this.gameOs = gameOs;
		this.gameContent = gameContent;
		this.gameDonateDefault = gameDonateDefault;
		this.gameFreeDownload = gameFreeDownload;
		this.gameRegisterDate = gameRegisterDate;
		this.gameShow = gameShow;
		this.gamePageColor = gamePageColor;
	}
	
	// 게시판 게임 태그용 생성자
	public Game(int gameNo, String gameName) {
		super();
		this.gameNo = gameNo;
		this.gameName = gameName;
	}
	
	// 메소드부 
	public int getGameNo() {
		return gameNo;
	}

	public void setGameNo(int gameNo) {
		this.gameNo = gameNo;
	}


	public String getGameName() {
		return gameName;
	}


	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


	public int getMemNo() {
		return memNo;
	}


	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}


	public String getGameGenre() {
		return gameGenre;
	}


	public void setGameGenre(String gameGenre) {
		this.gameGenre = gameGenre;
	}


	public String getGameOs() {
		return gameOs;
	}


	public void setGameOs(String gameOs) {
		this.gameOs = gameOs;
	}


	public String getGameContent() {
		return gameContent;
	}


	public void setGameContent(String gameContent) {
		this.gameContent = gameContent;
	}


	public int getGameDonateDefault() {
		return gameDonateDefault;
	}


	public void setGameDonateDefault(int gameDonateDefault) {
		this.gameDonateDefault = gameDonateDefault;
	}


	public String getGameFreeDownload() {
		return gameFreeDownload;
	}


	public void setGameFreeDownload(String gameFreeDownload) {
		this.gameFreeDownload = gameFreeDownload;
	}


	public Date getGameRegisterDate() {
		return gameRegisterDate;
	}


	public void setGameRegisterDate(Date gameRegisterDate) {
		this.gameRegisterDate = gameRegisterDate;
	}


	public String getGameShow() {
		return gameShow;
	}


	public void setGameShow(String gameShow) {
		this.gameShow = gameShow;
	}


	public String getGamePageColor() {
		return gamePageColor;
	}


	public void setGamePageColor(String gamePageColor) {
		this.gamePageColor = gamePageColor;
	}


	@Override
	public String toString() {
		return "Game [gameNo=" + gameNo + ", gameName=" + gameName + ", memNo=" + memNo + ", gameGenre=" + gameGenre
				+ ", gameOs=" + gameOs + ", gameContent=" + gameContent + ", gameDonateDefault=" + gameDonateDefault
				+ ", gameFreeDownload=" + gameFreeDownload + ", gameRegisterDate=" + gameRegisterDate + ", gameShow="
				+ gameShow + ", gamePageColor=" + gamePageColor + "]";
	}

}
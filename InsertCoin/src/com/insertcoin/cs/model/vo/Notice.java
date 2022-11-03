package com.insertcoin.cs.model.vo;

import java.sql.Date;

public class Notice {

	// 필드부 
	private int noticeNo; // NOTICE_NO NUMBER PRIMARY KEY,
	private String noticeTitle; // NOTICE_TITLE VARCHAR2(100 BYTE) NOT NULL,
	private String noticeContent; // NOTICE_CONTENT VARCHAR2(4000 BYTE) NOT NULL,
	private Date noticeRegisterDate; // NOTICE_REGISTER_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int noticeViews; // NOTICE_VIEWS NUMBER NOT NULL,
	private String noticeShow; // NOTICE_SHOW CHAR(1 BYTE) DEFAULT 'Y' CHECK (NOTICE_SHOW IN ('Y', 'N')));
	
	// 생성자부 
	public Notice() {
		super();
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeRegisterDate, int noticeViews,
			String noticeShow) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeRegisterDate = noticeRegisterDate;
		this.noticeViews = noticeViews;
		this.noticeShow = noticeShow;
	}
	
	// 공지사항 리스트 조회용 생성자부 
	public Notice(int noticeNo, String noticeTitle, Date noticeRegisterDate, int noticeViews) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeRegisterDate = noticeRegisterDate;
		this.noticeViews = noticeViews;
	}
	
	// 공지사항 상세조회용 생성자부
	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeRegisterDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeRegisterDate = noticeRegisterDate;
	}

	// 메소드부
	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeRegisterDate() {
		return noticeRegisterDate;
	}

	public void setNoticeRegisterDate(Date noticeRegisterDate) {
		this.noticeRegisterDate = noticeRegisterDate;
	}

	public int getNoticeViews() {
		return noticeViews;
	}

	public void setNoticeViews(int noticeViews) {
		this.noticeViews = noticeViews;
	}

	public String getNoticeShow() {
		return noticeShow;
	}

	public void setNoticeShow(String noticeShow) {
		this.noticeShow = noticeShow;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeRegisterDate=" + noticeRegisterDate + ", noticeViews=" + noticeViews + ", noticeShow="
				+ noticeShow + "]";
	}
	
}
package com.insertcoin.common.model.vo;

import java.sql.Date;

public class Attachment {
	
	//  필드부
	private int attachmentNo; // 파일번호
	private String attachmentPath; // 파일경로
	private String attachmentName; // 원본 파일명
	private String attachmentRename; // 수정 파일명
	private Date attachmentUploadDate; // 업로드일
	private String attachmentStatus; // 삭제유무
	private int memNo; // 참조 회원번호
	private int gameNo; // 참조 게임등록번호
	private int reviewNo; // 참조 리뷰등록번호
	private int genNo; // 참조 일반게시판 게시글번호
	private int genCommentNo; // 참조 일반게시판 댓글번호
	private int devNo; // 참조 개발자게시판 게시글번호
	private int devCommentNo;  // 참조 개발자게시판 댓글번호	
	private int noticeNo; // 참조 공지사항번호
	
	

	// 생성자부
	
	public Attachment() {
		super();
	}
	public Attachment(int attachmentNo, String attachmentPath, String attachmentName, String attachmentRename,
			Date attachmentUploadDate, String attachmentStatus, int memNo, int gameNo, int reviewNo, int genNo,
			int genCommentNo, int devNo, int devCommentNo, int noticeNo) {
		super();
		this.attachmentNo = attachmentNo;
		this.attachmentPath = attachmentPath;
		this.attachmentName = attachmentName;
		this.attachmentRename = attachmentRename;
		this.attachmentUploadDate = attachmentUploadDate;
		this.attachmentStatus = attachmentStatus;
		this.memNo = memNo;
		this.gameNo = gameNo;
		this.reviewNo = reviewNo;
		this.genNo = genNo;
		this.genCommentNo = genCommentNo;
		this.devNo = devNo;
		this.devCommentNo = devCommentNo;
		this.noticeNo = noticeNo;
	}
	
	
	// 메소드부 
	public int getAttachmentNo() {
		return attachmentNo;
	}
	public void setAttachmentNo(int attachmentNo) {
		this.attachmentNo = attachmentNo;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentRename() {
		return attachmentRename;
	}
	public void setAttachmentRename(String attachmentRename) {
		this.attachmentRename = attachmentRename;
	}
	public Date getAttachmentUploadDate() {
		return attachmentUploadDate;
	}
	public void setAttachmentUploadDate(Date attachmentUploadDate) {
		this.attachmentUploadDate = attachmentUploadDate;
	}
	public String getAttachmentStatus() {
		return attachmentStatus;
	}
	public void setAttachmentStatus(String attachmentStatus) {
		this.attachmentStatus = attachmentStatus;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public int getGameNo() {
		return gameNo;
	}
	public void setGameNo(int gameNo) {
		this.gameNo = gameNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getGenNo() {
		return genNo;
	}
	public void setGenNo(int genNo) {
		this.genNo = genNo;
	}
	public int getGenCommentNo() {
		return genCommentNo;
	}
	public void setGenCommentNo(int genCommentNo) {
		this.genCommentNo = genCommentNo;
	}
	public int getDevNo() {
		return devNo;
	}
	public void setDevNo(int devNo) {
		this.devNo = devNo;
	}
	public int getDevCommentNo() {
		return devCommentNo;
	}
	public void setDevCommentNo(int devCommentNo) {
		this.devCommentNo = devCommentNo;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	@Override
	public String toString() {
		return "Attachment [attachmentNo=" + attachmentNo + ", attachmentPath=" + attachmentPath + ", attachmentName="
				+ attachmentName + ", attachmentRename=" + attachmentRename + ", attachmentUploadDate="
				+ attachmentUploadDate + ", attachmentStatus=" + attachmentStatus + ", memNo=" + memNo + ", gameNo="
				+ gameNo + ", reviewNo=" + reviewNo + ", genNo=" + genNo + ", genCommentNo=" + genCommentNo + ", devNo="
				+ devNo + ", devCommentNo=" + devCommentNo + ", noticeNo=" + noticeNo + "]";
	}

}
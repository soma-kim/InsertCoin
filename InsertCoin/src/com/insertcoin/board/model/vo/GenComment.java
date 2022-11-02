package com.insertcoin.board.model.vo;

public class GenComment {
	
	// 필드부
	private int genCommentNo;
	private int genNo;
	private String memNo;
	private String genCommentContent;
	private String genCommentRegister;
	private String genCommentShow;
	
	// 생성자부
	public GenComment() {
		super();
	}

	public GenComment(int genCommentNo, int genNo, String memNo, String genCommentContent, String genCommentRegister,
			String genCommentShow) {
		super();
		this.genCommentNo = genCommentNo;
		this.genNo = genNo;
		this.memNo = memNo;
		this.genCommentContent = genCommentContent;
		this.genCommentRegister = genCommentRegister;
		this.genCommentShow = genCommentShow;
	}
	
	// 댓글 리스트 조회용 생성자
	public GenComment(int genCommentNo, String memNo, String genCommentContent, String genCommentRegister) {
		super();
		this.genCommentNo = genCommentNo;
		this.memNo = memNo;
		this.genCommentContent = genCommentContent;
		this.genCommentRegister = genCommentRegister;
	}

	// 메소드부
	public int getGenCommentNo() {
		return genCommentNo;
	}

	public void setGenCommentNo(int genCommentNo) {
		this.genCommentNo = genCommentNo;
	}

	public int getGenNo() {
		return genNo;
	}

	public void setGenNo(int genNo) {
		this.genNo = genNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getGenCommentContent() {
		return genCommentContent;
	}

	public void setGenCommentContent(String genCommentContent) {
		this.genCommentContent = genCommentContent;
	}

	public String getGenCommentRegister() {
		return genCommentRegister;
	}

	public void setGenCommentRegister(String genCommentRegister) {
		this.genCommentRegister = genCommentRegister;
	}

	public String getGenCommentShow() {
		return genCommentShow;
	}

	public void setGenCommentShow(String genCommentShow) {
		this.genCommentShow = genCommentShow;
	}

	@Override
	public String toString() {
		return "BoardComment [genCommentNo=" + genCommentNo + ", genNo=" + genNo + ", memNo=" + memNo
				+ ", genCommentContent=" + genCommentContent + ", genCommentRegister=" + genCommentRegister
				+ ", genCommentShow=" + genCommentShow + "]";
	}

}
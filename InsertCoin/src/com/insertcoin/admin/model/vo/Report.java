package com.insertcoin.admin.model.vo;

import java.sql.Date;

public class Report {

    // 필드부
    private int reportNo;
    private int reportType;
    private String memberEmail;
    private String reportReason;
    private Date reportDate;
    private String reportAccept;
    private String gameName;
    private String reviewContent;
    private String devTitle;
    private String devCommentContent;
    private String genTitle;
    private String genCommentContent;
    

    // 생성자부    
    public Report() {   
        super();
    }   
    
    
    public Report(int reportNo, int reportType, String memberEmail, String reportReason, Date reportDate, String reportAccept,
            String gameName, String reviewContent, String devTitle, String devCommentContent, String genTitle,
            String genCommentContent) {
        super();
        this.reportNo = reportNo;
        this.reportType = reportType;
        this.memberEmail = memberEmail;
        this.reportReason = reportReason;
        this.reportDate = reportDate;
        this.reportAccept = reportAccept;
        this.gameName = gameName;
        this.reviewContent = reviewContent;
        this.devTitle = devTitle;
        this.devCommentContent = devCommentContent;
        this.genTitle = genTitle;
        this.genCommentContent = genCommentContent;
    }


    // 조회용 메소드
    public Report(int reportNo, int reportType, String reportReason, Date reportDate, String reportAccept,
            String gameName, String reviewContent, String devTitle, String devCommentContent, String genTitle,
            String genCommentContent) {
        super();
        this.reportNo = reportNo;
        this.reportType = reportType;
        this.reportReason = reportReason;
        this.reportDate = reportDate;
        this.reportAccept = reportAccept;
        this.gameName = gameName;
        this.reviewContent = reviewContent;
        this.devTitle = devTitle;
        this.devCommentContent = devCommentContent;
        this.genTitle = genTitle;
        this.genCommentContent = genCommentContent;
    }



    // 메소드부
    public int getReportNo() {
        return reportNo;
    }



    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }



    public int getReportType() {
        return reportType;
    }



    public void setReportType(int reportType) {
        this.reportType = reportType;
    }



    public String getMemberEmail() {
        return memberEmail;
    }



    public void setMembeEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }



    public String getReportReason() {
        return reportReason;
    }



    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }



    public Date getReportDate() {
        return reportDate;
    }



    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }



    public String getReportAccept() {
        return reportAccept;
    }



    public void setReportAccept(String reportAccept) {
        this.reportAccept = reportAccept;
    }



    public String getGameName() {
        return gameName;
    }



    public void setGameName(String gameName) {
        this.gameName = gameName;
    }



    public String getReviewContent() {
        return reviewContent;
    }



    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }



    public String getDevTitle() {
        return devTitle;
    }



    public void setDevTitle(String devTitle) {
        this.devTitle = devTitle;
    }



    public String getDevCommentContent() {
        return devCommentContent;
    }
    
 

    public void setDevCommentContent(String devCommentContent) {
        this.devCommentContent = devCommentContent;
    }



    public String getGenTitle() {
        return genTitle;
    }



    public void setGenTitle(String genTitle) {
        this.genTitle = genTitle;
    }



    public String getGenCommentContent() {
        return genCommentContent;
    }



    public void setGenCommentContent(String genCommentContent) {
        this.genCommentContent = genCommentContent;
    }



    @Override
    public String toString() {
        return "Report [reportNo=" + reportNo + ", reportType=" + reportType + ", memberEmail=" + memberEmail + ", reportReason="
                + reportReason + ", reportDate=" + reportDate + ", reportAccept=" + reportAccept + ", gameName="
                + gameName + ", reviewContent=" + reviewContent + ", devTitle=" + devTitle + ", devCommentContent="
                + devCommentContent + ", genTitle=" + genTitle + ", genCommentContent=" + genCommentContent + "]";
    }

    
  
}
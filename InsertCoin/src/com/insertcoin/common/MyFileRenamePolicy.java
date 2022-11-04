package com.insertcoin.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	// 기존 파일 전달받아 파일명 수정 작업 후 수정된 파일 자체를 return

	@Override
	public File rename(File originFile) {
		
		// 원본 파일명
		String originName = originFile.getName();
		
		// 수정 파일명
		// gen_post__yyyymmdd_nnnnn
		// 파일 업로드 시간
		
		String text = "gen_post_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 5자리 랜덤값
		int ranNum = (int)(Math.random() * 90000) + 10000;
		
		// 원본파일 확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 결합
		String changeName = text + ranNum + ext;
		
		// 수정된 파일명으로 원본파일명 적용 후 파일 객체로 변환
				
		return new File(originFile.getParent(), changeName);
	}
	
}
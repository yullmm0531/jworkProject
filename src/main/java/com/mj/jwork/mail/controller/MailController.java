package com.mj.jwork.mail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mj.jwork.common.model.vo.PageInfo;
import com.mj.jwork.common.template.FileUpload;
import com.mj.jwork.common.template.Pagination;
import com.mj.jwork.employee.model.vo.Employee;
import com.mj.jwork.mail.model.service.MailServiceImpl;
import com.mj.jwork.mail.model.vo.Mail;
import com.mj.jwork.mail.model.vo.MailAt;

@Controller
public class MailController {
	
	@Autowired
	private MailServiceImpl mService;
	
	// 메일 리스트 조회
	@RequestMapping(value="list.ma")
	public ModelAndView mailListForward(@RequestParam(value="cpage", defaultValue="1") int currentPage, Mail m ,HttpSession session, ModelAndView mv) {
		
		int empNo = ((Employee)session.getAttribute("loginUser")).getEmpNo();
		m.setEmpNo(empNo);
		//System.out.println(m);
		
		int listCount = mService.selectListCount(m);
		int unReadCount = mService.selectUnReadListCount(m);
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 5, 10);
		
		ArrayList<Mail> mList = mService.selectMailList(m, pi);
		mv.addObject("mailCategory", m.getMailCategory())
		  .addObject("mailBoxNo", m.getMailBoxNo())
		  .addObject("listCount", listCount)
		  .addObject("unReadCount", unReadCount)
		  .addObject("pi", pi)
		  .addObject("mList", mList)
		  .setViewName("mail/mailListView");
		  
		return mv;
		
	}
	
	
	// 사용자 메일함 조회 서비스
	@ResponseBody
	@RequestMapping(value="mailBoxList.ma", produces="application/json; charset=UTF-8")
	public String ajaxSelectMailBox(int empNo) {
		
		ArrayList<Mail> mblist = mService.selectMailBoxList(empNo);
		
		return new Gson().toJson(mblist);
		
	}
	
	// 사용자 메일함 수정 서비스
	@ResponseBody
	@RequestMapping("updateMailBox.ma")
	public String ajaxUpdateMailBox(Mail m) {
		int result = mService.updateMailBox(m);
		
		return result > 0 ? "success" : "fail" ;
		
	}
	
	// 사용자 메일함 추가 서비스
	@ResponseBody
	@RequestMapping(value="insertMailBox.ma", produces="application/json; charset=UTF-8")
	public String ajaxInsertMailBox(Mail m) {
		
		int result = mService.insertMailBox(m);
		Mail mailbox = null;
		if(result > 0) {
			mailbox = mService.selectMailBox(m);
		}
		
		return new Gson().toJson(mailbox);
		
	}
	
	// 사용자 메일함 삭제 서비스
	@ResponseBody
	@RequestMapping("deleteMailBox.ma")
	public String ajaxDeleteMailBox(int mailBoxNo) {
		
		// 해당 메일함에 있는 메일들을 휴지통으로 이동
		int mailsResult = mService.updateMailsWithMailBox(mailBoxNo);
		
		// 메일이 있었을수도,, 없었을 수도 있음,, 이것에 대한건 나중에 조건 확인할 것
		
		int mailBoxResult = mService.deleteMailBox(mailBoxNo);
		
		
		return mailBoxResult > 0 ? "success" : "fail";
		
	}
	
	//메일작성페이지 포워딩
	@RequestMapping("mailEnrollForm.ma")
	public String mailEnrollForm() {
		
		return "mail/mailEnrollForm";
		
	}
	
	// 메일 이동 서비스
	@ResponseBody
	@RequestMapping("moveMail.ma")
	public String ajaxMoveMail(Mail m, @RequestParam(value="mailNoList[]") ArrayList<Integer> mailNoList) {
		
		//System.out.println(m);
		//System.out.println(mailNoList);
		int result = 1;
		for(int i=0; i<mailNoList.size(); i++) {
			//System.out.println(mailNoList.get(i));
			m.setMailNo(mailNoList.get(i));
			//System.out.println(m);
			result = result * mService.moveMail(m);
			
		}
		
		return result > 0 ? "success" : "fail";
		
	}
	
	// 메일 중요 서비스
	@ResponseBody
	@RequestMapping("important.ma")
	public String ajaxImportant(Mail m) {
		
		int result = mService.updateImportant(m);
		
		return result > 0 ? "success" : "fail";
		
	}
	
	// 메일 보내기 서비스
	@RequestMapping(value="sendMail.ma")
	public ModelAndView sendMail(Mail m, @RequestPart(value="upfile") List<MultipartFile> upfile, HttpSession session, ModelAndView mv) {
		
		System.out.println(m);
		System.out.println(upfile);
		
		
		
		
		int mailResult = mService.sendMail(m);
		int mailAtResult = 1;
		int detailResult = 1;
		
		
		if(mailResult > 0) {
			
			if(!upfile.get(0).getOriginalFilename().equals("")) {
				for(int i=0; i<upfile.size(); i++) {
					System.out.println(upfile.get(i).getOriginalFilename());
					String saveFilePath = FileUpload.saveFile(upfile.get(i), session, "resources/mailUploadFiles/");
					
					MailAt ma = new MailAt();
					ma.setOriginName(upfile.get(i).getOriginalFilename());
					ma.setChangeName(saveFilePath);
					
					mailAtResult = mailAtResult * mService.insertMailAt(ma);
					
				}
			}
			
			
			
			if(mailAtResult > 0) {
				Mail md = new Mail();
				if(m.getSend().equals("Y")) {
					String[] receiverArr = m.getReceiver().split(",");
					String[] receiverNoArr = m.getReceiverNo().split(",");
					
					for(int i=0; i<receiverArr.length; i++) {
						
						md.setEmpNo(Integer.parseInt(receiverNoArr[i]));
						md.setEmpName(receiverArr[i]);
						md.setType("R");
						System.out.println(md);
						detailResult = detailResult * mService.insertMailDetail(md);
						
						mv.addObject("send", "Y")
						  .addObject("message", "메일 전송 성공!")
						  .addObject("subMessage", "보낸 메일은 보낸 메일함에서 확인 가능합니다.");
						
					}
					
				} else {
					mv.addObject("send", "N")
					  .addObject("message", "임시저장 성공!")
					  .addObject("subMessage", "임시저장 메일은 임시저장함에서 확인 가능합니다.");
				}
					
				md.setEmpNo(m.getSenderNo());
				md.setEmpName(m.getSender());
				md.setType("S");
				
				detailResult = detailResult * mService.insertMailDetail(md);
			}
			
		}
		
		if(mailResult * mailAtResult * detailResult > 0) {
			mv.setViewName("mail/mailSuccess");
		} else {
			mv.setViewName("common/errorPage");
		}
		
		return mv;
		
	}
	
	@RequestMapping("detail.ma")
	public String selectMail(int mailNo) {
		
		
		return "mail/mailDetailView";
		
	}
	
	
	
}

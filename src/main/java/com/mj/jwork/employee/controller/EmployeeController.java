package com.mj.jwork.employee.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mj.jwork.common.template.FileUpload;
import com.mj.jwork.employee.model.service.EmployeeService;
import com.mj.jwork.employee.model.vo.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	// 로그인
	@RequestMapping("login.emp")
	public String loginEmployee(Employee e, Model model, HttpSession session) {
		Employee loginUser = eService.loginEmployee(e);
		if(loginUser == null) {
			model.addAttribute("errorMsg", "로그인 실패");
			return "common/errorPage";
		}else {
			session.setAttribute("loginUser", loginUser);
			return "common/menubar";
		}
	}
	
	// 마이페이지
	@RequestMapping("myPage.emp")
	public String myPage() {
		return "employee/myPage";
	}
	
	// 마이페이지 - 개인정보수정
	@RequestMapping("update.emp")
	public String updateEmployee(Employee e, HttpSession session, Model model) {
		int result = eService.updateEmployee(e);
		if(result>0) {
			Employee updateEmp = eService.loginEmployee(e);
			session.setAttribute("loginUser", updateEmp);
			session.setAttribute("alertMsg", "사원정보 변경 성공");
			return "redirect:myPage.emp";	
		}else {
			model.addAttribute("errorMsg", "사원정보 변경 실패");
			return "common/errorPage";
		}
	}
	
	// 마이페이지 - 프로필이미지수정
	@ResponseBody	
	@RequestMapping("uploadProfile.emp")
	public void uploadProfileImg(MultipartFile uploadFile, Employee e, 
								 String originalFile, HttpSession session) { 
		
		if(uploadFile != null) {
			String saveFilePath = FileUpload.saveFile(uploadFile, session, "resources/profile_images/");
			e.setProfileUrl(saveFilePath);
			
			int result = eService.updateProfileImg(e);
			
			if(result>0) {
				if(!originalFile.equals("")) {
					new File(session.getServletContext().getRealPath(originalFile)).delete();
				}
				
				session.setAttribute("loginUser", eService.loginEmployee(e));
			}
		}	
	}
	
}
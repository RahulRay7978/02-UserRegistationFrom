package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.constant.RegistrationConstant;
import com.nt.domain.UserForgetPasswordDetails;
import com.nt.service.UserMngtService;

@Controller
public class UserFogetPwdDetailsController {
	@Autowired
	private UserMngtService userService;
	/**
	 * This method for launching forget password page
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/showForgetPwd")
	public String showForm(Model model) {
		UserForgetPasswordDetails details=new UserForgetPasswordDetails();
		model.addAttribute("fPwd",details);
		return RegistrationConstant.FPWDSTR;
	}
	
	/**
	 * This methos for handle forget password submitions 
	 * @param details
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/forget")
	public String handleForgetPwdBtn(@ModelAttribute("fPwd")UserForgetPasswordDetails details,Model model) {
		boolean fetchRecordByEmailId = userService.fetchRecordByEmailId(details.getEmail());
		if(fetchRecordByEmailId) {
			model.addAttribute("succMsg","Check your email to get password");
		}
		else
			model.addAttribute("failMsg","Invalid Email");

		return RegistrationConstant.FPWDSTR;
	}
}

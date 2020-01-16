package com.nt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.constant.RegistrationConstant;
import com.nt.domain.UserUnlockDetailsBean;
import com.nt.service.UserMngtService;
@Controller
public class UserUnlockFormController {
	@Autowired
	private UserMngtService userService;
	@RequestMapping("/unlockAccount")
		public String unlockUserAccount(Model model,HttpServletRequest req) {
			UserUnlockDetailsBean unlockBean=new UserUnlockDetailsBean();
			unlockBean.setEmail((req.getParameter("email")));
			model.addAttribute("unlockBean",unlockBean);
		return RegistrationConstant.UNLOCKFORMSTR;
		}
	@RequestMapping("/unlockBtn")
	public String unlockSubmitBtn(@ModelAttribute("unlockBean")UserUnlockDetailsBean bean,Model model) {
		boolean checkValidEmailId = userService.checkValidEmailId(bean);
		if(checkValidEmailId) {
			model.addAttribute("succMsg", "your account is registered ");
		}
		else
			model.addAttribute("failMsg", "your account is not registered.. try again ");
		return RegistrationConstant.UNLOCKFORMSTR;
	}
}

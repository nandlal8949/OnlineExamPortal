package com.csmtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csmtech.bean.MenuText;
import com.csmtech.entity.Candidate;
import com.csmtech.entity.Users;
import com.csmtech.service.CandidateService;
import com.csmtech.service.ExamSetService;
import com.csmtech.service.MarksService;
import com.csmtech.service.MenuService;
import com.csmtech.service.QuestionsService;
import com.csmtech.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private CandidateService candService;
	
	@Autowired
	private ExamSetService examSetService;
	
	@Autowired
	private QuestionsService questionService;
	
	@Autowired
	private MarksService marksService;

	@GetMapping(value = "/")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping(value = "/")
	public String postLogin(@RequestParam String userName, @RequestParam String password, Model model) throws ParseException {
		Users user = userService.getUserDetails(userName, password);
			if(user != null) {
				session.setAttribute("SESSION", user);
				session.setMaxInactiveInterval(1000*60*60);
				
				//get menu items
				List<MenuText> menuItems = menuService.getAllMenus(user.getRole().getRoleId());
				session.setAttribute("MENUS", menuItems);
				if(user.getRole().getRoleId() == 1 && user.getIsDelete().contains("No") ) {
					return "redirect:/admin/admin";
				}else if(user.getRole().getRoleId() == 2 && user.getIsDelete().contains("No")) {					
					return "redirect:/proctor/proctor";
					
				}else if(user.getRole().getRoleId() == 3 && user.getIsDelete().contains("No")) {
					return "redirect:/hr/hr";
				}
			}
			
		if(user == null) {
			Candidate cand = candService.getCandidateByUserNameAndPassword(userName,password);
			
			if(cand != null) {
				session.setAttribute("SESSION1", cand);
				session.setMaxInactiveInterval(1000*60*60);
				List<MenuText> menuItems = menuService.getCandidateMenus();
				session.setAttribute("MENUS", menuItems);
				Integer examComplete = marksService.examCompleteAlready(cand.getCandId());				
				model.addAttribute("ALREADYCOMPLETE", examComplete);
				
				
				//time expire
				Integer limit = 0;
				if(cand.getExamSet() != null) {
					limit = cand.getExamSet().getTimeLimit();					
				}
				LocalTime localTime = cand.getExam().getLocalTime();
				String candExamDate = cand.getCandExamDate();
				Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(candExamDate);
				Date d1 = new Date(parse.getYear(), parse.getMonth(), parse.getDate(), localTime.getHour(), localTime.getMinute(), localTime.getSecond());
				long time2 = d1.getTime();
				limit = limit * 60 * 1000;
				time2 += limit;
				
				long oneDay = 1000*60*60*24;
				
				String presentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String examTime = new SimpleDateFormat("yyyy-MM-dd").format(time2-limit);
				
				if(time2 > new Date().getTime() && presentTime.equals(examTime) ) {
					model.addAttribute("RESULT", "PRESENTDATE");
				}else if(time2 < new Date().getTime()) {
					marksService.answerNotGivenQuestion(cand.getCandId());
					model.addAttribute("RESULT", "PASTDATE");
				}else if(time2 > (new Date().getTime() + oneDay)) {
					model.addAttribute("RESULT", "NEXTDATE");
				}
				
			}
			
			if(cand == null) {				
				return "login";
			}
			
			
			return "candidate/candidate";
			
		}
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		Object admin = session.getAttribute("SESSION");
		if(admin != null) {
			session.invalidate();
			return "redirect:/";
		}
		
		
		Object candidate = session.getAttribute("SESSION1");
		if(candidate != null) {
			session.invalidate();
			return "redirect:/";
		}
		return "redirect:/";
	}
	
}

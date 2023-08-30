package com.csmtech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csmtech.bean.MenuText;
import com.csmtech.entity.Candidate;
import com.csmtech.entity.ExamSetQuestion;
import com.csmtech.entity.Question;
import com.csmtech.entity.Users;
import com.csmtech.service.CandidateService;
import com.csmtech.service.ExamSetService;
import com.csmtech.service.MarksService;
import com.csmtech.service.MenuService;
import com.csmtech.service.QuestionsService;
import com.csmtech.service.UserService;

@Controller
public class CommonHomeController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ExamSetService examSetService;
	
	@Autowired
	private QuestionsService questionService;
	
	@Autowired
	private CandidateService candService;
	
	@Autowired
	private MarksService marksService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("admin/admin")
	public String getAdminHome(Model model) {
		List<MenuText> otherMenus = menuService.getOtherMenus(1);
		Integer uncompleteExamSets = examSetService.getUncompleteExamSets();
		model.addAttribute("UNCOMPLEEXAMSET", uncompleteExamSets);
		model.addAttribute("OTHERMENUS", otherMenus);
		
		Integer uncompleteQuestionSet = questionService.getUncompleteQuestionSet();
		model.addAttribute("UNCOMPLETEQUESTION", uncompleteQuestionSet);
		
		Integer uncompletenotGetExamSet = candService.uncompletecandNotGetExamSet();
		model.addAttribute("UNCOMPLETECANDIDATESET",uncompletenotGetExamSet);
		
		List<Candidate> pendingCheck = marksService.getAllSubjectivePendingCheck();
		model.addAttribute("SUBJECTIVEPENDING", pendingCheck != null ? pendingCheck.size() : 0);
		
		Integer totalCandidates = candService.getCandidateList().size();
		model.addAttribute("TOTALCANDIDATE", totalCandidates);
		
		Integer totalQuestions = questionService.getAllQuestions().size();
		model.addAttribute("TOTALQUESTIONS", totalQuestions);
		
		return "admin/admin";
	}
	
	@GetMapping("proctor/proctor")
	public String getProctorHome(Model model) {
		List<MenuText> otherMenus = menuService.getOtherMenus(1);
		Integer uncompleteExamSets = examSetService.getUncompleteExamSets();
		model.addAttribute("UNCOMPLEEXAMSET", uncompleteExamSets);
		model.addAttribute("OTHERMENUS", otherMenus);
		
		Integer uncompleteQuestionSet = questionService.getUncompleteQuestionSet();
		model.addAttribute("UNCOMPLETEQUESTION", uncompleteQuestionSet);
		
		Integer uncompletenotGetExamSet = candService.uncompletecandNotGetExamSet();
		model.addAttribute("UNCOMPLETECANDIDATESET",uncompletenotGetExamSet);
		
		List<Candidate> pendingCheck = marksService.getAllSubjectivePendingCheck();
		model.addAttribute("SUBJECTIVEPENDING", pendingCheck != null ? pendingCheck.size() : 0);
		
		Integer totalCandidates = candService.getCandidateList().size();
		model.addAttribute("TOTALCANDIDATE", totalCandidates);
		
		Integer totalQuestions = questionService.getAllQuestions().size();
		model.addAttribute("TOTALQUESTIONS", totalQuestions);
		
		return "proctor/proctor";
	}
	
	@GetMapping("hr/hr")
	public String getHrHome(Model model) {
		return "hr/hr";
	}
	
	@GetMapping("hr/resultlist")
	public String getCandidateResultList(Model model) {
		List<Candidate> candidateList = candService.getCandidateList();
		List<Integer> candList = new ArrayList<>();
		for (Candidate candidate : candidateList) {
			if(candidate.getExamSet() != null) {
				List<ExamSetQuestion> examSetQuestion = candidate.getExamSet().getExamSetQuestion();
				for (ExamSetQuestion exQues : examSetQuestion) {
					Integer questionId = exQues.getQuestionId();
					Question byId = questionService.getQuestionById(questionId);
					if(byId.getQuestionType().getQuesTypeId() == 2) {
						candList.add(candidate.getCandId());
						break;
					}
				}
			}
			
		}
			List<Candidate> candList2 = new ArrayList<>();
			for (Integer integer : candList) {
				candList2.add(candService.getCandidateInfo(integer));
			}
			model.addAttribute("CANDIDATELIST", candList2);
		return "hr/resultlist";
	}
	
	
	@GetMapping("/forgetPassword")
	public String getForgetPassword() {
		return "forgetPassword";
	}
	
	@PostMapping("/forgetPassword")
	public String postForgetPassword(@RequestParam String userName,@RequestParam String email, Model model) {
		Users user = userService.getUsersList().stream().filter(t->t.getUserName().equals(userName) && t.getEmail().equals(email)).findAny().orElse(null);
		Candidate cand = candService.getCandidateList().stream().filter(t->t.getCandUserName().equals(userName) && t.getCandEmail().equals(email)).findAny().orElse(null);
		if(user != null) {
			model.addAttribute("USER", user);
			return "forgetPassword2";
		}else if(user == null && cand != null) {
			model.addAttribute("CANDIDATE", cand);
			return "forgetPassword2";
		}else {			
			model.addAttribute("NOTEXISTS", "UserName and Email Incorrect");
			return "forgetPassword";
		}
	}
	
	@GetMapping("/forgetPassword2")
	public String getForgetPassword2() {
		return "forgetPassword2";
	}
	
	@PostMapping("/forgetPassword2")
	public String postForgetPassword2(Model model, @RequestParam String userId, @RequestParam String candId, @RequestParam String password) {
		if(userId.length() != 0) {
			Users user = userService.updateUsersList(Integer.parseInt(userId));
			user.setUserId(Integer.parseInt(userId));
			user.setPassword(password);
			userService.postUsersList(user);
			model.addAttribute("SUCCESS", "success");
		}else if(candId.length() != 0) {
			Candidate candidate = candService.getCandidateInfo(Integer.parseInt(candId));
			candidate.setCandId(Integer.parseInt(candId));
			candidate.setCandPassword(password);
			candService.saveCandidate(candidate);
			model.addAttribute("SUCCESS", "success");
		}else {
			model.addAttribute("SUCCESS", "failed");
		}
		return "forgetPassword2";
	}
}

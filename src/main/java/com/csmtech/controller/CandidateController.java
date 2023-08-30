package com.csmtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.Marks;
import com.csmtech.entity.Question;
import com.csmtech.service.CandidateService;
import com.csmtech.service.MarksService;
import com.csmtech.service.QuestionsService;

@Controller
public class CandidateController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private MarksService marksService;
	
	@Autowired
	private QuestionsService questionService;
	
	@GetMapping("candidate/candresetpassword/{candidateId}")
	public String getCandidatePasswordReset(@PathVariable Integer candidateId) {		
		return "candidate/candresetpassword";
	}
	
	@PostMapping("candidate/candresetpassword")
	public String postCandidatePasswordReset(@RequestParam String oldpassword, @RequestParam String newpassword) {
		Candidate cand = (Candidate) session.getAttribute("SESSION1");
		if(cand.getCandPassword().equals(oldpassword)) {
			cand.setCandPassword(newpassword);
			candidateService.saveCandidate(cand);
		}
		
		return "candidate/candidate";
	}
	
	@GetMapping("/candidate/test/{candId}")
	public String getTestPage(Model model, @PathVariable Integer candId) {
		Candidate candidateInfo = candidateService.getCandidateInfo(candId);
		model.addAttribute("CANDIDATE",candidateInfo);
		List<Marks> marksList  = marksService.getAllQuestionList();
		Integer questionNo = marksList.stream().filter(t->t.getCandidateId() == candId && t.getMarks() != null).map(t->t.getQuestionId()).collect(Collectors.toList()).size();
		List<Integer> collect = marksList.stream().filter(t->t.getCandidateId() == candId && t.getMarks() == null).map(t->t.getQuestionId()).collect(Collectors.toList());
		
		
		List<Integer> objList = new ArrayList<>();
		List<Integer> subList = new ArrayList<>();
		collect.forEach(t->{
			Question question = questionService.getQuestionById(t);
			if(question.getQuestionType().getQuesTypeId() == 1) {
				objList.add(t);
			}else {
				subList.add(t);
			}
		});
		
		
		if(objList.size() > 1) {
			Integer i =new Random().nextInt(objList.size());
			Question question = questionService.getQuestionById(objList.get(i));
			model.addAttribute("QUESTION",question);
			model.addAttribute("QUESTIONNO",questionNo+1);
		}
		
		if(objList.size() == 1) {
			Question question = questionService.getQuestionById(objList.get(0));
			model.addAttribute("QUESTION",question);
			model.addAttribute("QUESTIONNO",questionNo+1);
		}
		
		
		if(objList.size() == 0) {
			if(subList.size() > 1) {
				Integer i =new Random().nextInt(subList.size());
				Question question = questionService.getQuestionById(subList.get(i));
				model.addAttribute("QUESTION",question);
				model.addAttribute("QUESTIONNO",questionNo+1);
			}
			
			if(subList.size() == 1) {
				Question question = questionService.getQuestionById(subList.get(0));
				model.addAttribute("QUESTION",question);
				model.addAttribute("QUESTIONNO",questionNo+1);
			}
		}
		
		if(objList.size() == 0 && subList.size() == 1) {
			model.addAttribute("FINISHBUTTON", "Save & Finish");
		}
		if(subList.size() == 0) {
			model.addAttribute("EXAMFINISHED", "success");
		}
		 
		return "candidate/test";
	}
	
	@PostMapping("/candidate/test")
	public String postTestPage(@ModelAttribute Marks mark, @RequestParam String candidateAnswer1) throws ParseException {
		Candidate candidate = candidateService.getCandidateInfo(mark.getCandidateId());
		
		//time expire or not
		Boolean timeExpire = true;
		String date = candidate.getCandExamDate();
		LocalTime localTime = candidate.getExam().getLocalTime();
		Integer limit = candidate.getExamSet().getTimeLimit();
		Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		Calendar instance = Calendar.getInstance();
		Date d1 = new Date(parse.getYear(), parse.getMonth(), parse.getDate(), localTime.getHour(), localTime.getMinute(), localTime.getSecond());
		long time2 = d1.getTime();
		limit = limit * 60 * 1000;
		time2 += limit;
		
		if(time2 < new Date().getTime()) {
			timeExpire = false;
		}
		
		
		
		
		Marks marks = marksService.updateMarks(mark.getCandidateId(), mark.getQuestionId());
		Question quesId = questionService.getQuestionById(mark.getQuestionId());
			marks.setCandidateAnswer(mark.getCandidateAnswer());
		
			if(quesId.getQuestionType().getQuesTypeId() == 1 && timeExpire) {
				if(quesId.getCurrectAnswer().equals(mark.getCandidateAnswer())) {
				marks.setMarks(1.0);
				}else {
					marks.setMarks(0.0);	
				}
			}
			
			if(quesId.getQuestionType().getQuesTypeId() == 2 && timeExpire) {
				
				marks.setCandidateAnswer(candidateAnswer1);
				marks.setMarks(-1.0);
			}
			
		marksService.saveMarkswithAnswer(marks);
		
		return "redirect:/candidate/test/"+marks.getCandidateId();
	}
	
	@GetMapping("/candidate/result/{candId}")
	public String getResult(Model model, @PathVariable Integer candId) {
		Candidate candidateInfo = candidateService.getCandidateInfo(candId);
		model.addAttribute("CANDIDATE", candidateInfo);
		List<Marks> marksList  = marksService.getAllQuestionList();
		List<Marks> collect = marksList.stream().filter(t->t.getCandidateId() == candId && t.getMarks() != null).collect(Collectors.toList());
		boolean print = collect.stream().anyMatch(t->t.getMarks() == -1);
		model.addAttribute("PRINTVALID", print);
		List<List<Object>> objList = new ArrayList<>();
		List<List<Object>> subList = new ArrayList<>();
		
		for (Marks mark : collect) {
			List<Object> temp = new ArrayList<>();
			List<Object> temp2 = new ArrayList<>();
			Question question = questionService.getQuestionById(mark.getQuestionId());
			if(question.getQuestionType().getQuesTypeId() == 1) {
				temp.add(question.getQuestionText());
				temp.add(question.getQuestionCode());
				temp.add(question.getCurrectAnswer());
				temp.add(mark.getCandidateAnswer());
				temp.add(mark.getMarks());
				objList.add(temp);				
			}else if(question.getQuestionType().getQuesTypeId() == 2) {
				temp2.add(question.getQuestionText());
				temp2.add(question.getCurrectAnswer());
				temp2.add(mark.getCandidateAnswer());
				temp2.add(mark.getMarks());
				subList.add(temp2);
			}
		}
		model.addAttribute("OBJECTLIST",objList);
		model.addAttribute("SUBJECTLIST",subList);
		
		return "candidate/result";
	}
	
	
	@GetMapping("/candidate/resultprint/{candId}")
	public String getResultPrint(Model model, @PathVariable Integer candId) {
		Candidate candidateInfo = candidateService.getCandidateInfo(candId);
		
		List<Marks> marksList  = marksService.getAllQuestionList();
		List<Marks> collect = marksList.stream().filter(t->t.getCandidateId() == candId && t.getMarks() != null && t.getMarks() != -1).collect(Collectors.toList());
		
		Double obj = 0.0;
		Double sub = 0.0;
		Integer objQty = 0;
		Integer subQty = 0;
		for (Marks mark : collect) {
			Question question = questionService.getQuestionById(mark.getQuestionId());
			if(question.getQuestionType().getQuesTypeId() == 1) {
				obj += mark.getMarks();		
				objQty++;
			}else if(question.getQuestionType().getQuesTypeId() == 2) {
				sub += mark.getMarks();
				subQty++;
				
			}
	}
		
		Double total = (double) ((objQty * 1) + (subQty *3));
		Double totalMarks = obj + sub;
		double res = (totalMarks * 100)/total;
		String res1 = (res >= 50.0 ? "PASS" : "FAIL" );
		
		Map<String, Object> mp = new HashMap<>();
		
		mp.put("CANDIDATE", candidateInfo);
		mp.put("OBJ", obj);
		mp.put("SUB", sub);
		mp.put("OBJQTY", objQty);
		mp.put("SUBQTY", subQty);
		mp.put("TOTAL", total);
		mp.put("RESULT", res1);
		model.addAllAttributes(mp);
		return "candidate/resultprint";
	}
}

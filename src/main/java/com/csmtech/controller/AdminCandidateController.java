package com.csmtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.Exam;
import com.csmtech.entity.ExamSet;
import com.csmtech.service.CandidateService;
import com.csmtech.service.ExamService;
import com.csmtech.service.ExamSetService;
import com.csmtech.service.MarksService;

@Controller
public class AdminCandidateController {
	
	@Autowired
	private ExamSetService examSetService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private MarksService marksService;

	@GetMapping("admin/candidate")
	public String getCandidateList(Model model) {
		Map<String, Object> hasMap = new HashMap<>();
		List<Exam> examList = examService.getExamList();
		List<Candidate> candList = candidateService.getCandidateList();
		List<ExamSet> examSetList = examSetService.getExamSets();
		hasMap.put("EXAMLIST", examList);
		hasMap.put("CANDLIST", candList);
		hasMap.put("EXAMSETLIST", examSetList);
		model.addAllAttributes(hasMap);
		return "admin/candidate";
	}
	
	@PostMapping("admin/candidate")
	public String getCandidateInfo(@ModelAttribute Candidate candidate, HttpSession session) {
		if(candidate.getCandId() != null) {
			candidate.setCandId(candidate.getCandId());
		}
		
		Object object = session.getAttribute("SESSION");
		 if(object == null) {
			 return "candidate/candidate";
		 }
		 candidateService.saveCandidate(candidate);
		return "redirect:/admin/candidate";
	}
	
	@GetMapping("admin/candidate/delete/{candId}")
	public String deleteCandidateId(@PathVariable Integer candId) {
		candidateService.deleteCandidateId(candId);
		return "redirect:/admin/candidate";
	}
	
	@GetMapping("admin/candidate/update/{candId}")
	public String getCandidateInfo(@PathVariable Integer candId, Model model) {
		List<Exam> examList = examService.getExamList();
		List<Candidate> candList = candidateService.getCandidateList();
		Candidate candidate = candidateService.getCandidateInfo(candId);
		List<ExamSet> examSetList = examSetService.getExamSets();
		
		Map<String, Object> hasMap = new HashMap<>();
		
		hasMap.put("EXAMLIST", examList);
		hasMap.put("CANDLIST", candList);
		hasMap.put("CANDINFO", candidate);
		hasMap.put("EXAMSETLIST", examSetList);
		model.addAllAttributes(hasMap);
		return "admin/candidate";
	}
	
	@GetMapping("admin/candidate/assignment/{candId}")
	public String saveAssignment(@PathVariable Integer candId, Model model) {
		List<ExamSet> examSetList = examSetService.getExamSets();
		Candidate candidate = candidateService.getCandidateInfo(candId);
		Map<String, Object> hasMap = new HashMap<>();
		
		hasMap.put("EXAMSETLIST", examSetList);
		hasMap.put("CANDINFO", candidate);
		model.addAllAttributes(hasMap);
		return "admin/candidateassignment";
	}
	
	@PostMapping("admin/candidate/assignment")
	public String saveAssignmentwithResultQues(@ModelAttribute Candidate candidate, @ModelAttribute ExamSet examSet, Model model) {
		Candidate info = candidateService.getCandidateInfo(candidate.getCandId());
		marksService.saveAllQuestionMarksTable(examSet.getExamSetId(), info.getCandId());
		info.setExamSet(candidate.getExamSet());
		candidateService.saveCandidate(info);
		return "redirect:/admin/candidateassignmentlist";
	}
	
	@GetMapping("admin/candidateassignmentlist")
	public String addSettoCondidate(Model model) {
		List<Candidate> candidateList = candidateService.getCandidateList();
		List<Candidate> collect = candidateList.stream().filter(t->t.getExamSet()== null || t.getExamSet().getExamSetQuestion().size() == 0 ).collect(Collectors.toList());
		model.addAttribute("CANDIDATELIST", collect);
		return "admin/candidateassignmentlist";
	}

}

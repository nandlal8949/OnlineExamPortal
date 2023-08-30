package com.csmtech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.Marks;
import com.csmtech.service.CandidateService;
import com.csmtech.service.MarksService;

@Controller
public class AdminCheckSubjectiveQues {
	@Autowired
	private MarksService marksService;
	
	@Autowired
	private CandidateService candidateService;
	
	@GetMapping("admin/checksubques")
	public String getAllSubjectivePendingCheck(Model model) {
		List<Candidate> pendingCheck = marksService.getAllSubjectivePendingCheck();
		model.addAttribute("CHECKSUBQUES", pendingCheck);
		return "admin/checksubques";
	}
	
	@GetMapping("admin/checksubques/{candId}")
	public String getAllSubjectivePendingForCheck(Model model, @PathVariable Integer candId) {
		Candidate candidate = candidateService.getCandidateInfo(candId);
		model.addAttribute("CANDIDATE", candidate);
		List<List<Object>> list = marksService.getAllSubjectivePendingForCheck(candidate.getCandId(), candidate.getExamSet().getExamSetId());
		model.addAttribute("QUESTION", list);
		
		return "admin/checksubquescandidate";
	}
	
	@PostMapping("admin/checksubques/{candId}/{examSetId}")
	public void getAllSubjectivePendingForCheck(Model model, @PathVariable Integer candId, @PathVariable Integer examSetId, String temp, HttpServletResponse resp) throws IOException {
		JSONArray jsonArray = new JSONArray(temp);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			Integer quesId = object.getInt("questionId");
			Double marks = object.getDouble("marks");
			Marks mark = marksService.getMarksInfoByCandIdAndQuesId(candId, quesId);
			System.out.println(mark);
			mark.setMarks(marks);
			marksService.saveMarkswithAnswer(mark);
		}
		resp.setContentType("text/html");
		resp.getWriter().print("Data Successfully Saved");
	}
}

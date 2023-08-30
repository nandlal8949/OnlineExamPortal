package com.csmtech.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csmtech.entity.Exam;
import com.csmtech.entity.Items;
import com.csmtech.service.ExamService;
import com.csmtech.service.ItemService;

@Controller
public class AdminExamController {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ItemService itemService;

	@GetMapping("admin/exam")
	private String getExamList(Model model) {
		List<Items> itemList = itemService.getItemList();
		List<Exam> examList = examService.getExamList();
		model.addAttribute("ITEMLIST", itemList);
		model.addAttribute("EXAMLIST", examList);
		return "admin/exam";
	}
	
	@PostMapping("admin/exam")
	private String saveExamList(@ModelAttribute Exam exam, @RequestParam String hour,@RequestParam String min, @RequestParam String sec) {
		LocalTime of = null;
		try {
			of = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(min), Integer.parseInt(sec));			
		} catch (Exception e) {
			
		}
		exam.setLocalTime(of);
		if(exam.getExamId() != null ) {
			exam.setExamId(exam.getExamId());
		}
		examService.saveExamInfo(exam);
		return "redirect:/admin/exam";
	}
	
	@GetMapping("admin/exam/update/{examId}")
	private String getExamInfo(Model model, @PathVariable Integer examId) {
		List<Items> itemList = itemService.getItemList();
		List<Exam> examList = examService.getExamList();
		Exam exam = examService.getExamInfo(examId);
		
		model.addAttribute("ITEMLIST", itemList);
		model.addAttribute("EXAMLIST", examList);
		model.addAttribute("EXAM",exam);
		
		return "admin/exam";
	}
	
	@GetMapping("admin/exam/delete/{examId}")
	private String deleteExamInfo(@PathVariable Integer examId) {
		examService.deleteExamInfo(examId);
		
		return "redirect:/admin/exam";
	}
}

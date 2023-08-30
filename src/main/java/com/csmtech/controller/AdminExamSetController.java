package com.csmtech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.csmtech.entity.ExamSet;
import com.csmtech.entity.ExamSetQuestion;
import com.csmtech.entity.Items;
import com.csmtech.entity.Question;
import com.csmtech.service.CandidateService;
import com.csmtech.service.ExamSetQuestionService;
import com.csmtech.service.ExamSetService;
import com.csmtech.service.ItemService;
import com.csmtech.service.QuestionsService;

@Controller
public class AdminExamSetController {
	
	@Autowired
	private ExamSetService examSetService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ExamSetQuestionService examSetQuestionService;
	
	@Autowired
	private QuestionsService questionService;
	
	@Autowired
	private CandidateService candidateService;

	@GetMapping("admin/examset")
	public String getExamSets(Model model) {
		List<Items> itemList = itemService.getItemList();
		List<ExamSet> examSetList = examSetService.getExamSets();
		List<List<Object>> outerList = new ArrayList<>();
		for (ExamSet eSet : examSetList) {
			List<Object> innerList = new ArrayList<>();
			innerList.add(eSet.getExamSetId());
			innerList.add(eSet.getExamSetName());
			innerList.add(eSet.getItems() == null ? "All" : eSet.getItems().getItemName());
			List<ExamSetQuestion> setQuestion = eSet.getExamSetQuestion();
			
			int objCount1 = 0;
			int subCount1 = 0;
		for (ExamSetQuestion examSetQues : setQuestion) {
			Question question2 = questionService.getQuestionById(examSetQues.getQuestionId());
			if(question2.getQuestionType().getQuesTypeId() == 1) {
				objCount1++;
			}else {
				subCount1++;
			}
		}
		innerList.add(eSet.getObjQues()-objCount1);				
		innerList.add(eSet.getSubQues()-subCount1);
		innerList.add((eSet.getObjQues()-objCount1) + (eSet.getSubQues()-subCount1));
			innerList.add(eSet.getTotalScore());
			innerList.add(eSet.getPassScore());
			innerList.add(eSet.getObjQues());
			innerList.add(eSet.getSubQues());
			outerList.add(innerList);
		}
		outerList.sort((p1,p2)->{
			return ((Integer) p2.get(3) - (Integer) p1.get(3));
		});
		
		model.addAttribute("EXAMSETLIST",outerList);
		model.addAttribute("ITEMLIST",itemList);
		return "admin/examset";
	}
	
	@PostMapping("admin/examset")
	public String postExamSet(@ModelAttribute ExamSet examSet) {
		if(examSet.getExamSetId() != null) {
			examSet.setExamSetId(examSet.getExamSetId());
		}
		examSetService.postExamSet(examSet);
		return "redirect:/admin/examset";
	}
	
	@GetMapping("admin/examset/update/{examSetId}")
	public String updateExamSet(@PathVariable Integer examSetId, Model model) {		
		ExamSet examSet1 = examSetService.updateExamSet(examSetId);
		List<Items> itemList = itemService.getItemList();
		model.addAttribute("EXAMSET",examSet1);
		model.addAttribute("ITEMLIST",itemList);
		
		
		List<ExamSet> examSetList = examSetService.getExamSets();
		List<List<Object>> outerList = new ArrayList<>();
		for (ExamSet eSet : examSetList) {
			List<Object> innerList = new ArrayList<>();
			innerList.add(eSet.getExamSetId());
			innerList.add(eSet.getExamSetName());
			innerList.add(eSet.getItems() == null ? "All" : eSet.getItems().getItemName());
			List<ExamSetQuestion> setQuestion = eSet.getExamSetQuestion();
			
			int objCount1 = 0;
			int subCount1 = 0;
		for (ExamSetQuestion examSetQues : setQuestion) {
			Question question2 = questionService.getQuestionById(examSetQues.getQuestionId());
			if(question2.getQuestionType().getQuesTypeId() == 1) {
				objCount1++;
			}else {
				subCount1++;
			}
		}
		innerList.add(eSet.getObjQues()-objCount1);				
		innerList.add(eSet.getSubQues()-subCount1);
		innerList.add((eSet.getObjQues()-objCount1) + (eSet.getSubQues()-subCount1));
			innerList.add(eSet.getTotalScore());
			innerList.add(eSet.getPassScore());
			innerList.add(eSet.getObjQues());
			innerList.add(eSet.getSubQues());
			outerList.add(innerList);
		}
		outerList.sort((p1,p2)->{
			return ((Integer) p2.get(3) - (Integer) p1.get(3));
		});
		
		model.addAttribute("EXAMSETLIST",outerList);

		return "admin/examset";
	}
	
	@GetMapping("admin/examset/delete/{examSetId}")
	public String deleteExamSet(@PathVariable Integer examSetId) {
		
		candidateService.getCandidateList().stream().filter(t->t.getExamSet() != null && t.getExamSet().getExamSetId() == examSetId ).map(t-> t.getCandId()).forEach(t->{
			candidateService.deleteCandidateId(t);
		});
		
		examSetQuestionService.deleteExamSetQuestion(examSetId);
		examSetService.deleteExamSet(examSetId);
		return "redirect:/admin/examset";
	}
	
	@GetMapping("admin/examset/view/{examSetId}")
	public String viewExamSet(@PathVariable Integer examSetId, Model model) {
		
		List<ExamSetQuestion> examSetQuestionList = examSetQuestionService.getAllQuestionByExamId(examSetId);
		model.addAttribute("QUESTIONLIST", examSetQuestionList);
		List<List<Object>> listObj = new ArrayList<List<Object>>();
		
		for (ExamSetQuestion ques : examSetQuestionList) {
			List<Object> obj = new ArrayList<>();
			obj.add(ques.getExamCodeId());
			Question ques1 = questionService.getQuestionById(ques.getQuestionId());
			obj.add(ques.getQuestionId());
			obj.add(ques1.getQuestionText());
			obj.add(ques1.getQuestionType().getQuesTypeName());
			obj.add(ques.getExamSetQuestionId());
			listObj.add(obj);
		}
		model.addAttribute("QUESTIONLIST",listObj);
		model.addAttribute("EXAMID",examSetId);
		
		return "admin/examsetview";
	}
	
	@GetMapping("admin/examset/view/delete/{examSetId}/{questionId}")
	public String deleteAfterExamSet(@PathVariable Integer examSetId, @PathVariable Integer questionId, Model model) {
		examSetQuestionService.deleteExamSetQuestionByQuestionId(questionId);
		List<ExamSetQuestion> examSetQuestionList = examSetQuestionService.getAllQuestionByExamId(examSetId);
		model.addAttribute("QUESTIONLIST", examSetQuestionList);
		List<List<Object>> listObj = new ArrayList<List<Object>>();
		
		for (ExamSetQuestion ques : examSetQuestionList) {
			List<Object> obj = new ArrayList<>();
			obj.add(ques.getExamCodeId());
			Question ques1 = questionService.getQuestionById(ques.getQuestionId());
			obj.add(ques.getQuestionId());
			obj.add(ques1.getQuestionText());
			obj.add(ques1.getQuestionType().getQuesTypeName());
			obj.add(ques.getExamSetQuestionId());
			listObj.add(obj);
		}
		model.addAttribute("QUESTIONLIST",listObj);
		return "admin/examsetview";
	}
}

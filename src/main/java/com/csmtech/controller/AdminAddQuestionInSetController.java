package com.csmtech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.csmtech.entity.QuestionType;
import com.csmtech.entity.SubItem;
import com.csmtech.service.ExamSetQuestionService;
import com.csmtech.service.ExamSetService;
import com.csmtech.service.ItemService;
import com.csmtech.service.QuestionsService;
import com.csmtech.service.QuestionsTypeService;
import com.csmtech.service.SubItemService;

@Controller
public class AdminAddQuestionInSetController {
	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SubItemService subItemService;
	
	@Autowired
	private QuestionsTypeService quesTypeService;
	
	@Autowired
	private ExamSetService examSetService;
	
	@Autowired
	private ExamSetQuestionService examSetQuestionService;
	

	@GetMapping("admin/addquestioninset")
	public String addQuestionInSetList(Model model ) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<ExamSet> examSet = examSetService.getExamSets().stream().sorted(Comparator.comparing(ExamSet::getExamSetName)).collect(Collectors.toList());
		hashMap.put("EXAMSETLIST", examSet);
		model.addAllAttributes(hashMap);
		return "admin/addquestioninset";
	}
	
	@PostMapping("admin/addquestioninset/search")
	public String searchQuestionInSetList(Model model, @ModelAttribute ExamSet examSet, @ModelAttribute Items items, @ModelAttribute SubItem subItem, @ModelAttribute QuestionType questionType ) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//examSet obj and sub count
		List<ExamSetQuestion> examSetQues = examSetQuestionService.getAllQuestionByExamId(examSet.getExamSetId());
		Integer objCount = 0;
		Integer subCount = 0;
		for (ExamSetQuestion examSetQuestion : examSetQues) {
			Question question = questionsService.getQuestionById(examSetQuestion.getQuestionId());
			if(question.getQuestionType().getQuesTypeId() == 1) {
				objCount++;
			}else {
				subCount++;
			}
		}
		
		
		List<Question> qList = questionsService.getAllQuestions();
		List<Integer> collect3 = examSet.getExamSetQuestion().stream().map(t->t.getQuestionId()).collect(Collectors.toList());
		List<Question> qList2 = new ArrayList<>();
		for (Integer qId: collect3) {
			Question question2 = questionsService.getQuestionById(qId);
			qList2.add(question2);
		}
		
		qList.removeAll(qList2);
		Set<Question> objQuesSet = qList.stream().filter(t->t.getQuestionType().getQuesTypeId() == 1).collect(Collectors.toSet());
		Set<Question> subQuesSet = qList.stream().filter(t->t.getQuestionType().getQuesTypeId() == 2).collect(Collectors.toSet());
		if(objCount == examSet.getObjQues()) {
			qList.removeAll(objQuesSet);
		}
		if(subCount == examSet.getSubQues()) {
			qList.removeAll(subQuesSet);
		}
		
		
		List<ExamSet> examSet1 = examSetService.getExamSets().stream().sorted(Comparator.comparing(ExamSet::getExamSetName)).collect(Collectors.toList());
		
		if(examSet.getExamSetId() != null) {
			
			List<Question> collect = qList.stream().filter(i->i.getItems().getItemId() == items.getItemId()).collect(Collectors.toList());
			qList = collect;
			
			if(subItem.getSubItemId() != null) {
				List<Question> collect1 = qList.stream().filter(i->i.getSubItem().getSubItemId() == subItem.getSubItemId()).collect(Collectors.toList());
				qList = collect1;
				
				if(questionType.getQuesTypeId() != null ) {
					List<Question> collect2 = qList.stream().filter(i->i.getQuestionType().getQuesTypeId() == questionType.getQuesTypeId()).collect(Collectors.toList());
					qList = collect2;
				}
			}
			
		}

		hashMap.put("EXAMSETLIST", examSet1);
		hashMap.put("EXAMSET", examSet);
		hashMap.put("ITEMS", items);
		hashMap.put("QUESTIONLIST", qList);
		List<Object> of = List.of(examSet.getExamSetName(),objCount,subCount);
		hashMap.put("SETINFO",of);
		
		model.addAllAttributes(hashMap);
		return "admin/addquestioninset";
	}

	@PostMapping("admin/addquestioninset/update")
	public void updateQuestionInSetList(@ModelAttribute ExamSet examSet1, String temp, HttpServletResponse resp ) throws IOException {
		//examSet obj and sub count
				List<ExamSetQuestion> examSetQues = examSetQuestionService.getAllQuestionByExamId(examSet1.getExamSetId());
				Integer objCount = 0;
				Integer subCount = 0;
				for (ExamSetQuestion examSetQuestion : examSetQues) {
					Question question = questionsService.getQuestionById(examSetQuestion.getQuestionId());
					if(question.getQuestionType().getQuesTypeId() == 1) {
						objCount++;
					}else {
						subCount++;
					}
				}
		
		
		
		JSONArray jsonObj = new JSONArray(temp);
		for (int i = 0; i < jsonObj.length(); i++) {
			JSONObject ob =  (JSONObject) jsonObj.get(i);
			Integer questionId = Integer.parseInt(ob.getString("questionId"));
			Integer examSetId = Integer.parseInt(ob.getString("examCodeId"));
			ExamSetQuestion examSetQuestion = examSetQuestionService.getExamSetInfo(questionId,examSetId);
			if(examSetQuestion == null) {
				Question question = questionsService.getQuestionById(questionId);
				
				if(question.getQuestionType().getQuesTypeId() == 1 && objCount < examSet1.getObjQues()) {
					examSetQuestionService.saveExamSetQuestion(new ExamSetQuestion(null, questionId,examSetId));
					objCount++;
				}else if(question.getQuestionType().getQuesTypeId() == 2 && subCount < examSet1.getSubQues()) {
					examSetQuestionService.saveExamSetQuestion(new ExamSetQuestion(null, questionId,examSetId));
					subCount++;
				}
			}
		}
		
		resp.setContentType("text/html");
		resp.getWriter().print("Questions Updated");
		
	}
	
	
	
	//dropdown
	@GetMapping("admin/addquestioninset/examsetid/{examSetId}")
	public void getItemsList(@PathVariable Integer examSetId, HttpServletResponse resp) throws IOException {
		ExamSet updateExamSet = examSetService.updateExamSet(examSetId);
		resp.setContentType("text/html");
		
		if(updateExamSet.getItems() == null) {
			List<Items> itemList = itemService.getItemList();
			String ss = "<option value='0'> select </option>";
					for (Items items : itemList) {
						ss += "<option value="+items.getItemId()+"> "+items.getItemName()+" </option>";
					}
			
			resp.getWriter().print(ss);
		}else {
			String str = "<option value='0'> select </option><option value="+updateExamSet.getItems().getItemId()+"> "+updateExamSet.getItems().getItemName()+" </option>";
			resp.getWriter().print(str);
		}
		
	}
	
	@GetMapping("admin/addquestioninset/examsetitem/{examSetId}/{itemId}")
	public void getItemsList(@PathVariable Integer examSetId, @PathVariable Integer itemId,  HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		List<SubItem> subItem = subItemService.getSubItemListByitemId(itemId);
		String str = "<option value='0'> select </option>";
		for (SubItem sitem : subItem) {
			str += "<option value="+sitem.getSubItemId()+"> "+sitem.getSubItemName()+" </option>";
		}
		resp.getWriter().print(str);
		
	}
	
	//dropdown
	@GetMapping("admin/addquestioninset/questiontype/{subItemId}")
	public void getObjectiveList(@PathVariable Integer subItemId, HttpServletResponse resp) throws IOException {
		List<QuestionType> questionType = quesTypeService.getAllQuestionType();
		resp.setContentType("text/html");
		String str = "<option value='0'> select </option>";
		for (QuestionType questionType2 : questionType) {
			str += "<option value="+questionType2.getQuesTypeId()+"> "+questionType2.getQuesTypeName()+" </option>";
		}
		resp.getWriter().print(str);
	}
	
}

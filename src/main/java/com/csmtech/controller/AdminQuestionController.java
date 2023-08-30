package com.csmtech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.csmtech.entity.Answer;
import com.csmtech.entity.Items;
import com.csmtech.entity.Question;
import com.csmtech.entity.QuestionType;
import com.csmtech.entity.SubItem;
import com.csmtech.service.AnswerService;
import com.csmtech.service.ItemService;
import com.csmtech.service.QuestionsService;
import com.csmtech.service.QuestionsTypeService;
import com.csmtech.service.SubItemService;

@Controller
public class AdminQuestionController {
	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SubItemService subItemService;
	
	@Autowired
	private QuestionsTypeService quesTypeService;
	
	@Autowired
	private AnswerService answerService;
	
	@GetMapping("admin/questions")
	public String getAllQuestions(Model model) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<Question> qList = questionsService.getAllQuestions();
		List<Items> itemList = itemService.getItemList();
		List<QuestionType> quesTypeList = quesTypeService.getAllQuestionType();
		qList.sort((a,b)->{
			return b.getQuestionStatus().compareTo(a.getQuestionStatus());
		});
		hashMap.put("QUESTIONLIST", qList);
		hashMap.put("ITEMLIST", itemList);
		hashMap.put("TYPES", quesTypeList);
		
		model.addAllAttributes(hashMap);
		return "admin/questions";
	}
	
	@PostMapping("admin/questions/save")
	public void getAllQuestions(String temp, HttpServletResponse resp) throws IOException {
		
		Question question = new Question();
		JSONArray jsonArray = new JSONArray(temp);
		List<Answer> ansList = new ArrayList<>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj =  (JSONObject) jsonArray.get(i);
			if(i == 0) {
				String string = obj.getString("value");
				if(string.isEmpty()) {
					continue;
				}else {
					question.setQuestionId(obj.getInt("value"));
					answerService.deleteQuestion(question.getQuestionId());
				}
			}
			
			if(i == 1) {
				question.setItems(itemService.findItem(obj.getInt("value")));
			}
			if(i ==2) {
				question.setSubItem(subItemService.updateSubItem(obj.getInt("value")));
			}
			if(i == 3) {
				question.setQuestionType(obj.getInt("value") == 1 ?  new QuestionType(1, "Objective") : new QuestionType(2, "Subjective"));
			}
			if(i == 4) {
				question.setQuestionText(obj.getString("value"));
			}
			if(i == 5) {
				question.setQuestionCode(obj.getString("value"));
			}
			if(i==6) {
				question.setCurrectAnswer(obj.getString("value"));
			}
			if(i > 6) {
				Answer answer = new Answer(0,obj.getString("value"),null);
				ansList.add(answer);
			}
			
		}
		
		question.setQuestionStatus("Pending");
		Question ques = questionsService.saveQuestion(question);
		
		if(ques.getQuestionType().getQuesTypeId() == 1) {
			ansList.forEach(i->i.setQuestionId(ques.getQuestionId()));
			answerService.saveAnswers(ansList);
		}
		
		resp.setContentType("text/html");
		resp.getWriter().print("Data Saved Successfully");
		
	}
	
	@GetMapping("admin/questions/delete/{questionId}")
	public String deleteQuestion(@PathVariable Integer questionId) {
		answerService.deleteQuestion(questionId);
		questionsService.deleteQuestion(questionId);
		return "redirect:/admin/questions";
	}
	
	@GetMapping("admin/questions/update/{questionId}")
	public String updateQuestion(@PathVariable Integer questionId, Model model) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		List<Question> qList = questionsService.getAllQuestions();
		List<Items> itemList = itemService.getItemList();
		List<QuestionType> quesTypeList = quesTypeService.getAllQuestionType();
		Question question = questionsService.updateQuestion(questionId);
		List<SubItem> subItemList = subItemService.getSubItemListByitemId(question.getItems().getItemId());
		
		hashMap.put("QUESTIONLIST", qList);
		hashMap.put("ITEMLIST", itemList);
		hashMap.put("TYPES", quesTypeList);
		hashMap.put("SUBITEMLIST", subItemList);
		hashMap.put("QUESTION", question);
		
		model.addAllAttributes(hashMap);
		
		
		
		return "admin/questions";
	}
	
	
	
	//void methods for dropdown
	@GetMapping("admin/questions/{itemId}")
	public void getSubItemByItem(@PathVariable Integer itemId, HttpServletResponse resp) throws IOException {
		List<SubItem> subItemList = subItemService.getSubItemListByitemId(itemId);
		resp.setContentType("text/html");
		String temp = "<option value=\"0\">-select-</option>";
		for (SubItem subItem : subItemList) {
		temp += "<option value='"+subItem.getSubItemId()+"'>"+subItem.getSubItemName()+"</option>";
		}
		resp.getWriter().print(temp);
	}
	
	//for approve question
	@GetMapping("admin/questions/approve/{questionId}")
	public String approveQuestion(@PathVariable Integer questionId) {
		Question question = questionsService.getQuestionById(questionId);
		question.setQuestionId(questionId);
		question.setQuestionStatus("Approved");
		questionsService.saveQuestion(question);
		return "redirect:/admin/questions";
	}
}

package com.csmtech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.ExamSetQuestion;
import com.csmtech.entity.Marks;
import com.csmtech.entity.Question;
import com.csmtech.repository.MarksRepository;

@Service
public class MarksServiceImpl implements MarksService {

	@Autowired
	private MarksRepository marksRepository;
	
	@Autowired
	private ExamSetQuestionService examSetQuestionService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private QuestionsService questionsService;
	
	@Override
	public List<Marks> getAllQuestionList() {
		return marksRepository.findAll();
	}

	@Override
	public void saveAllQuestionMarksTable(Integer examSetId, Integer candId) {
		
		marksRepository.deleteByCandidateId(candId);
		List<ExamSetQuestion> list = examSetQuestionService.getAllQuestionByExamId(examSetId);		
		
		List<Marks> marksList = new ArrayList<>();
		for (ExamSetQuestion examSetQuestion : list) {
			marksList.add(new Marks(null,examSetQuestion.getQuestionId(),null,candId,null));
		}	
		System.out.println(marksList);
		marksRepository.saveAll(marksList);
	}

	@Override
	public Marks updateMarks(Integer candidateId, Integer questionId) {
		return marksRepository.findByQuestionIdAndCandidateId(questionId,candidateId);
	}

	@Override
	public void saveMarkswithAnswer(Marks marks) {
		marksRepository.save(marks);
	}

	@Override
	public List<Candidate> getAllSubjectivePendingCheck() {
		List<Marks> marksList  = marksRepository.findAll();
		Set<Integer> set = marksList.stream().filter(t-> (t.getMarks() != null && t.getMarks() == -1.0)).map(t->t.getCandidateId()).collect(Collectors.toSet());
		List<Candidate> candList = new ArrayList<>();
		if(set.size() > 0) {
			for (Integer integer : set) {
				candList.add(candidateService.getCandidateInfo(integer));
			}
		}
		return candList;
	}

	@Override
	public List<List<Object>> getAllSubjectivePendingForCheck(Integer candId, Integer examSetId) {
		Set<Integer> set = marksRepository.findAll().stream().filter(t-> t.getCandidateId() == candId && t.getMarks() != null && t.getMarks() == -1.0 ).map(t-> t.getQuestionId()).collect(Collectors.toSet());
		
		List<List<Object>> outerList = new ArrayList<>();
		for (Integer integer : set) {
			Question question = questionsService.getQuestionById(integer);
			List<Object> lst = new ArrayList<>();
			lst.add(question.getQuestionId());
			lst.add(question.getQuestionText());
			lst.add(question.getCurrectAnswer());
			Marks marks = marksRepository.findAll().stream().filter(t-> t.getCandidateId() == candId && t.getQuestionId() == question.getQuestionId() ).collect(Collectors.toList()).get(0);
			lst.add(marks.getCandidateAnswer());
			outerList.add(lst);
			
		}
		return outerList;
	}

	@Override
	public Marks getMarksInfoByCandIdAndQuesId(Integer candId, Integer quesId) {
		return marksRepository.findByQuestionIdAndCandidateId(quesId, candId);
	}

	@Override
	public Integer examCompleteAlready(Integer integer) {
		List<Marks> collect = marksRepository.findAll().stream().filter(t->t.getCandidateId() != null && t.getCandidateId() == integer).collect(Collectors.toList());
		Integer res = 1;
		for (Marks marks : collect) {
			if(marks.getMarks() == null) {
				res = 0;
			}
		}
		return res;
	}

	@Override
	public void answerNotGivenQuestion(Integer candId) {
		List<Marks> collect = marksRepository.findAll().stream().filter(t->t.getCandidateId() == candId).collect(Collectors.toList());
		for (Marks marks : collect) {
			if(marks.getCandidateAnswer() == null) {
				marks.setMarksId(marks.getMarksId());
				marks.setMarks(0.0);
				marks.setCandidateAnswer("Answer Not Given By Candidate");
				marksRepository.save(marks);
			}
		}
	}

}

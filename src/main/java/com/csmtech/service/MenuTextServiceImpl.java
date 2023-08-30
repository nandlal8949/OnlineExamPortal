package com.csmtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csmtech.bean.MenuText;

@Service
public class MenuTextServiceImpl implements MenuService {

	@Override
	public List<MenuText> getAllMenus(Integer roleId) {
		ArrayList<MenuText> list = new ArrayList<>();

		if (roleId == 1) {
			list.add(new MenuText("home", "Home", "admin/admin"));
			list.add(new MenuText("manage/users", "Manage Users", "admin/users"));
			list.add(new MenuText("manage/candidate", "Manage Candidate", "admin/candidate"));
			list.add(new MenuText("manage_exam", "Manage exam", "admin/exam"));
			list.add(new MenuText("manage_question", "Manage Question", "admin/questions"));
		} else if (roleId == 2) {
			list.add(new MenuText("home", "Home", "proctor/proctor"));
			list.add(new MenuText("manage/candidate", "Manage Candidate", "admin/candidate"));
			list.add(new MenuText("manage_exam", "Manage exam", "admin/exam"));
			list.add(new MenuText("manage_question", "Manage Question", "admin/questions"));
		} else if (roleId == 3) {
			list.add(new MenuText("home", "Home", "hr/hr"));
			list.add(new MenuText("manage/users", "Manage Users", "admin/users"));
			list.add(new MenuText("manage/result", "Result", "hr/resultlist"));
		}
		return list;
	}

	@Override
	public List<MenuText> getOtherMenus(Integer roleId) {
		ArrayList<MenuText> list = new ArrayList<>();

		if (roleId == 1) {
			list.add(new MenuText("manage/rolelist", "Role", "admin/rolelist"));
			list.add(new MenuText("manage/items", "Items", "admin/items"));
			list.add(new MenuText("manage_subitems", "subitems", "admin/subitems"));
			list.add(new MenuText("manage_questionset", "ExamSet", "admin/examset"));
			list.add(new MenuText("exam_manage", "Exam", "admin/exam"));
			list.add(new MenuText("manage_set", "Add question in Set", "admin/addquestioninset"));
			list.add(new MenuText("check_subject_question", "Check Subjective Question", "admin/checksubques"));
			list.add(new MenuText("Candidate not get ExamSet", "Candidate not get ExamSet", "admin/candidateassignmentlist"));
			list.add(new MenuText("ExamSet Not Complete !", "ExamSet Not Complete !", "admin/examset"));
			list.add(new MenuText("Questions Approval Pending", "Questions Approval Pending", "admin/questions"));
			list.add(new MenuText("Total Candidates", "Total Candidates", "admin/candidate"));
			list.add(new MenuText("Total Questions", "Total Questions", "admin/questions"));
		}
		return list;
	}

	@Override
	public List<MenuText> getCandidateMenus() {
		ArrayList<MenuText> list = new ArrayList<>();
			list.add(new MenuText("candidate_test", "Test", "candidate/test"));
			list.add(new MenuText("candidate_result", "Result", "candidate/result"));		
		return list;
	}

}

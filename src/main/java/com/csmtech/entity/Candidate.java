package com.csmtech.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer candId;

	private String candFirstname;	
	private String candLastname;
	private String candGender;
	private String candAddress;
	private String candMobile;
	private String candEmail;
	private String candDob;
	private String candUserName;
	private String candPassword;
	private String candExamDate;
	private String isDelete;
	
	@ManyToOne(targetEntity = Exam.class)
	@JoinColumn(name = "examId", foreignKey = @ForeignKey(name ="candidate_exam_foreign_key") )
	private Exam exam;
	
	@ManyToOne(targetEntity = ExamSet.class)
	@JoinColumn(name = "examSetId", foreignKey = @ForeignKey(name ="candidate_examset_foreign_key") )
	private ExamSet examSet;
}

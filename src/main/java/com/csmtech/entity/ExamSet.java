package com.csmtech.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examSetId;
	private String examSetName;
	private Integer timeLimit;
	private Double passScore;
	private Double totalScore;
	private Integer objQues;
	private Integer subQues;
	
	@ManyToOne(targetEntity = Items.class)
	@JoinColumn(name ="itemId", foreignKey = @ForeignKey(name ="examset_item_foreign_key"))
	private Items items;
	
	@OneToMany(targetEntity = ExamSetQuestion.class, mappedBy = "examCodeId")
	private List<ExamSetQuestion> examSetQuestion;
	
}

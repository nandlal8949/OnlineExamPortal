package com.csmtech.entity;

import java.util.List;

import javax.persistence.Column;
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
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionId;
	private String questionText;
	@Column(length = 700)
	private String questionCode;
	
	private String questionStatus;
	private String currectAnswer;
	
	@ManyToOne(targetEntity = QuestionType.class)
	@JoinColumn(name ="quesTypeId", foreignKey = @ForeignKey(name ="questions_question_type_foreign_key"))
	private QuestionType questionType;
	
	@ManyToOne(targetEntity = Items.class)
	@JoinColumn(name ="itemId", foreignKey = @ForeignKey(name ="questions_item_foreign_key"))
	private Items items;
	
	@ManyToOne(targetEntity = SubItem.class)
	@JoinColumn(name ="subItemId", foreignKey = @ForeignKey(name ="questions_subitem_foreign_key"))
	private SubItem subItem;
	
	@OneToMany(mappedBy = "questionId", targetEntity = Answer.class)
	private List<Answer> answer;
}

package br.trcs.ptg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Questões sorteadas que compõem determinado teste
 */
@Entity
@Table(name = "questions_test")
public class QuestionsTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "testId", nullable = false)
	private Test testId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "questionId", nullable = false)
	private Question questionId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Test getTestId() {
		return testId;
	}

	public void setTestId(Test testId) {
		this.testId = testId;
	}

	public Question getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Question questionId) {
		this.questionId = questionId;
	}
}
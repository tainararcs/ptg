package br.trcs.ptg.model;

import java.time.LocalDate;

import br.trcs.ptg.enums.Bimester;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tests")
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
    private LocalDate date;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "userId", nullable = false)
	private User userID;

	/**
	 * Disciplina a qual pertence o teste.
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "subjectId", nullable = false)
	private Subject subjectId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Bimester bimester;
	
	@Column(nullable = false)
	private int numberCorrectAnswers;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public Subject getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Subject subjectId) {
		this.subjectId = subjectId;
	}

	public Bimester getBimester() {
		return bimester;
	}

	public void setBimester(Bimester bimester) {
		this.bimester = bimester;
	}

	public int getNumberCorrectAnswers() {
		return numberCorrectAnswers;
	}

	public void setNumberCorrectAnswers(int numberCorrectAnswers) {
		this.numberCorrectAnswers = numberCorrectAnswers;
	}
}
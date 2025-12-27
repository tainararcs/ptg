package br.trcs.ptg.enums;

public enum Grade {
	
	FIRST_GRADE("Primeiro ano"),
	SECOND_GRADE("Segundo ano"),
	THIRD_GRADE("Terceiro ano");
	
	
	private String grade;

	
	private Grade(String grade) {
		this.setGrade(grade);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
package br.trcs.ptg.enums;

public enum Grade {
	
	FIRST_GRADE("Primeiro ano"),
	SECOND_GRADE("Segundo ano"),
	THIRD_GRADE("Terceiro ano");
	
	
	private String grade;

	
	private Grade(String grade) {
		this.grade = grade;
	}

//
//	public String getGrade() {
//		return grade;
//	}	
//	
//	// Método para obter o nome legível
//    @Override
//    public String toString() {
//        return super.toString().toLowerCase(); 
//    }
}

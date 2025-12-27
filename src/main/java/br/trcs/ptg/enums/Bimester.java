package br.trcs.ptg.enums;

public enum Bimester {
	
	FIRST_BIMESTER("Primeiro Bimestre"),
	SECOND_BIMESTER("Segundo Bimestre"),
	THIRD_BIMESTER("Terceiro Bimestre"),
	FOURTH_BIMESTER("Quarto Bimestre");
	
	private String bimester;

	private Bimester(String bimester) {
		this.bimester = bimester;
	}

	public String getBimester() {
		return bimester;
	}

	public void setBimester(String bimester) {
		this.bimester = bimester;
	}
}
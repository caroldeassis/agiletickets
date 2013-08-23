package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	DIARIA(1), 
	SEMANAL(7);
	
	private final int id;
	
	private Periodicidade(int id) {
		this.id = id;
	}
	
	public int getPeriodicidadeEmDias() {
		return id;
	}
}

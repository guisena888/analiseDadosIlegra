package com.ilegra.analiseDados.vo;

public class Venda {
	private int id;
	private double valor;
	
	public Venda(int id, double valor) {
		super();
		this.id = id;
		this.valor = valor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}

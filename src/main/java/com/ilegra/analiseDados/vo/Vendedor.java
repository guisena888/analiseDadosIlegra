package com.ilegra.analiseDados.vo;

public class Vendedor {
	private int id;
	private String nome;
	private double valorVendido;
	
	public Vendedor(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Vendedor(int id, String nome, double valorVendido) {
		super();
		this.id = id;
		this.nome = nome;
		this.valorVendido = valorVendido;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValorVendido() {
		return valorVendido;
	}
	public void setValorVendido(double valorVendido) {
		this.valorVendido = valorVendido;
	}
	
	
}

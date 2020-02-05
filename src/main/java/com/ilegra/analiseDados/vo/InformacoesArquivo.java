package com.ilegra.analiseDados.vo;

public class InformacoesArquivo {
	private int qtdeClientes;
	private int qtdeVendedores;
	private int idVendaMaisCara;
	private String piorVendedor;
	public InformacoesArquivo() {
		super();
	}
	
	public InformacoesArquivo(int qtdeClientes, int qtdeVendedores, int idVendaMaisCara, String piorVendedor) {
		super();
		this.qtdeClientes = qtdeClientes;
		this.qtdeVendedores = qtdeVendedores;
		this.idVendaMaisCara = idVendaMaisCara;
		this.piorVendedor = piorVendedor;
	}

	public int getQtdeClientes() {
		return qtdeClientes;
	}
	public void setQtdeClientes(int qtdeClientes) {
		this.qtdeClientes = qtdeClientes;
	}
	public int getQtdeVendedores() {
		return qtdeVendedores;
	}
	public void setQtdeVendedores(int qtdeVendedores) {
		this.qtdeVendedores = qtdeVendedores;
	}
	public int getIdVendaMaisCara() {
		return idVendaMaisCara;
	}
	public void setIdVendaMaisCara(int idVendaMaisCara) {
		this.idVendaMaisCara = idVendaMaisCara;
	}
	public String getPiorVendedor() {
		return piorVendedor;
	}
	public void setPiorVendedor(String piorVendedor) {
		this.piorVendedor = piorVendedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVendaMaisCara;
		result = prime * result + ((piorVendedor == null) ? 0 : piorVendedor.hashCode());
		result = prime * result + qtdeClientes;
		result = prime * result + qtdeVendedores;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacoesArquivo other = (InformacoesArquivo) obj;
		if (idVendaMaisCara != other.idVendaMaisCara)
			return false;
		if (piorVendedor == null) {
			if (other.piorVendedor != null)
				return false;
		} else if (!piorVendedor.equals(other.piorVendedor))
			return false;
		if (qtdeClientes != other.qtdeClientes)
			return false;
		if (qtdeVendedores != other.qtdeVendedores)
			return false;
		return true;
	}
	
	
}

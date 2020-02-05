package com.ilegra.analiseDados.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import com.ilegra.analiseDados.exception.AnaliseDadosException;
import com.ilegra.analiseDados.util.LogUtil;
import com.ilegra.analiseDados.util.PropertiesReader;
import com.ilegra.analiseDados.vo.InformacoesArquivo;
import com.ilegra.analiseDados.vo.Venda;
import com.ilegra.analiseDados.vo.Vendedor;

public class AnaliseDadosService {
	private FileService fileService;
	private PropertiesReader propertiesReader;
	private String[] linha;
	private InformacoesArquivo informacoesArquivo;
	private ArrayList<Vendedor> vendedores;
	
	
	public AnaliseDadosService() {
		fileService = new FileService();
		propertiesReader = new PropertiesReader("/config.properties");
	}

	public void processar() throws Exception {
		try{
			String userHome = System.getProperty("user.home");
			String dirEntrada =  userHome + propertiesReader.getStringValue("diretorio.entrada");
			String dirSaida =  userHome + propertiesReader.getStringValue("diretorio.saida");
			String dirLidos =  userHome + propertiesReader.getStringValue("diretorio.historico");
			LogUtil.info("Buscando novos arquivos no diretório de entrada...");
			File[] files = fileService.buscarArquivo(dirEntrada);
			for(File file : files) {
				LogUtil.info("Lendo arquivo "+ file.getName());
				InformacoesArquivo informacoesArquivo = lerArquivo(file);
				LogUtil.info("Arquivo lido com sucesso!");
				LogUtil.info("Criando arquivo de saída...");
				String arquivoSaida = criarArquivoSaida(informacoesArquivo, dirSaida, file.getName());
				LogUtil.info("Arquivo de saída criado com sucesso : " + arquivoSaida);
				fileService.moverArquivo(file, dirLidos);
				LogUtil.info("Arquivo movido para a pasta de histórico");
			}
		}catch(Exception e) {
			throw new AnaliseDadosException("Falha no processamento dos arquivos" + e, e);
		}
	}

	public InformacoesArquivo lerArquivo(File file) throws AnaliseDadosException {
		try {
			double valorVenda;
			informacoesArquivo = new InformacoesArquivo(0,0,0,"");
			vendedores = new ArrayList<Vendedor>();
			Vendedor piorVendedor = new Vendedor(0,"PiorDefault");
			Venda vendaMaisCara = new Venda(0,0);
			
			Scanner scanner = new Scanner(new FileInputStream(file), "ISO-8859-1");
			while(scanner.hasNextLine()) {
				linha = scanner.nextLine().split("ç");
				
				//vendedor
				if(linha[0].equals("001")) {
            		informacoesArquivo.setQtdeVendedores(informacoesArquivo.getQtdeVendedores()+1);
            		vendedores.add(new Vendedor(
            				Integer.parseInt(linha[0]),
            				linha[2].trim()
            				));
            	
            	//cliente
            	}else if(linha[0].equals("002")) {
            		informacoesArquivo.setQtdeClientes(informacoesArquivo.getQtdeClientes()+1);
            		
            	//venda
            	}else if(linha[0].equals("003")) {
            		valorVenda = calcularPrecoVenda(linha[2]);
            		String nome = linha[3].trim();
            		Optional<Vendedor> vendedor = vendedores
            				.stream()
            				.filter(x -> x.getNome().equals(nome))
            				.findFirst();
            		//Alterando valor vendido por vendedor para determinar o pior
            		vendedor.get().setValorVendido(vendedor.get().getValorVendido()+valorVenda);
            		
            		if(vendedor.get().getValorVendido() > vendaMaisCara.getValor()) {
            			vendaMaisCara.setId(Integer.parseInt(linha[1]));
            			vendaMaisCara.setValor(vendedor.get().getValorVendido());
            		}
            	}				
			}
			piorVendedor = vendedores
					.stream()
					.min(Comparator.comparing(Vendedor::getValorVendido))
					.orElseThrow(NoSuchElementException::new);
			informacoesArquivo.setPiorVendedor(piorVendedor.getNome());
			informacoesArquivo.setIdVendaMaisCara(vendaMaisCara.getId());			
			scanner.close();
			return informacoesArquivo;
		} catch (Exception e) {
			throw new AnaliseDadosException("Falha na leitura dos arquivos: " + e, e);
		}
	}
	
	public double calcularPrecoVenda(String vendas) throws AnaliseDadosException {
		double precoTotal = 0;
		try{
			vendas = vendas.replace("[", "");
			vendas = vendas.replace("]", "");
			String[] produtoDetalhado;
			String[] produtos = vendas.split(",");
			for (String produto : produtos) {
				produtoDetalhado = produto.split("-");
				precoTotal += Integer.parseInt(produtoDetalhado[1]) * Double.parseDouble(produtoDetalhado[2]);
			}
			return precoTotal;
		}catch(Exception e) {
			throw new AnaliseDadosException("Falha no cálculo do preço de venda: " + e, e);
		}
	}
	
	public String criarArquivoSaida(InformacoesArquivo informacoesArquivo, String dirSaida, String fileName) throws AnaliseDadosException {
		try {
			File arquivoSaida = new File(dirSaida + fileName + ".out");
			FileWriter writer = new FileWriter(arquivoSaida);
			writer.write("Quantidade de clientes: " + informacoesArquivo.getQtdeClientes() + "\n");
			writer.write("Quantidade de vendedores: " + informacoesArquivo.getQtdeVendedores() + "\n");
			writer.write("ID da venda mais cara: " + informacoesArquivo.getIdVendaMaisCara() + "\n");
			writer.write("Pior vendedor: " + informacoesArquivo.getPiorVendedor() + "\n");
			writer.close();
			
			return arquivoSaida.getName();
		} catch (IOException e) {
			throw new AnaliseDadosException("Falha ao criar Arquivo de saída: " + e, e);
		}
	}
}

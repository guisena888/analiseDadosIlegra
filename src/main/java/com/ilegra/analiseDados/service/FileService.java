package com.ilegra.analiseDados.service;

import java.io.File;

public class FileService {

	//Busca arquivo mais antigo no diretório solicitado
	public File[] buscarArquivo(String dir) {
		
		File file = new File(dir);
		File[] files = file.listFiles();
		return files;
		
	}
	
	public void moverArquivo(File file, String dir) {
		file.renameTo(new File(dir, file.getName()));
	}
	
}

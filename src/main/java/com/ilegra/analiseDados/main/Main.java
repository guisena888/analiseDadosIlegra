package com.ilegra.analiseDados.main;

import com.ilegra.analiseDados.service.AnaliseDadosService;

public class Main 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	AnaliseDadosService analiseDadosService = new AnaliseDadosService();
    	//Programa roda continuamente, com um intervalo de tempo determinado pelo comando sleep
    	while(true) {
        	try{
        		analiseDadosService.processar();
	        	Thread.sleep(1000);
        	}catch(Exception e) {
        		
        	}
        }
    }
}

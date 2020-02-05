package com.ilegra.analiseDados.analiseDados;

import java.io.File;

import com.ilegra.analiseDados.exception.AnaliseDadosException;
import com.ilegra.analiseDados.service.AnaliseDadosService;
import com.ilegra.analiseDados.vo.InformacoesArquivo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase
{
	
	AnaliseDadosService analiseDadosService = new AnaliseDadosService();

    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    public void testLeitura() throws AnaliseDadosException{	
    	File file = new File(getClass().getClassLoader().getResource("teste.txt").getFile());
    	InformacoesArquivo informacoesTeste = analiseDadosService.lerArquivo(file);
    	InformacoesArquivo informacoesTesteCorreta = new InformacoesArquivo(2,3,10,"Guilherme");
    	
        assertEquals( informacoesTeste, informacoesTesteCorreta );
    }
    
    public void testCalculoValor() throws AnaliseDadosException {
    	String valorTexto = "[1-34-10,2-33-1.50,3-40-0.10]";
    	double valorVenda = analiseDadosService.calcularPrecoVenda(valorTexto);
    	
    	assertEquals(valorVenda, 393.5, 0.0);
    }
    
    public void testCriarArquivoSaida() throws AnaliseDadosException {
    	InformacoesArquivo informacoesArquivo = new InformacoesArquivo(2,3,10,"Guilherme");
    	analiseDadosService.criarArquivoSaida(informacoesArquivo, System.getProperty("user.home") + "\\data\\out", "\\teste.txt");
    }
    
    
}

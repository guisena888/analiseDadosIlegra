package com.ilegra.analiseDados.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	private String path;

	public PropertiesReader(String path) {
		this.path = path;
	}

	public String getStringValue(String key) {

		Properties prop = new Properties();

		try {

			InputStream input = PropertiesReader.class.getResourceAsStream(path);
			prop.load(input);

			if (null != input) {
				input.close();
			}

		} catch (IOException e) {
			LogUtil.error("Erro ao buscar valor no arquivo de properties: " + path, e);
		}

		return prop.getProperty(key);
	}
}

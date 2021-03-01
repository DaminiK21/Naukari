package Naukri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public String getProperty(String propName) throws IOException {
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\config\\globalParamters.properties";
		
		String value;
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
		value= prop.getProperty(propName);
		return value;
		
	}
	
	

}

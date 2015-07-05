package common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	public static String getConfig(String key){
		InputStream inStream;
		Properties config_ = new Properties();	
		String configPath = "config.properties";
		String[] configPaths = new String[]{configPath};
		for(String confPath :configPaths){
			inStream = PropertiesManager.class.getClassLoader().getResourceAsStream(confPath);
			try {
				config_.load(inStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String retStr = config_.getProperty(key);
		return retStr;
	}
}

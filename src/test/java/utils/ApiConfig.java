package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiConfig {

    private static Properties properties;

    static {
        try{
            properties = new Properties();
            FileInputStream file = new FileInputStream("src\\test\\resources\\apitestdata.properties");
            properties.load(file);
            file.close();
        }catch (IOException e){
            System.out.println("Configuration file not found");
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

}

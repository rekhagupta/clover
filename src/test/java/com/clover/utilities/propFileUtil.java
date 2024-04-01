package com.clover.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class propFileUtil {
    private static Properties props;
    static {
        try{
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/search.properties");
            props = new Properties();
            props.load(in);
            in.close();;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load Properties");
        }
    }

    public static String getProperty(String key){
        return props.getProperty(key);
    }

//    public Properties getProperty(String fileName) {
//        try {
//            FileInputStream fileInput = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/" + fileName + ".properties"));
//            Properties properties = new Properties();
//            properties.load(fileInput);
//            return properties;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class propFileUtil {

    public Properties getProperty(String fileName) {
        try {
            FileInputStream fileInput = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/" + fileName + ".properties"));
            Properties properties = new Properties();
            properties.load(fileInput);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
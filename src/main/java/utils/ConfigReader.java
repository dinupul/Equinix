package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    /**
     * This method is used to load properties from config.properties file
     * @return Properties
     */
    public Properties initprop(){
        prop = new Properties();
        try {
            FileInputStream filein = new FileInputStream("src/test/resources/config/config.properties");
       prop.load(filein);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}

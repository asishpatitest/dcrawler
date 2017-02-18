package com.nvharikrishna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class AppConfig {
    final static Properties properties = new Properties();
    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private static boolean initialized = false;

    private static void init() {

        InputStream inputStream = null;
        try {
            inputStream = AppConfig.class.getClassLoader().getResourceAsStream("appConfig.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error while loading confguration", e);
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //Ignore it. Don't need it now.
                }
            }
        }
    }

    public static String get(String key){
        if(!initialized)
            init();

        return (String) properties.get(key);
    }
}

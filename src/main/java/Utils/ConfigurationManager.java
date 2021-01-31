package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//ConfigurationManager - Singleton Design Pattern

public class ConfigurationManager {
    //volatile - the value of this variable will be stored in main memory, not only in the thread
    private static volatile ConfigurationManager instance;
    private String hubUrl;
    private String baseUrl;
    private String browser;
    private final String configurationLocation = "src/configs/Configuration.properties";

    private ConfigurationManager(){
        loadData();
    }

    public static ConfigurationManager getInstance() {
        if(instance==null) {
            //only one thread can use this method in the same time
            synchronized (ConfigurationManager.class) {
                if(instance==null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadData() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configurationLocation));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("This is something wrong with the configuration file. " +
                    "File location: " + ConfigurationManager.getInstance().getConfigurationLocation());
        }
        hubUrl = properties.getProperty("hubUrl");
        baseUrl = properties.getProperty("baseUrl");
        browser = properties.getProperty("browser");
    }

    public String getBrowser() {
        return browser;
    }

    public String getHubUrl() {
        return hubUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getConfigurationLocation() {
        return configurationLocation;
    }
}
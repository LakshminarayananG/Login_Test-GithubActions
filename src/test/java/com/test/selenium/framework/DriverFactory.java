package com.test.selenium.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.test.selenium.enums.Browser;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    static WebDriver driver = null;
    static Properties globalProperties = Settings.getInstance();

    public static WebDriver getWebDriver(Browser browser)  {

        switch(browser){

            case CHROME:
                WebDriverManager.chromedriver().setup(); // method to get the webdriver dynamically
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--silent");
//                options.addArguments("--incognito");
                options.addArguments("--remote-allow-origins=*");
                options.setCapability("se:recordVideo", true);
                options.setCapability("se:screenResolution", "1920x1080");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case CHROME_HEADLESS:
                WebDriverManager.chromedriver().setup(); // method to get the webdriver dynamically
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless");
                chOptions.addArguments("--incognito");
                chOptions.addArguments("--remote-allow-origins=*");
                chOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chOptions);
                driver.manage().window().maximize();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;

            default:
                driver = null;
        }

        return driver;
    }

    public static WebDriver getRemoteWebDriver(Browser browser)  {

        WebDriver driver = null;
        Properties globalProperties = Settings.getInstance();
        switch(browser){

            case CHROME:
                WebDriverManager.chromedriver().setup(); // method to get the webdriver dynamically
                ChromeOptions RemoteChOptions = new ChromeOptions();
                if(globalProperties.getProperty("enableVideoRecording").equalsIgnoreCase("YES") || globalProperties.getProperty("enableVideoRecording").equalsIgnoreCase("TRUE")) {
                    RemoteChOptions.setCapability("se:recordVideo", true);
                    RemoteChOptions.setCapability("se:screenResolution", "1920x1080");
                    RemoteChOptions.setCapability("applicationCacheEnabled", false);
                }
                try {
                    String url = globalProperties.getProperty("remoteDriverUrl");
                    driver = new RemoteWebDriver(new URL(url), RemoteChOptions);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                driver.manage().window().maximize();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup(); // method to get the webdriver dynamically
                FirefoxOptions RemoteFFOptions = new FirefoxOptions();
                if(globalProperties.getProperty("enableVideoRecording").equalsIgnoreCase("YES") || globalProperties.getProperty("enableVideoRecording").equalsIgnoreCase("TRUE")) {
                    RemoteFFOptions.setCapability("se:recordVideo", true);
                    RemoteFFOptions.setCapability("se:screenResolution", "1920x1080");
                    RemoteFFOptions.setCapability("applicationCacheEnabled", false);
                }
                try {
                    String url = globalProperties.getProperty("remoteDriverUrl");
                    driver = new RemoteWebDriver(new URL(url), RemoteFFOptions);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                driver.manage().window().maximize();
                break;

        case EDGE:
            WebDriverManager.edgedriver().setup(); // method to get the webdriver dynamically
            EdgeOptions RemoteEdOptions = new EdgeOptions();
            if(globalProperties.getProperty("enableVideoRecording").equalsIgnoreCase("YES") || globalProperties.getProperty("enableVideoRecording").equalsIgnoreCase("TRUE")) {
                RemoteEdOptions.setCapability("se:recordVideo", true);
                RemoteEdOptions.setCapability("se:screenResolution", "1920x1080");
                RemoteEdOptions.setCapability("applicationCacheEnabled", false);
            }
            try {
                String url = globalProperties.getProperty("remoteDriverUrl");
                driver = new RemoteWebDriver(new URL(url), RemoteEdOptions);
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            driver.manage().window().maximize();
            break;

            default:
                driver = null;
        }

        return driver;
    }
}

package java.Com.Vishnu.acs.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BrowserDriverUtil {

    private static WebDriver driver = null;

    // Method to initialize the WebDriver based on the browser choice
    public static WebDriver getDriver(String browserType) {
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    driver = getChromeDriver();
                    break;
                case "firefox":
                    driver = getFirefoxDriver();
                    break;
                case "edge":
                    driver = getEdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser type not supported: " + browserType);
            }
        }
        return driver;
    }

    // Method to initialize Chrome WebDriver
    private static WebDriver getChromeDriver() {
        String chromeDriverPath = System.getProperty("user.dir") + "/drivers/chromedriver"; // Modify path as needed
        File chromeDriverFile = new File(chromeDriverPath);

        if (!chromeDriverFile.exists()) {
            throw new IllegalStateException("Chrome driver not found at: " + chromeDriverPath);
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start maximized
        options.addArguments("--disable-notifications"); // Disable notifications

        // Add any other custom options here

        WebDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        return chromeDriver;
    }

    // Method to initialize Firefox WebDriver
    private static WebDriver getFirefoxDriver() {
        String firefoxDriverPath = System.getProperty("user.dir") + "/drivers/geckodriver"; // Modify path as needed
        File firefoxDriverFile = new File(firefoxDriverPath);

        if (!firefoxDriverFile.exists()) {
            throw new IllegalStateException("Firefox driver not found at: " + firefoxDriverPath);
        }

        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized"); // Start maximized
        options.addArguments("--disable-notifications"); // Disable notifications

        // Add any other custom options here

        WebDriver firefoxDriver = new FirefoxDriver(options);
        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        return firefoxDriver;
    }

    // Method to initialize Edge WebDriver
    private static WebDriver getEdgeDriver() {
        String edgeDriverPath = System.getProperty("user.dir") + "/drivers/msedgedriver"; // Modify path as needed
        File edgeDriverFile = new File(edgeDriverPath);

        if (!edgeDriverFile.exists()) {
            throw new IllegalStateException("Edge driver not found at: " + edgeDriverPath);
        }

        System.setProperty("webdriver.edge.driver", edgeDriverPath);

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized"); // Start maximized
        options.addArguments("--disable-notifications"); // Disable notifications

        // Add any other custom options here

        WebDriver edgeDriver = new EdgeDriver(options);
        edgeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        return edgeDriver;
    }

    // Method to quit the driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


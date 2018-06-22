package moproDCC.moproDCC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utilities.moproDCC.WebEventListener;

public class baseFile {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver eventdriver;
	public static WebEventListener eventlisten;
	
	
	public baseFile() {		
		try {			
			FileInputStream fl = new FileInputStream("C:\\Users\\einsteinjackson\\eclipse-workspace\\moproDCC\\src\\main\\java\\property\\moproDCC\\object.properties");
			prop = new Properties();
			prop.load(fl);
		} catch(FileNotFoundException exep) {
			exep.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
	}	
	public static void initialize() {		
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\einsteinjackson\\Desktop\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "");
			driver = new EdgeDriver();		
	} else if(browserName.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver", "");
		driver = new FirefoxDriver();
	} else {System.out.println("Invalid Browser Name");}
		
		
		eventdriver = new EventFiringWebDriver(driver);
		eventlisten = new WebEventListener();
		eventdriver.register(eventlisten);
		driver = eventdriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
  } 	
	
}
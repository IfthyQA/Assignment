package screenshotOnFailure;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class FailScreenshot {

	public  FileLib flib=new FileLib();
	public  WebDriver driver=null;	
	public  String path="C:\\Users\\dev\\eclipse-workspace\\Personalbranding\\src\\screenshot\\data\\commonData.properties";
	public static WebDriver listenerDriver=null;
	
	
	@BeforeClass
	public  void configBC() throws InterruptedException {
		if(flib.getPropertyKeyValue(path, "browser").equals("chrome"))			
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\dev\\AppData\\chromedriver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox"); // Bypass OS security model
			driver = new ChromeDriver(options);
			listenerDriver=driver;
		}				
	}	
	
	@BeforeMethod
	public void configBm() throws InterruptedException
	{
		/*Step 1 : Launch the application*/		
		driver.get(flib.getPropertyKeyValue(path,"url"));
		driver.navigate().refresh();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	}

	@AfterMethod
	public void configAM()
	{			
		/*Verify the Logout Successful*/
		String expLogoutTitle = "ACHNET: Meet your career and business goals at Achiever Network".replaceAll(" ", "");
		String actLogoutTitle = driver.getTitle().replaceAll(" ", "");

		if(actLogoutTitle.equals(expLogoutTitle))
		{
			System.out.println("Logout is Successful ==> Test case PASSED");
		}
		else
			System.out.println("Logout is not Successful ==> Test Case FAILED");
	}

	@AfterClass
	public void configAC() throws InterruptedException
	{
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}
	}

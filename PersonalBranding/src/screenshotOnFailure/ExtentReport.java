package screenshotOnFailure;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ExtentReport {

	public  FileLib flib=new FileLib();
	public  WebDriver driver=null;
	public  String path="C:\\Users\\dev\\eclipse-workspace\\Personalbranding\\src\\screenshot\\data\\commonData.properties";
	public static WebDriver listenerDriver=null;
	
	
	@BeforeClass
	public  void configBC() {
		if(flib.getPropertyKeyValue(path, "browser").equals("chrome"))
		{
			driver=new ChromeDriver();
			listenerDriver=driver;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}	
	
	@BeforeMethod
	public void configBm()
	{
		/*Step 1 : Launch the application*/
		driver.get(flib.getPropertyKeyValue(path,"url"));
		
	}

	@AfterMethod
	public void configAM()
	{			
		/*Verify the LogOut Successful*/
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
	public void configAC()
	{
		driver.quit();
	}
	}

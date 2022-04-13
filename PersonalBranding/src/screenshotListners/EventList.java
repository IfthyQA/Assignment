package screenshotListners;

import java.io.File;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import screenshotOnFailure.FailScreenshot;

public class EventList implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		
		//get system date
		Date d= new Date();
		String date=d.toString().replaceAll(":", "_");
		System.out.println(date);
		
		//get only day from date
		int day=d.getDate();
		
		//split the whole date
		String[] arr= date.split(" ");
		
		//get the month
		String month=arr[1];
		
		//get the year
		String year=arr[5];
		
		//get the time
		String time=arr[3].replaceAll(":", "_");
		
		String path=day+" "+month+" "+year+" "+time;
		
		EventFiringWebDriver ef= new EventFiringWebDriver(FailScreenshot.listenerDriver);
		
		File src= ef.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("./ScreenShots/"+path+"_"+methodName+".png");
		
		try{
		FileUtils.copyFile(src, dest);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}



package softWay;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import screenshotOnFailure.FailScreenshot;

public class Flipkart extends FailScreenshot {

	@Test
	public void Cart() throws InterruptedException, IOException {

	    /* Navigate and Land on Flipkart */ 
	    WebDriverWait wait=new WebDriverWait(driver, 180);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/button")));Thread.sleep(3000);	
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/button")));Thread.sleep(3000);

	    /* Close the Login Pop-up */ 
	    driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
	    
	    /* Navigate to Mobiles */ 
	    driver.findElement(By.xpath("//div[contains(text(),\'Mobiles\')]")).click();
	    
	    /* Navigate to Mobiles List from Electronics Menu (Left Side) */ 
	    driver.findElement(By.linkText("Mobiles")).click();
	    	    	    
	    /* Click the First Mobiles */
	    String winHandleBefore = driver.getWindowHandle();
	    driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/a[1]/div[2]/div[1]/div[1]")).click();
	    for(String winHandle : driver.getWindowHandles()){
	        driver.switchTo().window(winHandle);
	    	}
	    /* Print the Price of the Mobile */
	    String price=driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[3]/div/div[4]/div[1]/div/div[1]")).getText();
	    System.out.println(price);
	    
	    /* Add to Cart */	    
	    driver.findElement(By.xpath("//button[contains(.,\' ADD TO CART\')]")).click();
	    
	    /* Increase the Iteam in the Cart */
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,\'+\')]")));Thread.sleep(3000);	
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,\'+\')]")));Thread.sleep(3000);	
	    driver.findElement(By.xpath("//button[contains(.,\'+\')]")).click();
	    
	    /* Print the Price After increased the Count */
	    String Incprice=driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[1]/span[1]")).getText();
	    System.out.println(Incprice);
	    
	    /* Close the New Tab which opened for Mobile */
	    driver.close();
	    driver.switchTo().window(winHandleBefore);
	}

}

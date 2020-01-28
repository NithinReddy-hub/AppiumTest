package com.ggktech.PublicLibrary;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;


import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;








public class PublicLibrary {
	Properties prop = new Properties();
	public static AndroidDriver<MobileElement> driver;
	//public static Logger logger= Logger.getLogger(PublicLibrary.class);
	public static Logger logger =Logger.getLogger(PublicLibrary.class);
 
	public static void launchApp() throws MalformedURLException, InterruptedException
	{
	    DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("deviceName","Android Emulator");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("platformVersion", "8.0"); 
		//capabilities.setCapability("automationName","AppiumTest");
		//capabilities.setCapability("app","C:\\Users\\nithin.thumalapalli\\AppiumProject\\src\\main\\resources\\Data\\eBay Online Shopping Buy Sell and Save Money_v5.38.0.14_apkpure.com.apk");
		// This package name of your app (you can get it from apk info app)
		capabilities.setCapability("appPackage","com.ebay.mobile"); 
		capabilities.setCapability("appActivity","com.ebay.mobile.activities.MainActivity"); 
		capabilities.setCapability("appWaitActivity","com.ebay.mobile.activities.MainActivity");
		// This is Launcher activity of your app (you can get it from apk info app)
		//Create RemoteWebDriver instance and connect to the Appium server
		//It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(3000);
		Thread.sleep(3000);

	}
	
	/**
	 * Method to quit the driver
	 */
	public void quitDriver()
	{
		driver.quit();
	}
	
	/**
	 * Method to wait until element is visible
	 * @param by
	 */
	public void waitForElement(By by)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class).pollingEvery(1, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
			logger.error("Exception in waitForElement()" + e);
			
		}
	}
	
	
	
	/**
	 * Scroll To Element
	 * @param by
	 */
	
	public void scrollToElementVertically(String visibleText)
	{

        driver.findElement(MobileBy.AndroidUIAutomator(
			"new UiScrollable(new UiSelector()).scrollIntoView("
                       + "new UiSelector().text(\""+visibleText+"\"))"));
        
        
		
	}
	
	
	//progressbar and horizontal scrolling
	public void progressBarMoveHorizontal(MobileElement ele)
	{
		TouchAction action=new TouchAction(driver);
		action.longPress(ElementOption.element(ele)).moveTo(ElementOption.element(ele,250,250)).release().perform();
		
	}
	public int[] getCoordinates(String xpath) throws Exception {
        //Locate element for which you wants to retrieve x y coordinates.
       //int arr[] ;
        int arr[] = new int[10];
        //Get the middle co-ordinates of the element and store in  the array.
        WebElement element = driver.findElementByXPath(xpath);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int middleX = (rightX + leftX) / 2;
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        System.out.println("x co-ordinat: "+middleX+"\n y co-ordinat: "+middleY+"\n left x axis: "+leftX+"\n right x: "+rightX);
        arr[0]= middleX;
        arr[1]= middleY;
        arr[2]= rightX;
        arr[3]= leftX;
        return arr;
        }
	
	public void scrollDown() {
	    //if pressX was zero it didn't work for me
	    int pressX = driver.manage().window().getSize().width / 2;
	    // 4/5 of the screen as the bottom finger-press point
	    int bottomY = driver.manage().window().getSize().height * 4/5;
	    // just non zero point, as it didn't scroll to zero normally
	    int topY = driver.manage().window().getSize().height / 8;
	    //scroll with TouchAction by itself
	    scroll(pressX, bottomY, pressX, topY);
	}

	/*
	 * don't forget that it's "natural scroll" where 
	 * fromY is the point where you press the and toY where you release it
	 */
	private void scroll(int fromX, int fromY, int toX, int toY) {
	    TouchAction touchAction = new TouchAction(driver);
	    touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
	}
	
	/**
	 * Method to drop element from one position to another position
	 * @param source
	 * @param dest
	 */
	public void dragAndDrop(MobileElement source,MobileElement dest)
	{
		  TouchAction touchAction = new TouchAction(driver);
		    touchAction.longPress(ElementOption.element(source)).moveTo(ElementOption.element(dest)).release().perform();
	}
	
//	public void swipe()
//	{
//        int[] co_ordinates = getCoordinates("//android.widget.RelativeLayout[@index='0']");
//        System.out.println(" x cordinate: " + co_ordinates[0] + "\n y cordinat: " + co_ordinates[1]);
//        int[] dinates =getCoordinates(Xpath of To Element, driver);
//        TouchAction touch = new TouchAction(driver);
//        touch.moveTo(moveToOptions)
//        touch.moveTo(co_ordinates[0], co_ordinates[1]).release().perform();
//
//	}
	public static void scrollDowntoXPath(String xPath) {
	    boolean flag=true;
	    int count=1;
	    while(flag){
	        try {
	            driver.findElement(By.xpath(xPath));
	            flag=false;
	            break;
	        }
	        catch(Exception NoSuchElementException) {
	            count=count+1;
	            Map<String, Object> params = new HashMap<>();
	            params.put("start","40%,90%");
	            params.put("end","40%,20%");
	            params.put("duration","2");
	            Object res= driver.executeScript("mobile:touch:swipe",params);
	        if(count==5)
	        {
	            break;
	        }
	        }
	    }
	}
	
	

	
	public void scrollToElementHorizontal()
	{
		
	}
	
	/** 
	 * Implicit Wait
	 * @param iSecs
	 */
	public void delay(int iSecs)
	{
		try
		{
			if (iSecs == 0)
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
			Thread.sleep(iSecs);
		}
		catch (Exception e)
		{
			logger.error("Exception in delay()" + e);
			
		}
	}
	
	/**
	 * Clicking Element in android app
	 * @param by
	 */
	public void clickElement(By by)
	{
		
		 try
			{
				fluentWait(by);
				MobileElement element = verifyElementExist(by);
				verifyElement(element);
				
				
				element.click();
				
			}
			catch (Exception e)
			{
				logger.error("Exception in clickElement()" + e);
				
			}
	}
	
	public void verifyElement(WebElement element)
	{
		if (element != null)
		{
			if (!element.isDisplayed())
			{
				logger.error(" is not displayed");
			}
			if (!element.isEnabled())
			{
				logger.error(" is not enabled");
			}
		}
		else
		{
			logger.error(" is not exist");
		}
	}
	
	public MobileElement verifyElementExist(By byVal)
	{
		MobileElement element = null;
		try
		{
			List<MobileElement> allElements = driver.findElements(byVal);
			int size = allElements.size();
			if (size != 0)
			{
				if (size == 1)
				{
					element = allElements.get(0);
				}
				else
				{
					logger.error("Found duplicate elements");
					element = allElements.get(0);
				}
			}
			else
			{
				logger.error("is not exist");
			}
		}
		catch (Exception e)
		{
			logger.error("Exception in verifyElementExist()" + e);
		
		}
		return element;
	}
	public void fluentWait(final By locator)
	{
		try
		{
			new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).until(new ExpectedCondition<WebElement>()
					{
						public WebElement apply(WebDriver driver)
						{
							return driver.findElement(locator);
						}
					});
			
		}
		catch (TimeoutException e)
		{
			logger.error("Exception in fluentWait()" + e);
		
		}
		catch (Exception e)
		{
			logger.error("Exception in fluentWait()" + e);
			
		}
	}
	

	/**
	 * Method to get the by locator
	 * @param name
	 * @return
	 */
	public By getObject(String name)
	{
		
		
		 FileReader reader;
		try {
			reader = new FileReader("src\\main\\resources\\OR.properties");
		
		
		prop.load(reader);	
		
		String value=prop.getProperty(name);
		
		String[] pair=value.split("\\|\\|");
	
		if(pair[0].equals("id"))
		{
			By element=By.id(pair[1]);
			
			return element;
		}
		if(pair[0].equals("xpath"))
		{
			By element=By.xpath(pair[1]);
			return element;
		}
		if(pair[0].equals("cssSelector"))
		{
			By element=By.cssSelector(pair[1]);
			return element;
		}
		if(pair[0].equals("Accessibility ID"))
		{
			By element=MobileBy.AccessibilityId(pair[1]);
			return element;
		}
	   if(pair[0].equals("Name"))
	   {
		   By element=By.name(pair[1]);
		   return element;
	   }
		}catch(Exception e)
		{
			logger.info(e);
		
		}
		
		return null;
	}
}



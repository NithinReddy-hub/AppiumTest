package com.ggktech.AppiumTest;

import java.util.List;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ggktech.PublicLibrary.PublicLibrary;

public class MainActivity extends PublicLibrary{
	
	@Test
	public void addToCart() throws InterruptedException
	{
		
		clickElement(getObject("Category"));
		clickElement(getObject("Books"));
		clickElement(getObject("AudioBooks"));
		fluentWait(getObject("PageLoad"));
		clickElement(getObject("Filter"));
		clickElement(getObject("Condition"));
		clickElement(getObject("BrandNew"));
		clickElement(getObject("Done"));
		fluentWait(getObject("Filter"));
		scrollDown();
	    clickElement(getObject("SelectedBook"));
		scrollToElementVertically("BUY IT NOW");
		clickElement(getObject("Quantity"));
		clickElement(getObject("Number"));
		clickElement(getObject("AddToCart"));
		
	}

}

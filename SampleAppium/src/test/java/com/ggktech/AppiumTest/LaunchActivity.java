package com.ggktech.AppiumTest;

import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ggktech.PublicLibrary.PublicLibrary;

public class LaunchActivity extends PublicLibrary{
	

	@BeforeTest
	public void setUpAndInstallApp() throws MalformedURLException, InterruptedException 
	{
	
		 launchApp();
		 logger.info("Started Application");
	}

	

	@AfterTest
	public void shutDown()
	{
		//quitDriver();
	}
}


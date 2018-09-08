package com.qa.testcases;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




public class LoginFreeCrm 

{
	WebDriver driver;
	
	
	@Test(priority=1)
	public void  loginAction() throws Exception
	
	{
		
		System.setProperty("webdriver.chrome.driver", "E:\\seleniumpr\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.freecrm.com/index.html");
		driver.manage().window().maximize();
		
		Properties prop=new Properties();
		FileInputStream fil=new FileInputStream("E:\\seleniumpr\\seleniumpractice2\\JenkinsMaven\\src\\main\\java\\com\\qa\\config\\config.ini");
		prop.load(fil);
		
		driver.findElement(By.cssSelector("input[type='text'][placeholder='Username']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.cssSelector("input[type='password'][placeholder='Password']")).sendKeys(prop.getProperty("pwd"));
		
		driver.findElement(By.cssSelector("input[type='submit'][value='Login']")).click();
		

		String actualTitle =driver.getTitle();
				
		Assert.assertEquals("CRMPRO", actualTitle);		
		
		int size = driver.findElements(By.tagName("frame")).size();
		System.out.println(size);
			
		
		
		
		
	}
	
	@Test(priority=2)
	
	public void logOutAction() throws Exception
	
	{
		driver.switchTo().frame("mainpanel");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(.,'   Logout   ')]")).click();
		driver.close();
		// close the browser test
		
	}
}
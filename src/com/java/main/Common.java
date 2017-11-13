package com.java.main;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Common extends BaseClass {
	public static Logger log = LoggerFactory.getLogger(Common.class.getName());
	
	public static boolean waitforelementpresent(By locator, int time ) throws Exception{
		
		System.out.println("Waiting for element " + locator + " to become visible for " + time + " seconds ");
		int totaltime=0;
		int total = time * 10;
		while(totaltime < total){
		try{
		if(driver.findElement((locator)).isDisplayed());
		System.out.println("Element" + locator + " found");
		return true;
		}
		catch(Exception e){
			log.error("Failure{}",e);
			totaltime=totaltime+10;
		}
		}
		System.out.println("Element" + locator + " not found");
		return false;
		
	}

	public static  void locatebyXpath(String locator){
		try{

			driver.findElement(By.xpath(locator));
		}
	
				catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	
	
	public static boolean isElementPresentbyxpath(By locator) {
		System.out.println("finding element inside CommonFunction isElementPresentbyxpath method....");	
		try {
		WebElement element = driver.findElement(locator);	
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(5 , TimeUnit.SECONDS);
		wait.withTimeout(120, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
		{
			public Boolean apply(WebDriver arg0) {

				if(element.isDisplayed())
				{
					System.out.println("Element" +  locator + " found");
					return true;
				}
				System.out.println("Waiting for element " +  locator + " to be enabled and polling in every 10 SECONDS Max to 120 Secounds");
				return false;
			}

		};
		wait.until(function);
		return true;
		}
		catch (NoSuchElementException e) {
			log.error("Element not present{}",e);
			System.out.println("Element" +  locator + " not present , Error :- "+e.getMessage());
			return false;
		}
		
	}


	
}


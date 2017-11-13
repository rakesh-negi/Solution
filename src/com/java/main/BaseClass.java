package com.java.main;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
	public static WebDriver driver;
	public static Properties Repository= new Properties();
	public File config;
	public FileInputStream F1;
	public File logger;
	public FileInputStream F2;
	public static ExtentReports extent;
	public static ExtentTest logs;
	
	static{
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent	=new ExtentReports(System.getProperty("user.dir")+"/src/target.Screenshots/Report1"+formater.format(calendar.getTime())+".html",false);
	}


	public static String getScreenshot(String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot TS = (TakesScreenshot) driver;
		File source = TS.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/src/target/Screenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeMethod
	public void beforeMethod(Method result){
		logs= extent.startTest(result.getName());
		logs.log(LogStatus.INFO, result.getName() + "Test is Started ");
	}

	@AfterClass(alwaysRun = true)
	public void endTest(){
		driver.close();
		extent.flush();
		extent.close();
	}
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if(result.getStatus() == ITestResult.FAILURE){
			logs.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logs.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
			String screenshotPath = getScreenshot(result.getName());
			System.out.print(screenshotPath);
			//To add it in the extent report
			logs.log(LogStatus.FAIL,logs.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logs.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logs);
	}

	@BeforeTest
	 public void Setup() throws IOException
	 {
		LoadConfigFile();
		LoadLogFile();
		SelectBrowser(Repository.getProperty("browser"));
		driver.get(Repository.getProperty("url"));
		
	 }
	public WebDriver SelectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){	
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/com/java/utils/geckodriver");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
			driver = new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/com/java/utils/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-proxy-server=socks5");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}
		else if(browser.equalsIgnoreCase("phantom")){			
			System.setProperty("phantomjs.binary.path",System.getProperty("user.dir")+"/src/com/java/utils/phantomjs");
			driver = new PhantomJSDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}
		return null;
	}
	

	public void LoadConfigFile() throws IOException{
		
		config = new File(System.getProperty("user.dir")+"/src/com/java/utils/config.properties");
		F1= new FileInputStream(config);
		Repository.load(F1);
	}
	
	public void LoadLogFile() throws IOException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		logger= new File(System.getProperty("user.dir")+"/src/com/java/utils/log4j.properties");
		Properties props = new Properties();
	    props.load(new FileInputStream(logger));
	    PropertyConfigurator.configure(props);
			
}		

    /*@AfterTest
    public void DriverQuit()
    {
      driver.quit();    
    	}*/

	public static Object[][] getdata(String excelname,String sheetname){

		Excel_Reader data=new Excel_Reader(System.getProperty("user.dir")+"/src/com/java/utils/"+excelname);
		int rownum=data.getRowCount(sheetname);
		System.out.println(rownum);
		int colnum=data.getColCount(sheetname);
		System.out.println(colnum);
		Object sampletest[][]=new Object[rownum-1][colnum];
		for(int i=1;i< rownum;i++){
			for(int j=0;j< colnum;j++){
				sampletest[i-1][j]=data.GetCellDAta(sheetname, j, i);
			}
		}

		return sampletest;

	}
	@DataProvider
	public Object[][] logindata(){
		Object[][] data=getdata("TestData1.xlsx","login");

		return data;

	}

    }

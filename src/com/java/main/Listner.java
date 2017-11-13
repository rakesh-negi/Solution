package com.java.main;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Listner extends BaseClass implements ITestListener, ISuiteListener, IInvokedMethodListener {

	// This belongs to ISuiteListener and will execute before the Suite start

	@Override

	public void onStart(ISuite arg0) {

		Reporter.log("About to begin executing Suite " + arg0.getName(), true);

	}

	// This belongs to ISuiteListener and will execute, once the Suite is finished

	@Override

	public void onFinish(ISuite arg0) {

		Reporter.log("About to end executing Suite " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute before starting of Test set/batch

	public void onStart(ITestContext arg0) {

		Reporter.log("About to begin executing Test " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute, once the Test set/batch is finished

	public void onFinish(ITestContext arg0) {

		Reporter.log("Completed executing test " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute only when the test is pass

	public void onTestSuccess(ITestResult arg0) {

		// This is calling the printTestResults method

		printTestResults(arg0);

	}

	// This belongs to ITestListener and will execute only on the event of fail test

	public void onTestFailure(ITestResult arg0) {
		if (!arg0.isSuccess()) {
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String methodname = arg0.getName();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/target/Screenshots/";
				File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodname + "_" + formater.format(calender.getTime()) + ".png");
				FileUtils.copyFile(scrFile, destFile);
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/> </a>");
				// This belongs to ITestListener and will execute before the main test start (@Test)
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	// This belongs to ITestListener and will execute before the main test start (@Test)

	public void onTestStart(ITestResult arg0) {

		System.out.println("The execution of the main test starts now");

	}

	// This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped

	public void onTestSkipped(ITestResult arg0) {

		printTestResults(arg0);

	}

	// This is just a piece of shit, ignore this

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test

	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

			case ITestResult.SUCCESS:

				status = "Pass";

				break;

			case ITestResult.FAILURE:

				status = "Failed";

				break;

			case ITestResult.SKIP:

				status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);

	}

	// This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

}
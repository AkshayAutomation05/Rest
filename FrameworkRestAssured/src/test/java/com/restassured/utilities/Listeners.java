package com.restassured.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter{
	
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	

	
	@Override
	public void onStart(ITestContext testContext) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName="Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"//test-output//"+reportName);
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"//extent-config.xml");
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name","Local host");
		extent.setSystemInfo("Enviroment","QA");
		extent.setSystemInfo("user","Akshay");
		extent.setSystemInfo("Browser","Chrome");
		extent.setSystemInfo("OS","Windows");
		
		htmlReporter.config().setDocumentTitle("NOPCommerece Test Report");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.PASS,tr.getName());
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.FAIL,tr.getName());
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f=new File(screenshotPath);
		if(f.exists())
		{
			try
			{
				test.fail("Screenshot is below: "+test.addScreenCaptureFromPath(screenshotPath));
			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
			
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test=extent.createTest(tr.getName());
		test.log(Status.SKIP,tr.getName());
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}



}

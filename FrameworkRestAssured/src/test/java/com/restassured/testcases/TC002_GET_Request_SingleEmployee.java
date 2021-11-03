package com.restassured.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GET_Request_SingleEmployee extends TestBase{
	
Logger logger=LogManager.getLogger(TC001_GET_Request_AllEmployees.class);
	
	@BeforeClass
	public void getSingleEmployees() throws InterruptedException
	{
		logger.info("------TC002_GET_Request_SingleEmployee------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee/"+empID);
		Thread.sleep(5000);
	}
	
	@Test
	public void checkResponse()
	{
		logger.info("----Checking response------");
		String resbody=response.getBody().asString();
		System.out.println("Response Body:"+resbody);
		logger.info("Response body captured... ");
		Assert.assertTrue(resbody!=null);	
		Assert.assertEquals(resbody.contains(empID),true);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("----Checking response status code------");
		int status=response.getStatusCode();
		logger.info("Response status: "+status);
		Assert.assertEquals(status,200);
	}
	
	@Test
	public void checkResponseTime()
	{
		logger.info("----Checking response time------");
		long responseTime=response.getTime();
		logger.info("Response time: "+responseTime);
		if(responseTime>2000)
			logger.warn("Response time is greater than 2000");
	}
	
	@Test
	public void checkStatusLine()
	{
		logger.info("----Checking Status Line------");
		String statusLine=response.getStatusLine();
		logger.info("Status Line: "+statusLine);
	}
	
	@Test
	public void checkContentType()
	{
		logger.info("----Checking Content Type------");
		String contentType=response.header("Content-Type");
		logger.info("Content Type: "+contentType);
	}
	
	@Test
	public void checkServerType()
	{
		logger.info("----Checking Server Type------");
		String serverType=response.header("Server");
		logger.info("Content Type: "+serverType);
	}
	
	@AfterClass
	public void tearDown()
	{
		logger.info("-------------Finished TC002_GET_Request_SingleEmployee------------");
	}

}

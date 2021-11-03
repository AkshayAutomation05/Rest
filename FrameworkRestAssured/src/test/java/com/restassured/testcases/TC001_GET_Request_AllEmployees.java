package com.restassured.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.restassured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_Request_AllEmployees extends TestBase {
	
	Logger logger=LogManager.getLogger(TC001_GET_Request_AllEmployees.class);
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException
	{
		logger.info("------TC001_GET_Request_AllEmployees------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
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
	
	@Test
	public void checkContentEncoding()
	{
		logger.info("----Checking Content Encoding------");
		String contentEncoding=response.header("Content-Encoding");
		logger.info("Content Type: "+contentEncoding);
	}
	
	@Test
	public void checkContentLenght()
	{
		logger.info("----Checking Content Length------");
		String contentLength=response.header("Content-Length");
		logger.info("Content Length: "+contentLength);
		
		if((Integer.parseInt(contentLength))<100)
			System.out.println("Content Length is less than 100");
	}
	
	
	@AfterClass
	public void tearDown()
	{
		logger.info("-------------Finished TC001_GET_Request_AllEmployees------------");
	}


}

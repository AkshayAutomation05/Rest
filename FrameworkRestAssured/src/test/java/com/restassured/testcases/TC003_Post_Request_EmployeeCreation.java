package com.restassured.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;
import com.restassured.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_Request_EmployeeCreation extends TestBase{
	
	
Logger logger=LogManager.getLogger(TC003_Post_Request_EmployeeCreation.class);

	String empName=RestUtils.getName();
	String empSalary=RestUtils.getSalary();
	String emapAge=RestUtils.getAge();
	
	@BeforeClass
	public void addEmployee() throws InterruptedException
	{
		logger.info("------TC003_Post_Request_EmployeeCreation------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject reqParam=new JSONObject();
		reqParam.put("name",empName);
		reqParam.put("salary",empSalary);
		reqParam.put("age",emapAge);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(reqParam.toJSONString());		
		response=httpRequest.request(Method.POST,"/create");
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
		
		Assert.assertEquals(resbody.contains(empName),true);
		Assert.assertEquals(resbody.contains(empSalary),true);
		Assert.assertEquals(resbody.contains(emapAge),true);
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
	
	@AfterClass
	public void tearDown()
	{
		logger.info("-------------Finished TC003_Post_Request_EmployeeCreation------------");
	}
	
	//{"name":"IIVk","salary":"12801","age":"14","id":2603}

}

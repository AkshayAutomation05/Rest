package com.restassured.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Request extends TestBase{
	
	Logger logger=LogManager.getLogger(TC005_Delete_Request.class);
	
	@BeforeClass
	public void deleteEmployee() throws InterruptedException
	{
		logger.info("------TC005_Delete_Request------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();	
		response=httpRequest.request(Method.GET,"/employees");
		
		JsonPath jsonpathEvaluator=response.jsonPath();
		String empID=jsonpathEvaluator.get("[0].id");
		
		Thread.sleep(5000);
		response=httpRequest.request(Method.DELETE,"/delete/empID");
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
		Assert.assertEquals(resbody.contains("deleted"),true);
	
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("----Checking response status code------");
		int status=response.getStatusCode();
		logger.info("Response status: "+status);
		Assert.assertEquals(status,200);
	}
	
	@AfterClass
	public void tearDown()
	{
		logger.info("-------------Finished TC005_Delete_Request------------");
	}

}

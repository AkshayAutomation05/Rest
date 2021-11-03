package com.restassured.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String getName()
	{
		String name=RandomStringUtils.randomAlphabetic(4);
		return name;
	}
	
	public static String getSalary()
	{
		String salary=RandomStringUtils.randomNumeric(5);
		return salary;
		
	}
	
	public static String getAge()
	{
		String age=RandomStringUtils.randomNumeric(2);
		return age;
	}

}

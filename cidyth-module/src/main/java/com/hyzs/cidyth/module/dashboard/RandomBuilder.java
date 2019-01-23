package com.hyzs.cidyth.module.dashboard;

import org.apache.commons.lang.RandomStringUtils;

public class RandomBuilder {
	
	public static String randomString(String chars,int length){
		String s = RandomStringUtils.random(length, chars);
		return s;
		
	}
	
	public static int randomNumber(int length){
		String s = RandomStringUtils.randomNumeric(length);
		return Integer.parseInt(s);
	}
}

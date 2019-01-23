package com.hyzs.cidyth.common.utils;

import java.util.regex.Pattern;
/**
 * 
 * @author jidw
 *
 */
public class RegExpUtil {
	
	public static boolean isNumber(String numStr){
		return Pattern.compile("\\d+").matcher(numStr).matches();
	}
}

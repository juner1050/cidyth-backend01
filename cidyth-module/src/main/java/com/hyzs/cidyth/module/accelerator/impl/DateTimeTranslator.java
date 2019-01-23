package com.hyzs.cidyth.module.accelerator.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

class DateTimeTranslator {
	
	public static Date parse2Date(String date, String... patterns) {
		Date result = null;
		if (date != null && date.trim().length() > 0 && patterns != null && patterns.length > 0) {
			date = StringUtils.removeAll(date.trim(), "([\\u4e00-\\u9fa5])+").replaceAll("\\s+", " ");
			List<String> strb = new ArrayList<String>();
			Pattern numberPattern = Pattern.compile("\\d+");
			Matcher numberMacther = numberPattern.matcher(date);
			while (numberMacther.find()) {
				String target = numberMacther.group();
				if (target.length() == 1) {
					int d = Integer.parseInt(target);
					if (d >= 0 || d <= 9) {
						target = "0" + d;
					}
				}
				strb.add(target);
			}
			date = (Joiner.on("").join(strb));
			for (String pattern : patterns) {
				try {
					result = new SimpleDateFormat(pattern).parse(date);
				} catch (ParseException e) {
				}
				if (result != null) {
					break;
				}
			}
		}
		return result;
	}
}
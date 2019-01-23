package com.hyzs.cidyth.common.utils;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author derrick
 *
 */
public class NumberChineseExchanger {
	/* 数字转中文
	 * @param num 数字
	 * @return
	 */
	public static String NumberToChinese(int num) {
		int unitPos = 0;
		String strIns = "", chnStr = "";
		boolean needZero = false;

		if (num == 0) {
			return chnNumChar.get(0);
		}

		while (num > 0) {
			int section = num % 10000;
			if (needZero) {
				chnStr = chnNumChar.get(0) + chnStr;
			}
			strIns = SectionToChinese(section);
			strIns += (section != 0) ? chnUnitSection.get(unitPos) : chnUnitSection.get(0);
			chnStr = strIns + chnStr;
			needZero = (section < 1000) && (section > 0);
			num = (int) Math.floor(num / 10000);
			unitPos++;
		}

		return chnStr;
	}
	//单个数字
	private static final List<String> chnNumChar = Lists.newArrayList("零", "一", "二", "三", "四", "五", "六", "七", "八", "九");
	//节权位
	private static final List<String> chnUnitSection = Lists.newArrayList("", "万", "亿", "万亿", "亿亿");
	//节内位
	private static final List<String> chnUnitChar = Lists.newArrayList("", "十", "百", "千");

	private static String SectionToChinese(double section) {
		String strIns = "";
		String chnStr = "";
		int unitPos = 0;
		boolean zero = true;
		while (section > 0) {
			int v = (int) (section % 10);
			if (v == 0) {
				if (!zero) {
					zero = true;
					chnStr = chnNumChar.get(v) + chnStr;
				}
			} else {
				zero = false;
				strIns = chnNumChar.get(v);
				strIns += chnUnitChar.get(unitPos);
				chnStr = strIns + chnStr;
			}
			unitPos++;
			section = Math.floor(section / 10);
		}
		return chnStr;
	}

	public static void main(String[] args) {
		String chn = NumberChineseExchanger.NumberToChinese(12);
		System.out.println(chn);
	}
}

package com.hyzs.cidyth.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringTokenUtil {
	/**
	 * 
	 * @param str 源字符串
	 * @param delimiters 
	 * @param trimTokens
	 * @param ignoreEmptyTokens
	 * @return
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters,
			boolean trimTokens, boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if ((!ignoreEmptyTokens) || (token.length() > 0)) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}
	
	public static int countOccurrencesOf(String str, String sub) {
		if ((str == null) || (sub == null) || (str.length() == 0)
				|| (sub.length() == 0)) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			count++;
			pos = idx + sub.length();
		}
		return count;
	}
	
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}



	public static int instr(String s, String sub, int offset, int seqNum) {
		Integer idx = null;
		Integer begin = null;
		if (seqNum <= 0) {
			return -1;
		}
		if (Math.abs(offset) == s.length()) {
			return -1;
		}
		if (offset < 0) {
			String waitTreat = null;
			begin = (s.length() - 1) + offset;
			if (begin == s.length() - 1) {
				waitTreat = s;
			} else if (begin == 0) {
				waitTreat = new String(s.substring(0, 1));
				if (waitTreat.equals(sub)) {
					return 0;
				} else {
					return -1;
				}
			} else {
				waitTreat = s.substring(0, begin + 1);
			}
			int counter = 0;
			String son = null;
			for (int i = waitTreat.length() - 1; i >= 0; i--) {
				son = subString(waitTreat, i, sub.length());
				if (son.equals(sub)) {
					counter++;
				} else {
					continue;
				}
				if (counter == seqNum) {
					idx = i;
					break;
				}
			}
		} else {
			String waitTreat = null;
			begin = offset;
			if (begin == s.length() - 1) {

				waitTreat = new Character(s.charAt(s.length() - 1)).toString();
				if (waitTreat.equals(sub)) {
					return s.charAt(s.length() - 1);
				} else {
					return -1;
				}
			} else if (begin == 0) {

				waitTreat = s;
			} else {
				waitTreat = s.substring(offset);
			}

			int counter = 0;
			String son = null;
			for (int j = 0; j < waitTreat.length() - sub.length(); j++) {
				son = subString(waitTreat, j, sub.length());
				if (son.equals(sub)) {
					counter++;
				} else {
					continue;
				}
				if (counter == seqNum) {
					idx = begin + j;
					break;
				}
			}
		}
		return idx;
	}

	public static String subString(String s, int offset, int len) {
		int counter = 0;
		StringBuilder strb = new StringBuilder();
		if (offset >= 0 && offset <= s.length() - 1) {
			for (int n = offset; n < s.length(); n++) {
				if (counter != len) {
					strb.append(s.charAt(n));
					counter++;
				} else {
					break;
				}
			}
			return strb.toString();
		} else {
			return null;
		}

	}

	public static String initCap(String s) {
		StringBuilder strb = new StringBuilder();
		String[] words = s.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = ((((int) words[i].charAt(0) >= 97 && (int) words[i]
					.charAt(0) <= 122)) ? ((char) (words[i].charAt(0) - 32))
					: words[i].charAt(0))
					+ words[i].substring(1);
			if (i < words.length - 1) {
				strb.append(words[i] + " ");
			} else {
				strb.append(words[i]);
			}
		}
		return strb.toString();
	}

	public static void main(String[] args) {
		System.out.println(StringTokenUtil.initCap("fdasfasd fadsfas dfasf"));
	}
}

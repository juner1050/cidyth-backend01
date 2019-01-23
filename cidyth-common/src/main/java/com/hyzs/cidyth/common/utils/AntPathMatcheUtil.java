package com.hyzs.cidyth.common.utils;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AntPathMatcheUtil {
	private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{[^/]+?\\}");
	public static final String DEFAULT_PATH_SEPARATOR = "/";
	private String pathSeparator;
	private final Map<String, AntPathStringMatcher> stringMatcherCache;
	private boolean trimTokens;

	public AntPathMatcheUtil() {
		this.pathSeparator = "/";
		this.stringMatcherCache = new ConcurrentHashMap(256);
		this.trimTokens = true;
	}

	public void setPathSeparator(String pathSeparator) {
		this.pathSeparator = (pathSeparator != null ? pathSeparator : "/");
	}

	public void setTrimTokens(boolean trimTokens) {
		this.trimTokens = trimTokens;
	}

	public boolean isPattern(String path) {
		return (path.indexOf('*') != -1) || (path.indexOf('?') != -1);
	}

	public boolean match(String pattern, String path) {
		return doMatch(pattern, path, true, null);
	}

	public boolean matchStart(String pattern, String path) {
		return doMatch(pattern, path, false, null);
	}

	protected boolean doMatch(String pattern, String path, boolean fullMatch,
			Map<String, String> uriTemplateVariables) {
		if (path.startsWith(this.pathSeparator) != pattern.startsWith(this.pathSeparator)) {
			return false;
		}
		String[] pattDirs = StringTokenUtil.tokenizeToStringArray(pattern, this.pathSeparator, this.trimTokens, true);

		String[] pathDirs = StringTokenUtil.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);

		int pattIdxStart = 0;
		int pattIdxEnd = pattDirs.length - 1;
		int pathIdxStart = 0;
		int pathIdxEnd = pathDirs.length - 1;
		while ((pattIdxStart <= pattIdxEnd) && (pathIdxStart <= pathIdxEnd)) {
			String patDir = pattDirs[pattIdxStart];
			if ("**".equals(patDir)) {
				break;
			}
			if (!matchStrings(patDir, pathDirs[pathIdxStart], uriTemplateVariables)) {
				return false;
			}
			pattIdxStart++;
			pathIdxStart++;
		}
		if (pathIdxStart > pathIdxEnd) {
			if (pattIdxStart > pattIdxEnd) {
				return !path.endsWith(this.pathSeparator);
			}
			if (!fullMatch) {
				return true;
			}
			if ((pattIdxStart == pattIdxEnd) && (pattDirs[pattIdxStart].equals("*"))
					&& (path.endsWith(this.pathSeparator))) {
				return true;
			}
			for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
				if (!pattDirs[i].equals("**")) {
					return false;
				}
			}
			return true;
		}
		if (pattIdxStart > pattIdxEnd) {
			return false;
		}
		if ((!fullMatch) && ("**".equals(pattDirs[pattIdxStart]))) {
			return true;
		}
		while ((pattIdxStart <= pattIdxEnd) && (pathIdxStart <= pathIdxEnd)) {
			String patDir = pattDirs[pattIdxEnd];
			if (patDir.equals("**")) {
				break;
			}
			if (!matchStrings(patDir, pathDirs[pathIdxEnd], uriTemplateVariables)) {
				return false;
			}
			pattIdxEnd--;
			pathIdxEnd--;
		}
		if (pathIdxStart > pathIdxEnd) {
			for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
				if (!pattDirs[i].equals("**")) {
					return false;
				}
			}
			return true;
		}
		while ((pattIdxStart != pattIdxEnd) && (pathIdxStart <= pathIdxEnd)) {
			int patIdxTmp = -1;
			for (int i = pattIdxStart + 1; i <= pattIdxEnd; i++) {
				if (pattDirs[i].equals("**")) {
					patIdxTmp = i;
					break;
				}
			}
			if (patIdxTmp == pattIdxStart + 1) {
				pattIdxStart++;
			} else {
				int patLength = patIdxTmp - pattIdxStart - 1;
				int strLength = pathIdxEnd - pathIdxStart + 1;
				int foundIdx = -1;
				int i = 0;
				if (i <= strLength - patLength) {
					for (int j = 0; j < patLength; j++) {
						String subPat = pattDirs[(pattIdxStart + j + 1)];
						String subStr = pathDirs[(pathIdxStart + i + j)];
						if (!matchStrings(subPat, subStr, uriTemplateVariables)) {
							if (foundIdx == -1) {
								return false;
							}
						}
					}
					foundIdx = pathIdxStart + i;
				}

				pattIdxStart = patIdxTmp;
				pathIdxStart = foundIdx + patLength;
			}
		}
		for (int i = pattIdxStart; i <= pattIdxEnd; i++) {
			if (!pattDirs[i].equals("**")) {
				return false;
			}
		}
		return true;
	}

	private boolean matchStrings(String pattern, String str, Map<String, String> uriTemplateVariables) {
		AntPathStringMatcher matcher = (AntPathStringMatcher) this.stringMatcherCache.get(pattern);
		if (matcher == null) {
			matcher = new AntPathStringMatcher(pattern);
			this.stringMatcherCache.put(pattern, matcher);
		}
		return matcher.matchStrings(str, uriTemplateVariables);
	}

	public String extractPathWithinPattern(String pattern, String path) {
		String[] patternParts = StringTokenUtil.tokenizeToStringArray(pattern, this.pathSeparator, this.trimTokens, true);

		String[] pathParts = StringTokenUtil.tokenizeToStringArray(path, this.pathSeparator, this.trimTokens, true);

		StringBuilder builder = new StringBuilder();
		int puts = 0;
		for (int i = 0; i < patternParts.length; i++) {
			String patternPart = patternParts[i];
			if (((patternPart.indexOf('*') > -1) || (patternPart.indexOf('?') > -1)) && (pathParts.length >= i + 1)) {
				if ((puts > 0) || ((i == 0) && (!pattern.startsWith(this.pathSeparator)))) {
					builder.append(this.pathSeparator);
				}
				builder.append(pathParts[i]);
				puts++;
			}
		}
		for (int i = patternParts.length; i < pathParts.length; i++) {
			if ((puts > 0) || (i > 0)) {
				builder.append(this.pathSeparator);
			}
			builder.append(pathParts[i]);
		}
		return builder.toString();
	}

	public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
		Map<String, String> variables = new LinkedHashMap();
		boolean result = doMatch(pattern, path, true, variables);
		if (!result) {
			throw new IllegalStateException("Pattern \"" + pattern + "\" is not a match for \"" + path + "\"");
		}
		return variables;
	}

	public String combine(String pattern1, String pattern2) {
		if (((pattern1 == null) || (pattern1.trim().length() == 0))
				&& ((pattern2 == null) || (pattern2.trim().length() == 0))) {
			return "";
		}
		if ((pattern1 == null) || (pattern1.trim().length() == 0)) {
			return pattern2;
		}
		if ((pattern2 == null) || (pattern2.trim().length() == 0)) {
			return pattern1;
		}
		boolean pattern1ContainsUriVar = pattern1.indexOf('{') != -1;
		if ((!pattern1.equals(pattern2)) && (!pattern1ContainsUriVar) && (match(pattern1, pattern2))) {
			return pattern2;
		}
		if (pattern1.endsWith("/*")) {
			if (pattern2.startsWith("/")) {
				return pattern1.substring(0, pattern1.length() - 1) + pattern2.substring(1);
			}
			return pattern1.substring(0, pattern1.length() - 1) + pattern2;
		}
		if (pattern1.endsWith("/**")) {
			if (pattern2.startsWith("/")) {
				return pattern1 + pattern2;
			}
			return pattern1 + "/" + pattern2;
		}
		int dotPos1 = pattern1.indexOf('.');
		if ((dotPos1 == -1) || (pattern1ContainsUriVar)) {
			if ((pattern1.endsWith("/")) || (pattern2.startsWith("/"))) {
				return pattern1 + pattern2;
			}
			return pattern1 + "/" + pattern2;
		}
		String fileName1 = pattern1.substring(0, dotPos1);
		String extension1 = pattern1.substring(dotPos1);
		int dotPos2 = pattern2.indexOf('.');
		String extension2;
		String fileName2;
		if (dotPos2 != -1) {
			fileName2 = pattern2.substring(0, dotPos2);
			extension2 = pattern2.substring(dotPos2);
		} else {
			fileName2 = pattern2;
			extension2 = "";
		}
		String fileName = fileName1.endsWith("*") ? fileName2 : fileName1;
		String extension = extension1.startsWith("*") ? extension2 : extension1;

		return fileName + extension;
	}

	public Comparator<String> getPatternComparator(String path) {
		return new AntPatternComparator(path);
	}

	private static class AntPatternComparator implements Comparator<String> {
		private final String path;

		private AntPatternComparator(String path) {
			this.path = path;
		}

		public int compare(String pattern1, String pattern2) {
			if ((pattern1 == null) && (pattern2 == null)) {
				return 0;
			}
			if (pattern1 == null) {
				return 1;
			}
			if (pattern2 == null) {
				return -1;
			}
			boolean pattern1EqualsPath = pattern1.equals(this.path);
			boolean pattern2EqualsPath = pattern2.equals(this.path);
			if ((pattern1EqualsPath) && (pattern2EqualsPath)) {
				return 0;
			}
			if (pattern1EqualsPath) {
				return -1;
			}
			if (pattern2EqualsPath) {
				return 1;
			}
			int wildCardCount1 = getWildCardCount(pattern1);
			int wildCardCount2 = getWildCardCount(pattern2);

			int bracketCount1 = StringTokenUtil.countOccurrencesOf(pattern1, "{");
			int bracketCount2 = StringTokenUtil.countOccurrencesOf(pattern2, "{");

			int totalCount1 = wildCardCount1 + bracketCount1;
			int totalCount2 = wildCardCount2 + bracketCount2;
			if (totalCount1 != totalCount2) {
				return totalCount1 - totalCount2;
			}
			int pattern1Length = getPatternLength(pattern1);
			int pattern2Length = getPatternLength(pattern2);
			if (pattern1Length != pattern2Length) {
				return pattern2Length - pattern1Length;
			}
			if (wildCardCount1 < wildCardCount2) {
				return -1;
			}
			if (wildCardCount2 < wildCardCount1) {
				return 1;
			}
			if (bracketCount1 < bracketCount2) {
				return -1;
			}
			if (bracketCount2 < bracketCount1) {
				return 1;
			}
			return 0;
		}

		private int getWildCardCount(String pattern) {
			if (pattern.endsWith(".*")) {
				pattern = pattern.substring(0, pattern.length() - 2);
			}
			return StringTokenUtil.countOccurrencesOf(pattern, "*");
		}

		private int getPatternLength(String pattern) {
			Matcher m = AntPathMatcheUtil.VARIABLE_PATTERN.matcher(pattern);
			return m.replaceAll("#").length();
		}
	}

	private static class AntPathStringMatcher {
		private static final Pattern GLOB_PATTERN = Pattern
				.compile("\\?|\\*|\\{((?:\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");
		private static final String DEFAULT_VARIABLE_PATTERN = "(.*)";
		private final Pattern pattern;
		private final List<String> variableNames = new LinkedList();

		public AntPathStringMatcher(String pattern) {
			StringBuilder patternBuilder = new StringBuilder();
			Matcher m = GLOB_PATTERN.matcher(pattern);
			int end = 0;
			while (m.find()) {
				patternBuilder.append(quote(pattern, end, m.start()));
				String match = m.group();
				if ("?".equals(match)) {
					patternBuilder.append('.');
				} else if ("*".equals(match)) {
					patternBuilder.append(".*");
				} else if ((match.startsWith("{")) && (match.endsWith("}"))) {
					int colonIdx = match.indexOf(':');
					if (colonIdx == -1) {
						patternBuilder.append("(.*)");
						this.variableNames.add(m.group(1));
					} else {
						String variablePattern = match.substring(colonIdx + 1, match.length() - 1);

						patternBuilder.append('(');
						patternBuilder.append(variablePattern);
						patternBuilder.append(')');
						String variableName = match.substring(1, colonIdx);
						this.variableNames.add(variableName);
					}
				}
				end = m.end();
			}
			patternBuilder.append(quote(pattern, end, pattern.length()));
			this.pattern = Pattern.compile(patternBuilder.toString());
		}

		private String quote(String s, int start, int end) {
			if (start == end) {
				return "";
			}
			return Pattern.quote(s.substring(start, end));
		}

		public boolean matchStrings(String str, Map<String, String> uriTemplateVariables) {
			Matcher matcher = this.pattern.matcher(str);
			if (matcher.matches()) {
				if (uriTemplateVariables != null) {
					if (this.variableNames.size() != matcher.groupCount()) {
						throw new IllegalArgumentException("The number of capturing groups in the pattern segment "
								+ this.pattern
								+ " does not match the number of URI template variables it defines, which can occur if "
								+ " capturing groups are used in a URI template regex. Use non-capturing groups instead.");
					}
					for (int i = 1; i <= matcher.groupCount(); i++) {
						String name = (String) this.variableNames.get(i - 1);
						String value = matcher.group(i);
						uriTemplateVariables.put(name, value);
					}
				}
				return true;
			}
			return false;
		}
	}
}

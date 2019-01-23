package com.hyzs.cidyth.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import com.google.common.base.Joiner;

public class DateUtil {
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static final String Y_M_D = "yyyy-MM-dd";
	public static final String[] DEFAULT_SUPPORTED_PATTERN = new String[] { "yyyyMMddHHmmssSSS", "yyyyMMddHHmmss",
			"yyyyMMdd", "HHmmss", "HHmmssSSS", "yyyy-MM-dd HH:mm:ss:SSS", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
			"HH:mm:ss", "HH:mm:ss:SSS", "yyyy/MM/dd", "yyyy/MM/dd HH:ss:ss"};

	/**
	 * 按指定的格式解析日期字符串
	 * 
	 * @param date
	 *            日期字符串
	 * @param pattern
	 *            日期格式 * @return
	 * @throws Exception
	 */
	public static Date parseToDate(String date, String pattern) throws Exception {
		if (StringUtils.isBlank(date)) {
			throw new Exception("日期不能为空");
		}
		if (StringUtils.isBlank(pattern)) {
			throw new Exception("日期格式不能为空");
		};
		SimpleDateFormat formmater = new SimpleDateFormat(pattern);
		return formmater.parse(date);
	}

	/**
	 * 
	 * @param date
	 *            日期字符串
	 * @param patterns
	 *            日期格式
	 * @return
	 */
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

	public static String getTimeStamp() {
		SimpleDateFormat formmater = new SimpleDateFormat(yyyyMMddHHmmssSSS);
		return formmater.format(new Date());
	}

	/**
	 * 两个日期相差天数（不区分周末和工作日）
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static int subDays(Date begin, Date end) {
		if (begin == null || end == null)
			throw new IllegalArgumentException("参数为空！");
		Period p = new Period(begin.getTime(), end.getTime(), PeriodType.days());
		return p.getDays();
	}

	/**
	 * 两个日期时间相差天数（只算工作日）
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static int subWorkDays(Date begin, Date end) {
		if (begin == null || end == null)
			throw new IllegalArgumentException("参数为空！");

		Instant instantBegin = begin.toInstant();
		Instant instantEnd = end.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		LocalDateTime beginDate = instantBegin.atZone(zoneId).toLocalDateTime();
		LocalDateTime endDate = instantEnd.atZone(zoneId).toLocalDateTime();
		if(beginDate.isAfter(endDate)){
			LocalDateTime temp = beginDate;
			beginDate = endDate;
			endDate = temp;
		}
		int day = 0;
		// 开始时间在结束时间之前，并且两个日期时间之间大于1天以上
		while (beginDate.isBefore(endDate)) {
			if (!beginDate.getDayOfWeek().name().equals(DayOfWeek.SATURDAY.name())
					&& !beginDate.getDayOfWeek().name().equals(DayOfWeek.SUNDAY.name())
					&& ChronoUnit.DAYS.between(beginDate, endDate) > 0) {
				day++;
			}
			beginDate = beginDate.plusDays(1);
		}

		return day;
	}

	/**
	 * 将毫秒数转换成天数
	 * 
	 * @param milliseconeds
	 *            毫秒数
	 * @return 天数
	 */
	public static long toDays(long milliseconeds) {
		return TimeUnit.MILLISECONDS.toDays(milliseconeds);
	}

	/**
	 * 获取本月的起始时间<br>
	 * 例如：当前是2018-04-19 12:00:00 则返回字符串"2018-04-01 00:00:00"
	 * 
	 * @return String
	 */
	public static String getMonthStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取本月的结束时间<br>
	 * 例如：当前是2018-04-19 12:00:00 则返回字符串"2018-04-31 23:59:59"
	 * 
	 * @return String
	 */
	public static String getMonthEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取当前时间的上个月的起始时间<br>
	 * 例如：当前是2018-04-19 12:00:00 则返回字符串"2018-03-01 00:00:00"
	 * 
	 * @return String
	 */
	public static String getLastMonthStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取当前时间的上个月的结束时间<br>
	 * 例如：当前是2018-04-19 12:00:00 则返回字符串"2018-03-31 23:59:59"
	 * 
	 * @return String
	 */
	public static String getLastMonthEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Y_M_D_H_M_S);
		// 获取最后一天
		int lastMonthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取相对本年的起始时间<br>
	 * 例如：当前是2018-04-19 12:00:00 year = -2,则返回字符串"2016-01-01 00:00:00" year =
	 * -1,则返回字符串"2017-01-01 00:00:00" year = 0,则返回字符串"2018-01-01 00:00:00" year
	 * = 1,则返回字符串"2019-01-01 00:00:00" year = 2,则返回字符串"2020-01-01 00:00:00"
	 * 
	 * @return String
	 */
	public static String getYearStartTime(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取相对本年的结束时间<br>
	 * 例如：当前是2018-04-19 12:00:00 year = -2,则返回字符串"2016-12-31 23:59:59" year =
	 * -1,则返回字符串"2017-12-31 23:59:59" year = 0,则返回字符串"2018-12-31 23:59:59" year
	 * = 1,则返回字符串"2019-12-31 23:59:59" year = 2,则返回字符串"2020-12-31 23:59:59"
	 * 
	 * @return String
	 */
	public static String getYearEndTime(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取本年到目前为止所有的月份
	 * 
	 * @return
	 */
	public static UpToNow getMonthsUpToNow() {
		List<Integer> monthList = new ArrayList<Integer>();// 月份
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);// 当前年份
		int months = calendar.get(Calendar.MONTH) + 1;
		for (int m = 1; m <= months; m++) {// 从一月开始到当前月份
			monthList.add(m);
		}
		return new UpToNow(year, monthList);
	}

	public static class UpToNow {
		private final int year;// 当前的年份
		private final List<Integer> months;// 到目前为止所有的月份

		public UpToNow(int y, List<Integer> ml) {
			this.year = y;
			this.months = ml;
		}

		public int getYear() {
			return year;
		}

		public List<Integer> getMonths() {
			return months;
		}

	}

	/**
	 * 被检查日期是否在指定日期之间
	 * 
	 * @param date
	 *            被检查日期
	 * @param start
	 *            左区间，如果为空，则检查在end之前
	 * @param end
	 *            右区间，如果为空，则检查在start之前
	 * @return
	 */
	public static boolean isBetween(Date date, Date start, Date end) {
		if (start == null && end == null) {
			return true;
		}
		if (start == null) {
			return date.before(end);
		}
		if (end == null) {
			return date.after(start);
		}
		return date.after(start) && date.before(end);
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 *
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间（排除周末，只算周一到周五）
	 *
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getWorkDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		int addDays = 0;
		while(addDays < day){
			now.add(Calendar.DAY_OF_MONTH, +1);
			int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
			// 周六=7  周日=1
			if (dayOfWeek != 1 && dayOfWeek != 7){
				addDays++;
			}
		}
		return now.getTime();
	}

	private static DateTime doGetStartOfToday() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		DateTime dateTime = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
		return dateTime;
	}

	/**
	 * 返回当天的起点时间。
	 * 
	 * @return
	 */
	public static Date getBeginTimeOfToday() {
		return doGetStartOfToday().toDate();
	}

	/**
	 * 获取当天终点时间。
	 * 
	 * @return
	 */
	public static Date getEndTimeOfToday() {
		return doGetStartOfToday().plusHours(23).plusMinutes(59).plusSeconds(59).plusMillis(999).toDate();
	}

	/**
	 * 获指定日期算起指定相差天数的日期那天最初
	 * 
	 * @param date
	 *            指定的日期,如果为null,则默认为当前
	 * @param days
	 *            天数,正数或者负数
	 * @return 日期。
	 */
	public static Date getStartOfDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.DAY_OF_YEAR, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 000);
		return cal.getTime();
	}

	/**
	 * 获指定日期算起指定相差天数的日期那天最末
	 * 
	 * @param date
	 *            指定的日期,如果为null,则默认为当前
	 * @param days
	 *            天数,正数或者负数
	 * @return 格式化后的日期字符串。
	 */
	public static Date getEndOfDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.DAY_OF_YEAR, days);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(Y_M_D_H_M_S);
		return df.format(date);
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = null;
		if ((pattern == null) || ("".equals(pattern))) {
			df = new SimpleDateFormat(Y_M_D_H_M_S);
		} else {
			df = new SimpleDateFormat(pattern);
		}
		return df.format(date);
	}

	public static boolean checkDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat(Y_M_D_H_M_S);
		try {
			dateFormat.parse(date);
		} catch (Exception e) {
			return false;
		}
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		return m.matches();
	}

	public static Date getSkipWorkday(int skipDay) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int dayOfWeek = cal.get(7);
		switch (dayOfWeek) {
		case 5:
			cal.add(5, skipDay + 2);
			break;
		case 6:
			cal.add(5, skipDay + 2);
			break;
		case 7:
			cal.add(5, skipDay + 1);
			break;
		default:
			cal.add(5, skipDay);
		}
		return cal.getTime();
	}

	public static Date getSkipWorkday(Date date, int skipDay) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int dayOfWeek = cal.get(7);
		switch (dayOfWeek) {
		case 5:
			cal.add(5, skipDay + 2);
			break;
		case 6:
			cal.add(5, skipDay + 2);
			break;
		case 7:
			cal.add(5, skipDay + 1);
			break;
		default:
			cal.add(5, skipDay);
		}
		return cal.getTime();
	}

	/**
	 * 闰年判断
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (((year % 4 == 0) && (year % 100 != 0)) || ((year % 4 == 0) && (year % 400 == 0))) {
			return true;
		}
		return false;
	}

	/**
	 * 获取两个日期直接相差的天、小时、分、秒
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @throws ParseException
	 */
	public static TimeDistance getTimeDistance(String startTime, Date endTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return getTimeDistance(formatter.parse(startTime), endTime);
	}

	/**
	 * 获取两个日期直接相差的天、小时、分、秒
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @throws ParseException
	 */
	public static TimeDistance getTimeDistance(Date startTime, String endTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return getTimeDistance(startTime, formatter.parse(endTime));
	}

	/**
	 * 获取两个日期直接相差的天、小时、分、秒
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @throws ParseException
	 */
	public static TimeDistance getTimeDistance(String startTime, String endTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return getTimeDistance(formatter.parse(startTime), formatter.parse(endTime));
	}

	/**
	 * 获取两个日期直接相差的天、小时、分、秒
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @throws ParseException
	 */
	public static TimeDistance getTimeDistance(Date startTime, Date endTime) {
		long milliseconds = endTime.getTime() - startTime.getTime();
		long oneDay = TimeUnit.DAYS.toMillis(1);// 一天的毫秒数(1000 * 24 * 60 * 60)
		long oneHur = TimeUnit.HOURS.toMillis(1);// 一小时的毫秒数(1000 * 60 * 60)
		long oneMiu = TimeUnit.MINUTES.toMillis(1);// 一分钟的毫秒数(1000 * 60)
		long oneSec = TimeUnit.SECONDS.toMillis(1);// 一秒钟的毫秒数(1000)

		long days = milliseconds / oneDay;// 计算差多少天
		long hours = milliseconds % oneDay / oneHur + days * 24;// 计算差多少小时
		long minutes = milliseconds % oneDay % oneHur / oneMiu + days * 24 * 60;// 计算差多少分钟
		long seconds = milliseconds % oneDay % oneHur % oneMiu / oneSec;// 计算差多少秒

		return new TimeDistance(milliseconds, seconds, (minutes - days * 24 * 60), (hours - days * 24), days);
	}

	public static class TimeDistance {
		private long milliseconds;// 毫秒

		private long seconds;// 秒

		private long minutes;// 分钟

		private long hours;// 小时

		private long days;// 天数

		public TimeDistance(long milliseconds, long seconds, long minutes, long hours, long days) {
			super();
			this.milliseconds = milliseconds;
			this.seconds = seconds;
			this.minutes = minutes;
			this.hours = hours;
			this.days = days;
		}

		void setMilliseconds(long milliseconds) {
			this.milliseconds = milliseconds;
		}

		void setSeconds(long seconds) {
			this.seconds = seconds;
		}

		void setMinutes(long minutes) {
			this.minutes = minutes;
		}

		void setHours(long hours) {
			this.hours = hours;
		}

		void setDays(long days) {
			this.days = days;
		}

		public long getMilliseconds() {
			return milliseconds;
		}

		public long getSeconds() {
			return seconds;
		}

		public long getMinutes() {
			return minutes;
		}

		public long getHours() {
			return hours;
		}

		public long getDays() {
			return days;
		}
	}

	public static String dateToStr(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	public static void main(String[] args) {
		// 预期是2018-06-27   +16天
		//System.out.println("args = " + getWorkDateAfter(new Date(), 4));
		String dateStr = "2018-06-07 09:21:20";
		String dateStr2 = "2018-06-15 09:21:11";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			Date date = format.parse(dateStr);
			Date date2 = format.parse(dateStr2);
			System.out.println("subWorkDays() = " + subWorkDays(date, date2));
			System.out.println("subWorkDays() = " + subWorkDays(date2, date));
			int days = (int) ((date.getTime() - date2.getTime()) / (1000*3600*24));
			//System.out.println("两个日期的差距：" + days);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//将日期转成Date对象作比较
		/*try {
			Date fomatDate1 = sdf.parse(date2);
			System.out.println("args = " + fomatDate1.compareTo(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

	}
}
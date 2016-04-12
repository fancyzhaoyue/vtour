package com.fan.common.util;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期处理类。
 *
 */
public class DateUtil {
	/**
	 * 根据当前日期计算出与当前日期间隔时间单位的日期
	 * 
	 * @param currentDate
	 *            当前日期
	 * @param dateUnit
	 *            时间单位
	 * @param prev
	 *            前滚标志
	 * @param dateUnitType
	 *            滚动日期单位的类型
	 * @return
	 */
	public static Date rollDateByDateUnit(Date currentDate, int dateUnit,
			boolean prev, int dateUnitType) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		calendar.add(dateUnitType, prev ? -dateUnit : dateUnit);
		return new Date(calendar.getTime().getTime());
	}
}
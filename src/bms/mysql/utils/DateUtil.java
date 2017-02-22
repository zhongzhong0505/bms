package bms.mysql.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * 将格式为“yyyy年MM月dd日”的日期转换为格式为“yyyy-MM-dd” 的日期
	 * @param dateStr 要转换的日期字符串
	 * @return 转换后的日期字符串
	 * @throws Exception 抛出所有异常
	 */
	public static String getTagerDate(String dateStr) throws Exception{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date result = sdf1.parse(dateStr);
		return sdf2.format(result);
	}
}

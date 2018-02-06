package blog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtils {

	
	
	/*
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date){
		 //System.out.println("DateUtils -> getGormatDate 변경전value : " + date);
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //System.out.println("DateUtils -> getGormatDate 변경후value : " + format.format(date));
		 return  format.format(date);
	}
	
	
	/**
	 * String(datetime)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String date) throws ParseException{
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 return  format.parse(date);
	}
}

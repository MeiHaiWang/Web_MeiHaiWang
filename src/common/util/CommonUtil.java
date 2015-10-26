package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
	/* 年齢を求める*/
	public static int getAgeForBirthday(Date userBirth) {
    	Date nowDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	Calendar birthDay = Calendar.getInstance();
    	birthDay.setTime(userBirth);
    	Calendar today = Calendar.getInstance();
    	today.setTime(nowDate);
    	int age = today.get(Calendar.YEAR)-birthDay.get(Calendar.YEAR);
    	birthDay.clear(Calendar.YEAR);
    	today.clear(Calendar.YEAR);
    	if(birthDay.after(today)){
    		age-=1;
    	}
    	return age;
	}

	
	
}

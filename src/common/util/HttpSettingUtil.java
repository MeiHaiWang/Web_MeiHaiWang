package common.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpSettingUtil {

	public HttpSettingUtil(){

	}

	public static void setServletMetaInfo(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		 response.setLocale(Locale.JAPAN);
		 response.setContentType("application/json; charset=UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");

	}
}
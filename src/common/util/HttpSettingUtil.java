package common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpSettingUtil {

	public HttpSettingUtil(){

	}

	public void setServletMetaInfo(HttpServletRequest request,HttpServletResponse response)  throws Exception{

		 response.setContentType("application/json; charset=UTF-8");
		 request.setCharacterEncoding("UTF-8");
	}
}
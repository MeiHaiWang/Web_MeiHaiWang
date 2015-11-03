package business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IServiceExcuter {

	public HttpServletResponse excuteService(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

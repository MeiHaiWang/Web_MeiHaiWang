package presentation.action;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.service.GetConditionForSalonSearchService;
import business.service.GetLikeForStylistSearchService;

///@WebServlet(name="GetLikeForStylistSearch",urlPatterns={"/api/:version/stylist_search/liking"})
public class GetLikeForStylistSearchAction extends HttpServlet{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static Logger myLog = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetLikeForStylistSearchAction() {
		super();
	}

	/**
	 * @param HttpServletRequest
	 *            request クライアント送信用リクエストパラメータ
	 * @param HttpServletRequest
	 *            response クライアント返却用レスポンスパラメータ
	 * @author Hiroki Ebina
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//do nothing
	}

	/**
	 * @param HttpServletRequest
	 *            request クライアント送信用リクエストパラメータ
	 * @param HttpServletRequest
	 *            response クライアント返却用レスポンスパラメータ
	 * @author Hiroki Ebina
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String languageCode = request.getHeader("langage");
		if(languageCode != null && languageCode.equals("jp")){
			response.setLocale(Locale.JAPAN);
		}
		else if(languageCode != null && languageCode.equals("ch")){
			response.setLocale(Locale.CHINA);
		}
		else{
			//defaultは中国
			response.setLocale(Locale.CHINA);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//service excute
		GetLikeForStylistSearchService service = new GetLikeForStylistSearchService();
		//service.excuteService(request, response);
	    
	}	
}	
	
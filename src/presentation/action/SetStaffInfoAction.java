package presentation.action;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.service.SetSalonInfoService;
import business.service.SetStaffInfoService;

@WebServlet(name="SetStaffInfoServlet",urlPatterns={"/api/:version/setStaffInfo"})
public class SetStaffInfoAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SetStaffInfoAction(){
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
		SetStaffInfoService service = new SetStaffInfoService();
		service.excuteService(request, response);
	}	
}

/**
 *     {
      t_stylist_name,
      t_stylist_sex,
      t_stylist_phoneNumber,
      t_stylist_mail,
      t_stylist_imagePath,
      t_stylist_birth,
      t_stylist_position,
      t_stylist_experienceYear,
      t_stylist_specialMenu,
      t_stylist_message,
    }
 */

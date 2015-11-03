package presentation.action;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.factory.ServiceExcuteController;
import business.service.CheckLoginService;
import business.service.CheckSessionService;
import business.service.IServiceExcuter;
import business.service.SMScertificationService;
import business.service.UserLoginService;
import common.util.ConfigUtil;
import common.util.HttpSettingUtil;
import common.constant.Constant;

@WebServlet(name="RequestEntranceServlet",urlPatterns={"/api/:version/*"})
public class HttpRequestEntrance extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Logger logger = LogManager.getLogger();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
    public HttpRequestEntrance() {
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	logger.info("{}:doGet Start",HttpRequestEntrance.class.getName(), request.getRequestURI());
        try {
			HttpSettingUtil.setServletMetaInfo(request, response);
			//ルートパスでAPIが叩かれた場合
			if(request.getRequestURI().equals(ConfigUtil.getConfig("contextPath"))){
				return;
			}
			ServiceExcuteController controller = new ServiceExcuteController();
			IServiceExcuter service = controller.getServiceExcuter(request, response,Constant.HTTP_REQUEST_TYPE_GET);
			if(service != null){
				service.excuteService(request, response);
			}
		} catch (Exception e) {
			logger.error(e);
		} 
        logger.info("{}:doGet End",HttpRequestEntrance.class.getName());
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
		logger.trace("{}:doPost Start",this.getClass().toString(), request.getRequestURI());
		try {
			HttpSettingUtil.setServletMetaInfo(request, response);
			
			//ルートパスでAPIが叩かれた場合
			if(request.getRequestURI().equals(ConfigUtil.getConfig("contextPath"))){
				return;
			}
			ServiceExcuteController controller = new ServiceExcuteController();
			IServiceExcuter service = controller.getServiceExcuter(request, response,Constant.HTTP_REQUEST_TYPE_POST);
			
			// 強制的にセッションを作る「ユーザ登録」と「ログイン」に関してはセッションチェックとログイン状態の確認対象外
			if( /* regist-user ->      */   !(service instanceof SMScertificationService)
				/* user-app login ->   */	&& !(service instanceof UserLoginService )
				/* salon-tool login -> */	&& !(service instanceof CheckLoginService) 
		        /* salon-tool session-check -> */ && !(service instanceof CheckSessionService)){

				if(!isSession(request, response)){
					throw new Exception(Constant.MESSAGE_ERROR_NO_SESSION);
				}
				else if(!isUserLogin(request, response) && !isSalonLogin(request, response)){
					throw new Exception(Constant.MESSAGE_ERROR_NO_USER);
				}
			}
			
			if(service != null){
				service.excuteService(request, response);
			}
			
		} catch (Exception e) {
			logger.error(e);
		} 
		logger.trace("{}:doPost End",this.getClass().toString());
	}  
	
	private boolean isSession(HttpServletRequest request,HttpServletResponse response){
		boolean isSession = false;
		if(Integer.parseInt(ConfigUtil.getConfig("filterFlag")) == 1){
	        if(request.getSession(false) != null){
	        	isSession = true;
	        }
	    }
		return isSession;
    }
	
	private boolean isUserLogin(HttpServletRequest request,HttpServletResponse response){
		boolean isUserLogin = false;
		String userHash = request.getHeader(Constant.USERID) != null ? request.getHeader(Constant.USERID) : "";
	    if(!userHash.equals(Constant.USER_NOT_LOGIN) && !userHash.equals("")){
	    	isUserLogin = true;
	    }
		return isUserLogin;
    }

	private boolean isSalonLogin(HttpServletRequest request,HttpServletResponse response){
		boolean isSalonLogin = false;
		String salonId_str = (String)request.getSession(false).getAttribute("t_hairSalonMaster_salonId");
		if(salonId_str != null){
			isSalonLogin = true;
		}
		return isSalonLogin;
    }

}
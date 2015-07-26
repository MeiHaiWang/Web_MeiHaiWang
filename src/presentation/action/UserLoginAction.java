package presentation.action;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.service.UserLoginService;
import common.util.EncryptUtil;

@WebServlet(name="UserLoginServlet",urlPatterns={"/api/:version/login"})
public class UserLoginAction extends HttpServlet {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static Logger myLog = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginAction() {
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
		//TODO テスト用key 本番ではアプリ側と調整した場所にkeyファイルを配置する
		final byte[] key = "azu93fzzei93084jnnekamel2".getBytes();
		byte[] eTel = request.getParameter("etel").getBytes();
		byte[] etelIv = request.getParameter("telIv").getBytes();
		byte[] epw = request.getParameter("epw").getBytes();
		byte[] epwIv = request.getParameter("pwIv").getBytes();
		int snsLinkFlag = request.getParameter("snsLink") != null ?
				Integer.parseInt(request.getParameter("snsLink").toString()) : 0;
		String userHash = request.getParameter("userHash") != null ?
				request.getParameter("userHash").toString() : null;
		if(userHash == "NULL") userHash = null;
		
		try {
			byte[] tel = EncryptUtil.decrypt(key, etelIv, eTel);
			byte[] pw = EncryptUtil.decrypt(key, epwIv, epw);
			
			if(snsLinkFlag == 0){
				UserLoginService service = new UserLoginService();
				service.excuteService(request, response, tel.toString(), pw.toString(), userHash);
			}else if(snsLinkFlag == 1) {
				//TODO SNS連携の場合の処理
			}
			
		} catch (InvalidKeyException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}		
}

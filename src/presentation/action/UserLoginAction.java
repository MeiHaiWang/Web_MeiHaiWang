package presentation.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import net.sf.json.JSONObject;

import org.bouncycastle.util.encoders.Base64;

import business.service.UserLoginService;
import common.util.EncryptUtil;

//@WebServlet(name="UserLoginServlet",urlPatterns={"/api/:version/login"})
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

		//service側で処理するように
		UserLoginService service = new UserLoginService();
		service.excuteService(request, response);
		
		/*
		final byte[] key = "azu93fzzei93084jnnekamel2asdfghj".getBytes("UTF-8");
		//etelIv,epwIvを暗号化するときに使用したIvを取得(秘密鍵前半の16バイト)
		final byte[] ivIv = "azu93fzzei93084j".getBytes("UTF-8");
		
		byte[] eTel = Base64.decode(request.getParameter("etel"));
		byte[] etelIv = Base64.decode(request.getParameter("etelIv"));
		byte[] epw = Base64.decode(request.getParameter("epw"));
		byte[] epwIv = Base64.decode(request.getParameter("epwIv"));
		int snsLinkFlag = request.getParameter("snsLink") != null ?
				Integer.parseInt(request.getParameter("snsLink").toString()) : 0;
		String userHash = request.getParameter("userHash") != null ?
				request.getParameter("userHash").toString() : null;
		
		if(userHash == "NULL" ) userHash = null;
		
		try {
			String telIv = new String(EncryptUtil.decrypt(key, ivIv, etelIv));
			String pwIv = new String(EncryptUtil.decrypt(key, ivIv, epwIv));
			String tel = new String(EncryptUtil.decrypt(key, telIv.getBytes("UTF-8"), eTel));
			String pw = new String(EncryptUtil.decrypt(key, pwIv.getBytes("UTF-8"), epw));
			
			if(snsLinkFlag == 0){
				UserLoginService service = new UserLoginService();
				service.excuteService(request, response, tel.toString(), pw.toString(), userHash);
			}else if(snsLinkFlag == 1) {
				//TODO SNS連携の場合の処理
			}
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			writeResponseError(response,e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			writeResponseError(response,e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			writeResponseError(response,e);
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			writeResponseError(response,e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			writeResponseError(response,e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			writeResponseError(response,e);
		}
	*/

	}		
	
	/*
	private void writeResponseError(HttpServletResponse response,Exception e){
		boolean result = false;
		JSONObject jsonObject = new JSONObject();
		String resultStr = String.valueOf(result);
    	jsonObject.put("result", resultStr);		    	
    	jsonObject.put("cause", e.getStackTrace().toString());
		try {
			 PrintWriter out = response.getWriter();
			 out.print(jsonObject);
			 out.flush();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}
	*/
}

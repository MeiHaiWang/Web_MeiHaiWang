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

import common.util.EncryptUtil;
import business.service.AddHairStyleFavoriteService;
import business.service.SMScertificationService;

@WebServlet(name="UserRegistServlet",urlPatterns={"/api/:version/smscert"})
public class SMScertificationAction extends HttpServlet {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static Logger myLog = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SMScertificationAction() {
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
		String keyStr = "azu93fzzei93084jnnekamel2asdfghj";
		final byte[] key = keyStr.getBytes("UTF-8");
		//etelIv,epwIvを暗号化するときに使用したIvを取得(秘密鍵前半の16バイト)
		final byte[] ivIv = "azu93fzzei93084j".getBytes("UTF-8");
		
		byte[] eTel = request.getParameter("etel").getBytes("UTF-8");
		byte[] etelIv = request.getParameter("etelIv").getBytes("UTF-8");
		byte[] epw = request.getParameter("epw").getBytes("UTF-8");
		byte[] epwIv = request.getParameter("epwIv").getBytes("UTF-8");
		System.out.println(request.getParameter("etelIv"));
		
		try {
			String telIv = new String(EncryptUtil.decrypt(key, ivIv, etelIv));
			String pwIv = new String(EncryptUtil.decrypt(key, ivIv, epwIv));
			String tel = new String(EncryptUtil.decrypt(key, telIv.getBytes("UTF-8"), eTel));
			String pw = new String(EncryptUtil.decrypt(key, pwIv.getBytes("UTF-8"), epw));
			//MEMO 暫定で登録する
			//TODO SMS認証サービスを使用してワンタイムキーを返却する
			SMScertificationService service = new SMScertificationService();
			service.excuteService(request, response, tel, pw);
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

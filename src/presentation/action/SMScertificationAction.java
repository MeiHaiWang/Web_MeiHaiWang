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
		//TODO テスト用key 本番ではアプリ側と調整した場所にkeyファイルを配置する
		final byte[] key = "azu93fzzei93084jnnekamel2asdfghj".getBytes();
		byte[] eTel = request.getParameter("etel").getBytes();
		byte[] etelIv = request.getParameter("telIv").getBytes();
		byte[] epw = request.getParameter("epw").getBytes();
		byte[] epwIv = request.getParameter("pwIv").getBytes();
		
		try {
			String tel = new String(EncryptUtil.decrypt(key, etelIv, eTel));
			String pw = new String(EncryptUtil.decrypt(key, epwIv, epw));
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

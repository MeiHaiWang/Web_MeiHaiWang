package presentation.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import business.service.UploadImageService;
import business.service.UploadSalonImageService;
import business.service.UserLoginService;
import common.util.EncryptUtil;

/**
 * 

uploadImage

    概要：画像をアップロード
    入力：{ file:画像File情報 }
    処理：Postされたファイルをサーバ内に保存しURLを返却する
    出力：{ result:アップロード成否, image_path:画像URL }

 *
 */

@WebServlet(name="UploadSalonImageServlet",urlPatterns={"/api/:version/uploadSalonImage"})
@MultipartConfig(location="/tmp/files", maxFileSize=1000000, maxRequestSize=1000000, fileSizeThreshold=1000000)
public class UploadSalonImageAction extends HttpServlet {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static Logger myLog = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern(
            "uuuu-MM-dd-HH-mm-ss-SSS");
    private static final String FILE = "upload-file";
    private static final ZoneId HERE = ZoneId.of("Asia/Shanghai");


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadSalonImageAction() {
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
	
        // tempディレクトリをアイテムファクトリーの一次領域に設定
        ServletContext servletContext = this.getServletConfig().getServletContext();
		//service excute
		UploadSalonImageService service = new UploadSalonImageService();
		service.excuteService(request, response, servletContext);

	}	
}
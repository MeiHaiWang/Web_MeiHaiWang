package business.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ImageDao;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.PropertiesManager;

public class UploadImageService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response,
			ServletContext servletContext){
		
		/**
		 * Declaration values
		 */
		
		//debug
		System.out.println("UploadImageService");
		
		int responseStatus = HttpServletResponse.SC_OK;
		/*
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		*/

		boolean result = false;
		HttpSession session = request.getSession(false);

  		//salonId kokokara
  	    int salonId = -1;
  	    //get a salonId by session
  		String salonId_str = "";
  		if (session != null){
  			salonId_str = (String)session.getAttribute("t_hairSalonMaster_salonId");
  		}
  		if(salonId_str != null){			
  			if(salonId_str.compareTo("") != 0){
  				salonId = Integer.parseInt(salonId_str);
  			}
  		}
  		if(salonId < 0){
  	        //get a salonId by parameter
  	        salonId = request.getParameter(Constant.PARAMETER_SALONID)!= null 
  			?Integer.parseInt(request.getParameter(Constant.PARAMETER_SALONID)) : -1;
  		}
  		//salonId kokomade		
	
  		//変数定義
        int ImageId = -1;
  		long ImageSize = 0;  		
  		String ImageName = "";
		String ImageUrl = "http://localhost:8080/MeiHaiWangWebProject/img/notfound.jpg";
        String ImageFileName = "";
        String hashValue = null;

        //すでにアップロードされたファイルかどうか
        boolean insertFlag = false;

        //tempファイル
        File tmpfile;
		//tmpfile = (File)servletContext.getAttribute("javax.servlet.context.tempdir");
		tmpfile = new File(ConfigUtil.getConfig("tmppath"));
		//フォルダがなければ作成する
		if(!tmpfile.exists()){
			tmpfile.mkdir();
			tmpfile = new File(ConfigUtil.getConfig("tmppath"));
		}
		
		try{
	        // アップロードファイルを受け取る準備
	        // ディスク領域を利用するアイテムファクトリーを作成
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setRepository(tmpfile);
			
	        // ServletFileUploadを作成
	        ServletFileUpload upload = new ServletFileUpload(factory);        

	        //Uploadされるファイルサイズの最大値(200*1024)を設定
	        upload.setFileSizeMax(Integer.parseInt(ConfigUtil.getConfig("imagemaxsize")));
						
	        //リクエストをファイルアイテムのリストに変換
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
     
			// アップロードパス取得
			String upPath = ConfigUtil.getConfig("imagepath");
    		// アップロード用のフォルダがなければ作成
			File newdir = new File(ConfigUtil.getConfig("imagepath"));
    		if(!newdir.exists()){
    			newdir.mkdir();
    			newdir = new File(ConfigUtil.getConfig("imagepath"));
    		}
			//upPath = servletContext.getRealPath("/") + "upload/";
			//System.out.println("upPath : "+upPath);

    		//書き込み用バッファ
    		byte[] buff = new byte[1024];
    		int size = 0;

			// アップロードファイルの処理
    		for (FileItem item : items) {
    			if (!item.isFormField()) {
    				//ImageName対応(アップロード時のファイル名)
	    			ImageName = item.getName();
	    			//アップロードしたファイルのサイズ
	    			ImageSize = item.getSize();
	    			
    				try{
	    				DBConnection dbConnection = new DBConnection();
	    				java.sql.Connection conn = dbConnection.connectDB();
	    				if(conn!=null){
	    					ImageDao imageDao = new ImageDao();
	    					/*
	    					//file がすでにアップロードされたものかどうかを確認(hashを使って比較)
	    					//未アップロードならid=-1
	    					hashValue = getHashValue(item);
	    					ImageId = imageDao.checkImageExist(
	    							dbConnection,
	    							hashValue,
	    							salonId
	    					      );
    					      */
		        			//Imageが未アップロードなら空きimageIdを取得
		        			if(ImageId<0){
		        				if(conn!=null){
		        					ImageId = imageDao.getImageId(
		        							dbConnection
		        							);
		        				}
		        				//アップロード＆テーブル更新flag
		        				insertFlag = true;
		        			}
	        			}else{
	    					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	    					throw new Exception("DabaBase Connect Error");
	    				}
	    				dbConnection.close();
	    			}catch(Exception e){
	    				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	    				e.printStackTrace();
	    			}

    				//ImageFileName対応
	    			/*
	    			 * 添付ファイルID.拡張子」 例：　1.png
	    			 */
	    			if(item.getName().indexOf("png")>0){
	        			ImageFileName = Integer.toString(ImageId) + ".png";    				
	    			}else if(item.getName().indexOf("jpg")>0){
	        			ImageFileName = Integer.toString(ImageId) + ".jpg";    				    				
	    			}else if(item.getName().indexOf("jpeg")>0){
	        			ImageFileName = Integer.toString(ImageId) + ".jpeg";    				    				
	    			}else{
	    				//debug
	    				System.out.println("Error...");
	    				break;
	    			}
    				
	    			// ファイルをuploadディレクトリに保存
	    			if(insertFlag){
		    			// ファイルをuploadディレクトリに保存
		    			BufferedInputStream in;
		    			in = new BufferedInputStream(item.getInputStream());
		    			//File f = new File(upPath + item.getName());
		    			File f = new File(upPath + ImageFileName);
		    			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
		    			while ((size = in.read(buff)) > 0) {
		    				out.write(buff, 0, size);
	    				}
		    			out.close();
		    			in.close();
	    			}

	    			//レスポンス対応(result,imageUrl)
	    			//ImageUrl対応
		            /*ファイル名に半角英数のみを許す
		            if(ImageName.matches("[0-9a-zA-Z.]+")){
			            //ImageUrl = ConfigUtil.getConfig("imageurl")+servletContext.getContextPath() + "/upload/" + ImageName;
			            ImageUrl = ConfigUtil.getConfig("imageurl") + ImageName;
			            result = true;
		            }
		            */
	    			ImageUrl = ConfigUtil.getConfig("imageurl") + ImageFileName;	              

	    			if(insertFlag){
		    			//テーブル更新(sql insert)
		    			try{
		    				DBConnection dbConnection = new DBConnection();
		    				java.sql.Connection conn = dbConnection.connectDB();
		    				
		    				if(conn!=null){
		    					ImageDao imageDao = new ImageDao();
		    					result = imageDao.setImageInfo(
		    							dbConnection,
		    							salonId,
		    							ImageId,
		    							ImageName,
		    							ImageUrl,
		    							hashValue,
		    							Long.toString(ImageSize)
		    					      );
		    					dbConnection.close();
		    				}else{
		    					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		    					throw new Exception("DabaBase Connect Error");
		    				}
		    			}catch(Exception e){
		    				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		    				e.printStackTrace();
		    			}
	    			}
	    			//uploadImage成功
	    			result = true;
    			} else {
    				System.out.println("ファイル以外の処理...");
    				// ここでは処理せず、直接requestからgetParamしてもいいと思います。
				}
			}
		} catch (FileUploadException e) {
          // 例外処理
        	e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		//レスポンスに設定するJSON Object
        /*
         * { result:アップロード成否, image_path:画像URL }
         */
        JSONObject jsonObject = new JSONObject();
		String resultStr = String.valueOf( result );
    	jsonObject.put("result", resultStr);		    	
	    jsonObject.put("image_path", ImageUrl);
	    
	    //debug
	    System.out.println(result +"," + ImageUrl);
	    
	    PrintWriter out;
		try {
			out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //response.getWriter().flush();
        response.setStatus(responseStatus);
		return response;
	}

	//hash計算
    public static String getHashValue(FileItem targetValue){
    	MessageDigest md = null;
    	StringBuffer buffer = new StringBuffer();
    	try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    md.update(targetValue.get());
	    byte[] valueArray = md.digest();
	    // ハッシュ値の配列をループ
	    for(int i = 0; i < valueArray.length; i++){
	        // 値の符号を反転させ、16進数に変換
	        String tmpStr = Integer.toHexString(valueArray[i] & 0xff);
	        if(tmpStr.length() == 1){
	            // 値が一桁だった場合、先頭に0を追加し、バッファに追加
	            buffer.append('0').append(tmpStr);
	        } else {
	            // その他の場合、バッファに追加
	            buffer.append(tmpStr);
	        }
	    }
	    // 完了したハッシュ計算値を返却
	    return buffer.toString();
    }

	
}


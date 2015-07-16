package business.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
		
  		long ImageSize = 0;  		
  		String ImageName = "";
		String ImageUrl = "";
		
		File tmpfile;
		//tmpfile = (File)servletContext.getAttribute("javax.servlet.context.tempdir");
		tmpfile = new File(ConfigUtil.getConfig("tmppath"));

		//makedir?
		if(!tmpfile.exists()){
			tmpfile.mkdir();
			tmpfile = new File(ConfigUtil.getConfig("tmppath"));
		}
		
		try{
	        // (2) アップロードファイルを受け取る準備
	        // ディスク領域を利用するアイテムファクトリーを作成
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setRepository(tmpfile);
			
	        // ServletFileUploadを作成
	        ServletFileUpload upload = new ServletFileUpload(factory);        

	        //Upload setMaxSize.
	        upload.setSizeMax(200 * 1024);
			upload.setFileSizeMax(100 * 1024);
						
	        // (3) リクエストをファイルアイテムのリストに変換
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
     
			// アップロードパス取得
			/*
			String upPath = null;
			InputStream inStream;
			Properties config_ = new Properties();	
			String configPath = "config.properties";
			String[] configPaths = new String[]{configPath};
			for(String confPath :configPaths){
				inStream = PropertiesManager.class.getClassLoader().getResourceAsStream(confPath);
				config_.load(inStream);
			}
			String imageurl = config_.getProperty("imagepath");
			upPath = imageurl;
			*/
          String upPath = ConfigUtil.getConfig("imagepath");
          
    		//makedir?
			File newdir = new File(ConfigUtil.getConfig("imagepath"));
    		if(!newdir.exists()){
    			newdir.mkdir();
    			newdir = new File(ConfigUtil.getConfig("imagepath"));
    		}
          
          //TODO : test : 通し番号をつけたい
          //upPath = servletContext.getRealPath("/") + "upload/";
          System.out.println("upPath : "+upPath);
          
          byte[] buff = new byte[1024];
          int size = 0;
          for (FileItem item : items) {
              System.out.println("item: "+item.getName());
            // (4) アップロードファイルの処理
            if (!item.isFormField()) {
            	
            	//file がすでにアップロードされたものかどうかを確認
    			try{
    				DBConnection dbConnection = new DBConnection();
    				java.sql.Connection conn = dbConnection.connectDB();
    				
    				if(conn!=null){
    					ImageDao imageDao = new ImageDao();
    					result = imageDao.checkImageExist(
    							dbConnection,
    							item.getName(),
    							salonId
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
            	

    			//uploadされていなければ
    			if(result){
	              // ファイルをuploadディレクトリに保存
	              BufferedInputStream in;
	              in = new BufferedInputStream(item.getInputStream());
	              File f = new File(upPath + item.getName());
	              BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
	              while ((size = in.read(buff)) > 0) {
	                out.write(buff, 0, size);
	              }
	              out.close();
	              in.close();
	     
	              //アップロードしたファイルのサイズ
	              ImageSize = item.getSize();
	              // アップロードしたファイルへのURLリンク
	              //response.getWriter().print(servletContext.getContextPath() + "/upload/" + item.getName());
	              ImageName = item.getName();
//	              ImageUrl = ConfigUtil.getConfig("imageurl")+servletContext.getContextPath() + "/upload/" + ImageName;
	              ImageUrl = ConfigUtil.getConfig("imageurl") + ImageName;
	              result = true;
    			}              
              // (5) フォームフィールド（ファイル以外）の処理
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

		if(result){
			//sql insert
			try{
				DBConnection dbConnection = new DBConnection();
				java.sql.Connection conn = dbConnection.connectDB();
				
				if(conn!=null){
					ImageDao imageDao = new ImageDao();
					result = imageDao.setImageInfo(
							dbConnection,
							salonId,
							ImageName,
							ImageUrl,
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

	/**
		 * 
		
		try{
			List<HairSalonInfo> infoList = new ArrayList<HairSalonInfo>();
			if(result){
				DBConnection dbConnection = new DBConnection();
				java.sql.Connection conn = dbConnection.connectDB();
				if(conn!=null){
					SalonDao salonDao = new SalonDao();
					salonContactUserName = salonDao.getCheckSession(dbConnection, salonId);
					dbConnection.close();
				}else{
					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
					throw new Exception("DabaBase Connect Error");
				}
			}			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			JSONArray salonArray = new JSONArray();
	    	JSONObject jsonOneData = new JSONObject();
	    	jsonOneData.put("t_hairSalonMaster_salonId", salonId);		    	
	    	jsonOneData.put("t_hairSalonMaster_contactUserName", salonContactUserName);		    	
	    	salonArray.add(jsonOneData);
		    jsonObject.put("", salonArray);
		    
		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
		 */

}

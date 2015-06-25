package business.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.SalonDao;
import common.constant.Constant;
import common.model.HairSalonInfo;
import common.util.DBConnection;

public class UploadImageService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response,
			ServletContext servletContext){
		
        int responseStatus = HttpServletResponse.SC_OK;
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;

		boolean result = false;
		HttpSession session = request.getSession(false);
		//debug
		//System.out.println("login: " + session.getAttribute("salonId"));		

		/**
		 * 
		 */

		String ImageUrl = "";
		
        // (2) アップロードファイルを受け取る準備
        // ディスク領域を利用するアイテムファクトリーを作成
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository((File) servletContext.getAttribute("javax.servlet.context.tempdir"));

        // ServletFileUploadを作成
        ServletFileUpload upload = new ServletFileUpload(factory);        
        try {
          // (3) リクエストをファイルアイテムのリストに変換
          List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
     
          // アップロードパス取得
          String upPath = servletContext.getRealPath("/") + "upload/";
          byte[] buff = new byte[1024];
          int size = 0;
     
          for (FileItem item : items) {
            // (4) アップロードファイルの処理
            if (!item.isFormField()) {
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
     
              // アップロードしたファイルへのURLリンク
              //response.getWriter().print(servletContext.getContextPath() + "/upload/" + item.getName());
              ImageUrl = servletContext.getContextPath() + "/upload/" + item.getName();
              result = true;
              
              // (5) フォームフィールド（ファイル以外）の処理
            } else {
              // ここでは処理せず、直接requestからgetParamしてもいいと思います。
            }
          }
        } catch (FileUploadException e) {
          // 例外処理
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
	    jsonObject.put("result", result);
	    jsonObject.put("image_path", ImageUrl);
	    
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
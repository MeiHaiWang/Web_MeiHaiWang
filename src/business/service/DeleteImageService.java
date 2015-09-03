package business.service;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.ImageDao;
import common.constant.Constant;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class DeleteImageService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
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
  		
		String imagePath = request.getParameter("path") != null ?
				request.getParameter("path").toString() : null;

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				ImageDao imageDao = new ImageDao();
				result = ImageDao.DeleteImageForMaster(
						dbConnection,
						imagePath
						);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/**
			 * ふぁいるの削除
			 */
			if(result){
				boolean fileDelete = true;
				// アップロードパス取得
				String upPath = ConfigUtil.getConfig("imagepath");
				String imageName = imagePath.substring(imagePath.lastIndexOf("/")+1);
				
				try{
					File file = new File(upPath + imageName);
					if (file.exists()){
						file.delete();
						//debug
					    System.out.println("ファイルは存在します");
					}else{
						//debug
					    System.out.println("ファイルは存在しません");
					}
				}catch(Exception e){
					fileDelete = false;
					e.printStackTrace();
				}
				if(!fileDelete) result = false;
			}
			
			/*
			    {
			      result:レコード更新成否,
			    }
			 * 
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
			
		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		    
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;
	}
}

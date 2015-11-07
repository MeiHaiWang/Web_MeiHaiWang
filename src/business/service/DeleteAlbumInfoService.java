package business.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.SalonDao;
import common._model.THairSalonMasterInfo;
import common.constant.Constant;
import common.util.DBConnection;

public class DeleteAlbumInfoService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        /*
        int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		 // userIdがパラメータ。なかったら-1を入れておく。
        //TODO テスト用
        userId = 1;
        */
        
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
  		
		String t_hairStyle_id = request.getParameter("t_hairStyle_id") != null ?
				request.getParameter("t_hairStyle_id").toString() : null;

		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null && t_hairStyle_id!=null){
				HairStyleDao hairStyleDao = new HairStyleDao();
				int resultInt = hairStyleDao.logicalDelete(dbConnection, Integer.parseInt(t_hairStyle_id));
				if(resultInt>0){
					SalonDao salonDao = new SalonDao();
					THairSalonMasterInfo salonInfo = new THairSalonMasterInfo();
					salonInfo = salonDao.get(dbConnection, salonId);
					String hsIds = salonInfo.getTHairSalonMasterHairStyleId();
					List<String> hsIdList = Arrays.asList(hsIds.split(","));
					hsIds = "";
					for(int index=0;index<hsIdList.size();index++){
						if(!hsIdList.get(index).equals(t_hairStyle_id)){
							hsIds += hsIdList.get(index)+",";
						}
					}
					hsIds = hsIds.substring(0,hsIds.length()-1);
					salonInfo.setTHairSalonMasterHairStyleId(hsIds);
					resultInt = salonDao.update(dbConnection, salonInfo);
				}
				if(resultInt>0) result = true;
				/*
				result = hairStyleDao.DeleteHairStyleInfoForMaster(
						dbConnection,
						t_hairStyle_id,
						salonId
						);
						*/
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
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

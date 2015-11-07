package business.service;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import common._model.THairStyleInfo;
import common.constant.Constant;
import common.model.HairStyleInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *        入力：

    {
      t_hairSalonMaster_salonId,
      t_hairStyle_hairTypeId,
      t_hairStyle_name,
      t_hairStyle_stylistId,
      t_hairStyle_imagePath,
      t_hairStyle_searchConditionId,
      t_hairStyle_areaId,
      t_hairStyle_message
    }

    出力：
    {
      result:レコード更新成否,
      t_hairStyle_id:登録したアルバムのID,
    }
 */

public class SetAlbumInfoService implements IServiceExcuter{
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
		   
		String t_hairSalonMaster_salonId = request.getParameter("t_hairSalonMaster_salonId") != null ?
				request.getParameter("t_hairSalonMaster_salonId").toString() : null;
		String t_hairStyle_id = request.getParameter("t_hairStyle_id") != null ?
				request.getParameter("t_hairStyle_id").toString() : null;
		String t_hairStyle_hairTypeId = request.getParameter("t_hairStyle_hairTypeId") != null ?
				request.getParameter("t_hairStyle_hairTypeId").toString() : null;
		String t_hairStyle_name = request.getParameter("t_hairStyle_name") != null ?
				request.getParameter("t_hairStyle_name").toString() : null;
		String t_hairStyle_stylistId = request.getParameter("t_hairStyle_stylistId") != null ?
				request.getParameter("t_hairStyle_stylistId").toString() : null;
		String t_hairStyle_imagePath = request.getParameter("t_hairStyle_imagePath") != null ?
				request.getParameter("t_hairStyle_imagePath").toString() : null;
		String t_hairStyle_searchConditionId = request.getParameter("t_hairStyle_searchConditionId") != null ?
				request.getParameter("t_hairStyle_searchConditionId").toString() : null;
		String t_hairStyle_areaId = request.getParameter("t_hairStyle_areaId") != null ?
				request.getParameter("t_hairStyle_areaId").toString() : null;
		String t_hairStyle_message = request.getParameter("t_hairStyle_message") != null ?
				request.getParameter("t_hairStyle_message").toString() : null;
		String t_hairStyle_updateDate = request.getParameter("t_hairStyle_updateDate") != null ?
				request.getParameter("t_hairStyle_updateDate").toString() : null;

		if(t_hairSalonMaster_salonId != null) salonId = Integer.parseInt(t_hairSalonMaster_salonId);
		//hairStyleInfo を渡したほうがきれいかも.
		THairStyleInfo hairStyleInfo = new THairStyleInfo();
		int hairStyleId = -1;
		if(t_hairStyle_id != null && t_hairStyle_id != "") hairStyleId = Integer.parseInt(t_hairStyle_id);
		hairStyleInfo.setTHairStyleId(hairStyleId);
		int hairTypeId = -1;
		if(t_hairStyle_hairTypeId != null && t_hairStyle_hairTypeId != "") hairTypeId = Integer.parseInt(t_hairStyle_hairTypeId);
		hairStyleInfo.setTHairStyleHairTypeId(hairTypeId);
		hairStyleInfo.setTHairStyleName(t_hairStyle_name);
		int stylistId = -1;
		if(t_hairStyle_stylistId != null && t_hairStyle_stylistId != "") stylistId = Integer.parseInt(t_hairStyle_stylistId);
		hairStyleInfo.setTHairStyleStylistId(stylistId);
		if(t_hairStyle_imagePath != null && t_hairStyle_imagePath != ""){
			hairStyleInfo.setTHairStyleImagePath(t_hairStyle_imagePath);
		}else{
			hairStyleInfo.setTHairStyleImagePath("img/notfound.jpg");
		}
		if(t_hairStyle_searchConditionId != null && t_hairStyle_searchConditionId != "")
			hairStyleInfo.setTHairStyleSearchConditionId(t_hairStyle_searchConditionId);
		hairStyleInfo.setTHairStyleAreaId(t_hairStyle_areaId);
		hairStyleInfo.setTHairStyleMessage(t_hairStyle_message);
        // Date型変換
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date uD = new Date();
		try {
			uD = sdf.parse(t_hairStyle_updateDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		hairStyleInfo.setTHairStyleUpdateDate(uD);
		
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			boolean result = false;
			//int hairStyleId = -1;
			JSONObject jsonObject = new JSONObject();
			
			if(conn!=null){
				HairStyleDao hairStyleDao = new HairStyleDao();
				/*
				hairStyleId = hairStyleDao.setAlbumInfoForMaster(
						dbConnection,
						salonId,
						hairStyleInfo
						);
				if(hairStyleId > 0) result = true;
				*/
				int resultInt = -1;
				if(hairStyleInfo.getTHairStyleId()>0){
					resultInt = hairStyleDao.save(dbConnection, hairStyleInfo);
				}else{
					resultInt = hairStyleDao.update(dbConnection, hairStyleInfo);
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			/*
			    {
			      result:レコード更新成否,
			      t_hairStyle_hairStyleId:登録したサービスのID,
			    }
			 */
			
			String resultStr = String.valueOf( result );
	    	jsonObject.put("result", resultStr);		    	
		    jsonObject.put("t_hairStyle_Id", hairStyleId);
			
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

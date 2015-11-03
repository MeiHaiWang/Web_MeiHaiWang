package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.HairTypeDao;
import common.constant.Constant;
import common.model.HairTypeInfo;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
 *
    getHairTypeList
        概要：ヘアスタイルタイプ一覧取得
        入力：なし
        出力：

    {
      type:[
        {
          t_hairType_id,
          t_hairType_name
        },
        ...
      ]
    }
 *
 */

public class GetHairTypeListService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;

        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<HairTypeInfo> HairTypeList_m  = new ArrayList<HairTypeInfo>();
			List<HairTypeInfo> HairTypeList_w  = new ArrayList<HairTypeInfo>();
			
			if(conn!=null){
				HairTypeDao hairTypeDao = new HairTypeDao();
				HairTypeList_m = hairTypeDao.getHairTypeCategoryInfo(dbConnection, Constant.MEN);	
				HairTypeList_w = hairTypeDao.getHairTypeCategoryInfo(dbConnection, Constant.WOMEN);	
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
			/**
			 * 
			    {
			      type:[
			        {
			          t_hairType_id,
			          t_hairType_name
			        },
			        ...
			      ]
			    }
    			 */
			
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray hairTypeArray = new JSONArray();
		    for(HairTypeInfo info : HairTypeList_m){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_hairType_id", info.getHairTypeId());
		    	jsonOneData.put("t_hairType_name", info.getHairTypeName());
			    hairTypeArray.add(jsonOneData);
		    }
		    for(HairTypeInfo info : HairTypeList_w){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_hairType_id", info.getHairTypeId());
		    	jsonOneData.put("t_hairType_name", info.getHairTypeName());
			    hairTypeArray.add(jsonOneData);
		    }
		    jsonObject.put("type", hairTypeArray);		    
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

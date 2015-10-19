package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.ConditionDao;
import business.dao.HairStyleDao;
import business.dao.HairTypeDao;
import common.constant.Constant;
import common.model.ConditionInfo;
import common.model.ConditionTitleInfo;
import common.model.HairStyleInfo;
import common.model.HairTypeInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class GetHairTypeCategoryService {
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
        String gender = request.getParameter("gender")!= null
        		?request.getParameter("gender") : "";
		 //gender=0 　性別フラグ　0=男性、1=女性
        /*
		try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			List<HairTypeInfo> infoList = new ArrayList<HairTypeInfo>();
			if(conn!=null){
				HairTypeDao hairTypeDao = new HairTypeDao();
				infoList = hairTypeDao.getHairTypeCategoryInfo(dbConnection, gender);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
		    
		    // 返却用サロンデータ（jsonデータの作成）
			JSONArray hairTypeArray = new JSONArray();
		    for(HairTypeInfo hairTypeInfo : infoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", hairTypeInfo.getHairTypeId());
		    	jsonOneData.put("name", hairTypeInfo.getHairTypeName());
		    	jsonOneData.put("image", hairTypeInfo.getHairTypeImagePath());
		    	hairTypeArray.add(jsonOneData);
		    }
		    jsonObject.put("catalogCategory",hairTypeArray);

		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    */
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			List<ConditionInfo> ConditionInfoList  = new ArrayList<ConditionInfo>();
			List<ConditionTitleInfo> ConditionTitleInfoList  = new ArrayList<ConditionTitleInfo>();
			
			ConditionInfo conditionInfo = new ConditionInfo();
			ConditionTitleInfo conditionTitleInfo = new ConditionTitleInfo();
			if(conn!=null){
				ConditionDao conditionDao = new ConditionDao();
				//int conditionType = -1;
				String conditionType = "";
				if(gender != null){
					/*
					if(isNumber(gender)){
						//conditionType = Integer.parseInt(t_masterSearchConditionType);
						conditionType = conditionDao.getConditionTypeName(dbConnection, gender);
					}else{
						conditionType = gender;
					}
					*/
					if(Integer.parseInt(gender)==0){
						conditionType=ConfigUtil.getConfig("conditiontype_men");//"男性用ヘアスタイル検索条件";
					}else{
						conditionType=ConfigUtil.getConfig("conditiontype_women");//"女性用ヘアスタイル検索条件";
					}
					//debug
					System.out.println("conditionType:"+conditionType);
					ConditionTitleInfoList = conditionDao.getConditionTitleInfo(dbConnection, conditionType);
					List<ConditionTitleInfo> LongTypeList = new ArrayList<ConditionTitleInfo>();
					LongTypeList.add(ConditionTitleInfoList.get(1));
					//ConditionInfoList = conditionDao.getConditionInfo(dbConnection, ConditionTitleInfoList);	
					ConditionInfoList = conditionDao.getConditionInfo(dbConnection, LongTypeList);	

					//ヘアタイプの画像を取得
					HairTypeDao hairTypeDao = new HairTypeDao();
					String hairTypeImage = "";
					for(int i=0;i<ConditionInfoList.size();i++){
						ConditionInfo cInfo = ConditionInfoList.get(i);
						hairTypeImage = hairTypeDao.getHairTypeImage(dbConnection, cInfo.getConditionId());
						cInfo.setConditionImagePath(hairTypeImage);
						ConditionInfoList.set(i,cInfo);
					}
				}
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}

			//レスポンスに設定するJSON Object(title)
			JSONObject jsonObject = new JSONObject();
			/*
			JSONArray condTitleArray = new JSONArray();
		    for(ConditionTitleInfo condTitleInfo : ConditionTitleInfoList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", condTitleInfo.getConditionTitleId());
		    	jsonOneData.put("name", condTitleInfo.getConditionTitleName());
		    	condTitleArray.add(jsonOneData);
		    }
		    // 返却用サロンデータ（jsonデータの作成）
		    jsonObject.put("catalogCategory",condTitleArray);
				*/
			// レスポンスに設定するJSON Object(value)
			JSONArray condInfoArray = new JSONArray();
			//0,1は、男性、女性が格納されているので必要無し
		    for(int i=0; i<ConditionInfoList.size(); i++){
		    	ConditionInfo condInfo = ConditionInfoList.get(i);
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("id", condInfo.getConditionId());
		    	//jsonOneData.put("titleID", condInfo.getConditionTitleId());
		    	jsonOneData.put("name", condInfo.getConditionName());
		    	jsonOneData.put("image", condInfo.getConditionImagePathImage());
		    	condInfoArray.add(jsonOneData);
		    }
		    jsonObject.put("catalogCategory", condInfoArray);
		    		    
		    //debug
		    //System.out.println(jsonObject.toString(4));
		    
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
	
	public boolean isNumber(String num) {
	    try {
	        Integer.parseInt(num);
	        return true;
	        } catch (NumberFormatException e) {
	        return false;
	    }
	}

}

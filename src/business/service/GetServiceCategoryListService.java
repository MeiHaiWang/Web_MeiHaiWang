package business.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import business.dao.MenuCategoryDao;
import business.dao.MenuDao;
import business.dao.SalonDao;
import common.model.MenuCategoryInfo;
import common.model.MenuInfo;
import common.util.DBConnection;

public class GetServiceCategoryListService {
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
        int responseStatus = HttpServletResponse.SC_OK;

        /*
		int userId = request.getHeader(Constant.HEADER_USERID)!= null 
        		?Integer.parseInt(request.getHeader(Constant.HEADER_USERID)) : -1;
		*/
        
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();

			//List<Integer> menuIdList = new ArrayList<Integer>();
			List<MenuCategoryInfo> menuCategoryList  = new ArrayList<MenuCategoryInfo>();
			
			if(conn!=null){
				//SalonDao salonDao = new SalonDao();
				//menuIdList = salonDao.getMenuIdList(dbConnection, salonId);
				MenuCategoryDao menuCatDao = new MenuCategoryDao();
				menuCategoryList = menuCatDao.getMenuCategoryListInfo(dbConnection);	
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
			      category:[
			        {
			          t_menuCategory_categoryId,
			          t_menuCategory_name
			        },
			        ...
			      ]
			    }
			 */
			
		    // 返却用サロンデータ（jsonデータの作成）
		    JSONArray menuCatArray = new JSONArray();
		    for(MenuCategoryInfo info : menuCategoryList){
		    	JSONObject jsonOneData = new JSONObject();
		    	jsonOneData.put("t_menuCategory_categoryId", info.getMenuCategoryId());
		    	jsonOneData.put("t_menuCategory_name", info.getMenuCategoryName());
			    menuCatArray.add(jsonOneData);
		    }
		    jsonObject.put("category",menuCatArray);		    
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

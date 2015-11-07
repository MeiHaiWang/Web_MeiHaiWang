package business.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import business.dao.SampleUserDao;

import common._model.TUserInfo;
import common.util.DBConnection;

public class SampleUserService implements IServiceExcuter {
	
	private SampleUserDao userDao = new SampleUserDao();
	
	private JSONObject jsonObject = new JSONObject();
	
	@Override
	public HttpServletResponse excuteService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DBConnection dbConnection = new DBConnection();
		dbConnection.connectDB();
		
		try {
			TUserInfo tUserInfo = userDao.get(dbConnection, 16);
			int id = 1;
			if (tUserInfo == null) {
				tUserInfo = new TUserInfo();
				tUserInfo.setTUserName("hoge");
				tUserInfo.setTUserMail("hoge@hoge");
				id = userDao.save(dbConnection, tUserInfo);
			}
			User user = new User();
			user.setId(id);
			user.setName(tUserInfo.getTUserName());
			user.setMail(tUserInfo.getTUserMail());
			
			jsonObject.put("result", user);
			
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			return response;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// 上位に投げるか握りつぶすかはよくわからん
			throw e;
		}
		
	}
	
	public static class User {
		
		private int id;
		
		private String name;
		
		private String mail;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getMail() {
			return mail;
		}
		
		public void setMail(String mail) {
			this.mail = mail;
		}
		
	}
}

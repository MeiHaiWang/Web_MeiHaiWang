package business.service;

import java.io.PrintWriter;
import java.net.Authenticator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import business.dao.SalonDao;
import common.model.HairSalonInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.MailAuthenticator;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegistSalonService implements IServiceExcuter{
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		/**
		 * Declaration value
		 */
        int responseStatus = HttpServletResponse.SC_OK;

        String contactUserName = request.getParameter("userName")!= null?
        		request.getParameter("userName") : "";
 
        String salonName = request.getParameter("salonName")!= null?
                request.getParameter("salonName") : "";   
            
        String parentAreaId = request.getParameter("parentArea")!= null?
                request.getParameter("parentArea") : "";        

        String childAreaId = request.getParameter("childArea")!= null?
                request.getParameter("childArea") : "";
                
        String address = request.getParameter("address")!= null?
                request.getParameter("address") : ""; 
        
        String tel = request.getParameter("tel")!= null?
                request.getParameter("tel") : "";          
       
        String mail = request.getParameter("mail")!= null?
                request.getParameter("mail") : "";    
        
        String pass = request.getParameter("pass")!= null?
                request.getParameter("pass") : "";  
        
        boolean result = false;
        try{
			DBConnection dbConnection = new DBConnection();
			java.sql.Connection conn = dbConnection.connectDB();
			HairSalonInfo salonInfo = new HairSalonInfo();
			if(conn!=null){
				salonInfo.setSalonContactUserName(contactUserName);
				salonInfo.setHairSalonName(salonName);
				salonInfo.setSalonAreaId(childAreaId);
				salonInfo.setAddress(address);
				salonInfo.setTelNumber(tel);
				salonInfo.setMail(mail);
				salonInfo.setPassword(pass);
				SalonDao salonDao = new SalonDao();
				result  = salonDao.registSalonOnDisable(salonInfo, dbConnection);
				dbConnection.close();
			}else{
				responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				throw new Exception("DabaBase Connect Error");
			}
			//レスポンスに設定するJSON Object
			JSONObject jsonObject = new JSONObject();
			String resultStr = String.valueOf(result);
	    	jsonObject.put("regist", resultStr);
	    	
		    PrintWriter out = response.getWriter();
		    out.print(jsonObject);
		    out.flush();
		    try{
/*	            Properties property = new Properties();
	            property.put("mail.smtp.auth", "true");
	            property.put("mail.smtp.starttls.enable", "true");
	            property.put("mail.smtp.host", ConfigUtil.getConfig("smtp_hostName"));
	            property.put("mail.smtp.port", ConfigUtil.getConfig("smtp_portNum"));
	            property.put("mail.smtp.debug", "true");
	            MailAuthenticator mailCreator = new MailAuthenticator(ConfigUtil.getConfig("smtp_userName"),ConfigUtil.getConfig("smtp_password"));
	            Session session = Session.getInstance(property,mailCreator);
	            
	            MimeMessage mimeMessage = new MimeMessage(session);

	            InternetAddress toAddress = new InternetAddress(mail, contactUserName);

	            mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);

	            InternetAddress fromAddress = new InternetAddress(ConfigUtil.getConfig("smtp_senderAddress"),ConfigUtil.getConfig("smtp_senderName"));

	            mimeMessage.setFrom(fromAddress);

	            mimeMessage.setSubject("たいとっる", "ISO-2022-JP");

	            mimeMessage.setText("ほんぶーん", "ISO-2022-JP");

	            Transport.send(mimeMessage);

	            out.println("<htm><body>");
	            out.println("■お問い合わせ内容を担当者へ送信しました。");
	            out.println("<body></html>");*/
		    }catch(Exception e){
		    	responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		    	e.printStackTrace();
		    }
               
		}catch(Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	    
		response.setStatus(responseStatus);
		return response;		
	}
}

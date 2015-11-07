package business.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import net.sf.json.JSONObject;
import business.dao.HairStyleDao;
import business.dao.ImageDao;
import business.dao.StylistDao;
import common._model.THairStyleInfo;
import common.constant.Constant;
import common.constant.TableConstant;
import common.model.HairStyleInfo;
import common.model.StylistInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

/**
 * 
 * @author kanijunnari
	 *Request Params
	
	stylistId=1234    
	gender=0 or 1 (性別)
	liking=1&liking=2 スタイルのタグ（かわいい系）
	title="ああああ"　タイトル
	message="ヘアスタイルの説明"
	image=画像データjpegでいいかな？？

	*Response
	{result: true/false}
 */
public class SetHairStyleService implements IServiceExcuter{
	@SuppressWarnings({ "unchecked", "unused" })
	public HttpServletResponse excuteService(HttpServletRequest request,
			HttpServletResponse response){
		
        int responseStatus = HttpServletResponse.SC_OK;
  		HttpSession session = request.getSession(false);

  		Map<String, String> paramList = new HashMap<String ,String>();
  		
  		/**
  		 * まず画像ファイルの処理
  		 * 画像をアップロードし、ImageUrlを生成
  		 */

  		//変数定義
        int ImageId = -1;
  		long ImageSize = 0;  		
  		String ImageName = "";
		String ImageUrl = ConfigUtil.getConfig("notfound_imageurl");
        String ImageFileName = "";
        String hashValue = null;
        
        boolean result=false;
        int salonId = -1;
        
        //すでにアップロードされたファイルかどうか
        //boolean insertFlag = false;
        boolean insertFlag = true;

        //tempファイル
        File tmpfile;
		//tmpfile = (File)servletContext.getAttribute("javax.servlet.context.tempdir");
		tmpfile = new File(ConfigUtil.getConfig("tmppath"));
		//フォルダがなければ作成する
		if(!tmpfile.exists()){
			tmpfile.mkdir();
			tmpfile = new File(ConfigUtil.getConfig("tmppath"));
		}
		
		try{
	        // アップロードファイルを受け取る準備
	        // ディスク領域を利用するアイテムファクトリーを作成
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setRepository(tmpfile);
			
	        // ServletFileUploadを作成
	        ServletFileUpload upload = new ServletFileUpload(factory);        

	        //Uploadされるファイルサイズの最大値(200*1024)を設定
	        upload.setFileSizeMax(Integer.parseInt(ConfigUtil.getConfig("imagemaxsize")));
						
	        //リクエストをファイルアイテムのリストに変換
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
     
			// アップロードパス取得
			String upPath = ConfigUtil.getConfig("imagepath");
    		// アップロード用のフォルダがなければ作成
			File newdir = new File(ConfigUtil.getConfig("imagepath"));
    		if(!newdir.exists()){
    			newdir.mkdir();
    			newdir = new File(ConfigUtil.getConfig("imagepath"));
    		}
			//upPath = servletContext.getRealPath("/") + "upload/";
			//System.out.println("upPath : "+upPath);

    		//書き込み用バッファ
    		byte[] buff = new byte[1024];
    		int size = 0;

			// アップロードファイルの処理
    		for (FileItem item : items) {
    			if (!item.isFormField()) {
    				//ImageName対応(アップロード時のファイル名)
	    			ImageName = item.getName();
	    			//アップロードしたファイルのサイズ
	    			ImageSize = item.getSize();
	    			
    				DBConnection dbConnection = new DBConnection();
    				java.sql.Connection conn = dbConnection.connectDB();
    				if(conn!=null){
    					ImageDao imageDao = new ImageDao();
    					/*
    					//file がすでにアップロードされたものかどうかを確認(hashを使って比較)
    					//未アップロードならid=-1
    					hashValue = getHashValue(item);
    					ImageId = imageDao.checkImageExist(
    							dbConnection,
    							hashValue,
    							salonId
    					      );
					      */
	        			//Imageが未アップロードなら空きimageIdを取得
	        			if(ImageId<0){
	        				if(conn!=null){
	        					ImageId = imageDao.getImageId(
	        							dbConnection
	        							);
	        				}
	        				//アップロード＆テーブル更新flag
	        				//insertFlag = true;
	        			}
        			}else{
    					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    					throw new Exception("DabaBase Connect Error");
    				}
    				//dbConnection.close();
    				
    				//ImageFileName対応
	    			/*
	    			 * 添付ファイルID.拡張子」 例：　1.png
	    			 */
    				//debug
    				//System.out.println("item.getName():"+item.getName());
    				boolean pngFlag = false;
	    			if(item.getName().indexOf("png")>0){
	        			ImageFileName = Integer.toString(ImageId) + ".png";   
	        			pngFlag = true;
	    			}else if(item.getName().indexOf("jpg")>0){
	        			ImageFileName = Integer.toString(ImageId) + ".jpg";    				    				
	    			}else if(item.getName().indexOf("jpeg")>0){
	        			ImageFileName = Integer.toString(ImageId) + ".jpeg";    				    				
	    			}
    				else if(item.getName().indexOf("JPG")>0){
    					ImageFileName = Integer.toString(ImageId) + ".JPG";    				    				
    				}
    				else if(item.getName().indexOf("JPEG")>0){
    					ImageFileName = Integer.toString(ImageId) + ".JPEG";    				    				
    				}
    				else if(item.getName().indexOf("PNG")>0){
    					ImageFileName = Integer.toString(ImageId) + ".PNG"; 
    					pngFlag = true;
    				}
	    			else{
	    				//debug
	    				System.out.println("Error...");
	    				break;
	    			}
    				
	    			// ファイルをuploadディレクトリに保存
	    			if(insertFlag){
		    			// ファイルをuploadディレクトリに保存
		    			BufferedInputStream in;
		    			in = new BufferedInputStream(item.getInputStream());
		    			//File f = new File(upPath + item.getName());
		    			File f = new File(upPath + ImageFileName);
		    			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
		    			while ((size = in.read(buff)) > 0) {
		    				out.write(buff, 0, size);
	    				}
		    			out.close();
		    			in.close();
	    			}

        			/**
        			 * png -> jpeg変換
        			 */
	    			boolean convertResult = true;
	    			if(pngFlag){
	    				//debug
	    				System.out.println("Png -> Jpeg item:"+upPath + item.getName());
	        			String ImageFileName_jpg = Integer.toString(ImageId) + ".jpeg";   
	        			String from = upPath + ImageFileName;
	        			String to   = upPath + ImageFileName_jpg;
	        			try {
	        			    BufferedImage image = ImageIO.read(new File(from));
	        			    BufferedImage tmp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
	        			    Graphics2D off = tmp.createGraphics();
	        			    off.drawImage(image, 0, 0, Color.WHITE, null);
	        			    ImageIO.write(tmp, "jpg", new File(to));
	        			} catch (Exception e) {
 	        				convertResult = false;
	        			    System.out.println("error");
	        			}
        				//debug
        				//System.out.println((new File(to)).length()+">"+ConfigUtil.getConfig("imagemaxsize"));
	        			if((new File(to)).length()
	        					> Integer.parseInt(ConfigUtil.getConfig("imagemaxsize"))){
	        				convertResult = false;
	        				break;
	        			}

	        			/**
	        			 * ふぁいるの削除
	        			 */
        				try{
        					File file = new File(upPath + ImageFileName);
        					if (file.exists()){
        						file.delete();
        					}
        				}catch(Exception e){
        					e.printStackTrace();
        				}

        				ImageFileName = ImageFileName_jpg;
	    			}	    			

	    			/*
        			//画像リサイズ
        			String ImageFileName2 = "R"+ImageFileName;
        			try{
        				String args[] = {upPath + ImageFileName, upPath+ImageFileName2, "720", "1024"};
        				//System.out.println("resize-path:"+upPath+ImageFileName2);
        				ResizeImage.main(args);
        			}catch(Exception e){
        				e.printStackTrace();
        			}
        			*/
	    			
	    			//レスポンス対応(result,imageUrl)
	    			//ImageUrl対応
		            /*ファイル名に半角英数のみを許す
		            if(ImageName.matches("[0-9a-zA-Z.]+")){
			            //ImageUrl = ConfigUtil.getConfig("imageurl")+servletContext.getContextPath() + "/upload/" + ImageName;
			            ImageUrl = ConfigUtil.getConfig("imageurl") + ImageName;
			            result = true;
		            }
		            */
	    			ImageUrl = ConfigUtil.getConfig("imageurl") + ImageFileName;	              

	    			if(insertFlag || !convertResult){
		    			//テーブル更新(sql insert)
	    				if(conn!=null){
	    					ImageDao imageDao = new ImageDao();
	    					result = imageDao.setImageInfo(
	    							dbConnection,
	    							salonId,
	    							ImageId,
	    							ImageName,
	    							ImageUrl,
	    							hashValue,
	    							Long.toString(ImageSize)
	    					      );
	    					dbConnection.close();
	    				}else{
	    					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	    					throw new Exception("DabaBase Connect Error");
	    				}
	    			}
	    			//uploadImage成功
	    			result = true;
    			} else {
    				//パラメータはここで取得できる
    				//System.out.println("ファイル以外の処理...");
    				//String otherFieldName = item.getFieldName();
    	            //String otherFieldValue = item.getString();
    	            //debug
    	            //System.out.println("Parameter:"+item.getFieldName() +","+ item.getString());
    	            //スタイリストidからサロンidを取得
    	            if(item.getFieldName().equals("stylistId")){
	    				DBConnection dbConnection = new DBConnection();
	    				java.sql.Connection conn = dbConnection.connectDB();
	    				if(conn!=null){
	    	            	StylistDao stylistDao = new StylistDao();
	    	            	//salonId = stylistDao.getStylistSalonId(dbConnection, item.getString());
	    	            	//StylistInfo stylistInfo = new StylistInfo();
	    	            	//stylistInfo.setObjectId(Integer.parseInt(item.getString()));
	    	            	salonId = stylistDao.get(dbConnection, Integer.parseInt(item.getString())).getTStylistSalonId();
	    	            	//debug
	    	            	//System.out.println("salonId :"+salonId);
	        			}else{
	    					responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	    					throw new Exception("DabaBase Connect Error");
	    				}
	    				dbConnection.close();
    	            }
    	            //likingだったら連結してhashmapに格納
    	            if(item.getFieldName().equals("liking")){
    	            	if(paramList.containsKey("liking")){
            	            paramList.put(item.getFieldName(), paramList.get("liking")+","+item.getString());
    	            	}else{
            	            paramList.put(item.getFieldName(), item.getString());
    	            	}
    	            }else{
        				//HashMapにパラメータを格納
        	            paramList.put(item.getFieldName(), item.getString());
    	            }
				}
			}
		} catch (Exception e){
			responseStatus = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		
  		/**
   		 * 続いて、ヘアスタイル登録の処理
  		 */
  		/*
		String t_hairSalonMaster_salonId = request.getParameter("t_hairSalonMaster_salonId") != null ?
				request.getParameter("t_hairSalonMaster_salonId").toString() : null;
				*/
		int t_hairSalonMaster_salonId = salonId;
		/*
		String t_hairStyle_id = request.getParameter("t_hairStyle_id") != null ?
				request.getParameter("t_hairStyle_id").toString() : null;
		String t_hairStyle_hairTypeId = request.getParameter("t_hairStyle_hairTypeId") != null ?
				request.getParameter("t_hairStyle_hairTypeId").toString() : null;
		*/
		String t_hairStyle_name = paramList.get("title") != null ?
				paramList.get("title").toString() : null;
		String t_hairStyle_stylistId = paramList.get("stylistId") != null ?
				paramList.get("stylistId").toString() : null;
		String t_hairStyle_imagePath = ImageUrl;
		String t_hairStyle_searchConditionId = paramList.get("liking") != null ?
				paramList.get("liking").toString() : null;
		if(t_hairStyle_searchConditionId!=null && t_hairStyle_searchConditionId.contains("%2C")){
			t_hairStyle_searchConditionId = t_hairStyle_searchConditionId.replace("%2C", ",");
		}
				/*
		String t_hairStyle_areaId = request.getParameter("t_hairStyle_areaId") != null ?
				request.getParameter("t_hairStyle_areaId").toString() : null;
				*/
		String t_hairStyle_message = paramList.get("message") != null ?
				paramList.get("message").toString() : null;
				/*
		String t_hairStyle_updateDate = request.getParameter("t_hairStyle_updateDate") != null ?
				request.getParameter("t_hairStyle_updateDate").toString() : null;
				*/
		//現在日時を取得する
        Calendar c = Calendar.getInstance();
        //フォーマットパターンを指定して表示する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(sdf.format(c.getTime()));
        String t_hairStyle_updateDate = sdf.format(c.getTime());

		//if(t_hairSalonMaster_salonId != null) salonId = Integer.parseInt(t_hairSalonMaster_salonId);
		//hairStyleInfo に情報を格納
		THairStyleInfo hairStyleInfo = new THairStyleInfo();
		/*
		int hairStyleId = -1;
		if(t_hairStyle_id != null && t_hairStyle_id != "") hairStyleId = Integer.parseInt(t_hairStyle_id);
		hairStyleInfo.setHairStyleId(hairStyleId);
		int hairTypeId = -1;
		if(t_hairStyle_hairTypeId != null && t_hairStyle_hairTypeId != "") hairTypeId = Integer.parseInt(t_hairStyle_hairTypeId);
		hairStyleInfo.setHairTypeId(hairTypeId);
		*/
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
		//hairStyleInfo.setHairStyleAreaId(t_hairStyle_areaId);
		hairStyleInfo.setTHairStyleMessage(t_hairStyle_message);
        // Date型変換
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

			int hairStyleId = -1;
			JSONObject jsonObject = new JSONObject();
						
			if(conn!=null){
				if(salonId>0 && result){
					StylistDao stylistDao = new StylistDao();
					//hairStyleInfo.setHairStyleAreaId(stylistDao.getStylistAreaId(dbConnection,hairStyleInfo.getStylistId()));
					StylistInfo info = new StylistInfo();
					info.setObjectId(hairStyleInfo.getTHairStyleStylistId());
					String areaId = stylistDao.get(dbConnection, hairStyleInfo.getTHairStyleStylistId()).getTStylistAreaId();
					if(areaId!=null && Integer.parseInt(areaId)>0) hairStyleInfo.setTHairStyleAreaId(areaId);
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
		    //jsonObject.put("t_hairStyle_Id", hairStyleId);
			
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

package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import common.util.ConfigUtil;
import common.util.DBConnection;
import common.util.ListUtilities;

public class ImageDao {

	public ImageDao(){
	}
		
	public boolean setImageInfo(DBConnection dbConnection, int salonId,
			int ImageId,
			String ImageName,
			String ImageUrl,
			String hashValue,
			String ImageSize) {
		/**
		 *   INSERT INTO `MEIHAIWAN_TEST`.`t_image` (`t_image_id`, `t_image_name`, `t_image_filepath`, `t_image_size`) VALUES ('1', 'name1', 'img.com', '1000');
		 */

		boolean result = false;
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_image` ("
				+ "`t_image_id`, `t_image_name`, `t_image_filepath`, `t_image_size`, `t_image_salonId`, `t_image_hash`) VALUES ('";
		String sql2 = "', '";
		String sql3 = "');";

		/*
		String sql_update1 = "UPDATE `MEIHAIWAN_TEST`.`t_image` SET `t_image_size` = '";
		String sql_update2 = "' WHERE `t_image`.`t_image_id` = ";
		String sql_update3 = ";";
		*/
		
		Statement statement = dbConnection.getStatement();

		StringBuilder sql = new StringBuilder(sql1);
		sql.append(ImageId+sql2);
		sql.append(ImageName+sql2);
		sql.append(ImageUrl + sql2);
		sql.append(ImageSize + sql2);
		sql.append(salonId +sql2);
		sql.append(hashValue+ sql3);
		//debug
		System.out.println(sql);
		
		try {
			int result_int = statement.executeUpdate(sql.toString());
			if(result_int >= 0) result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		//update (sizeのみ)
		}else{
			String sql = sql_update1 + ImageSize + sql_update2 + Integer.toString(ImageId) + sql_update3;
			try {
				int result_int = statement.executeUpdate(sql);
				System.out.println(sql);
				if(result_int >= 0) result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 */		
		return result;	
	}

	public int checkImageExist(DBConnection dbConnection, String hash, int salonId) {
		int result = -1;
		String sql = "SELECT t_image_id FROM `t_image` WHERE `t_image_hash` = '" + hash + "'" + " AND `t_image_salonId` = " + salonId;
		Statement statement = dbConnection.getStatement();
		//debug
		System.out.println(sql);
		try {
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("t_image_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}

	public int getImageId(DBConnection dbConnection) {
		String sql = "SELECT * FROM `t_image` WHERE `t_image_id` = ";
		Statement statement = dbConnection.getStatement();
		//debug
		System.out.println(sql);
		int t_image_id = 0;
		for(int i=1; i<Integer.MAX_VALUE; i++){
			try {
				ResultSet rs = statement.executeQuery(sql+Integer.toString(i));
				if(!rs.next()){
					t_image_id = i;
					break;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return t_image_id;	
	}

	public static boolean DeleteImageForMaster(DBConnection dbConnection,
			String imagePath) {
		boolean result = false;

		/*
		 *SELECT `t_image_id` FROM `t_image` WHERE `t_image_filepath` = 'http'
		 *DELETE FROM `MEIHAIWAN_TEST`.`t_image` WHERE `t_image`.`t_image_id` = 10
		 */
		
		String sql1 = "SELECT `t_image_id` FROM `t_image` WHERE `t_image_filepath` = '"+imagePath+"'";
		String sql2 = "DELETE FROM `"+ConfigUtil.getConfig("dbname")+"`.`t_image` WHERE `t_image`.`t_image_id` =";
		Statement statement = dbConnection.getStatement();
		
		//debug
		System.out.println(sql1);
		
		try {
			int imageId = -1;
			ResultSet rs = statement.executeQuery(sql1);
			if(rs.next()){
				imageId = rs.getInt("t_image_id");
			}

			//debug
			System.out.println(sql2+imageId);
			if(imageId>0){
				int result_int = statement.executeUpdate(sql2 + imageId);
				if(result_int > 0) result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
}

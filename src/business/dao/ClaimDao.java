package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.model.ClaimInfo;
import common.util.ConfigUtil;
import common.util.DBConnection;

public class ClaimDao {

	public boolean setColaim(DBConnection dbConnection, ClaimInfo claimInfo) {
	boolean result = true;
		
		/**
		 * SQL 例:
		 * 
			INSERT INTO `MEIHAIWAN_TEST`.`t_claim` (`t_claim_id`, `t_claim_userId`, `t_claim_salonId`, `t_claim_reserveId`, `t_claim_menuId`, `t_claim_message`) VALUES ('1', '1', '1', '1', '1', '1');		
		 */
		String sql1 = "INSERT INTO `"+ConfigUtil.getConfig("dbname")+"`.`t_claim` ("
				+ "`t_claim_userId`, `t_claim_salonId`, `t_claim_reserveId`, `t_claim_menuId`, `t_claim_date`, `t_claim_message`) VALUES ('";
		String sql2 = "', '";
		String sql_end = "');";
				
		Statement statement = dbConnection.getStatement();
		
		String sql = sql1 +
				+ claimInfo.getClaimUserId() + sql2
				+ claimInfo.getClaimSalonId() + sql2
				+ claimInfo.getClaimReservationId() + sql2
				+ claimInfo.getClaimMenuId() + sql2
				+ claimInfo.getClaimDate() + sql2
				+ claimInfo.getClaimMessage()
				+ sql_end;
	
			//debug
			System.out.println("sql= "+ sql);
			
			try {
				int result_int = statement.executeUpdate(sql);
				if(result_int <= 0) result = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return result;
	}

}

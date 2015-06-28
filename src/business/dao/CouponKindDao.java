package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.model.CouponKindInfo;
import common.util.DBConnection;

public class CouponKindDao {
	public CouponKindDao(){
	}

	public List<CouponKindInfo> getCouponKindInfo(DBConnection dbConnection) throws SQLException{
		String sql= "SELECT * FROM `t_masterCouponKind`";

		List<CouponKindInfo> couponKindInfoList = new ArrayList<CouponKindInfo>();
		Statement statement = dbConnection.getStatement();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				CouponKindInfo ckInfo = new CouponKindInfo();
				 ckInfo.setCouponKindId(rs.getInt("t_couponKind_id"));
				 ckInfo.setCouponKindName(rs.getString("t_couponKind_name"));
				 couponKindInfoList.add(ckInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return couponKindInfoList;
	}

}

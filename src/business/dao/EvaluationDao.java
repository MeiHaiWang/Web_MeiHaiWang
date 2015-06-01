package business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import common.model.EvaluationInfo;
import common.model.ReviewInfo;
import common.util.DBConnection;

public class EvaluationDao {

	public List<EvaluationInfo> getReviewEvaluationInfo(DBConnection dbConnection, List<Integer> reviewIdList) throws SQLException{

		List<EvaluationInfo> EvaluationInfoList = new ArrayList<EvaluationInfo>();
		String sql = "SELECT `t_evaluation_evaluationId`, `t_evaluation_name`, `t_evaluation_point`, `t_evaluation_userId` "
				+ "FROM `t_evaluation` WHERE t_evaluation_evaluationId = ";

		Statement statement = dbConnection.getStatement();
		try {			
			for(int reviewId : reviewIdList){
				ResultSet rs = statement.executeQuery(sql+reviewId);
				EvaluationInfo evalInfo = new EvaluationInfo();
				while(rs.next()){
					evalInfo.setEvaluationId(rs.getInt("t_evaluation_evaluationId"));
					evalInfo.setEvaluationName(rs.getString("t_evaluation_name"));
					evalInfo.setEvaluationPoint(rs.getInt("t_evaluation_point"));
					evalInfo.setEvaluationUserId(rs.getInt("t_evaluation_userId"));
					EvaluationInfoList.add(evalInfo);
				}
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return EvaluationInfoList;
	}
	
}

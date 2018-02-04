package blog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import blog.utils.DBUtils;
import blog.utils.DateUtils;

public class VisitorDB {

	
	private static final Connection conn = null;
	/*
	 * 浏览?�信?��
	 */
	public  static void visit(HttpServletRequest request){
			
		String remoteAddr = request.getRemoteAddr();
		String localAddr = request.getLocalAddr();			
		String remoteHost = request.getRemoteHost();
		String time =DateUtils.getFormatDate(new Date());	 
	System.out.println("VisitorDB -> visit remote IP : " + remoteHost);
		String sql ="insert into t_visitor values(visit_id.nextval,?,?,?,?)";
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, remoteAddr);
			ps.setString(2, time);
			ps.setString(3, localAddr);
			ps.setString(4, remoteHost);
			ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {		
			e.printStackTrace();
		}		 	 	
	}
	
	/**
	 * ?��?��浏览??
	 * @return
	 */
	public static int totalVisit(){
		int result = 0;
		try {
		Connection conn = DBUtil.getConnection();		
		String sql ="select count(blog_id) from t_visitor";
		
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			DBUtils.Close(ps,rs);
		} catch (SQLException e) {		
			e.printStackTrace();
		}		 	 
		return result;		
	}
	
	
	/**
	 * 第几个浏览�??
	 * @return
	 */
	public static int totalMember(){
			
		int result = 0;
		String sql ="SELECT COUNT(DISTINCT(ip)) FROM t_visitor";
		try {
			Connection conn = DBUtil.getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			DBUtils.Close(ps,rs);
		} catch (SQLException e) {		
			e.printStackTrace();
		}		 	 
		return result;
	}
	
}

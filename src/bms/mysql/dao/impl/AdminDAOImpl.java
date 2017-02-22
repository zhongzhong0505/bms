package bms.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bms.mysql.dao.IAdminDAO;
import bms.mysql.dbc.DBConnection;
import bms.mysql.vo.AdminVO;

/**
 * IAdminDAO接口的实现类
 * @author zhong
 *
 */
public class AdminDAOImpl implements IAdminDAO{
	private PreparedStatement pstmt = null ;

	@Override
	public AdminVO findByAdminName(String adminName) throws Exception {
		
		Connection conn =  new DBConnection().getConnection();;
		
		AdminVO adminVO = null;
		ResultSet rs = null;
		
		String sql = "select * from admin where adminName=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,adminName);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			adminVO = new AdminVO();
			adminVO.setAdminName(adminName);
			adminVO.setPassword(rs.getString(2));
			adminVO.setRealName(rs.getString(3));
			adminVO.setEmail(rs.getString(4));
			adminVO.setPhoto(rs.getString(5));
		}
		
		this.pstmt.close();
		conn.close();
		return adminVO;
	}
	@Override
	public boolean updatePassword(String password) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		boolean flag = false;
		String sql = "update admin set password=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,password);
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		pstmt.close();
		conn.close();
		return flag;
	}
	@Override
	public boolean update(AdminVO vo) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		boolean flag = false;
		String sql = "update admin set realName=?,email=?,photo=? where adminName=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,vo.getRealName());
		pstmt.setString(2,vo.getEmail());
		pstmt.setString(3,vo.getPhoto());
		pstmt.setString(4,vo.getAdminName());
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		pstmt.close();
		conn.close();
		return flag;
	}

}

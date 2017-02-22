package bms.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bms.mysql.dao.IUserDAO;
import bms.mysql.dbc.DBConnection;
import bms.mysql.vo.UserVO;

/**
 * IUserDAO接口的实现类
 * @author yang
 *
 */
public class UserDAOImpl implements IUserDAO{

	private PreparedStatement pstmt = null ;
	
	/**
	 * 增加用户
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean addUser(UserVO user) throws Exception {
		
		Connection conn =  new DBConnection().getConnection();
		
		boolean flag = false;
		String sql = "insert into user(user_card,user_name,user_sex,user_email,user_phone,user_date,user_status,user_faculty) values(?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUser_card());
		pstmt.setString(2, user.getUser_name());
		pstmt.setString(3, user.getUser_sex());
		pstmt.setString(4, user.getUser_email());
		pstmt.setString(5, user.getUser_phone());
		pstmt.setString(6, user.getUser_date());
		pstmt.setString(7, user.getUser_status());
		pstmt.setString(8, user.getUser_faculty());
		
		int result = 0;
		result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		
		conn.close();
		return flag;
	}

	/**
	 * 删除用户
	 * @param user_id
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean deleteUser(int user_id) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		boolean flag = false;
		String sql = "delete from user where user_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user_id);
		
		int  result = 0;
		result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		
		this.pstmt.close();
		conn.close();
		return flag;
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean updateUser(UserVO user) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		boolean flag = false;
		String sql = null;
		if(user.getUser_id() > 0){
			sql = "update user set user_card=?,user_name=?,user_sex=?,user_email=?,user_phone=?,user_date=?,user_status=?,user_faculty=? where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_card());
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_sex());
			pstmt.setString(4, user.getUser_email());
			pstmt.setString(5, user.getUser_phone());
			pstmt.setString(6, user.getUser_date());
			pstmt.setString(7, user.getUser_status());
			pstmt.setString(8, user.getUser_faculty());
			pstmt.setInt(9, user.getUser_id());
		}else{
			sql = "update user set user_name=?,user_sex=?,user_email=?,user_phone=?,user_date=?,user_status=?,user_faculty=? where user_card = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getUser_sex());
			pstmt.setString(3, user.getUser_email());
			pstmt.setString(4, user.getUser_phone());
			pstmt.setString(5, user.getUser_date());
			pstmt.setString(6, user.getUser_status());
			pstmt.setString(7, user.getUser_faculty());
			pstmt.setString(8, user.getUser_card());
		}
		

		
		int result = 0;
		result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		
		this.pstmt.close();
		conn.close();
		return flag;
	}

	/**
	 * 得到总的记录数
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getCount() throws Exception {
		Connection conn = new DBConnection().getConnection();
		String sql = "select COUNT(*) from user ";
		pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		int result = 0;
		if(rs.next()){
			result = rs.getInt(1);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return result;
	}

	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @return List<UserVO>
	 * @throws Exception
	 */
	@Override
	public List<UserVO> findPageUser(int start, int limit) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		List <UserVO> list = new ArrayList<UserVO>(); 
		String sql = "select * from user limit ?,?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,start);
		pstmt.setInt(2,limit);
		ResultSet rs = pstmt.executeQuery();
		UserVO vo = null;
		while(rs.next()){
			vo = new UserVO();
			vo.setUser_id(rs.getInt(1));
			vo.setUser_card(rs.getString(2));
			vo.setUser_name(rs.getString(3));
			vo.setUser_sex(rs.getString(4));
			vo.setUser_email(rs.getString(5));
			vo.setUser_phone(rs.getString(6));
			vo.setUser_date(rs.getString(7));
			vo.setUser_status(rs.getString(8));
			vo.setUser_faculty(rs.getString(9));
			list.add(vo);
		}
		
		rs.close();
		conn.close();
		return list;
	}

	/**
	 * 根据关键字查询
	 * @param key
	 * @return List<UserVO>
	 * @throws Exception
	 */
	@Override
	public List<UserVO> findUsersByKey(String key) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		List <UserVO> list = new ArrayList<UserVO>();
		String sql = "select * from user where user_card like ? or user_name like ? or user_email like ? or user_phone like ?";
		if(key == null){
			sql = "select * from user";
			pstmt = conn.prepareStatement(sql);
		}else{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+key+"%");
			pstmt.setString(2,"%"+key+"%");
			pstmt.setString(3,"%"+key+"%");
			pstmt.setString(4,"%"+key+"%");
		}
		
		ResultSet rs = pstmt.executeQuery();
		UserVO vo = null;
		while(rs.next()){
			vo = new UserVO();
			vo.setUser_id(rs.getInt(1));
			vo.setUser_card(rs.getString(2));
			vo.setUser_name(rs.getString(3));
			vo.setUser_sex(rs.getString(4));
			vo.setUser_email(rs.getString(5));
			vo.setUser_phone(rs.getString(6));
			vo.setUser_date(rs.getString(7));
			vo.setUser_status(rs.getString(8));
			vo.setUser_faculty(rs.getString(9));
			list.add(vo);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public boolean findUserByCard(String user_card) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		String sql = "select * from user where user_card=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_card);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		rs.close();
		this.pstmt.close();
		conn.close();
		return false;
	}



}

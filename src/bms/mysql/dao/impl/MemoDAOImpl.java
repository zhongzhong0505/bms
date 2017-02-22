package bms.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bms.mysql.dao.IMemoDAO;
import bms.mysql.dbc.DBConnection;
import bms.mysql.vo.MemoVO;

/**
 * IMemoDAO接口的实现类
 * @author yang
 *
 */
public class MemoDAOImpl implements IMemoDAO{

	private PreparedStatement pstmt = null ;

	
	/**
	 * 增加备忘信息
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean addMemo(MemoVO memo) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		boolean flag = false;
		String sql = "insert into memo (now_status,memo_date,memo_info,isTip,tip_type,of_user) value(?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memo.getNow_status());
		pstmt.setString(2, memo.getMemo_date());
		pstmt.setString(3, memo.getMemo_info());
		pstmt.setString(4, memo.getIsTip());
		pstmt.setString(5, memo.getTip_type());
		pstmt.setString(6, memo.getOf_user());
		
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
	 * 删除备忘信息
	 * @param memo_id
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean deleteMemo(int memo_id) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		boolean flag = false;
		String sql = "delete from memo where memo_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memo_id);
		
		int result = 0;
		result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		pstmt.close();
		conn.close();
		return flag;
	}
	
	/**
	 * 修改备忘信息
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean updateMemo(MemoVO memo) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		boolean flag = false;
		String sql = "update memo set now_status=?,memo_date=?,memo_info=?,isTip=?,tip_type=?,of_user=? where memo_id=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memo.getNow_status());
		pstmt.setString(2, memo.getMemo_date());
		pstmt.setString(3, memo.getMemo_info());
		pstmt.setString(4, memo.getIsTip());
		pstmt.setString(5, memo.getTip_type());
		pstmt.setString(6, memo.getOf_user());
		pstmt.setInt(7, memo.getMemo_id());
		
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
		Connection conn =  new DBConnection().getConnection();;
		String sql = "select COUNT(*) from memo ";
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
	 * 分页
	 * @param start
	 * @param limit
	 * @return List<MemoVO>
	 * @throws Exception
	 */
	@Override
	public List<MemoVO> findPageMemo(int start, int limit) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		List <MemoVO> list = new ArrayList<MemoVO>(); 
		String sql = "select * from memo limit ?,?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,start);
		pstmt.setInt(2,limit);
		ResultSet rs = pstmt.executeQuery();
		MemoVO vo = null;
		while(rs.next()){
			vo = new MemoVO();
			vo.setMemo_id(rs.getInt(1));
			vo.setNow_status(rs.getString(2));
			vo.setMemo_date(rs.getString(3));
			vo.setMemo_info(rs.getString(4));
			vo.setIsTip(rs.getString(5));
			vo.setTip_type(rs.getString(6));
			vo.setOf_user(rs.getString(7));
			list.add(vo);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return list;
	}

	/**
	 * 根据关键字查询备忘信息
	 * @param key
	 * @return List<MemoVO>
	 * @throws Exception
	 */
	@Override
	public List<MemoVO> findMemosByKey(String key) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		List <MemoVO> list = new ArrayList<MemoVO>();
		String sql = "select * from memo where now_status = ? or memo_info like ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,key);
		pstmt.setString(2,"%"+key+"%");
		ResultSet rs = pstmt.executeQuery();
		MemoVO vo = null;
		while(rs.next()){
			vo = new MemoVO();
			vo.setMemo_id(rs.getInt(1));
			vo.setNow_status(rs.getString(2));
			vo.setMemo_date(rs.getString(3));
			vo.setMemo_info(rs.getString(4));
			vo.setIsTip(rs.getString(5));
			vo.setTip_type(rs.getString(6));
			vo.setOf_user(rs.getString(7));
			list.add(vo);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return list;
	}

}

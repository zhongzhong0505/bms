package bms.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bms.mysql.dao.IBorrowHistoryDAO;
import bms.mysql.dbc.DBConnection;
import bms.mysql.vo.BorrowHistoryVO;
/**
 * 借阅历史实现类
 * @author zhong
 */
public class BorrowHistoryDAOImpl implements IBorrowHistoryDAO {
	private PreparedStatement pstmt = null;
	

	@Override
	public List<BorrowHistoryVO> findHistorysByKey(String key) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		
		String sql = "select * from borrowhistory where user_name like ? or borrow_book_name like ?";
		
		List<BorrowHistoryVO> list = new ArrayList<BorrowHistoryVO>();
		
		ResultSet rs = null;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+key+"%");
		pstmt.setString(2,"%"+key+"%");
		rs = pstmt.executeQuery();
		while(rs.next()){
			BorrowHistoryVO vo = new BorrowHistoryVO();
			vo.setUser_card(rs.getString(1));
			vo.setUser_name(rs.getString(2));
			vo.setBorrow_book_name(rs.getString(3));
			vo.setBorrow_book_date(rs.getString(4));
			vo.setReturn_book_date(rs.getString(5));
			vo.setUser_phone(rs.getString(6));
			list.add(vo);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public int getCount() throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from borrowhistory";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		
		pstmt.close();
		conn.close();
		return count;
	}

	@Override
	public List<BorrowHistoryVO> findPageHistory(int start, int limit)
			throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		
		List<BorrowHistoryVO> list = new ArrayList<BorrowHistoryVO>();
		String sql = "select * from borrowhistory limit ?,?";
		
		ResultSet rs = null;
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,start);
		pstmt.setInt(2,limit);
		rs = pstmt.executeQuery();
		while(rs.next()){
			BorrowHistoryVO vo = new BorrowHistoryVO();
			vo.setUser_card(rs.getString(1));
			vo.setUser_name(rs.getString(2));
			vo.setBorrow_book_name(rs.getString(3));
			vo.setBorrow_book_date(rs.getString(4));
			vo.setReturn_book_date(rs.getString(5));
			vo.setUser_phone(rs.getString(6));
			list.add(vo);
		}
		pstmt.close();
		conn.close();
		return list;
	}
	@Override
	public boolean addHistory(BorrowHistoryVO vo) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		
		boolean flag = false;
		String sql = "insert into borrowhistory values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,vo.getUser_card());
		pstmt.setString(2,vo.getUser_name());
		pstmt.setString(3,vo.getBorrow_book_name());
		pstmt.setString(4,vo.getBorrow_book_date());
		pstmt.setString(5,vo.getReturn_book_date());
		pstmt.setString(6,vo.getUser_phone());
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		pstmt.close();
		conn.close();
		return flag;
	}

}

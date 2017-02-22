package bms.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bms.mysql.dao.IBorrowsearchDAO;
import bms.mysql.dbc.DBConnection;
import bms.mysql.vo.BorrowSearchVO;

/**
 * IBorrowsearchDAO接口的实现类
 * @author yang
 *
 */
public class BorrowSearchDAOImpl implements IBorrowsearchDAO {

	private PreparedStatement pstmt = null ;

	/**
	 * 通过关键字查询
	 * @param key
	 * @return List<BorrowsearchVO>
	 * @throws Exception
	 */
	@Override
	public List<BorrowSearchVO> findBooksByKey(String key) throws Exception {
		Connection conn = null;
		List<BorrowSearchVO> list = null;
		ResultSet rs = null;
		try {
			conn = new DBConnection().getConnection();;
			list = new ArrayList<BorrowSearchVO>();
			String sql = "select * from borrowsearch where book_name like ? or book_type like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+key+"%");
			pstmt.setString(2,"%"+key+"%");
			rs = pstmt.executeQuery();
			BorrowSearchVO vo = null;
			while(rs.next()){
				vo = new BorrowSearchVO();
				vo.setBook_id(rs.getInt(1));
				vo.setBook_type(rs.getString(2));
				vo.setBook_name(rs.getString(3));
				vo.setBook_isbn(rs.getString(4));
				vo.setUser_card(rs.getString(5));
				vo.setUser_name(rs.getString(6));
				vo.setUser_sex(rs.getString(7));
				vo.setBorrow_date(rs.getString(8));
				vo.setReturn_date(rs.getString(9));
				vo.setPhone_number(rs.getString(10));
				list.add(vo);
			}
		} catch (Exception e) {}finally{
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		return list;
	}
	/**
	 * 得到总的记录数
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getCount() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = new DBConnection().getConnection();;
			String sql = "select COUNT(*) from borrowsearch ";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			result = 0;
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (Exception e) {}finally{
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		
		
		return result;
	}
	/**
	 * 分页
	 * @param start
	 * @param limit
	 * @return List <BorrowsearchVO>
	 * @throws Exception
	 * 
	 */
	@Override
	public List<BorrowSearchVO> findPageBook(int start, int limit)
			throws Exception {
		Connection conn = null;
		List<BorrowSearchVO> list = null;
		ResultSet rs = null;
		try {
			conn = new DBConnection().getConnection();;
			list = new ArrayList<BorrowSearchVO>(); 
			String sql = "select * from borrowsearch limit ?,?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,start);
			pstmt.setInt(2,limit);
			rs = pstmt.executeQuery();
			BorrowSearchVO vo = null;
			while(rs.next()){
				vo = new BorrowSearchVO();
				vo.setBook_id(rs.getInt(1));
				vo.setBook_type(rs.getString(2));
				vo.setBook_name(rs.getString(3));
				vo.setBook_isbn(rs.getString(4));
				vo.setBook_count(rs.getInt(5));
				vo.setUser_card(rs.getString(6));
				vo.setUser_name(rs.getString(7));
				vo.setUser_sex(rs.getString(8));
				vo.setBorrow_date(rs.getString(9));
				vo.setReturn_date(rs.getString(10));
				vo.setPhone_number(rs.getString(11));
				list.add(vo);
			}
		} catch (Exception e) {}finally{
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return list;
	}
	@Override
	public boolean addRecord(BorrowSearchVO vo) throws Exception {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = new DBConnection().getConnection();;
			flag = false;
			String sql = "insert into borrowsearch(book_type,book_name," +
					"book_isbn,book_count,user_card,user_name,user_sex," +
					"borrow_date,return_date,phone_number) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getBook_type());
			pstmt.setString(2,vo.getBook_name());
			pstmt.setString(3,vo.getBook_isbn());
			pstmt.setInt(4,vo.getBook_count());
			pstmt.setString(5,vo.getUser_card());
			pstmt.setString(6,vo.getUser_name());
			pstmt.setString(7,vo.getUser_sex());
			pstmt.setString(8,vo.getBorrow_date());
			pstmt.setString(9,vo.getReturn_date());
			pstmt.setString(10,vo.getPhone_number());
			int result = pstmt.executeUpdate();
			if(result>0){
				flag = true;
			}
		} catch (Exception e) {}finally{
			pstmt.close();
			conn.close();
		}
		
		return flag;
	}
	@Override
	public boolean deleteRecord(String isbn) throws Exception {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = new DBConnection().getConnection();;
			flag = false;
			String sql = "delete from borrowsearch where book_isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,isbn);
			int result = pstmt.executeUpdate();
			if(result>0){
				flag = true;
			}
		} catch (Exception e) {}finally{
			pstmt.close();
			conn.close();
		}
	
		return flag;
	}
	@Override
	public BorrowSearchVO findRecordByIsbn(String isbn) throws Exception {
		Connection conn =  null;
		BorrowSearchVO vo = null;
		try{
		conn = new DBConnection().getConnection();;
		String sql = "select * from borrowsearch where book_isbn=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,isbn);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			vo = new BorrowSearchVO();
			vo.setBook_id(rs.getInt(1));
			vo.setBook_type(rs.getString(2));
			vo.setBook_name(rs.getString(3));
			vo.setBook_isbn(rs.getString(4));
			vo.setUser_card(rs.getString(5));
			vo.setUser_name(rs.getString(6));
			vo.setUser_sex(rs.getString(7));
			vo.setBorrow_date(rs.getString(8));
			vo.setReturn_date(rs.getString(9));
			vo.setPhone_number(rs.getString(10));
		}
		}catch(Exception e){}finally{
			pstmt.close();
			conn.close();
		}
		return vo;
	}
	@Override
	public void update(int book_count, String user_card) throws Exception {
		Connection conn = null;
		try{
			conn =  new DBConnection().getConnection();
			String sql = "update borrowsearch set book_count=? where user_card=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,book_count);
			pstmt.setString(2,user_card);
			pstmt.executeUpdate();
		}catch(Exception e){}finally{
			pstmt.close();
			conn.close();
		}
		
	}
	

}

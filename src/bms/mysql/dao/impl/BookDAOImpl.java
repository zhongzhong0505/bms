package bms.mysql.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bms.mysql.dao.IBookDAO;
import bms.mysql.dbc.DBConnection;
import bms.mysql.vo.BookVO;
import bms.mysql.vo.UserVO;

/**
 * IBookDAO接口的实现类
 * @author yang
 *
 */
public class BookDAOImpl implements IBookDAO{
	
	private PreparedStatement pstmt = null ;
	
	/**
	 * 增加图书信息
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean addBook(BookVO book) throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		
		
		boolean flag = false;
		String sql = "insert into book(book_type,book_name,book_isbn,book_author,book_press,book_press_date,book_count,book_price,book_location,book_note)  values(?,?,?,?,?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getBook_type());
		pstmt.setString(2, book.getBook_name());
		pstmt.setString(3, book.getBook_isbn());
		pstmt.setString(4, book.getBook_author());
		pstmt.setString(5, book.getBook_press());
		pstmt.setString(6, book.getBook_press_date());
		pstmt.setInt(7, book.getBook_count());
		pstmt.setString(8, book.getBook_price());
		pstmt.setString(9, book.getBook_location());
		pstmt.setString(10, book.getBook_note());
		
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		
		this.pstmt.close();
		conn.close();
		return flag;
	}
	
	/**
	 * 删除图书信息
	 * @param id
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean deleteBook(int book_id) throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		boolean flag = false;
		String sql = "delete from book where book_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, book_id);
		
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		
		this.pstmt.close();
		conn.close();
		return flag;
	}
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean updateBook(BookVO book) throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		boolean flag = false;
		String sql = "update book set book_type=?,book_name=?,book_isbn=?,book_author=?,book_press=?,book_press_date=?,book_count=?,book_price=?,book_location=?,book_note=? where book_id = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,book.getBook_type());
		pstmt.setString(2, book.getBook_name());
		pstmt.setString(3, book.getBook_isbn());
		pstmt.setString(4, book.getBook_author());
		pstmt.setString(5, book.getBook_press());
		pstmt.setString(6, book.getBook_press_date() );
		pstmt.setInt(7, book.getBook_count());
		pstmt.setString(8, book.getBook_price());
		pstmt.setString(9, book.getBook_location());
		pstmt.setString(10, book.getBook_note());
		pstmt.setInt(11, book.getBook_id());
		
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		
		this.pstmt.close();
		conn.close();
		return flag;
	}
	
	/**
	 * 得到总的图书数量
	 * @return int
	 * @throws Exception
	 */
	public int getCount() throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		String sql = "select sum(book_count) from book ";
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
	 * @return List<BookVO>
	 * @throws Exception
	 */
	@Override
	public List <BookVO> findPageBook(int start,int limit) throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		List <BookVO> list = new ArrayList<BookVO>(); 
		String sql = "select * from book limit ?,?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,start);
		pstmt.setInt(2,limit);
		ResultSet rs = pstmt.executeQuery();
		BookVO vo = null;
		while(rs.next()){
			vo = new BookVO();
			vo.setBook_id(rs.getInt(1));
			vo.setBook_type(rs.getString(2));
			vo.setBook_name(rs.getString(3));
			vo.setBook_isbn(rs.getString(4));
			vo.setBook_author(rs.getString(5));
			vo.setBook_press(rs.getString(6));
			vo.setBook_press_date(rs.getString(7));
			vo.setBook_count(rs.getInt(8));
			vo.setBook_price(rs.getString(9));
			vo.setBook_location(rs.getString(10));
			vo.setBook_note(rs.getString(11));
			list.add(vo);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return list;
	}
	
	/**
	 * 根据关键字查询图书信息
	 * @param key
	 * @return List<BookVO>
	 * @throws Exception
	 */
	@Override
	public List<BookVO> findBooksByKey(String key) throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		List <BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book where book_name like ? or book_type like ? or book_press like ?";
		if(key == null){
			sql = "select * from book";
			pstmt = conn.prepareStatement(sql);
		}else{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+key+"%");
			pstmt.setString(2,"%"+key+"%");
			pstmt.setString(3,"%"+key+"%");
		}
		
		ResultSet rs = pstmt.executeQuery();
		BookVO vo = null;
		while(rs.next()){
			vo = new BookVO();
			vo.setBook_id(rs.getInt(1));
			vo.setBook_type(rs.getString(2));
			vo.setBook_name(rs.getString(3));
			vo.setBook_isbn(rs.getString(4));
			vo.setBook_author(rs.getString(5));
			vo.setBook_press(rs.getString(6));
			vo.setBook_press_date(rs.getString(7));
			vo.setBook_count(rs.getInt(8));
			vo.setBook_price(rs.getString(9));
			vo.setBook_location(rs.getString(10));
			vo.setBook_note(rs.getString(11));
			list.add(vo);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return list;
	}

	/**
	 * 根据类型名称查数量
	 * @param typeName
	 * @return int
	 */
	@Override
	public int getAllCount(String type_name) throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		String sql = "select sum(book_count) from book where book_type =?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, type_name);
		
		int result = 0;
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return result;
	}
	
	/**
	 * 查选借出图书的总数
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getBorrowCount() throws Exception{
		Connection conn =  new DBConnection().getConnection();;
		String sql = "select sum(book_count) from borrowsearch";
		pstmt = conn.prepareStatement(sql);
		
		int result = 0;
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			result = rs.getInt(1);
		}
		
		rs.close();
		this.pstmt.close();
		conn.close();
		return result;
	}

	@Override
	public List<String> getAllBookType() throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		
		String sql = "SELECT DISTINCT book_type  FROM book";
		List<String> types = new ArrayList<String>();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs= null;
		rs = pstmt.executeQuery();
		while(rs.next()){
			types.add(rs.getString(1));
		}
		pstmt.close();
		conn.close();
		return types;
	}

	@Override
	public boolean updateBookStatus(String book_isbn,String status) throws Exception {
		Connection conn =  new DBConnection().getConnection();;
		
		boolean flag = false;
		String sql = "update book set isBorrow = ? where book_isbn=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,status);
		pstmt.setString(2,book_isbn);
		int result = pstmt.executeUpdate();
		if(result>0){
			flag = true;
		}
		pstmt.close();
		conn.close();
		return flag;
	}

	@Override
	public String getBookStatus(String book_isbn) throws Exception {
		
		Connection conn =  new DBConnection().getConnection();;
		String status = null;
		String sql = "select isBorrow from book where book_isbn=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,book_isbn);
		ResultSet rs = null;
		
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			status = rs.getString(1);
		}
		pstmt.close();
		conn.close();
		return status;
	}

	@Override
	public UserVO valid(String card) throws Exception {
		
		UserVO vo = null;
		Connection conn =  new DBConnection().getConnection();
		String sql = "select * from user where user_card=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,card);
		ResultSet rs = null;
		
		rs = pstmt.executeQuery();
		if(rs.next()){
			vo = new UserVO(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
		}
		return vo;
	}

	@Override
	public int getBookCountByName(String book_name) throws Exception {
		int count = 0;
		
		Connection conn =  new DBConnection().getConnection();
		String sql = "select book_count from book where book_name=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,book_name);
		ResultSet rs = null;
		
		rs = pstmt.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}

	@Override
	public void updateBook_count(int count,String book_name) throws Exception {
		Connection conn =  new DBConnection().getConnection();
		String sql = "update book set book_count=? where book_name=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,count);
		pstmt.setString(2,book_name);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

}

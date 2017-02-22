package bms.mysql.dao;

import java.util.List;

import bms.mysql.vo.BookVO;
import bms.mysql.vo.UserVO;

/**
 * 图书接口
 * @author zhong
 *
 */
public interface IBookDAO {
	
	/**
	 * 增加图书信息
	 * @param book
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean addBook(BookVO book) throws Exception;
	/**
	 * 删除图书信息
	 * @param id
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean deleteBook(int book_id) throws Exception;
	/**
	 * 修改图书信息
	 * @param book
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean updateBook(BookVO book) throws Exception;
	/**
	 * 修改图书状态
	 * @param book_isbn
	 * @param status
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean updateBookStatus(String book_isbn,String status) throws Exception;
	
	/**
	 * 得到书籍的状态
	 * @param book_isbn
	 * @return String
	 * @throws Exception 抛出所有异常
	 */
	String getBookStatus(String book_isbn) throws Exception;
	/**
	 * 得到总的记录数
	 * @return int
	 * @throws Exception 抛出所有异常
	 */
	int getCount() throws Exception;
	/**
	 * 分页
	 * @param start
	 * @param limit
	 * @return List<BookVO>
	 * @throws Exception 抛出所有异常
	 */
	List<BookVO> findPageBook(int start,int limit) throws Exception;
	
	/**
	 * 根据关键字查询图书信息
	 * @param key
	 * @return List<BookVO>
	 * @throws Exception 抛出所有异常
	 */
	List<BookVO> findBooksByKey(String key) throws Exception;
	/**
	 * 根据类型名称查数量
	 * @param typeName
	 * @return int
	 */
	int getAllCount(String type_name) throws Exception;
	
	
	/**
	 * 查选借出图书的总数
	 * @return int
	 * @throws Exception 抛出所有异常
	 */
	int getBorrowCount() throws Exception;
	
	/**
	 * 得到所有的图书种类
	 * @return List<String>
	 * @throws Exception 抛出所有异常
	 */
	List<String> getAllBookType() throws Exception;
	/**
	 * 验证用户名是否存在
	 * @param card 卡号
	 * @return
	 * @throws Exception  抛出所有异常
	 */
	UserVO valid(String card) throws Exception;
	/**
	 * 根据图书名称得到数量
	 * @param book_name 
	 * @return
	 * @throws Exception
	 */
	int getBookCountByName(String book_name) throws Exception;
	/**
	 * 跟新图书数量
	 * @param count
	 * @throws Exception
	 */
	void updateBook_count(int count,String book_name) throws Exception;

}

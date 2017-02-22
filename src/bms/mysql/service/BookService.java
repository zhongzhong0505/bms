package bms.mysql.service;

import java.io.OutputStream;
import java.util.List;

import bms.mysql.vo.BookVO;
import bms.mysql.vo.BorrowSearchVO;
import bms.mysql.vo.ChartVO;
import bms.mysql.vo.DataVO;
import bms.mysql.vo.UserVO;

public interface BookService {
	
	
	/**
	 * 显示一页数据
	 * @param limit 
	 * @param start 
	 * @return	List<BookVO> 查询出来的数据集合
	 * @throws Exception 抛出所有异常
	 */
	public List<BookVO> listOnePage(int start, int limit) throws Exception;
	
	/**
	 * 查询图书总数
	 * @return int 图书总数
	 * @throws Exception  抛出所有异常
	 */
	public int getCount() throws Exception;
	
	/**
	 * 查询图书的类型和对应的数量
	 * @return  List<ChartVO>  查询得到的结果集
	 */
	public List<ChartVO> getBookTypesAndName()  throws Exception;
	
	/**
	 * 图书状态
	 * @return List<ChartVO>  查询得到的结果集
	 * @throws Exception  抛出所有异常
	 */
	public List<ChartVO> getBookStatus() throws Exception;
	
	
	/**
	 * 根据关键字查询
	 * @param keyword 查询的关键字
	 * @return List<BookVO> 查询得到的结果集
	 * @throws Exception 抛出所有异常
	 */
	public List<BookVO> findByKeyword(String keyword) throws Exception;
	
	/**
	 * 增加图书
	 * @param bookVO 
	 * @throws Exception 抛出所有异常
	 */
	public void addBook(BookVO bookVO) throws Exception;
	
	/**
	 * 
	 * @param ids
	 * @throws Exception 抛出所有异常
	 */
	public void deleteBook(String ids) throws Exception;
	
	/**
	 * 更新图书信息的方法
	 * @param data 
	 * @throws Exception 抛出所有异常
	 */
	public void updateBook(String data) throws Exception;
	
	/**
	 * 得到图书统计信息的方法
	 * @return List<DataVO> 数据集合
	 * @throws Exception 抛出所有异常
	 */
	public List<DataVO> findDataOfBook() throws Exception;
	
	/**
	 * 导出数据到excel的方法
	 * @param out 输出流
	 * @throws Exception 抛出所有异常
	 */
	public void export(OutputStream out) throws Exception;
	
	/**
	 * 借阅操作
	 * @param bookVO
	 * @return 
	 * @throws Exception  抛出所有异常
	 */
	public boolean borrow(BorrowSearchVO bookVO) throws Exception;
	
	/**
	 * 验证卡号是否存在
	 * @param card 卡号
	 * @return BorrowSearchVO 
	 * @throws Exception  抛出所有异常
	 */
	public UserVO valid(String card) throws Exception;
	
	/**
	 * 还书的方法
	 * @param borrowVO
	 * @throws Exception  抛出所有异常
	 */
	public void back(BorrowSearchVO borrowVO) throws Exception;

}

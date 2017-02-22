package bms.mysql.dao;

import java.util.List;

import bms.mysql.vo.BorrowSearchVO;

public interface IBorrowsearchDAO {
	/**
	 * 增加记录
	 * @param vo
	 * @return boolean
	 * @throws Exception
	 */
	boolean addRecord(BorrowSearchVO vo) throws Exception;
	/**
	 * 根据图书的isbn删除记录
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	boolean deleteRecord(String isbn) throws Exception;
	
	/**
	 * 通过关键字查询
	 * @param key
	 * @return List<BorrowsearchVO>
	 * @throws Exception
	 */
	List<BorrowSearchVO> findBooksByKey(String key) throws Exception;
	/**
	 * 得到总的记录数
	 * @return int
	 * @throws Exception
	 */
	int getCount() throws Exception;
	/**
	 * 分页
	 * @param start
	 * @param limit
	 * @return List <BorrowsearchVO>
	 * @throws Exception
	 * 
	 */
	List <BorrowSearchVO> findPageBook(int start,int limit) throws Exception;
	
	/**
	 * 根据图书的isbn查询记录
	 * @param isbn
	 * @return BorrowsearchVO
	 * @throws Exception
	 */
	BorrowSearchVO findRecordByIsbn(String isbn) throws Exception;
	/**
	 * 更新图书数量
	 * @param book_count 图书数量
	 * @param user_card 读者卡号
	 * @throws Exception 抛出所有异常
	 */
	void update(int book_count, String user_card) throws Exception;
	
}

package bms.mysql.dao;

import java.util.List;

import bms.mysql.vo.BorrowHistoryVO;

public interface IBorrowHistoryDAO {
	/**
	 * 增加借阅历史记录
	 * @param vo
	 * @return boolean
	 * @throws Exception
	 */
	boolean addHistory(BorrowHistoryVO vo) throws Exception;
	/**
	 * 通过关键字查询
	 * @param key
	 * @return List<BorrowhistoryVO>
	 * @throws Exception
	 */
	List<BorrowHistoryVO> findHistorysByKey(String key) throws Exception;
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
	 * @return List <BorrowhistoryVO>
	 * @throws Exception
	 * 
	 */
	List <BorrowHistoryVO> findPageHistory(int start,int limit) throws Exception;
}

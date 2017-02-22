package bms.mysql.service;

import java.util.List;

import bms.mysql.vo.BorrowHistoryVO;

public interface BorrowHistoryService {
	
	/**
	 * 分页查询
	 * @param limit 
	 * @param start 
	 * @return
	 * @throws Exception 抛出所有异常
	 */
	public List<BorrowHistoryVO> listOnePage(int start, int limit) throws Exception;
	/**
	 * 总的记录数
	 * @return
	 * @throws Exception  抛出所有异常
	 */
	public int getCount() throws Exception;
	/**
	 * 根据关键字查询
	 * @param keyword
	 * @return
	 * @throws Exception 
	 */
	public List<BorrowHistoryVO> findByKyword(String keyword) throws Exception;

}

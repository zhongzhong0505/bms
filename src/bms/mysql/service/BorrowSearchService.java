package bms.mysql.service;

import java.util.List;

import bms.mysql.vo.BorrowSearchVO;

public interface BorrowSearchService {
	/**
	 * 显示一页数据
	 * @param limit 
	 * @param start 
	 * @return	List<BorrowSearchVO> 查询出来的数据集合
	 * @throws Exception 抛出所有异常
	 */
	public List<BorrowSearchVO> listOnePage(int start, int limit) throws Exception;
	/**
	 * 查询记录总数
	 * @return int 图书总数
	 * @throws Exception  抛出所有异常
	 */
	public int getCount() throws Exception;
	
	/**
	 * 根据关键字查询
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<BorrowSearchVO> findByKeyWord(String keyword) throws Exception;

}

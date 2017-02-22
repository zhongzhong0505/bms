package bms.mysql.service.impl;

import java.util.List;

import bms.mysql.dao.IBorrowHistoryDAO;
import bms.mysql.dao.impl.BorrowHistoryDAOImpl;
import bms.mysql.service.BorrowHistoryService;
import bms.mysql.vo.BorrowHistoryVO;

public class BorrowHistoryServiceImpl implements BorrowHistoryService {
	private IBorrowHistoryDAO historyDAO = new BorrowHistoryDAOImpl();
	@Override
	public List<BorrowHistoryVO> listOnePage(int start,int limit) throws Exception {
		
		return historyDAO.findPageHistory(start, limit);
	}
	@Override
	public int getCount() throws Exception {
		
		return historyDAO.getCount();
	}
	@Override
	public List<BorrowHistoryVO> findByKyword(String keyword) throws Exception {
		
		return historyDAO.findHistorysByKey(new String(keyword.getBytes("iso-8859-1"),"utf-8"));
	}

}

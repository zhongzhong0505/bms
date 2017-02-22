package bms.mysql.service.impl;

import java.util.List;

import bms.mysql.dao.IBorrowsearchDAO;
import bms.mysql.dao.impl.BorrowSearchDAOImpl;
import bms.mysql.service.BorrowSearchService;
import bms.mysql.vo.BorrowSearchVO;

public class BorrowSearchServiceImpl implements BorrowSearchService {
	private IBorrowsearchDAO borrowsearchDAO = new BorrowSearchDAOImpl();
	@Override
	public List<BorrowSearchVO> listOnePage(int start, int limit)
			throws Exception {
		
		
		return borrowsearchDAO.findPageBook(start, limit);
	}

	@Override
	public int getCount() throws Exception {
		
		return borrowsearchDAO.getCount();
	}

	@Override
	public List<BorrowSearchVO> findByKeyWord(String keyword) throws Exception {
		
		return borrowsearchDAO.findBooksByKey(new String(keyword.getBytes("iso-8859-1"),"utf-8"));
	}

}

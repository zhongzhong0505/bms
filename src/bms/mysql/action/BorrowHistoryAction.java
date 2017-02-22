package bms.mysql.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bms.mysql.service.BorrowHistoryService;
import bms.mysql.service.impl.BorrowHistoryServiceImpl;
import bms.mysql.vo.BorrowHistoryVO;

public class BorrowHistoryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5481374583754107001L;
	private int start;
	private int limit;
	private String keyword;
	private Map<String,Object> result;
	//private List<BorrowHistoryVO> historys ;
	private BorrowHistoryService bhService = new BorrowHistoryServiceImpl();
	
	@Override
	public String execute() throws Exception {
		result = new HashMap<String, Object>();
		int count = 0;
		count = bhService.getCount();
		List<BorrowHistoryVO> historys = bhService.listOnePage(start,limit);
		result.put("totalCount",count);
		result.put("historys", historys);
		return SUCCESS;
	}
	
	public String search() throws Exception{
		result = new HashMap<String, Object>();
		List<BorrowHistoryVO> historys = bhService.findByKyword(keyword);
		result.put("totalCount",historys.size());
		result.put("historys", historys);
		return SUCCESS;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	
	
	
}

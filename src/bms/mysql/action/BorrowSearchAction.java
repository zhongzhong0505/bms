package bms.mysql.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bms.mysql.service.BorrowSearchService;
import bms.mysql.service.impl.BorrowSearchServiceImpl;
import bms.mysql.vo.BorrowSearchVO;

public class BorrowSearchAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1839882754780746729L;
	private int start;
	private int limit;
	private String keyword;
	private Map<String,Object> result;
	//private List<BorrowSearchVO> borrows ;
	private BorrowSearchService bsService = new BorrowSearchServiceImpl();
	
	
	/**
	 * 加载数据
	 */
	public String execute() throws Exception{
		result = new HashMap<String, Object>();
		int count = 0 ;
		count = bsService.getCount();
		List<BorrowSearchVO> borrows = bsService.listOnePage(start,limit);
		result.put("totalCount",count);
		result.put("borrows", borrows);
		
		return SUCCESS;
	}
	
	/**
	 * 根据关键字查询
	 * @return
	 * @throws Exception 
	 */
	public String search() throws Exception{
		List<BorrowSearchVO> borrows = bsService.findByKeyWord(keyword);
		result = new HashMap<String, Object>();
		result.put("totalCount", borrows.size());
		result.put("borrows", borrows);
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

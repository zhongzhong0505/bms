package bms.mysql.action;

import java.util.List;

import bms.mysql.service.BookService;
import bms.mysql.service.impl.BookServiceImpl;
import bms.mysql.vo.ChartVO;

public class BookStatusAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8958007029059628434L;
	private BookService bookService = new BookServiceImpl();
	private List<ChartVO> result ;
	
	
	public String execute() throws Exception{
		List<ChartVO> charts = bookService.getBookStatus();
		this.setResult(charts);
		return SUCCESS;
	}
	public List<ChartVO> getResult() {
		return result;
	}
	public void setResult(List<ChartVO> result) {
		this.result = result;
	}
	
	
}

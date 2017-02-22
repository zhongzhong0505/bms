package bms.mysql.action;

import java.util.List;

import bms.mysql.service.BookService;
import bms.mysql.service.impl.BookServiceImpl;
import bms.mysql.vo.ChartVO;

/**
 * @author Administrator
 *
 */
public class BookChartsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5058110314446454178L;
	private BookService bookService = new BookServiceImpl();
	private List<ChartVO> result ;
	public String execute() throws Exception{
		List<ChartVO> charts = bookService.getBookTypesAndName();
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

package bms.mysql.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bms.mysql.service.BookService;
import bms.mysql.service.impl.BookServiceImpl;
import bms.mysql.vo.BookVO;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 图书信息
 * @author zhong
 *
 */
public class BookInfoAction extends BaseAction implements ModelDriven<BookVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4710234604291732460L;
	
	//模型驱动
	private BookVO bookVO = new BookVO();
	
	//分页的参数
	private int start;
	private int limit;
	//查询的参数
	private String keyword;
	//删除的参数
	private String ids;
	//修改的参数
	private String data;
	//结果集合
	private Map<String,Object> result;
	//导出数据的文件名
	private String filename;
	//导出数据的输入流
	private InputStream input;
	//private List<BookVO> books ;
	private BookService bookService = new BookServiceImpl();
	
	public String execute() throws Exception{
		int count = 0;
		result = new HashMap<String, Object>();
		count = bookService.getCount();
		List<BookVO> books = bookService.listOnePage(start,limit);
		result.put("totalCount",count);
		result.put("books",books);
		return SUCCESS;
	}
	
	
	/**
	 * 查询
	 * @return
	 * @throws Exception 
	 */
	public String search() throws Exception{
		result = new HashMap<String, Object>();
		List<BookVO> books = bookService.findByKeyword(keyword);
		result.put("totalCount",books.size());
		result.put("books",books);
		
		return SUCCESS;
	}
	
	/**
	 * 增加图书
	 * @return
	 * @throws Exception 
	 */
	public String add() throws Exception{
		bookService.addBook(bookVO);
		result = new HashMap<String, Object>();
		result.put("success","true");
		return SUCCESS;
	}
	
	/**
	 * 删除图书
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		
		bookService.deleteBook(ids);
		result = new HashMap<String, Object>();
		result.put("success","true");
		return SUCCESS;
	}
	//更新操作
	public String update() throws Exception{
		bookService.updateBook(data);
		result = new HashMap<String, Object>();
		result.put("success","true");
		return SUCCESS;
	}
	
	public String export() throws Exception{
		
		filename = "图书数据.xls";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bookService.export(out);
		input = new ByteArrayInputStream(out.toByteArray());
		return "export";
	}
	
	public Map<String, Object> getResult() {
		return result;
	}


	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
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


	public String getData() {
		return data;
	}


	public String getFilename() throws Exception {
		return new String(filename.getBytes("utf-8"),"iso-8859-1");
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public void setData(String data) {
		this.data = data;
	}


	public InputStream getInput() {
		return input;
	}


	public void setInput(InputStream input) {
		this.input = input;
	}


	@Override
	public BookVO getModel() {
		return bookVO;
	}
}

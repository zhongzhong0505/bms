package bms.mysql.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import bms.mysql.dao.IBookDAO;
import bms.mysql.dao.IBorrowHistoryDAO;
import bms.mysql.dao.IBorrowsearchDAO;
import bms.mysql.dao.impl.BookDAOImpl;
import bms.mysql.dao.impl.BorrowHistoryDAOImpl;
import bms.mysql.dao.impl.BorrowSearchDAOImpl;
import bms.mysql.service.BookService;
import bms.mysql.utils.DateUtil;
import bms.mysql.utils.ExcelUtil;
import bms.mysql.vo.BookVO;
import bms.mysql.vo.BorrowHistoryVO;
import bms.mysql.vo.BorrowSearchVO;
import bms.mysql.vo.ChartVO;
import bms.mysql.vo.DataVO;
import bms.mysql.vo.UserVO;

public class BookServiceImpl implements BookService {
	
	private IBookDAO bookDAO = new BookDAOImpl();
	private IBorrowsearchDAO borrowsearchDAO = new BorrowSearchDAOImpl();
	private IBorrowHistoryDAO borrowHistoryDAO = new BorrowHistoryDAOImpl();
	@Override
	public List<BookVO> listOnePage(int start,int limit) throws Exception {
		List<BookVO> books = null;
		books = bookDAO.findPageBook(start, limit);
		return books;
	}

	@Override
	public int getCount() throws Exception {
		
		return bookDAO.getCount();
	}

	@Override
	public List<ChartVO> getBookTypesAndName() throws Exception {
		
		List<ChartVO> charts = new ArrayList<ChartVO>();
		List<String> types = bookDAO.getAllBookType();
		ChartVO vo = null;
		for(String type : types){
			
			vo = new ChartVO(type, bookDAO.getAllCount(type));
			charts.add(vo);
		}
		return charts;
	}

	@Override
	public List<ChartVO> getBookStatus() throws Exception {
		List<ChartVO> result = new ArrayList<ChartVO>();
		int total = bookDAO.getCount();//得到图书总数
		int borrow = bookDAO.getBorrowCount();//得到借出的图书数量
		ChartVO chartVO1 = new ChartVO("在库",total-borrow);
		ChartVO chartVO2 = new ChartVO("借出",borrow);
		
		result.add(chartVO1);
		result.add(chartVO2);
		
		return result;
	}

	@Override
	public List<BookVO> findByKeyword(String keyword) throws Exception {
		return bookDAO.findBooksByKey(new String(keyword.getBytes("iso-8859-1"),"utf-8"));
	}

	@Override
	public void addBook(BookVO bookVO) throws Exception {
		//转换日期格式
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf1.parse(bookVO.getBook_press_date());
		bookVO.setBook_press_date(sdf2.format(date));
		
		bookDAO.addBook(bookVO);
	}

	@Override
	public void deleteBook(String ids) throws Exception {
		
		if(!ids.contains(",")){//只有一个ID
			bookDAO.deleteBook(Integer.valueOf(ids));
		}else{//处理多个ID
			String[] memos = ids.split(",");
			for(String id : memos){
				bookDAO.deleteBook(Integer.valueOf(id));
			}
		}
	}

	@Override
	public void updateBook(String data) throws Exception {
		//将json数据转换为List集合
		JSONArray jsonarray = JSONArray.fromObject(data);  
	    List<?> list = (List<?>)JSONArray.toList(jsonarray, new BookVO(), new JsonConfig());
	    
	    for(Object vo : list){
	    	bookDAO.updateBook((BookVO)vo);
	    }
	}

	@Override
	public List<DataVO> findDataOfBook() throws Exception {
		
		List<DataVO> result = new ArrayList<DataVO>();
		
		//得到所有的图书类型
		List<String> types = bookDAO.getAllBookType();
		
		DataVO vo = null;
		for(String type : types){
			vo = new DataVO(type,String.valueOf(bookDAO.getAllCount(type)));
			result.add(vo);
		}
		
		return result;
	}

	@Override
	public void export(OutputStream out) throws Exception {
		
		List<BookVO> books = bookDAO.findBooksByKey(null);
	
		//表格的列名
		List<String> title = new ArrayList<String>();
		title.add("图书ID");
		title.add("图书类型");
		title.add("图书名称");
		title.add("图书ISBN");
		title.add("图书作者");
		title.add("出版社");
		title.add("出版日期");
		title.add("图书数量");
		title.add("图书价格");
		title.add("图书位置");
		title.add("备注信息");
		
		
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> temp = null;
		for(BookVO book: books){
			temp = new ArrayList<String>();
			temp.add(book.getBook_id()+"");
			temp.add(book.getBook_type());
			temp.add(book.getBook_name());
			temp.add(book.getBook_isbn());
			temp.add(book.getBook_author());
			temp.add(book.getBook_press());
			temp.add(book.getBook_press_date());
			temp.add(book.getBook_count()+"");
			temp.add(book.getBook_price());
			temp.add(book.getBook_location());
			temp.add(book.getBook_note());
			data.add(temp);
		}
		
		ExcelUtil.exportExcel("图书数据", title, data, out);
	}

	@Override
	public boolean borrow(BorrowSearchVO borrowVO) throws Exception {
		
		boolean flag = false;
		int count = 0;
		count = bookDAO.getBookCountByName(borrowVO.getBook_name());
		
		if(count >= borrowVO.getBook_count()){
			//转换日期格式
			borrowVO.setBorrow_date(DateUtil.getTagerDate(borrowVO.getBorrow_date()));
			borrowVO.setReturn_date(DateUtil.getTagerDate(borrowVO.getReturn_date()));
			
			//2.将借阅信息加入到借阅表中
			borrowsearchDAO.addRecord(borrowVO);
			
			//3.更新数量
			bookDAO.updateBook_count(count-borrowVO.getBook_count(),borrowVO.getBook_name());
			
			flag = true;
		}
		
		return flag;
	}

	@Override
	public UserVO valid(String card) throws Exception {
		
		return bookDAO.valid(card);
	}

	@Override
	public void back(BorrowSearchVO borrowVO) throws Exception {
		
		BorrowSearchVO vo = borrowsearchDAO.findRecordByIsbn(borrowVO.getBook_isbn());
		
		
		//修改图书的库存数量
		int count = bookDAO.getBookCountByName(borrowVO.getBook_name());
		bookDAO.updateBook_count(borrowVO.getBook_count()+count,borrowVO.getBook_name());
		
		borrowVO.setBorrow_date(vo.getBorrow_date());
		borrowVO.setPhone_number(vo.getPhone_number());
		
		//将borrowVO转换为BorrowHistoryVO 
		BorrowHistoryVO historyVO = toBorrowHistoryVO(borrowVO);
		
		//2.将借阅信息添加到借阅历史中
		borrowHistoryDAO.addHistory(historyVO);
		
		//3.修改借阅查询表中的数据
		//borrowsearchDAO.update(borrowVO.getBook_count(),borrowVO.getUser_card());
		borrowsearchDAO.deleteRecord(borrowVO.getBook_isbn());
	}

	private BorrowHistoryVO toBorrowHistoryVO(BorrowSearchVO borrowVO) {
		BorrowHistoryVO historyVO = null;
		if(borrowVO != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//当前操作时的日期为归还日期
			String date = format.format(new Date());
			
			historyVO = new BorrowHistoryVO(borrowVO.getUser_card(),borrowVO.getUser_name(),borrowVO.getBook_name(),borrowVO.getBorrow_date(),date,borrowVO.getPhone_number());
		}
		return historyVO;
	}
	

}

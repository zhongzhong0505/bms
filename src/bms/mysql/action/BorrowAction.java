package bms.mysql.action;

import java.util.HashMap;
import java.util.Map;

import bms.mysql.service.BookService;
import bms.mysql.service.impl.BookServiceImpl;
import bms.mysql.vo.BorrowSearchVO;
import bms.mysql.vo.UserVO;

import com.opensymphony.xwork2.ModelDriven;

public class BorrowAction extends BaseAction implements ModelDriven<BorrowSearchVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1274971842549382172L;
	private BorrowSearchVO borrowVO = new BorrowSearchVO();
	
	private BookService bookService = new BookServiceImpl();
	//结果集
	private Map<String,Object> result;
	//借书操作
	public String execute() throws Exception{
		result = new HashMap<String, Object>();
		boolean flag = bookService.borrow(borrowVO);
		if(flag){
			result.put("success","true");
			result.put("msg","操作成功！");
		}else{
			result.put("msg","库存不足！");
			result.put("success","false");
		}
		return SUCCESS;
	}
	//验证卡号
	public String valid() throws Exception{
		result = new HashMap<String, Object>();
		String card = borrowVO.getUser_card();
		UserVO vo = bookService.valid(card);
		if(vo == null){
			result.put("success","false");
		}else{
			result.put("success","true");
			result.put("data",vo);
		}
		return SUCCESS;
	}
	public String back(){
		result = new HashMap<String, Object>();
		try {
			bookService.back(borrowVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("success","true");
		return SUCCESS;
	}
	
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	@Override
	public BorrowSearchVO getModel() {
		return borrowVO;
	}
}

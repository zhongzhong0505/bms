package bms.mysql.vo;

/**
 * 借阅历史
 * @author zhong
 *
 */
public class BorrowHistoryVO {
	private String user_card;
	private String user_name;
	private String borrow_book_name;
	private String borrow_book_date;
	private String return_book_date;
	private String user_phone;
	
	public BorrowHistoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BorrowHistoryVO(String user_card, String user_name,
			String borrow_book_name, String borrow_book_date,
			String return_book_date, String user_phone) {
		super();
		this.user_card = user_card;
		this.user_name = user_name;
		this.borrow_book_name = borrow_book_name;
		this.borrow_book_date = borrow_book_date;
		this.return_book_date = return_book_date;
		this.user_phone = user_phone;
	}
	public String getUser_card() {
		return user_card;
	}
	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getBorrow_book_name() {
		return borrow_book_name;
	}
	public void setBorrow_book_name(String borrow_book_name) {
		this.borrow_book_name = borrow_book_name;
	}
	public String getBorrow_book_date() {
		return borrow_book_date;
	}
	public void setBorrow_book_date(String borrow_book_date) {
		this.borrow_book_date = borrow_book_date;
	}
	public String getReturn_book_date() {
		return return_book_date;
	}
	public void setReturn_book_date(String return_book_date) {
		this.return_book_date = return_book_date;
	}

	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
}

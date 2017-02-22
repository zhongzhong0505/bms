package bms.mysql.vo;

public class BorrowSearchVO {
	

	private int book_id ;
	private String book_type;
	private String book_name;
	private String book_isbn;
	private String user_card;
	private String user_name;
	private String user_sex;
	private String borrow_date;
	private String return_date;
	private String phone_number;
	private int book_count;
	public BorrowSearchVO() {
		super();
	}

	public BorrowSearchVO(String book_type, String book_name,
			String book_isbn, String user_card, String user_name,
			String user_sex,String borrow_date,
			String return_date, String phone_number,int book_count) {
		
		this.book_type = book_type;
		this.book_name = book_name;
		this.book_isbn = book_isbn;
		this.user_card = user_card;
		this.user_name = user_name;
		this.user_sex = user_sex;
		this.borrow_date = borrow_date;
		this.return_date = return_date;
		this.phone_number = phone_number;
		this.book_count = book_count;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_type() {
		return book_type;
	}

	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_isbn() {
		return book_isbn;
	}

	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
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

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}


	public String getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public int getBook_count() {
		return book_count;
	}

	public void setBook_count(int book_count) {
		this.book_count = book_count;
	}
	
		
}

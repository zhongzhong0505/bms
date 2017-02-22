package bms.mysql.vo;


/**
 * 书籍信息
 * @author zhong
 *
 */
public class BookVO {
	private Integer book_id;
	private String book_type;
	private String book_name;
	private String book_isbn;
	private String book_author;
	private String book_press;
	private String book_press_date;
	private Integer book_count;
	private String book_price;
	private String book_location;
	private String book_note;
	
	public BookVO() {
		super();
	}
	
	public BookVO(String book_type, String book_name, String book_isbn,
			String book_author, String book_press,
			String book_press_date, Integer book_count, String book_price,
			String book_location, String book_note) {
		super();
		this.book_type = book_type;
		this.book_name = book_name;
		this.book_isbn = book_isbn;
		this.book_author = book_author;
		this.book_press = book_press;
		this.book_press_date = book_press_date;
		this.book_count = book_count;
		this.book_price = book_price;
		this.book_location = book_location;
		this.book_note = book_note;
	}

	public BookVO(Integer book_id, String book_type, String book_name,
			String book_isbn, String book_author, String isBorrow,
			String book_press, String book_press_date, Integer book_count,
			String book_price, String book_location, String book_note) {
		super();
		this.book_id = book_id;
		this.book_type = book_type;
		this.book_name = book_name;
		this.book_isbn = book_isbn;
		this.book_author = book_author;
		this.book_press = book_press;
		this.book_press_date = book_press_date;
		this.book_count = book_count;
		this.book_price = book_price;
		this.book_location = book_location;
		this.book_note = book_note;
	}

	public Integer getBook_id() {
		return book_id;
	}
	
	public void setBook_id(Integer book_id) {
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
	
	public String getBook_author() {
		return book_author;
	}
	
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	
	public String getBook_press() {
		return book_press;
	}
	
	public void setBook_press(String book_press) {
		this.book_press = book_press;
	}
	
	public String getBook_press_date() {
		return book_press_date;
	}
	
	public void setBook_press_date(String book_press_date) {
		this.book_press_date = book_press_date;
	}
	
	public Integer getBook_count() {
		return book_count;
	}
	
	public void setBook_count(Integer book_count) {
		this.book_count = book_count;
	}
	
	public String getBook_price() {
		return book_price;
	}
	
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	
	public String getBook_location() {
		return book_location;
	}
	
	public void setBook_location(String book_location) {
		this.book_location = book_location;
	}
	
	public String getBook_note() {
		return book_note;
	}
	
	public void setBook_note(String book_note) {
		this.book_note = book_note;
	}
	
}

package bms.mysql.vo;


public class UserVO {

	private int user_id;
	private String user_card;
	private String user_name;
	private String user_sex;
	private String user_email;
	private String user_phone;
	private String user_date;
	private String user_status;
	private String user_faculty;
	
	public UserVO() {
		super();
	}

	public UserVO(String user_card, String user_name,
			String user_sex, String user_email, String user_phone,
			String user_date,String user_status, String user_faculty) {
		super();
		this.user_card = user_card;
		this.user_name = user_name;
		this.user_sex = user_sex;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_date = user_date;
		this.user_status = user_status;
		this.user_faculty = user_faculty;
	}


	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_date() {
		return user_date;
	}

	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getUser_faculty() {
		return user_faculty;
	}

	public void setUser_faculty(String user_faculty) {
		this.user_faculty = user_faculty;
	}
	
}

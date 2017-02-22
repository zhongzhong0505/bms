package bms.mysql.vo;

public class MemoVO {

	private int memo_id;
	private String now_status;
	private String memo_date;
	private String memo_info;
	private String isTip;
	private String tip_type;
	private String of_user;
	
	public MemoVO() {
		super();
	}

	public MemoVO(int memo_id, String now_status, String memo_date,
			String memo_info, String isTip, String tip_type, String of_user) {
		super();
		this.memo_id = memo_id;
		this.now_status = now_status;
		this.memo_date = memo_date;
		this.memo_info = memo_info;
		this.isTip = isTip;
		this.tip_type = tip_type;
		this.of_user = of_user;
	}

	public int getMemo_id() {
		return memo_id;
	}

	public void setMemo_id(int memo_id) {
		this.memo_id = memo_id;
	}

	public String getNow_status() {
		return now_status;
	}

	public void setNow_status(String now_status) {
		this.now_status = now_status;
	}

	public String getMemo_date() {
		return memo_date;
	}

	public void setMemo_date(String memo_date) {
		this.memo_date = memo_date;
	}

	public String getMemo_info() {
		return memo_info;
	}

	public void setMemo_info(String memo_info) {
		this.memo_info = memo_info;
	}

	public String getIsTip() {
		return isTip;
	}

	public void setIsTip(String isTip) {
		this.isTip = isTip;
	}

	public String getTip_type() {
		return tip_type;
	}

	public void setTip_type(String tip_type) {
		this.tip_type = tip_type;
	}

	public String getOf_user() {
		return of_user;
	}

	public void setOf_user(String of_user) {
		this.of_user = of_user;
	}
	
}

package bms.mysql.action;


public class ExitAction extends BaseAction{
	private String success;
	
	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -1346478659113272930L;

	
	/**
	 * 用户退出处理
	 * @return String
	 * @throws Exception 
	 */
	public String exit() throws Exception{
		request.getSession().removeAttribute("admin");
		request.getSession().invalidate();
		this.setSuccess("true");
		return SUCCESS;
	}
}

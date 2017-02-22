package bms.mysql.action;

import java.util.HashMap;
import java.util.Map;

import bms.mysql.service.LoginExitService;
import bms.mysql.service.impl.LoginExitServiceImpl;
import bms.mysql.vo.AdminVO;

import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends BaseAction implements ModelDriven<AdminVO> {

	private static final long serialVersionUID = -94728871613887537L;
	
	private AdminVO adminVO = new AdminVO();
	private LoginExitService leService = new LoginExitServiceImpl();
	private Map<String,Object> result;

	/**
	 * 登录验证
	 * @return String
	 * @throws Exception 
	 */
	public String login() throws Exception{
		if(adminVO.getCode().equals(request.getSession().getAttribute("rand"))){
			AdminVO vo = leService.login(adminVO);
			if(vo!=null){
				request.getSession().setAttribute("admin",vo);
				return SUCCESS;
			}
		}

		return ERROR;
	}
	
	
	public String info(){
		result = new HashMap<String, Object>();
		result.put("success", "true");
		result.put("data",request.getSession().getAttribute("admin"));
		return SUCCESS;
	}
	
	public Map<String, Object> getResult() {
		return result;
	}


	public void setResult(Map<String, Object> result) {
		this.result = result;
	}


	@Override
	public AdminVO getModel() {
		return adminVO;
	}

}

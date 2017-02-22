package bms.mysql.interceptor;
import org.apache.struts2.ServletActionContext;

import bms.mysql.vo.AdminVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**
 * 判断用户是否登录的拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5602947304812544808L;


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		if(url.endsWith("login")){
			return invocation.invoke();
		}
		
		AdminVO admin = (AdminVO) invocation.getInvocationContext().getSession().get("admin");	
		if(admin != null){
			return invocation.invoke();
		}
		return Action.LOGIN;
	}

}

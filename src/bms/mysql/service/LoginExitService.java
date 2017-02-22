package bms.mysql.service;

import bms.mysql.vo.AdminVO;

public interface LoginExitService {
	
	/**
	 * 用户登录验证的方法
	 * @param adminVO
	 * @return	AdminVO
	 * @throws Exception 抛出所有异常
	 */
	public AdminVO login(AdminVO adminVO) throws Exception;

}

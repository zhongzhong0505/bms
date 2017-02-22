package bms.mysql.service.impl;

import bms.mysql.dao.IAdminDAO;
import bms.mysql.dao.impl.AdminDAOImpl;
import bms.mysql.service.LoginExitService;
import bms.mysql.vo.AdminVO;

public class LoginExitServiceImpl implements LoginExitService {
	
	private IAdminDAO adminDAO = new AdminDAOImpl();
	@Override
	public AdminVO login(AdminVO adminVO) throws Exception {
		return adminDAO.findByAdminName(adminVO.getAdminName());
	}
}

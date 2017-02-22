package bms.mysql.dao;

import bms.mysql.vo.AdminVO;

/**
 * 管理员接口
 * @author zhong
 *
 */
public interface IAdminDAO {
	/**
	 * 根据管理员的姓名查找
	 * @param userName
	 * @return AdminVO
	 * @throws Exception
	 */
	AdminVO findByAdminName(String adminName) throws Exception;
	
	/**
	 * 修改密码
	 * @param password
	 * @return boolean
	 * @throws Exception
	 */
	boolean updatePassword(String password) throws Exception;
	/**
	 * 修改信息
	 * @param vo
	 * @return boolean
	 * @throws Exception
	 */
	boolean update(AdminVO vo) throws Exception;
}

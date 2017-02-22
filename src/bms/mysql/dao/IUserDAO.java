package bms.mysql.dao;

import java.util.List;

import bms.mysql.vo.UserVO;

/**
 * 用户管理接口
 * @author zhong
 *
 */
public interface IUserDAO {
	/**
	 * 增加用户
	 * @param user
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean addUser(UserVO user) throws Exception;
	/**
	 * 删除用户
	 * @param user_id
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean deleteUser(int user_id) throws Exception;
	/**
	 * 更新用户信息
	 * @param user
	 * @return boolean
	 * @throws Exception 抛出所有异常
	 */
	boolean updateUser(UserVO user) throws Exception;
	/**
	 * 得到总的记录数
	 * @return int
	 * @throws Exception 抛出所有异常
	 */
	int getCount() throws Exception;
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @return List<UserVO>
	 * @throws Exception 抛出所有异常
	 */
	List<UserVO> findPageUser(int start,int limit) throws Exception;
	
	/**
	 * 根据关键字查询
	 * @param key
	 * @return List<UserVO>
	 * @throws Exception 抛出所有异常
	 */
	List<UserVO> findUsersByKey(String key) throws Exception;
	/**
	 * 根据读者卡号查询
	 * @param user_card
	 * @return
	 * @throws Exception 抛出所有异常
	 */
	boolean findUserByCard(String user_card) throws Exception;

}

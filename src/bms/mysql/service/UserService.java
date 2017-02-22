package bms.mysql.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import bms.mysql.vo.UserVO;

public interface UserService {
	
	/**
	 * 分页查询
	 * @param start 开始记录
	 * @param limit 需要查询的记录数
	 * @return 查询出来的结果集
	 * @throws Exception 抛出所有异常
	 */
	public List<UserVO> listOnePage(int start, int limit) throws Exception;
	
	/**
	 * 查询记录数
	 * @return 查询出来的总的记录数
	 * @throws Exception 抛出所有异常
	 */
	public int getCount() throws Exception;
	
	/**
	 * 根据关键字查询
	 * @param keyword
	 * @return
	 * @throws Exception 抛出所有异常
	 */
	public List<UserVO> findByKeyword(String keyword) throws Exception;
	
	/**
	 * 增加用户
	 * @param userVO
	 * @throws Exception 抛出所有异常
	 */
	public void addUser(UserVO userVO) throws Exception;
	
	/**
	 * 删除用户
	 * @param ids
	 * @throws Exception 抛出所有异常
	 */
	public void deleteUser(String ids) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param data
	 * @throws Exception  抛出所有异常
	 */
	public void updateUser(String data) throws Exception;
	
	/**
	 * 导出表格的方法
	 * @param out 
	 * @throws Exception  抛出所有异常
	 */
	public void export(OutputStream out) throws Exception;
	
	/**
	 * 导入数据的方法
	 * @param importFile
	 * @return 
	 * @throws Exception  抛出所有异常
	 */
	public void impot(File importFile) throws Exception;
	

}

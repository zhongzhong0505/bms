package bms.mysql.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import bms.mysql.dao.IUserDAO;
import bms.mysql.dao.impl.UserDAOImpl;
import bms.mysql.service.UserService;
import bms.mysql.utils.DateUtil;
import bms.mysql.utils.ExcelUtil;
import bms.mysql.vo.UserVO;

public class UserServiceImpl implements UserService {
	private IUserDAO userDAO = new UserDAOImpl();
	@Override
	public List<UserVO> listOnePage(int start, int limit) throws Exception {
		List<UserVO> users = null;
		users = userDAO.findPageUser(start, limit);
		return users;
	}
	@Override
	public int getCount() throws Exception {
		return userDAO.getCount();
	}
	@Override
	public List<UserVO> findByKeyword(String keyword) throws Exception {
		
		return userDAO.findUsersByKey(new String(keyword.getBytes("iso-8859-1"),"utf-8"));
	}
	@Override
	public void addUser(UserVO userVO) throws Exception {
		//转换日期格式
		userVO.setUser_date(DateUtil.getTagerDate(userVO.getUser_date()));
		
		//初始化用户状态
		userVO.setUser_status("正常");
		
		userDAO.addUser(userVO);
	}
	@Override
	public void deleteUser(String ids) throws Exception {
		if(!ids.contains(",")){//只有一个ID
			userDAO.deleteUser(Integer.valueOf(ids));
		}else{//处理多个ID
			String[] memos = ids.split(",");
			for(String id : memos){
				userDAO.deleteUser(Integer.valueOf(id));
			}
		}
	}
	@Override
	public void updateUser(String data) throws Exception {
		//将json数据转换为List集合
		JSONArray jsonarray = JSONArray.fromObject(data);  
	    List<?> list = (List<?>)JSONArray.toList(jsonarray, new UserVO(), new JsonConfig());
	    
	    for(Object vo : list){
	    	userDAO.updateUser((UserVO)vo);
	    }
	}
	@Override
	public void export(OutputStream out) throws Exception {
		//输出流
		//OutputStream out = new FileOutputStream(file);
		
		
		//得到所有数据
		List<UserVO> users = userDAO.findUsersByKey(null);
		//表格的列名
		List<String> title = new ArrayList<String>();
		title.add("读者ID");
		title.add("读者卡号");
		title.add("读者姓名");
		title.add("读者性别");
		title.add("读者邮箱");
		title.add("读者电话");
		title.add("出生日期");
		title.add("卡的状态");
		title.add("所在院系");
		
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> temp = null;
		for(UserVO user : users){
			temp = new ArrayList<String>();
			temp.add(user.getUser_id()+"");
			temp.add(user.getUser_card());
			temp.add(user.getUser_name());
			temp.add(user.getUser_sex());
			temp.add(user.getUser_email());
			temp.add(user.getUser_phone());
			temp.add(user.getUser_date());
			temp.add(user.getUser_status());
			temp.add(user.getUser_faculty());
			data.add(temp);
		}
		
		//使用工具类将数据加入到输出流中
		ExcelUtil.exportExcel("用户数据", title, data, out);
	}
	@Override
	public void impot(File importFile) throws Exception {
		//得到导入的数据
		List<List<String>> data = ExcelUtil.importExcel(new FileInputStream(importFile));
		List<UserVO> list = toUserList(data);//将数据转换为UserVO对象的集合
		//依次添加到数据库
		for(UserVO vo : list){
			//查看数据库中是否有卡号相同的用户
			boolean flag = userDAO.findUserByCard(vo.getUser_card());
			//如果数据库中有卡号相同的用户，则做更新操作
			if(flag)userDAO.updateUser(vo);
			//如果没有则做添加操作
			else userDAO.addUser(vo);	
		}
	}
	/**
	 * 将数据转换为UserVOn对象的List集合
	 * @param data List<List<String>> List<String>数据类型的集合
	 * @return List<UserVO> UserVO类型的集合
	 */
	private List<UserVO> toUserList(List<List<String>> data) {
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;
		for(List<String> var: data){
			//[3, 20114207125, 二二, 男, 8282828284@qq.com, 1995-02-01, 82828282821, 正常, 软件服务外包学院]
			userVO = new UserVO(var.get(1),var.get(2),var.get(3),var.get(4),var.get(5),var.get(6),var.get(7),var.get(8));
			list.add(userVO);
		}
		
		return list;
	}

}

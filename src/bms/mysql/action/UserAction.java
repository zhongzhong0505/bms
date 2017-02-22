package bms.mysql.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bms.mysql.service.UserService;
import bms.mysql.service.impl.UserServiceImpl;
import bms.mysql.vo.UserVO;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 用户信息
 * @author zhong
 *
 */
public class UserAction extends BaseAction implements ModelDriven<UserVO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7893689882864700526L;
	//模型驱动
	private UserVO userVO = new UserVO();
	//分页参数
	private int start;
	private int limit;
	//查询参数
	private String keyword;
	//删除参数
	private String ids;
	//修改的参数
	private String data;
	//结果集
	private Map<String,Object> result;
	//导出数据的输入流
	private InputStream input;
	//导出数据的文件名
	private String filename;
	
	private File importFile;
	private UserService userService = new UserServiceImpl();
	
	
	public String execute() throws Exception{
		result = new HashMap<String, Object>();
		int count = 0;
		count = userService.getCount();
		List<UserVO> users = userService.listOnePage(start, limit);
		result.put("totalCount", count);
		result.put("users", users);
		return SUCCESS;
	}
	
	/**
	 * 查询数据
	 * @return
	 * @throws Exception 
	 */
	public String search(){
		result = new HashMap<String, Object>();
		List<UserVO> users;
		try {
			users = userService.findByKeyword(keyword);
			result.put("totalCount", users.size());
			result.put("users", users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 增加用户
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		result = new HashMap<String, Object>();
		userService.addUser(userVO);
		result.put("success","true");
		return SUCCESS;
	}
	/**
	 * 删除用户
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		result = new HashMap<String, Object>();
		userService.deleteUser(ids);
		result.put("success","true");
		return SUCCESS;
	}
	/**
	 * 更新用户数据
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		result = new HashMap<String, Object>();
		userService.updateUser(data);
		result.put("success","true");
		
		return SUCCESS;
	}
	
	/**
	 * 导出数据到excel
	 * @return
	 * @throws Exception 
	 */
	public String export() throws Exception{
		filename = "读者数据.xls";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		userService.export(out);
		input = new ByteArrayInputStream(out.toByteArray());
		return "export";
	}
	public String impot(){
		//Part part = request.getPart("importFile");
		result = new HashMap<String, Object>();
		try {
			userService.impot(importFile);
			result.put("success","true");
		} catch (Exception e) {
			result.put("success","false");
		}
		return SUCCESS;
	}
	
	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public Map<String, Object> getResult() {
		return result;
	}


	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getFilename() throws Exception {
		return new String(filename.getBytes("utf-8"),"iso-8859-1");
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	@Override
	public UserVO getModel() {
		return userVO;
	}

	
	
}

package bms.mysql.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bms.mysql.service.BookService;
import bms.mysql.service.impl.BookServiceImpl;
import bms.mysql.vo.DataVO;

public class DataStaticAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3546520958596912857L;
	
	private Map<String,Object> result;
	private BookService bookService = new BookServiceImpl();
	public String execute() throws Exception{
		result = new HashMap<String, Object>();
		Map<String,String> chart = new HashMap<String, String>();
		/**
		 * 	exportEnabled='1'(开启导出模式)  exportAtClient='1'(确定客户端导出)  
			exportHandler='fcExporter1' 
			(对应前台导出处理程序的标识，网络说是写一个jsp、asp或php的路径，那是服务端导出，在这里不用)
 			exportDialogMessage='正在生成,请稍候...' (导出时的提示语) 
			exportFormats='JPG=生成JPG图片|PDF=生成PDF文件'(右键菜单格式化成中文)。
		 */
		chart.put("caption", "图书类别柱形统计图");
		chart.put("xAxisName", "图书类别");
		chart.put("yAxisName", "数量");
		chart.put("numberSuffix", "本");
		chart.put("exportEnabled", "1");
		chart.put("exportAtClient", "1");
		//chart.put("logoURL", "images/jsu.png");//设置图标
		//chart.put("logoAlpha", "30");//设置图标的透明度
		//chart.put("logoScale", "50");//设置图标的大小
		//chart.put("logoPosition", "TL");//设置图标显示的位置
		chart.put("exportHandler", "fcExporter1");
		chart.put("exportFormats", "JPG=生成JPG图片|PDF=生成PDF文件");
		chart.put("exportDialogMessage", "正在生成,请稍候...");
		result.put("chart",chart);
		
		List<DataVO> data = bookService.findDataOfBook();
		result.put("data",data);
		return SUCCESS;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	
}

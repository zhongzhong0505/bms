package bms.mysql.utils;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @description excel表格工具类
 * @author zhong
 * @date 2013/6/27
 */
public class ExcelUtil {
	/**
	 * @name excel表格导出的方法
	 * @param sheetName sheet的名称
	 * @param title	表格的列名
	 * @param data	表格的数据
	 * @param out	输出流
	 * @throws Exception	抛出所有的异常
	 * 导出的时候需要设置文件的类型和头信息
	 * response.setContentType("application/vnd.ms-excel");
	 * response.setHeader("Content-Disposition", "attachment;filename=zzt.xls");
	 */
	public static void exportExcel(String sheetName,List<String> title,List<List<String>> data,OutputStream out) throws Exception{
		//初始化workbook
		WritableWorkbook  workbook = Workbook.createWorkbook(out);
		//建立一个sheet
		WritableSheet sheet = workbook.createSheet(sheetName, 0);
		
		//以标签的形式写入
		Label lab = null;
		
		//将表格的列名写入
		for(int i=0;i<title.size();i++){
			lab = new Label(i,0,title.get(i));
			sheet.addCell(lab);
		}
		//将数据写入
		for(int y=0;y<data.size();y++){
			for(int x=0;x<data.get(y).size();x++){
				lab = new Label(x,y+1,data.get(y).get(x));
				sheet.addCell(lab);
			}
		}
		//写入
		workbook.write();
		//关闭workbook
		workbook.close();
	}
	/**
	 * @name 导入excel数据的方法
	 * @param input excel文件的输入流对象
	 * @return	List<List<String>>类型的集合
	 * @throws Exception 抛出所有的异常
	 */
	public static List<List<String>> importExcel(InputStream input) throws Exception{
		
		//根据输入流对象得到workBook对象
		Workbook workbook = Workbook.getWorkbook(input);
		
		//得到所有的sheet
		Sheet sheets[] = workbook.getSheets();
		//存储数据的集合
		List<List<String>> list = new ArrayList<List<String>>();
		
		List<String> temp = null;
		
		
		for(int z=0;sheets!=null && z<sheets.length;z++){//遍历每一个sheet
			for(int y=1;y<sheets[z].getRows();y++){//遍历每一行
				temp = new ArrayList<String>();
				for(int x=0;x<sheets[z].getColumns();x++){//遍历每一行的每一列，并且将数据加入到list集合中
					String content = sheets[z].getCell(x,y).getContents();
					temp.add(content);
				}
				list.add(temp);
			}
		}
		return list;
	}
}

package bms.mysql.vo;

public class ChartVO {
	private String name;
	private int data;
	public ChartVO(String name,int data){
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
}

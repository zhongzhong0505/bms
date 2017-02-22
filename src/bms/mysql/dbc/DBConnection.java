package bms.mysql.dbc;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * 数据库连接类jdbc:mysql://localhost:3306/mldn
 */
public class DBConnection{
	/*public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/bms";
	public static final String DBUSER = "root";
	public static final String DBPASS = "55665";*/
	private Connection conn = null;
	public DBConnection(){
		Properties prop = new Properties();   
        InputStream in = this.getClass().getResourceAsStream("/dbc.properties");
		try{
			prop.load(in);
			String driver = prop.getProperty("driver").trim();
			String url = prop.getProperty("url").trim();
			String user = prop.getProperty("user").trim();
			String password = prop.getProperty("password").trim();
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public  Connection getConnection(){
		return conn;
	}
	public void close(){
		try{
			if(conn!=null){
				try{
					conn.close();
				}catch(Exception e){
					throw e;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
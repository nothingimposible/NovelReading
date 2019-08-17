package xiaoshuo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import shiyan6.SQLstudent;

class link_s{
	 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String uri="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Novel";
    String s_user="sa";
    String s_password="19980903kai";
    SQLstudent sq=new SQLstudent(driverName,uri,s_user,s_password);
    
}

class Myconnection{
	
	public static Connection getconnection(String driverName,String uri,String user,String password){
		Connection conn=null;
		try {
			Class.forName(driverName);
			try {
				conn=DriverManager.getConnection(uri,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return conn;
		}
		
	}
}
class SQLstudent{
	 Statement stmt;
	 PreparedStatement pstmt;
	 ResultSet rs;
	private String driverName;
	private String uri;
	 Connection conn;
	private String user;
	private String password;
	private Connection getconn(){
		return Myconnection.getconnection(driverName, uri,user,password);
	}
	public SQLstudent(String driverName,String uri,String user,String password){
		this.driverName=driverName;
		this.uri=uri;
		this.user=user;
		this.password=password;
		conn=getconn();
		try {
			this.stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
public class Database {
	 
	 
}

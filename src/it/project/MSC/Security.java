package it.project.MSC;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Security extends ActionSupport {
		private String username;
		private String password;
		private String response;
		
		public  void setUsername(String username){
			this.username = username;
		}
		public  void setPassword(String password){
			this.password = password;
		}
		public  void setResponse(String response){
			this.response = response;
		}
		
		public String  getUsername(){
			return username;
		}
		public String getPassword(){
			return password;
		}
		public String getResponse(){
			return response;
		}
	 }
	
public String  DbUtil{
	private static Connection dbConnection = null;

	  public static String getConnection() {
	      if (dbConnection != null) {
	          return dbConnection;
	      }
	      else {
	          try {
	        	  InputStream inputStream = DbUtil.class.getClassLoader()
	        			  .getResourceAsStream("db.properties");
	        	  Properties properties = new Properties();
	        	  if(properties !=null){
	        		  properties.load(inputStream);
	        		  
	        		  String dbDriver = properties.getProperty("dbDriver");
	        		  String connectionUrl = properties
	        				  .getProperty("connectionUrl");
	        		  String username = properties.getProperty("username");
	        		  String password = properties.getProperty("password");
	        		  
	        		  Class.forName(dbDriver).newInstance();
	        		  Connection conn = DriverManager.getConnection(connectionUrl,username,password);
	        			
	        		 
	        		  PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM users WHERE username=? and password=?");
	        		  ps.setString(1,username);
	        		  ps.setString(2,password);
	        		  ResultSet rs=ps.executeQuery();
	        		  if(rs !=null){
	        			  while (rs.next())
	        			  {
	        				  if(rs.getInt(1)==1){
	        					  response ="error";
	        					  return ActionSupport.SUCCESS;}
	        				  }
	        				  }
	        			  }
	        		  }
	        		  status=rs.next();        		  	        		  
	        		  
	        	  }
	     
	        	         	 
	          } catch (Exception e) {
	        	  e.printStackTrace();
	          }
	          return dbConnection;
	      }
	  }
}




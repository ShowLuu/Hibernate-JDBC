package com.ss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBTools {
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private Connection con;
	private Statement sta;
	private PreparedStatement pre;
	private ResultSet res;
	
	static{
		ResourceBundle res=ResourceBundle.getBundle("db");
		driver=res.getString("driver");
		url=res.getString("url");
		username=res.getString("username");
		password=res.getString("password");
		System.out.println(driver+"="+url+"="+username+"="+password);
	}
	
	public Connection getCon(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public Statement getSta(){
		try {
			if(con==null||con.isClosed()){
				con=getCon();
			}
			sta=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sta;
	}
	
	public PreparedStatement getPre(String sql){
		try {
			if(con==null||con.isClosed()){
				con=getCon();
			}
			pre=con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pre;
	}
	
	public ResultSet getRes(String sql){
		try {
			if(sta==null||sta.isClosed()){
				sta=getSta();
			}
			res=sta.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int update(String sql){
		int count=0;
		try {
			if(sta==null||sta.isClosed()){
				sta=getSta();
			}
			count=sta.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	//记录数
	public int count(String table){
		String sql="select * from "+table+" ";
		int count = 0;
		try {
			if(sta==null||sta.isClosed()){
				sta=getSta();
			}
			res=sta.executeQuery(sql);
			res.last();
			count=res.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count+1;
	}
	
	//最大ID
	public int maxCount(String table){
		String sql="select max(Id) from "+table+" ";
		int count = 0;
		try {
			if(sta==null||sta.isClosed()){
				sta=getSta();
			}
			res=sta.executeQuery(sql);
			while(res.next()){
				count=(int)res.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public void close(){
		try {
			if(res!=null||!res.isClosed()){
				res.close();
			}
			if(sta!=null||!sta.isClosed()){
				sta.close();
			}
			if(con!=null||!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

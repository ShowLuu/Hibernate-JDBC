package com.ss.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ss.hibernate.entity.User;

public class DBTest {
	
	public List<User> find(){
		DBTools db=new DBTools();
		String sql="select * from user";
		ResultSet result=db.getRes(sql);
		List<User> list = new ArrayList<>();
		try {
			while(result.next()){
				User user=new User();
				user.setId(result.getLong(1));
				user.setUsername(result.getString(2));
				user.setPassword(result.getString(3));
				user.setEnable(result.getInt(4));
				user.setCreateTime(result.getDate(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close();
		}
		return list;
	}
	
	public void insert01(){
//		String date=new Date(System.currentTimeMillis()).toString();
//		System.out.println(date);
		Date date=new Date(System.currentTimeMillis());
		DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
		System.out.println(df2.format(date));
		DBTools db=new DBTools();
		int id=db.maxCount("user");
		System.out.println(id);
		String sql="insert into user value("+id+",'111','111',0,'"+date+"')";
		int count=db.update(sql);
		if(count>0){
			System.out.println("新增成功");
		}
	}
	
	public void insert02(){
		DBTools db=new DBTools();
		String sql="insert into user value(?,?,?,?,?)";
		PreparedStatement pre=db.getPre(sql);
		List<User> users=getList();
		Date date=new Date(System.currentTimeMillis());
		int count=0;
		try {
			for(User user:users){
				count++;
				pre.setLong(1, user.getId());
				pre.setString(2, user.getUsername());
				pre.setString(3, user.getPassword());
				pre.setInt(4, user.getEnable());
				pre.setDate(5, date);
				pre.addBatch();
				pre.executeBatch();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count+"个成功");
	}
	
	public List<User> getList(){
		List<User> lists=new ArrayList<>();
		DBTools db=new DBTools();
		long count=db.maxCount("user");
		for(int i=0;i<5;i++){
			User user=new User();
			count=count+1;
			user.setId(count);
			user.setUsername("lu"+i);
			user.setPassword("1"+i);
			user.setEnable(i);
			lists.add(user);
		}
		return lists;
	}
	
	public static void main(String[] args) {
		DBTest db=new DBTest();
		db.insert02();
		
	}

}

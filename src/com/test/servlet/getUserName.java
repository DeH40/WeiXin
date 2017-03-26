package com.test.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.database.DBHepler;


public class getUserName {

	
public String getU(String openid)
{	
	
	try {
		Connection connection=DBHepler.getConnection();
		String sql = "select * from opid_username where openid='OPENID';".replace("OPENID", openid);
		PreparedStatement statement = null;
		ResultSet rs = null;
		statement = connection.prepareStatement(sql);
		rs = statement.executeQuery();
		if (rs!=null) {
			while(rs.next())
			return rs.getString("username");
		}
		else {
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
	}


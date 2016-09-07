/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uilayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 464336
 */
public class TravelExpertsDB {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
	Connection conn = null;	
        try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
		} catch (ClassNotFoundException | SQLException e){
			throw e;
		}
        return conn;
    }
    
}

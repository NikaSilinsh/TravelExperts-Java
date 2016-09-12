import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
private Connection conn;
	
	
	public DBConnection() {
		// TODO Auto-generated constructor stub
			
		try{
			// Load the driver
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println(e);
		}
		
		try{
			//Establish a connection
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
			 //conn.setAutoCommit(false);
		        //stmt1 = conn.createStatement();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
	}		
	
		Connection get_Connection() {
			return conn;
		}
}

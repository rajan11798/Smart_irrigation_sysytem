import java.sql.Connection;
import java.sql.DriverManager;

public class Conn1 
{
	static Connection con=null;
	
	 public static Connection getConnection()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		if(con==null)
		
			con=DriverManager.getConnection("jdbc:mysql://localhost/WeatherData","root","root");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
	 public static void main(String[] args)
	 {
		 System.out.println(getConnection());
	 }

}

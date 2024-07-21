package LMSPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LMSMain {


	public static void register()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username: ");
		String username=sc.next();
		System.out.println("Enter password: ");
		String password=sc.next();
		System.out.println("enter the confirm password");
		String conpass=sc.next();
		if(password.equals(conpass))
		{
			System.out.println("Enter the role (Admin or Librarian): ");
			String role = sc.next();
			try {
			String str = "insert into logindetails values('"+username+"','"+password+"','"+role+"')";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			//Connection con = DatabaseConnection.getMyConnection();
			Statement stmt = con.createStatement();
			int res =stmt.executeUpdate(str);
			if(res>=1) {
				System.out.println("registered successfuly");
				
				login();
			}
			else
				System.out.println("registration failed");
				
			}catch(Exception ex)
			{
				System.out.println("problem in registration"+ex.getMessage());
			}
		}else
		{
			System.out.println("password doesn't match");
			register();
		}
		
	}
	public static void login()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("                              LOGIN PAGE");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Enter the username: ");
		String username=sc.next();
		System.out.println("Enter the password: ");
		String password=sc.next();
		try
		{
			String str = "select *from logindetails where username='"+username+"' and password='"+password+"' ";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			
			
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery(str);
			if(rs!=null)
			{
				rs.next();
				if(rs.getString("role").equalsIgnoreCase("librarian"))
				{
				System.out.println("student login successfull");
				LibrarianModule module =new LibrarianModule();
				module.start();
				}else if(rs.getString("role").equalsIgnoreCase("admin"))
				{
					System.out.println("admin login successfull");
					AdminModule amodule =new AdminModule();
					amodule.start();
				}
					
				
			}else {
				System.out.println("login failed");
				login();
			}
		}catch(Exception ex)
		{
			System.out.println("exception during login"+ex.getMessage());
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner sc =new Scanner(System.in);
		char ch1=' ';
		do 
		{
			System.out.println("1: Register");
			System.out.println("2: Login");
			System.out.println("enter the option ");
			char ch=sc.next().charAt(0);
			switch(ch)
			{
				case '1': register();
				break;
				
				case '2': login();
				break;
				
				default: System.out.println("invalid");
			}
			System.out.println("***********************************************************************************");
			System.out.println("do you want to continue on Registration Page if yes press y");
			ch1=sc.next().charAt(0);
		}while(ch1=='y'|| ch1=='Y');	


	
	}
}

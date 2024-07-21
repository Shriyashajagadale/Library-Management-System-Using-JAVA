package LMSPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {
	

	public void addLib() throws SQLException, ClassNotFoundException {
		
		//connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
		
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Id : ");
		int id=sc.nextInt();
		System.out.println("Enter Librarian name : ");
		String name=sc.next();
		System.out.println("Enter Age : ");
		int age=sc.nextInt();
		System.out.println("Enter Mobile No : ");
		String mob=sc.next();
		Statement st=con.createStatement();	
		String str = "insert into Librarian values("+id+",'"+name+"',"+age+",'"+mob+"')";
		int result =st.executeUpdate(str);
		System.out.println(result);
	}
	public void viewLib() throws ClassNotFoundException, SQLException {
		
		//connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
		
		Statement st=con.createStatement();	
		 String str1="select * from Librarian";
		 ResultSet rs=st.executeQuery(str1);
	
		  System.out.print(" ");
		  System.out.print("Id");
		  System.out.print(" 	");
		  System.out.print("Name");
		  System.out.print(" 		");
		  System.out.print("Age");
		  System.out.print(" 	   ");
		  System.out.print("Mobile No");
		  System.out.println(" ");
		  System.out.println("--------------------------------------------------");
	  while(rs.next()) 
	  { 
		  
		  
		  System.out.print(" ");
		  System.out.print(rs.getInt("id"));
		  System.out.print(" 	");
		  System.out.print(rs.getString("name"));
		  System.out.print(" 	");
		  System.out.print(rs.getInt("age"));
		  System.out.print(" 	");
		  System.out.print(rs.getDouble("mob"));
		  
		  System.out.println(" 	");
	  }
	
	}
	public void deleteLib() throws ClassNotFoundException, SQLException {
		Scanner sc= new Scanner(System.in);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
		
		PreparedStatement pst= con.prepareStatement("DELETE FROM Librarian where id=?");
		System.out.println("Enter the ID which you want to delete: ");
		int id =sc.nextInt();
		pst.setInt(1, id);
		int result =pst.executeUpdate();
		//ResultSet rs=pst.executeQuery();
		System.out.println("Record deleted successfully for id "+id);
		
	}


}


package LMSPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Librarian {


		public void addBook() throws ClassNotFoundException, SQLException {

			//connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Id : ");
			int bookId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Book Name : ");
			String bookName=sc.nextLine();
			System.out.println("Enter Author Name : ");
			String authorName=sc.nextLine();
			System.out.println("Enter Quantity : ");
			int quantity=sc.nextInt();
			int issued=0;
			Statement st=con.createStatement();	
			String str = "insert into bookdetails values('"+bookId+"','"+bookName+"','"+authorName+"','"+quantity+"','"+issued+"')";
			int result =st.executeUpdate(str);
			System.out.println(result);
			
			
		}
		
		public void viewBook() throws ClassNotFoundException, SQLException {
			

			//connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			
			Statement st=con.createStatement();	
			 String str1="select * from bookdetails";
			 ResultSet rs=st.executeQuery(str1);
		
			  System.out.print(" ");
			  System.out.print("Id");
			  System.out.print(" 	");
			  System.out.print("Book Name");
			  System.out.print(" 	   ");
			  System.out.print("Author Name");
			  System.out.print("	    ");
			  System.out.print("Quantity");
			  System.out.print(" 	   ");
			  System.out.print("Issued");
			  System.out.println(" ");
			  System.out.println("-------------------------------------------------------------------------------");
			  while(rs.next()) 
			  { 
				  
				  
				  System.out.print(" ");
				  System.out.print(rs.getInt("id"));
				  System.out.print(" 	");
				  System.out.print(rs.getString("bname"));
				  System.out.print(" 	   ");
				  System.out.print(rs.getString("aname"));
				  System.out.print(" 		");
				  System.out.print(rs.getInt("quantity"));
				  System.out.print(" 		");
				  System.out.print(rs.getInt("issued"));
				  System.out.print(" 	");
				 
				  
				  System.out.println(" 	");
				
			}
			  
	}
		
		public void searchBook() throws ClassNotFoundException, SQLException {
			

			//connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			Scanner sc=new Scanner(System.in);
			char option1=' ';
			do {
			System.out.println("     1. Search Book by Id:  ");
			System.out.println("     2. Search Book by Name:  ");
			System.out.println(">>>>>");
			int ch2= sc.nextInt();
			switch(ch2) 
			{
				case 1:
					System.out.println("Enter the Book id to search:");
					int bid = sc.nextInt();
					String str = "select *from bookdetails  where id="+bid+" ";
					try {
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(str);
						  
						  System.out.print(" ");
						  System.out.print("Id");
						  System.out.print(" 	");
						  System.out.print("Book Name");
						  System.out.print(" 		");
						  System.out.print("Author Name");
						  System.out.println(" ");
						  System.out.println("--------------------------------------------------");
						
						 while(rs.next())
							{
							  System.out.print(" ");
							  System.out.print(rs.getInt("id"));
							  System.out.print(" 	");
							  System.out.print(rs.getString("bname"));
							  System.out.print(" 	");
							  System.out.print(rs.getString("aname"));
							  System.out.print(" 	");
								
							}
							
						}catch(Exception ex)
						{
							System.out.println(ex.getMessage());
						}
					
	
					break;
				case 2:
					System.out.println("Enter the Book name to search:");
					String bookname = sc.next();
					String str1 = "select *from bookdetails  where bname='"+bookname+"' ";
					try {
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(str1);
						  
						  System.out.print(" ");
						  System.out.print("Id");
						  System.out.print("	");
						  System.out.print("Book Name");
						  System.out.print("	");
						  System.out.print("Author Name");
						  System.out.println(" ");
						  System.out.println("--------------------------------------------------");
						
						 while(rs.next())
							{
							  System.out.print(" ");
							  System.out.print(rs.getInt("id"));
							  System.out.print("	");
							  System.out.print(rs.getString("bname"));
							  System.out.print("		");
							  System.out.print(rs.getString("aname"));
							  System.out.print(" 	");
							  System.out.println(" ");
								
							}
							
						}catch(Exception ex)
						{
							System.out.println(ex.getMessage());
						}
					
					break;
				default:System.out.println("You Entered Wrong option!!!!!!!!");
			}
				System.out.println("   ");
				System.out.println("***********************************************************************************");
				System.out.println("do you want to continue in SEARCH if yes press y ");
				option1=sc.next().charAt(0);
			}while(option1=='Y' || option1=='y');
			
			
		}
		
		public void issueBook() throws ClassNotFoundException, SQLException {
			
			int status=0;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Issue Id : ");
			int issueId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Student Name : ");
			String studentName=sc.nextLine();
			System.out.println("Enter Book Id : ");
			int bookId=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter Issue date : ");
			String issuedate=sc.nextLine();
			System.out.println("Enter Return date : ");
			String returndate=sc.nextLine();
			Statement st=con.createStatement();	
			
			
			
			// Check book availability
	        String checkBookQuery = "SELECT quantity, issued FROM bookdetails WHERE id = ?";
	        PreparedStatement checkStmt = con.prepareStatement(checkBookQuery);
	        checkStmt.setInt(1, bookId);
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            int quantity = rs.getInt("quantity");
	            int issued = rs.getInt("issued");

	            if (quantity > 0) {
	                // Issue the book and update the bookdetails table
	                String updateBookQuery = "UPDATE bookdetails SET quantity = ?, issued = ? WHERE id = ?";
	                PreparedStatement updateStmt = con.prepareStatement(updateBookQuery);
	                updateStmt.setInt(1, quantity - 1);
	                updateStmt.setInt(2, issued + 1);
	                updateStmt.setInt(3, bookId);
	                updateStmt.executeUpdate();

	                // Insert into issuebook table
	                String insertIssueQuery = "INSERT INTO issuebook VALUES (?, ?, ?, ?, ?)";
	                PreparedStatement insertStmt = con.prepareStatement(insertIssueQuery);
	                insertStmt.setInt(1, issueId);
	                insertStmt.setString(2, studentName);
	                insertStmt.setInt(3, bookId);
	                insertStmt.setString(4, issuedate);
	                insertStmt.setString(5, returndate);
	                insertStmt.executeUpdate();

	                System.out.println("Book issued successfully!");
	            } else {
	                System.out.println("Book is not available in sufficient quantity.");
	            }
	        } else {
	            System.out.println("Book not found.");
	        }

			
			
		}
		
		public void viewIssuedBook() throws ClassNotFoundException, SQLException {
			

			//connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			
			Statement st=con.createStatement();	
			 String str1="select * from issuebook";
			 ResultSet rs=st.executeQuery(str1);
		
			  System.out.print(" ");
			  System.out.print("Issue Id");
			  System.out.print(" 	");
			  System.out.print("Student Name");
			  System.out.print(" 	");
			  System.out.print("Book Id");
			  System.out.print(" 	");
			  System.out.print("Issue date");
			  System.out.print(" 	");
			  System.out.print("Return date");
			  System.out.println(" ");
			  System.out.println("-------------------------------------------------------------------------------");
			  while(rs.next()) 
			  { 
				  
				  
				  System.out.print(" ");
				  System.out.print(rs.getInt("issueid"));
				  System.out.print(" 		");
				  System.out.print(rs.getString("studname"));
				  System.out.print(" 	");
				  System.out.print(rs.getInt("bid"));
				  System.out.print(" 	");
				  System.out.print(rs.getString("issuedate"));
				  System.out.print(" 	");
				  System.out.print(rs.getString("returndate"));
				  System.out.print(" 	");
				 
				  
				  System.out.println(" 	");
				
			}
			
			
		}
		
		public void returnBook() throws SQLException, ClassNotFoundException {
			

			//connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/LMSdb","root","12345");
			Scanner sc=new Scanner(System.in);
			
			
			
			   // Retrieve book id from issuebook table
		    System.out.println("Enter the ISSUE ID which you want to Return: ");
		    int issueId = sc.nextInt();
		    String getBookQuery = "SELECT bid FROM issuebook WHERE issueid = ?";
		    PreparedStatement getBookStmt = con.prepareStatement(getBookQuery);
		    getBookStmt.setInt(1, issueId);
		    ResultSet rs = getBookStmt.executeQuery();

		    if (rs.next()) {
		        int bookId = rs.getInt("bid");

		        // Delete the record from issuebook table
		        PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM issuebook WHERE issueid = ?");
		        deleteStmt.setInt(1, issueId);
		        int result = deleteStmt.executeUpdate();

		        if (result > 0) {
		            // Update the bookdetails table
		            String updateBookQuery = "UPDATE bookdetails SET quantity = quantity + 1, issued = issued - 1 WHERE id = ?";
		            PreparedStatement updateStmt = con.prepareStatement(updateBookQuery);
		            updateStmt.setInt(1, bookId);
		            updateStmt.executeUpdate();

		            System.out.println("Book returned successfully!");
		        } else {
		            System.out.println("Failed to return the book. Please check the ISSUE ID.");
		        }
		    } else {
		        System.out.println("Issue ID not found.");
		    }


		}

}

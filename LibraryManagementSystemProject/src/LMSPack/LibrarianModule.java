package LMSPack;

import java.sql.SQLException;
import java.util.Scanner;

public class LibrarianModule {
	
	public void start() throws ClassNotFoundException, SQLException
	{
		Librarian l=new Librarian();
		Scanner sc=new Scanner(System.in);
	
		char option1=' ';
		do 
		{
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("                              LIBRARIAN MODULE");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("     1. Add Book ");
				System.out.println("     2. view Books ");
				System.out.println("     3. Search Book ");
				System.out.println("     4. Issue Book ");
				System.out.println("     5. View issued Books ");
				System.out.println("     6. Return Book ");
				System.out.println(">>>>>>>: ");
				
				int ch2= sc.nextInt();
				switch(ch2) 
				{
					case 1:l.addBook();
						break;
					case 2:l.viewBook();
						break;
					case 3:l.searchBook();
						break;
					case 4:l.issueBook();
						break;
					case 5:l.viewIssuedBook();
						break;
					case 6:l.returnBook();
						break;
					default:System.out.println("You Entered Wrong option!!!!!!!!");
			}
			System.out.println("***********************************************************************************");
			System.out.println("do you want to continue in Librarian Module if yes press y ");
			option1=sc.next().charAt(0);
		}while(option1=='Y' || option1=='y');
	}
}

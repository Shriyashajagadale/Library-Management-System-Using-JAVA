package LMSPack;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminModule {
	public void start() throws ClassNotFoundException, SQLException
	{
		char option1=' ';
		do 
		{
			Admin a=new Admin();
			Scanner sc =new Scanner(System.in);
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("                              ADMIN MODULE");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("     1. Add Librarian ");
			System.out.println("     2. view Librarian ");
			System.out.println("     3. Delete Librarian ");
			System.out.println(">>>>>>>: ");
			int ch1= sc.nextInt();
			switch(ch1) 
			{
				case 1:a.addLib();
					break;
				case 2:a.viewLib();
					break;
				case 3:a.deleteLib();
					break;
				default: System.out.println("You entered wrong choice!!!!!!!!!!!");
			}
			System.out.println("***********************************************************************************");
			System.out.println("do you want to continue in ADMIN MODULE if yes press y ");
			option1=sc.next().charAt(0);
		}while(option1=='Y' || option1=='y');
		
		
	}

}

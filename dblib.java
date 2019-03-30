package asg1;

import java.io.PrintStream;
import java.util.Scanner;

public class dblib {
	public String list(String year, String semester)
	{
		String res = "";
		return res;
	}
	
	public String list(String stdID, String year, String semester)
	{
		String res = "";
		return res;
		
	}
	
	public String register(String stdID, String courseID, String sectionID)
	{
		String res = "";
		return res;
	}
	
	public static void main(String args[])
	{
		PrintStream cout = System.out;
        Scanner cin = new Scanner(System.in);
        boolean quit = false;
        boolean flag = false;
        dblib db = new dblib();
        String res = "";
        while (!flag)
        {
        	cout.println("I, Ruize Xu, certify that this project is my work only."
        		+ "I have not used source code from third parties (except those that were discussed in-class).");
        	cout.println();
        	cout.println("Please Enter year");
        	String year = cin.nextLine();
        	cout.flush();
        	cout.println("Please Enter semester(S for spring, F for fall)");
        	String semester = cin.nextLine();
        	cout.flush();
        	res = db.list(year,semester);
        	if (res == "NC")//no enough capacity
        	{
        		quit = true;
        		cout.println("no enought capacity");
        	}
        	while (!quit)
        	{
        		cout.println("q to quit, l to list classes, r to register");
        		cout.flush();
        		String input = cin.nextLine();
        		int c = input.charAt(0);
        		switch (c) {
        			case 'q':
        			case 'Q':
        				quit = true;
        				break;
        			
        			case 'l':
        			case 'L':
        			{
        				cout.println("Enter student no");
        				cout.flush();
        				String stdID = cin.nextLine();
        				res = db.list(stdID,year,semester);
        				break;
        			}
        			
        			case 'r':
        			case 'R':
        			{
        				cout.println("Enter student no");
        				cout.flush();
        				String stdID = cin.nextLine();
        				cout.println("Enter course no");
        				cout.flush();
        				String courseID = cin.nextLine();
        				cout.println("Enter section no");
        				cout.flush();
        				String sectionID = cin.nextLine();
        				res = db.list(stdID,year,semester);
        				if (res == "NC")
        				{
        					cout.println("no enough capacity");
        				}
        				else if (res == "FK")
        				{
        					cout.println("FK violation");
        				}
        				else if (res == "PK")
        				{
        					cout.println("PK violation");
        				}
        				break;
        			}
        			default:	
            			cout.println("please make sure you are entering q,l or r");
            			cout.flush();
            			break;
            	}
        	}
        	break;	
        }

	}
}

package wcfrtservice.client;

import wcfservice.stub.*;

import java.io.PrintStream;
import java.util.*;

import wcfservice.stub.RTService;
import wcfservice.stub.IRegistration;
import java.util.List;

public class RTClient {

    public static void main(String [] args) {

        RTService rts = new RTService();
        IRegistration ir= rts.getBasicHttpBindingIRegistration();


        PrintStream cout = System.out;
        Scanner cin = new Scanner(System.in);
        boolean quit = false;
        boolean flag = false;
        String res = "";
        while (!flag) {
            cout.println("Please Enter year");
            String year = cin.nextLine();
            cout.flush();
            cout.println("Please Enter semester(S for spring, F for fall)");
            String semester = cin.nextLine();
            cout.flush();
            ArrayOfstring x = ir.list(year,semester);
            List<String>  ls = x.getString();
            for (String s : ls)
            {
                cout.println(s);
            }
            while (!quit) {
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
                    case 'L': {
                        cout.println("Enter student no");
                        cout.flush();
                        String stdID = cin.nextLine();
                        ArrayOfstring x1 = ir.list2(year,semester,stdID);
                        List<String>  ls1 = x1.getString();
                        for (String s : ls1)
                        {
                            cout.println(s);
                        }
                        break;
                    }

                    case 'r':
                    case 'R': {
                        cout.println("Enter student no");
                        cout.flush();
                        String stdID = cin.nextLine();
                        cout.println("Enter course no");
                        cout.flush();
                        String courseID = cin.nextLine();
                        cout.println("Enter section no");
                        cout.flush();
                        String sectionID = cin.nextLine();
                        res = ir.register(courseID,sectionID,stdID);
                        if (res == "NC") {
                            cout.println("no enough capacity");
                        } else if (res == "FK") {
                            cout.println("FK violation");
                        } else if (res == "PK") {
                            cout.println("PK violation");
                        }
                        else System.out.println(res);
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

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using courseRegister;

namespace Client
{
    class Client
    {
        static void Main(string[] args)
        {
            Library db = new Library("ism6236", "ism6236bo");
            Console.WriteLine("Semester: ");
            String semester = Console.ReadLine(); 
            Console.WriteLine("Year: ");
            String year = Console.ReadLine();


            List<String> secList = db.List(year, semester);
            for (int i = 0; i < secList.Count; i++)
            {
                String[] row = (secList[i].Split(','));
                for (int n = 0; n < 11; n++)
                {
                    Console.WriteLine(row[n] + " | ");
                }
                Console.WriteLine(" |");
            }

            Console.WriteLine("q to quit, l to list classes, r to register");
            String input = Console.ReadLine();
            bool quit = false;
            while (!quit)
            {
                char c = input[0];
                switch (c)
                {
                    case 'l':
                    case 'L':
                        Console.WriteLine("Semester: ");
                        string sem = Console.ReadLine();
                        Console.WriteLine("Enter Student No:");
                        string StuNo = Console.ReadLine();
                        Console.WriteLine("Year: ");
                        string y = Console.ReadLine();

                        List<String> sectionList = db.List( y, sem, StuNo);

                        for (int i = 0; i < sectionList.Count; i++)
                        {
                            String[] row = sectionList[i].Split(',');
                            Console.WriteLine(row[0] + " | ");


                            Console.WriteLine();
                        }
                        break;
                    case 'r':
                    case 'R':
                        Console.WriteLine("Enter Student No:");
                        string sn = Console.ReadLine();
                        Console.WriteLine("Enter Course No:");
                        string CourseNo = Console.ReadLine();
                        Console.WriteLine("Enter Section No:");
                        string SectionNo = Console.ReadLine();
                        Console.WriteLine("Enter N to quit, P to finalize the register ");
                        string d = Console.ReadLine();

                        if (d.ToLower().Equals("p"))
                        {
                            db.Register(CourseNo, SectionNo,sn);
                            break;
                        }
                        else
                        {
                            break;
                        }

                    case 'q':
                    case 'Q':
                        quit = true;
                        break;
                }

                if (!quit)
                {
                    
                    Console.WriteLine("q to quit, l to list classes, r to register");
                    input = Console.ReadLine();
                }

            }
        }
    }
}

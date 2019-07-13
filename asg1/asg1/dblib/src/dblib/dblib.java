package dblib;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dblib {

        private static String username = "ism6236";
        private static String pass = "ism6236bo";
        private static Connection mcn;
        static private String mservername;
        static private String mdbname;
        static private String url;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Constructors ">
    //static constructor
    static {
        mservername = "MSSQLSERVER";
        mdbname = "registration";
    }
        public dblib(String uid, String pass)
        {
            setConnection(uid,pass);
        }
        public void setConnection(String uid, String pass)
        {
            try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost\\" + mservername
                        + ";databaseName=" + mdbname + ";user=" + uid + ";password=" + pass + ";";
                mcn = DriverManager.getConnection(
                        connectionUrl);
                System.out.println("Success loading sql Driver!");
            }
            catch (SQLException ex)
            {
                System.out.print("Error loading ql Driver!");
                ex.printStackTrace();
            }
            catch (Exception e)
            {
                System.out.print("Error loading sql Driver!");
                e.printStackTrace();
            }
        }
        public List<String> list(String year, String semester)
        {
            List<String> res = new ArrayList<>();
            try
            {
                String sem = "";
                if (semester.charAt(0) == 'f' || semester.charAt(0) == 'F') sem = "Fall";
                else if (semester.charAt(0) == 's' || semester.charAt(0) == 'S') sem = "Spring";
                Statement stmt = mcn.createStatement();
                String sql = String.format("select * from section where Year = '%s'",year);
                sql += String.format(" and Semester = '%s'",sem);
                sql += "and Capacity > TotalEnrolled";
                //System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String resultRow = "";
                for (int i = 1; i <= cols; ++ i)
                {
                    resultRow += rsmd.getColumnName(i) + "\t";
                }
                resultRow += "\n";
                res.add(resultRow);
                while (rs.next())
                {

                    String c = rs.getString(1);
                    String s = rs.getString(2);
                    String f = rs.getString(3);
                    String r = rs.getString(4);
                    String ca = rs.getString(5);
                    String d = rs.getString(6);
                    String t = rs.getString(7);
                    String to = rs.getString(8);
                    String se = rs.getString(9);
                    String y = rs.getString(10);
                    res.add(c
                            + "\t" + s +"\t" + "\t"+ f +"\t"+ "\t" +"\t" + r + "\t" +"\t" + ca + "\t" + "\t"
                            + d + "\t" + "\t"+ t + "\t" +"\t" +"\t" + to +"\t" +"\t" + "\t"+ se +"\t" + "\t" + y  +"\n" );
                }
                rs.close();
            }
            catch (Exception e)
            {
                System.out.print("get data error!");
                e.printStackTrace();
            }
            return res;
        }

        public List<String> list(String stdID, String year, String semester)
        {
            List<String> res = new ArrayList<>();
            try
            {
                String sem = "";
                if (semester.charAt(0) == 'f' || semester.charAt(0) == 'F') sem = "Fall";
                else if (semester.charAt(0) == 's' || semester.charAt(0) == 'S') sem = "Spring";
                Statement stmt = mcn.createStatement();
                String sql = String.format("select section.courseNo,section.sectionNo, Room, days, time from section join enrollment on section.courseNo = enrollment.courseNo where Year = '%s'",year);
                sql += String.format(" and Semester = '%s'",sem);
                sql += String.format(" and stuNo = '%s'", stdID);
                System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                String resultRow = "";
                for (int i = 1; i <= cols; ++ i)
                {
                    resultRow += rsmd.getColumnName(i) + "\t";
                }
                resultRow += "\n";
                res.add(resultRow);
                while (rs.next())
                {

                    String c = rs.getString(1);
                    String s = rs.getString(2);
                    String f = rs.getString(3);
                    String r = rs.getString(4);
                    String ca = rs.getString(5);
                    res.add(c
                            + "\t" + s +"\t" + "\t"+ f + "\t" + r + "\t" + "\t" + ca + "\t" + "\t"
                            +"\n" );
                }
                rs.close();
            }
            catch (Exception e)
            {
                System.out.print("get data error!");
                e.printStackTrace();
            }
            return res;

        }

        public String register(String stdID, String courseID, String sectionID)
        {
            String res = "";
            try
            {
                int size = 0;
                Statement stmt = mcn.createStatement();
                String sql = String.format("select Capacity, TotalEnrolled from section where CourseNo = '%s'",courseID);
                sql += String.format(" and SectionNO = '%s'",sectionID);
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next())
                {
                    size = 1;
                    int cap = Integer.parseInt(rs.getString(1));
                    int enr = Integer.parseInt(rs.getString(2));
                    if (cap <= enr) return "NC";
                }
                if (size == 0) return "Wrong sectionNO or courseNO";
                String sql1 = String.format("insert INTO ENROLLMENT VALUES ('%s', '%s' , '%s' , '%s')", stdID, courseID, sectionID, " ");
                System.out.println(sql1);
                String sql2 = String.format("update section set TotalEnrolled = TotalEnrolled + 1 where CourseNo = '%s'",courseID);
                sql2 += String.format(" and SectionNO = '%s'",sectionID);
                stmt.execute(sql1);
                stmt.execute(sql2);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
                if (ex.getErrorCode() == 547) return "FK";
                else
                return "PK";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return res;
        }

    public static void main(String[] args) {
        dblib o = new dblib("ism6236","ism6236bo");
        String res = o.register("1","1","1");
        System.out.println(res);
    }


}


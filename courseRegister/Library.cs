using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Data;

namespace courseRegister
{
    public class Library
    {
        #region Data
        private SqlConnection mcn;
        private const string mdbname = "registration";
        #endregion
        //Data Source=(local);Initial Catalog=registration;Integrated Security=True

        public Library(string uid, string pass)
        {
            mcn = new SqlConnection
            {
                ConnectionString = String.Format("Data Source=DESKTOP-SSECH63;Initial Catalog=registration;Integrated Security=True")
            };
            mcn.Open();
        }

        #region Business Method
        public List<String> List(string year, string semester)
        {
            List<String> sections = new List<String>();
            try
            {

                String sem = "";
                if (semester[0] != 'f' && semester[0] != 'F')
                {
                    if (semester[0] == 's' || semester[0] == 'S')
                    {
                        sem = "Spring";
                    }
                }
                else
                {
                    sem = "Fall";
                }
                String sql = String.Format("select * from section where Year = {0}", year);
                sql = sql + String.Format(" and Semester = '{0}'", sem);
                sql = sql + " and Capacity > TotalEnrolled";
                Console.WriteLine(sql);
                SqlCommand s = new SqlCommand(sql, mcn);
                SqlDataReader rs = s.ExecuteReader();

                if(rs.HasRows)
                    while(rs.Read())
                    {
                        sections.Add(string.Format("{0},{1},{2},{3},{4},{5},{6},{7},{8},{9}", rs.GetString(0), rs.GetString(1), rs.GetString(2), rs.GetString(3), rs.GetValue(4), rs.GetString(5), rs.GetString(6), rs.GetValue(7), rs.GetString(8), rs.GetValue(9)));
                    }
                rs.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return sections;
        }

        public List<String> List(string year, string semester, string stdID)
        {
            List<String> sections = new List<String>();
            try
            {

                String sem = "";
                if (semester[0] != 'f' && semester[0] != 'F')
                {
                    if (semester[0] == 's' || semester[0] == 'S')
                    {
                        sem = "Spring";
                    }
                }
                else
                {
                    sem = "Fall";
                }
                String sql = String.Format("select section.courseNo,section.sectionNo, Room, days, time from section join enrollment on section.courseNo = enrollment.courseNo where Year = {0}", year);
                sql = sql + String.Format(" and Semester = '{0}'", sem);
                sql = sql + String.Format(" and stuNo = '{0}'", stdID);
                SqlCommand s = new SqlCommand(sql, mcn);
                SqlDataReader rs = s.ExecuteReader();

                if (rs.HasRows)
                    while (rs.Read())
                    {
                        sections.Add(string.Format("{0},{1},{2},{3},{4}", rs.GetValue(0), rs.GetValue(1), rs.GetValue(2), rs.GetValue(3), rs.GetValue(4)));
                    }
                rs.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return sections;

        }
        public string Register(string courseID, string sectionID, string stdID)
        {

            String res = "";
            try
            {
                //bool size = false;
                String sql = String.Format("select Capacity, TotalEnrolled from section where CourseNo = '{0}'", courseID);
                sql = sql + String.Format(" and SectionNO = '{0}'", sectionID);
                SqlCommand s = new SqlCommand(sql, mcn);
                SqlDataReader rs = s.ExecuteReader();
                rs.Close();
                /** while (rs.HasRows)
                 {
                     size = true;
                     if (rs.GetInt32(1) <= rs.GetInt32(2))
                         return "NC";
                 }

                 if (!size)
                 {
                     return "Wrong sectionNO or courseNO";
                 }**/
                String sql1 = String.Format("insert INTO ENROLLMENT VALUES ('{0}', '{1}' , '{2}' , '{3}')", stdID, courseID, sectionID, " ");
                String sql2 = String.Format("update section set TotalEnrolled = TotalEnrolled + 1 where CourseNo = '{0}'", courseID);
                sql2 = sql2 + String.Format(" and SectionNO = '{0}'", sectionID);
                Console.WriteLine(sql1);
                SqlCommand s1 = new SqlCommand(sql1, mcn);
                s1.ExecuteNonQuery();
                SqlCommand s2 = new SqlCommand(sql2, mcn);
                s2.ExecuteNonQuery();
            }
            catch (SqlException var11)
            {
                Console.WriteLine(var11.StackTrace);
                if (var11.ErrorCode == 547)
                {
                    return "FK";
                }

                return "PK";
            }
            catch (Exception var12)
            {
                Console.WriteLine(var12.StackTrace);
            }

            return res;
        }
        #endregion
    }
}

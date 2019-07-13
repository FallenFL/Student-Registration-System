using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using courseRegister;

namespace RegistrationService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class RTService : iRegistration
    {
        private Library mdb;

        public RTService()
        {
            mdb = new Library("ism6236", "ism6236bo");
        }
        public List<String> list(String year, String semester)
        {
            return mdb.List(year, semester);
        }

        public List<String> list2(String year, String semester, String stdID)
        {
            return mdb.List(year, semester, stdID);
        }

        public String register(String course, String section, String stdID)
        {
            return mdb.Register(course, section, stdID);
        }


    }
}

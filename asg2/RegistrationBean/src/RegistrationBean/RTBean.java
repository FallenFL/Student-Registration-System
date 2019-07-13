package RegistrationBean;

import javax.ejb.Stateless;
import remotelib.IRemoteRt;
import dblib.dblib;
import java.util.List;

@Stateless(name = "RTEJB")
public class RTBean implements IRemoteRt {
    private dblib mdb;
    public RTBean() {
         mdb = new dblib("ism6236", "ism6236bo");
        }

        @Override
        public List<String> list (String year, String semester){
            return mdb.list(year, semester);
        }

        @Override
        public List<String> list (String year, String semester, String stdID){
            return mdb.list(year, semester, stdID);
        }

        @Override
        public String register (String courseNO, String sectionNO, String stdID){
            return mdb.register(courseNO, sectionNO, stdID);
        }
}

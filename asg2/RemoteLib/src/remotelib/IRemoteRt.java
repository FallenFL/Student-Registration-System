package remotelib;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IRemoteRt {
    public List<String> list(String year, String semester);
    public List<String> list(String year, String semester, String stdID);
    public String register(String courseNO, String sectionNO, String stdID);
}

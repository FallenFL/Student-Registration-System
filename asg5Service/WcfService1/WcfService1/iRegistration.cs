using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RegistrationService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface iRegistration
    {

        [OperationContract]
        List<String> list(String year, String semester);

        [OperationContract]
        List<String> list2(String year, String semester, String stdID);

        [OperationContract]
        String register(String course, String section, String stdID);

        // TODO: Add your service operations here
    }
}

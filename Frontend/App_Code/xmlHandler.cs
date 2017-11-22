using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Xml;

namespace XMLHndlr
{
    public static class xmlHandler
    {
        public static string handle_WDriveMessage(XmlDocument xml)
        {
            string xpath_status = "controller.WDriveMessage/status";
            string xpath_error = "controller.WDriveMessage/content";

            string status = xml.SelectSingleNode(xpath_status).Value;

            if (status.Equals("OK"))
                return status;
            else
                return xml.SelectSingleNode(xpath_error).Value;
        }
    }
}

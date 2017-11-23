
using System.Data;
using System.Xml;

namespace XMLHndlr
{
    public static class xmlHandler
    {
        public static string handle_WDriveMessage(XmlDocument xml)
        {
            string xpath_status = "controller.WDriveMessage/status";
            string xpath_error = "controller.WDriveMessage/content";

            string status = xml.SelectSingleNode(xpath_status).InnerText;

            if (status.Equals("OK"))
                return status;
            else
                return xml.SelectSingleNode(xpath_error).InnerText;
        }

        public static DataTable handle_FileList(XmlDocument xml)
        {
            DataTable data = new DataTable();
            data.Columns.AddRange(new DataColumn[3]
            {
                new DataColumn("Nombre", typeof(string)),
                new DataColumn("Fecha de modificación", typeof(string)),
                new DataColumn("Tamaño", typeof(string))
            });

            string xpath = "controller.WDriveMessage/content/controller.WDriveFile";

            var archivos = xml.SelectNodes(xpath);
            
            foreach (XmlNode archivo in archivos)
            {
                string nombre = archivo.SelectSingleNode("name").InnerText;
                if (!nombre.EndsWith(".txt"))
                    nombre += @"\";

                data.Rows.Add(nombre,
                    archivo.SelectSingleNode("lastModifiedTime").InnerText,
                    archivo.SelectSingleNode("size").InnerText + " Bytes"
                    );
            }

            return data;
        }

        /*public static DataTable handle_FileInfo(XmlDocument xml)
        {
            DataTable data = new DataTable();
            data.Columns.AddRange(new DataColumn[3]
            {
                new DataColumn("Nombre", typeof(string)),
                new DataColumn("Fecha de modificación", typeof(string)),
                new DataColumn("Tamaño", typeof(string))
            });

            string xpath = "controller.WDriveMessage/content/controller.WDriveFile";

            var archivos = xml.SelectNodes(xpath);

            foreach (XmlNode archivo in archivos)
            {
                string nombre = archivo.SelectSingleNode("name").InnerText;
                if (!nombre.EndsWith(".txt"))
                    nombre += @"\";

                data.Rows.Add(nombre,
                    archivo.SelectSingleNode("lastModifiedTime").InnerText,
                    archivo.SelectSingleNode("size").InnerText + " Bytes"
                    );
            }

            return data;
        }*/
    }
}

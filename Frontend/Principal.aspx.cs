using System;
using System.Data;
using System.Web.UI;
using API;
using XMLHndlr;
using System.Xml;

public partial class _Default : Page
{

    private void displayAlert(string alert)
    {
        ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "Atención!", "alert('" + alert + "')", true);
    }

    protected void Page_Load(object sender, EventArgs e)
    {
        literalRuta.Text = APIHandler.getCurrentDir();
        fillExplorer();
    }

    private void fillExplorer()
    {
        string stringResponse = APIHandler.listFiles();

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK"))
        {
            DataTable data = xmlHandler.handle_FileList(xmlResponse);
            tablaExplorador.DataSource = data;
            tablaExplorador.DataBind();
        }
        else
            displayAlert(msg);

    }

    protected void btnNuevo_Click(object sender, EventArgs e)
    {
        
    }
}
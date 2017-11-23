
using System;
using System.Web;
using System.Web.UI;
using System.Xml;
using API;
using XMLHndlr;

public partial class Account_Login : Page {
    
    private void displayAlert(string alert)
    {
        ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "Atención!", "alert('" + alert + "')", true);
    }

    protected void Page_Load(object sender, EventArgs e)
    {
        APIHandler.currentlyLogged = false;
    }

    protected void LogIn(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            if (UserName.Text.Equals(""))
            {
                displayAlert("El nombre de usuario no puede estar vacío.");
                return;
            }

            string stringResponse = APIHandler.loadAccount(UserName.Text, Password.Text);

            XmlDocument xmlResponse = new XmlDocument();
            xmlResponse.LoadXml( stringResponse );

            string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
            if (msg.Equals("OK"))
            {
                APIHandler.currentlyLogged = true;
                Response.Redirect("~/Principal");
            }
            else
                displayAlert(msg);
            
        }

    }
}
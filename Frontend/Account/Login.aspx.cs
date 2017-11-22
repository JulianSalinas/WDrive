
using System;
using System.Web;
using System.Web.UI;
using System.Xml;
using API;

public partial class Account_Login : Page {
    
    private void displayAlert(string alert)
    {
        ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "Atención!", "alert('" + alert + "')", true);
    }

    protected void Page_Load(object sender, EventArgs e)
    { 
        
    }

    protected void LogIn(object sender, EventArgs e)
    {
        if (IsValid)
        {
            if (UserName.Text.Equals(""))
            {
                displayAlert("El nombre de usuario no puede estar vacío.");
                return;
            }

            string stringResponse = APIHandler.loadAccount(UserName.Text, Password.Text);

            XmlDocument xmlResponse = new XmlDocument();
            xmlResponse.LoadXml( stringResponse );

            APIHandler.generartxt(stringResponse);


            
        }

    }
}
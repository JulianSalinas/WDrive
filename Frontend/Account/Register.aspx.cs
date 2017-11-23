
using System;
using System.Web.UI;
using API;
using System.Xml;
using XMLHndlr;

public partial class Account_Register : Page
{


    private void displayAlert(string alert)
    {
        ScriptManager.RegisterClientScriptBlock(Page, Page.GetType(), "Atención!", "alert('" + alert + "')", true);
    }

    APIHandler api;

    protected void CreateUser_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            if (UserName.Text.Equals(""))
            {
                displayAlert("El nombre de usuario no puede estar vacío.");
                return;
            }

            string stringResponse = api.createAccount(UserName.Text, Password.Text,
                Int32.Parse(ByteAmount.Text));

            XmlDocument xmlResponse = new XmlDocument();
            xmlResponse.LoadXml(stringResponse);

            string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
            if (msg.Equals("OK"))
                Response.Redirect("~/Account/Login");
            else
                displayAlert(msg);

        }
    }

    protected void Page_Load(object sender, EventArgs e)
    {
        api = new APIHandler();
    }
}
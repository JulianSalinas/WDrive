
using System;
using System.Web.UI;
using API;
using System.Xml;

public partial class Account_Register : Page
{


    private void displayAlert(string alert)
    {
        ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "Atención!", "alert('" + alert + "')", true);
    }

    protected void CreateUser_Click(object sender, EventArgs e)
    {
        if(Page.IsValid)
        {
            if (UserName.Text.Equals(""))
            {
                displayAlert("El nombre de usuario no puede estar vacío.");
                return;
            }

            string stringResponse = APIHandler.createAccount(UserName.Text, Password.Text, 
                Int32.Parse(ByteAmount.Text) );

            XmlDocument xmlResponse = new XmlDocument();
            xmlResponse.LoadXml(stringResponse);

            APIHandler.generartxt(stringResponse);


        }
    }
}
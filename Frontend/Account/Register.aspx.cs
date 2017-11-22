using Microsoft.AspNet.Identity;
using System;
using System.Linq;
using System.Web.UI;
using Frontend;

public partial class Account_Register : Page
{
    protected void CreateUser_Click(object sender, EventArgs e)
    {
        if(Page.IsValid)
            ErrorMessage.Text = "Registrar sin implementar";
        else
            ErrorMessage.Text = "Algunos campos son inválidos";
    }
}
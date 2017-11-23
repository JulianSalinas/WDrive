using System;
using System.Data;
using System.Web.UI;
using API;
using XMLHndlr;
using System.Xml;
using System.Web.UI.WebControls;

public partial class _Default : Page
{
    static DataTable fileData;
    private string slash = @"\";

    private void displayAlert(string alert)
    {
        ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "Atención!", "alert('" + alert + "')", true);
    }

    protected void Page_Load(object sender, EventArgs e)
    {
        if(!APIHandler.currentlyLogged)
            Response.Redirect("~/Account/Login");

        literalRuta.Text = APIHandler.getCurrentDir();

        if(!IsPostBack)
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
            fileData = xmlHandler.handle_FileList(xmlResponse);
            tablaExplorador.DataSource = fileData;
            tablaExplorador.DataBind();
        }
        else
            displayAlert(msg);

    }

    protected void btnNuevo_Click(object sender, EventArgs e)
    {
        
    }

    protected void abrir_archivo(GridViewCommandEventArgs args)
    {
        var index = Int32.Parse(args.CommandArgument.ToString());
        var filename = fileData.Rows[index].Field<string>("Nombre");
        string dirname = "";
        string stringResponse;

        if (filename.EndsWith(slash))
        {
            dirname = filename.TrimEnd(slash.ToCharArray()[0]);
            stringResponse = APIHandler.accessDir(dirname);
        }
        else
            stringResponse = "";

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK") && dirname.Equals(""))
        {
            
        }
        else
            displayAlert(msg);

        page_refresh();
    }

    protected void tablaExplorador_RowCommand(object sender, GridViewCommandEventArgs e)
    {
        if (e.CommandName.Equals("Abrir"))
            abrir_archivo(e);
    }

    protected void tablaExplorador_SelectedIndexChanged(object sender, EventArgs e)
    {
        var marca = (LinkButton) tablaExplorador.SelectedRow.Cells[0].Controls[0];

        if (marca.Text.Equals("Marcar"))
        {
            marca.Text = "Desmarcar";
            tablaExplorador.SelectedRow.BackColor = System.Drawing.Color.PaleTurquoise;
        }
        else
        {
            marca.Text = "Marcar";
            tablaExplorador.SelectedRow.BackColor = System.Drawing.Color.White;
        }
    }

    protected void btnVolver_Click(object sender, EventArgs e)
    {

        string stringResponse = APIHandler.accessDir("..");

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (!msg.Equals("OK"))
            displayAlert(msg);

        page_refresh();
    }

    private void page_refresh() { Response.Redirect(Request.Url.ToString()); }
}
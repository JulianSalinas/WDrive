using API;
using System;
using System.Data;
using System.IO;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml;
using XMLHndlr;


public partial class _Default : Page
{
    static DataTable fileData;
    private string slash = @"\";

    private void displayAlert(string alert)
    {
        //ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "Atención!", "alert('" + alert + "')", true);
        txtAlerta.Text = alert;
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupAlerta').modal('show');", true);
    }

    protected void Page_Load(object sender, EventArgs e)
    {
        if(!APIHandler.currentlyLogged)
            Response.Redirect("~/Account/Login");

        literalRuta.Text = APIHandler.getCurrentDir();

        if (!IsPostBack || APIHandler.refresh)
        {
            fillExplorer(); APIHandler.refresh = false;
        }

        if (APIHandler.pastebinFull)
            btnPegar.Visible = true;
        else
            btnPegar.Visible = false;

    }

    private void fillExplorer()
    {
        showSpaceDetails();
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

    private void showSpaceDetails()
    {

        XmlDocument xmlTotalSpace = new XmlDocument();
        xmlTotalSpace.LoadXml(APIHandler.getTotalSpace());

        XmlDocument xmlAvailableSpace = new XmlDocument();
        xmlAvailableSpace.LoadXml(APIHandler.getAvailableSpace());

        labelEspacioTotal.Text = "Espacio total: " + xmlHandler.handle_Space(xmlTotalSpace);
        labelEspacioDisponible.Text = "Espacio disponible: " + xmlHandler.handle_Space(xmlAvailableSpace);

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
        {
            stringResponse = APIHandler.openFile(filename);

            XmlDocument xmlResponse2 = new XmlDocument();
            xmlResponse2.LoadXml(stringResponse);

            var contenido = xmlHandler.handle_FileContent(xmlResponse2);

            if (contenido == null)
            {
                displayAlert("No se puedo abrir el archivo " + filename);
                return;
            }
            else
            {
                mostrarEdit(filename, contenido);
                return;
            }
        }

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK"))
        {
            if (filename.EndsWith(slash))
                APIHandler.dirLevel += 1;
            page_refresh();
        }
        else
            displayAlert(msg);
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
            tablaExplorador.SelectedRow.BackColor = System.Drawing.Color.PowderBlue;
        }
        else
        {
            marca.Text = "Marcar";
            tablaExplorador.SelectedRow.BackColor = System.Drawing.Color.White;
        }
    }

    protected void btnVolver_Click(object sender, EventArgs e)
    {
        string stringResponse = APIHandler.backDir();

        if (stringResponse == null)
            return;

        XmlDocument xmlResponse = new XmlDocument();        
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (!msg.Equals("OK"))
            displayAlert(msg);
        else
        {
            APIHandler.dirLevel -= 1;
            page_refresh();
        }      
    }

    protected void enviar_a_portapapeles(object sender, EventArgs e)
    {
        var selectedIndex = -1;

        for (int i = 0; i < tablaExplorador.Rows.Count; i++)
        {
            var marca = (LinkButton) tablaExplorador.Rows[i].Cells[0].Controls[0];
            if (marca.Text.Equals("Desmarcar"))
            {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1)
        {
            displayAlert("No se seleccionó un archivo.");
            return;
        }

        string filename = fileData.Rows[selectedIndex].Field<string>("Nombre").TrimEnd(slash.ToCharArray()[0]);

        string stringResponse = "";

        if (((Button)sender).ID.Equals("btnCortar"))
        {
            APIHandler.movingAction = true;
            stringResponse = APIHandler.cutFile(filename);
        }
        else if (((Button)sender).ID.Equals("btnCopiar"))
        {
            APIHandler.movingAction = false;
            stringResponse = APIHandler.copyFile(filename);
        }

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK"))
        {
            APIHandler.pastebinFull = true;
            APIHandler.pastingFile = filename;   
            displayAlert("Archivo movido a portapapeles");                   
        }
        else
            displayAlert(msg);
    }

    protected void btnPegar_Click(object sender, EventArgs e)
    {
        if (!APIHandler.pastebinFull)
        {
            displayAlert("No hay archivos para pegar.");
            return;
        }

        bool existe = existe_archivo(APIHandler.pastingFile);

        if (existe)
        {
            desplegarConfirmacion("El archivo ya existe. ¿Desea reemplazarlo?");
            return;
        }

        string stringResponse = APIHandler.pasteFile();

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK"))
        {
            displayAlert("Archivo pegado con éxito.");
            if (APIHandler.movingAction)
                APIHandler.pastebinFull = false;
        }
        else
            displayAlert(msg);

    }

    private void page_refresh() { Response.Redirect(Request.Url.ToString()); }

    protected bool existe_archivo(string filename)
    {
        string stringResponse = APIHandler.exists(filename);

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_ExistMessage(xmlResponse);
        if (msg.Equals("false"))
            return false;
        if (msg.Equals("true"))
            return true;
        else
            displayAlert("Error verificando existencia del archivo.");
        return true;
    }

    protected void btnEliminar_Click(object sender, EventArgs e)
    {
        var selectedIndex = -1;
        bool sirvio = false;

        for (int i = 0; i < tablaExplorador.Rows.Count; i++)
        {
            var marca = (LinkButton) tablaExplorador.Rows[i].Cells[0].Controls[0];
            if (marca.Text.Equals("Desmarcar"))
            {
                selectedIndex = i;
                string filename = fileData.Rows[selectedIndex].Field<string>("Nombre").TrimEnd(slash.ToCharArray()[0]);

                string stringResponse = APIHandler.deleteFile(filename);

                XmlDocument xmlResponse = new XmlDocument();
                xmlResponse.LoadXml(stringResponse);

                string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
                if (!msg.Equals("OK"))
                {
                    displayAlert(msg);
                    return;
                }
                else
                    sirvio = true;                    
            }
        }

        if (selectedIndex == -1)
        {
            displayAlert("No se seleccionó un archivo.");
            return;
        }

        if (sirvio)
            page_refresh();
    }

    protected void btnCompartir_Click(object sender, EventArgs e)
    {
        txtUsuario.Text = "";
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupCompartir').modal('show');", true);
    }

    private void desplegarConfirmacion(string prompt)
    {
        literalConfirmacion.Text = prompt;
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupConfirmacion').modal('show');", true);
    }

    protected void btnPopupCompartir_Click(object sender, EventArgs e)
    {

        var selectedIndex = -1;

        for (int i = 0; i < tablaExplorador.Rows.Count; i++)
        {
            var marca = (LinkButton) tablaExplorador.Rows[i].Cells[0].Controls[0];
            if (marca.Text.Equals("Desmarcar"))
            {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1)
        {
            displayAlert("No se seleccionó un archivo.");
            return;
        }

        string filename = fileData.Rows[selectedIndex].Field<string>("Nombre").TrimEnd(slash.ToCharArray()[0]);
        string usuario = txtUsuario.Text;

        string stringResponse = APIHandler.shareFile(filename, usuario);

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK"))
        {
            displayAlert("Compartido con éxito");
        }
        else
            displayAlert(msg);

        showSpaceDetails();
    }


    protected void btnPopupConfirmacionSI_Click(object sender, EventArgs e)
    {
        string stringResponse = APIHandler.pasteFile();

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK"))
        {
            displayAlert("Archivo pegado con éxito.");
            if (APIHandler.movingAction)
                APIHandler.pastebinFull = false;
        }
        else
            displayAlert(msg);
    }

    protected void btnPopupConfirmacionNO_Click(object sender, EventArgs e)
    {
        APIHandler.movingAction = false;
        APIHandler.pastebinFull = false;
        APIHandler.confirmation = false;

    }

    protected void btnNuevoArchivo_Click(object sender, EventArgs e)
    {
        txtArchivo.Text = "";
        txtContenido.Text = "";
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupCrearArchivo').modal('show');", true);
    }

    protected void btnPopupCrearArchivo_Click(object sender, EventArgs e)
    {
        string archivo = txtArchivo.Text;
        string contenido = txtContenido.Text;

        string stringResponse = APIHandler.createFile(archivo, contenido);

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK")) displayAlert("Archivo creado con éxito.");
        else displayAlert(msg);

        fillExplorer();

    }

    protected void btnNuevoDirectorio_Click(object sender, EventArgs e)
    {
        txtDirectorio.Text = "";
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupCrearDirectorio').modal('show');", true);
    }

    protected void btnPopupCrearDirectorio_Click(object sender, EventArgs e)
    {
        string directorio = txtDirectorio.Text;
        string stringResponse = APIHandler.createDir(directorio);

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK")) displayAlert("Directorio creado con éxito.");
        else displayAlert(msg);
        fillExplorer();
    }

    protected void btnPopupEditar_Click(object sender, EventArgs e)
    {
        APIHandler.createFile(editArchivo.Text, editContent.Text);
    }

    protected void mostrarEdit(string filename, string content)
    {
        editArchivo.Text = filename;
        editContent.Text = content;
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupEditarArchivo').modal('show');", true);
    }

    protected void mostrarPopupDescarga(object sender, EventArgs e)
    {
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupDescargar').modal('show');", true);
    }

    protected void btnPopupDescargar_Click(object sender, EventArgs e)
    {
        string filename = editArchivo.Text;
        string ruta = rutaDescarga.Text;
        string content = editContent.Text;

        if (ruta.EndsWith(@"\"))
            System.IO.File.WriteAllText(@ruta + filename, content);
        else
            System.IO.File.WriteAllText(@ruta + @"\" + filename, content);

        displayAlert("Archivo descargado.");
    }

    protected void btnSubirArchivo_Click(object sender, EventArgs e)
    {
        ScriptManager.RegisterStartupScript(this, GetType(), "Pop", "$('#popupSubirArchivo').modal('show');", true);
    }

    protected void btnPopupSubirArchivo_Click(object sender, EventArgs e)
    {
        string archivo = Path.GetFileName(fileUpload.FileName);
        string contenido = System.Text.Encoding.UTF8.GetString(fileUpload.FileBytes);

        string stringResponse = APIHandler.createFile(archivo, contenido);

        XmlDocument xmlResponse = new XmlDocument();
        xmlResponse.LoadXml(stringResponse);

        string msg = xmlHandler.handle_WDriveMessage(xmlResponse);
        if (msg.Equals("OK")) displayAlert("Archivo creado con éxito.");
        else displayAlert(msg);

        fillExplorer();

    }

}
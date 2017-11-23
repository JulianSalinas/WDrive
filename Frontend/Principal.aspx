<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Principal.aspx.cs" Inherits="_Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="row">
        <hr />        
        <div class="text-info">   
            <h4> 
                <asp:Button runat="server" ID="btnVolver" Text="Regresar <<" CssClass="btn btn-rounded" />
                <asp:Literal runat="server" ID="literalRuta" Text="Ejemplo de ruta"/> 
            </h4>
        </div>
    </div>

    <hr />

    <div class="row">
        <div class="col-md-8">
            <asp:GridView ID="tablaExplorador" CssClass="table table-striped table-hover" runat="server">
            </asp:GridView>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <asp:Button ID="btnNuevo" CssClass="btn btn-rounded btn-customWidth"
                    runat="server" Text="Nuevo" OnClick="btnNuevo_Click" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnEditar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Editar seleccionado" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnMover" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Mover seleccionado" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnEliminar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Eliminar seleccionados" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnCompartir" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Compartir seleccionados" />
            </div>
        </div>
    </div>
</asp:Content>

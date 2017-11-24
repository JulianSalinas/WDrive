<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Principal.aspx.cs" Inherits="_Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="row">
        <hr />        
        <div class="col-md-8">
            <div class="text-info">   
                <h4> 
                    <asp:Button runat="server" ID="btnVolver" Text="Regresar <<" CssClass="btn btn-rounded" OnClick="btnVolver_Click" />
                    <asp:Literal runat="server" ID="literalRuta" Text="Ejemplo de ruta"/> 
                </h4>
            </div>
        </div>
        <div class="col-md-4">
            <div class="text-danger">
                <h4><asp:Literal ID="literalConfirmacion" runat="server" Text="Reemplazar archivos ya existentes?" />
                <asp:CheckBox ID="checkReemplazo" runat="server" /></h4>
            </div>
        </div>
    </div>

    <hr />

    <div class="row">
        <div class="col-md-4">
            <div class="form-group">
                <asp:Button ID="btnNuevo" CssClass="btn btn-rounded btn-customWidth"
                    runat="server" Text="Nuevo"/>
            </div>
            <div class="form-group">
                <asp:Button ID="btnEditar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Editar seleccionado"/>
            </div>
            <div class="form-group">
                <asp:Button ID="btnCortar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Cortar seleccionado" OnClick="enviar_a_portapapeles" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnCopiar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Copiar seleccionado" OnClick="enviar_a_portapapeles" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnPegar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Pegar desde portapapeles" OnClick="btnPegar_Click" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnEliminar" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Eliminar seleccionados" OnClick="btnEliminar_Click" />
            </div>
            <div class="form-group">
                <asp:Button ID="btnCompartir" CssClass="btn btn-rounded btn-customWidth" 
                    runat="server" Text="Compartir seleccionados" />
            </div>

            <div class="form-group">
                <h3>
                    <asp:Label ID="labelEspacioTotal" CssClass="label label-default label-customWidth" 
                    runat="server" Text="Espacio total: " />
                </h3>
            </div>

            <div class="form-group">
                <h3>
                    <asp:Label ID="labelEspacioDisponible" CssClass="label label-default label-customWidth" 
                    runat="server" Text="Espacio disponible: " />
                </h3>
            </div>

        </div>
        <div class="col-md-8">
            <div id="ScrollList" style="height: 300px; overflow: auto">
                <asp:GridView ID="tablaExplorador" CssClass="table table-hover" runat="server" EmptyDataText="Sin archivos..." HorizontalAlign="Center" ShowHeaderWhenEmpty="True" OnSelectedIndexChanged="tablaExplorador_SelectedIndexChanged" OnRowCommand="tablaExplorador_RowCommand">
                    <Columns>
                        <asp:CommandField HeaderText=".." SelectText="Marcar" ShowCancelButton="False" ShowSelectButton="True" />
                        <asp:ButtonField CommandName="Abrir" HeaderText=".." Text="Abrir" />
                    </Columns>
                </asp:GridView>
            </div>
        </div>
    </div>
</asp:Content>

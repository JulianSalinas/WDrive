<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Principal.aspx.cs" Inherits="_Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="row">
        <hr />        
        <div class="col-md-8">
            <div class="text-info">   

                    <asp:Label CssClass="label label-default" runat="server" padding="100px" Text="Ruta actual: " />
                    <h4> 
                    <asp:Literal runat="server" Text="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"/> 
                    <asp:Literal runat="server" ID="literalRuta" Text="Ejemplo de ruta"/> 
                </h4>
            </div>
        </div>
    </div>

    <hr />

    <div class="row">
        <div class="col-md-4">

            <div class="form-group">
                <asp:Button ID="btnNuevoArchivo" CssClass="btn btn-rounded btn-customWidth"
                    runat="server" Text="Nuevo archivo" OnClick="btnNuevoArchivo_Click"/>
            </div>

            <div class="form-group">
                <asp:Button ID="btnNuevoDirectorio" CssClass="btn btn-rounded btn-customWidth"
                    runat="server" Text="Nuevo directorio" OnClick="btnNuevoDirectorio_Click"/>
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
                    runat="server" Text="Compartir seleccionado" OnClick="btnCompartir_Click" />
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
                    <asp:Button runat="server" ID="btnVolver" Text="Regresar <<" CssClass="btn btn-rounded" OnClick="btnVolver_Click" />
            </div>
        </div>
    </div>



     <div id="popupCompartir" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Compartir</h4>
                </div>

                <div class="modal-body">
                    <asp:TextBox ID="txtUsuario" runat="server" placeholder="Usuario para compartir" class="form-control"></asp:TextBox><br />
                </div>

                <div class="modal-footer">
                    <asp:Button runat="server" Text="Cancelar" CssClass="btn btn-default"  data-dismiss="modal"/>
                    <asp:Button runat="server" Text="Compartir" CssClass="btn btn-info" OnClick="btnPopupCompartir_Click"/>
                </div>

            </div>
        </div>
    </div>

    <div id="popupCrearDirectorio" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Crear directorio</h4>
                </div>

                <div class="modal-body">
                    <asp:TextBox ID="txtDirectorio" runat="server" placeholder="Nombre del directorio" class="form-control"></asp:TextBox><br />
                </div>

                <div class="modal-footer">
                    <asp:Button runat="server" Text="Cancelar" CssClass="btn btn-default"  data-dismiss="modal" />
                    <asp:Button runat="server" Text="Crear" CssClass="btn btn-info" OnClick="btnPopupCrearDirectorio_Click"/>
                </div>

            </div>
        </div>
    </div>

    <div id="popupCrearArchivo" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Crear archivo</h4>
                </div>

                <div class="modal-body">
                    <asp:TextBox ID="txtArchivo" runat="server" placeholder="Nombre del archivo" class="form-control"></asp:TextBox><br />
                    <asp:TextBox ID="txtContenido" runat="server" placeholder="Contenido del archivo" class="form-control" TextMode="MultiLine"></asp:TextBox><br />
                </div>

                <div class="modal-footer">
                    <asp:Button runat="server" Text="Cancelar" CssClass="btn btn-default"  data-dismiss="modal"/>
                    <asp:Button runat="server" Text="Crear" CssClass="btn btn-info" OnClick="btnPopupCrearArchivo_Click"/>
                </div>

            </div>
        </div>
    </div>

    <div id="popupConfirmacion" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">¿Continuar?</h4>
                </div>

                <div class="modal-body">
                    <asp:Literal ID="literalConfirmacion" runat="server" text="¿Continuar?"/>
                </div>

                <div class="modal-footer">
                    <div class="btn-group">
                        <asp:Button runat="server" Text="Si" CssClass="btn btn-success" OnClick="btnPopupConfirmacionSI_Click" />
                        <asp:Button runat="server" Text="No" CssClass="btn btn-danger" OnClick="btnPopupConfirmacionNO_Click" />
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div id="popupAlerta" class="modal fade" role="dialog"> 
        <div class="modal-dialog"> 
            <div class="modal-content"> 
 
                <div class="modal-header"> 
                    <button type="button" class="close" data-dismiss="modal">&times;</button> 
                    <h4 class="modal-title">Alerta</h4> 
                </div> 
 
                <div class="modal-body"> 
                    <h3> 
                        <asp:Label ID="txtAlerta" CssClass="label" ForeColor="Black" runat="server" Text="Espacio total: " /> 
                    </h3> 
                </div> 
 
                <div class="modal-footer"> 
                    <asp:Button runat="server" Text="OK" CssClass="btn btn-default"  data-dismiss="modal"/> 
                </div> 
 
            </div> 
        </div> 
    </div> 

</asp:Content>

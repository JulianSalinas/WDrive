<%@ Page Title="Crear nuevo drive" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Register.aspx.cs" Inherits="Account_Register" %>

<%@ Register Src="~/Account/Logo.ascx" TagPrefix="uc" TagName="Logo" %>

<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <h2><%: Title %></h2>
    <p class="text-danger">
        <asp:Literal runat="server" ID="ErrorMessage" />
    </p>

    <div class="row">
        <h4 style="margin-left:20px">Ingrese los datos del nuevo usuario de drive</h4>
        <div class="col-md-8">
            <div class="form-horizontal">                
                <hr />
                <asp:ValidationSummary runat="server" CssClass="text-danger" />
                <div class="form-group">
                    <asp:Label runat="server" AssociatedControlID="UserName" CssClass="col-md-2 control-label">Username</asp:Label>
                    <div class="col-md-10">
                        <asp:TextBox runat="server" ID="UserName" CssClass="form-control" />
                        <asp:RegularExpressionValidator runat="server" ControlToValidate="UserName"
                                CssClass="text-danger" 
                                ValidationExpression="^[a-zA-Z]+[a-zA-Z0-9]{3,}$"
                                ErrorMessage="Nombre de usuario inválido. Debe ser alfanumérico y al menos 4 carácteres." />
                    </div>
                </div>
                <div class="form-group">
                    <asp:Label runat="server" AssociatedControlID="Password" CssClass="col-md-2 control-label">Contraseña</asp:Label>
                    <div class="col-md-10">
                        <asp:TextBox runat="server" ID="Password" TextMode="Password" CssClass="form-control" />
                        <asp:RequiredFieldValidator runat="server" ControlToValidate="Password"
                            CssClass="text-danger" ErrorMessage="El campo de contraseña es obligatorio." />
                    </div>
                </div>
                <div class="form-group">
                    <asp:Label runat="server" AssociatedControlID="ConfirmPassword" CssClass="col-md-2 control-label">Confirmar contraseña</asp:Label>
                    <div class="col-md-10">
                        <asp:TextBox runat="server" ID="ConfirmPassword" TextMode="Password" CssClass="form-control" />
                        <asp:RequiredFieldValidator runat="server" ControlToValidate="ConfirmPassword"
                            CssClass="text-danger" Display="Dynamic" ErrorMessage="El campo de confirmación de contraseña es obligatorio." />
                        <asp:CompareValidator runat="server" ControlToCompare="Password" ControlToValidate="ConfirmPassword"
                            CssClass="text-danger" Display="Dynamic" ErrorMessage="La contraseña y la contraseña de confirmación no coinciden." />
                    </div>
                </div>
                <div class="form-group">
                    <asp:Label runat="server" AssociatedControlID="ByteAmount" CssClass="col-md-2 control-label">Capacidad en bytes</asp:Label>
                    <div class="col-md-10">
                        <asp:TextBox runat="server" ID="ByteAmount" TextMode="Number" CssClass="form-control" />
                        <asp:RegularExpressionValidator runat="server" ControlToValidate="ByteAmount"
                            ValidationExpression="^[1-9]+$"
                            CssClass="text-danger" ErrorMessage="Se requiere un número entero positivo." />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <asp:Button runat="server" OnClick="CreateUser_Click" Text="Registrar" CssClass="btn btn-default" />
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <section id="socialLoginForm">
                <uc:logo runat="server" id="OpenAuthLogin" />
            </section>
        </div>
    </div>
</asp:Content>


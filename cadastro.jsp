<%-- 
    Document   : cadastro
    Created on : 21/04/2016, 17:46:47
    Author     : André
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js" type="text/javascript"></script>
        <title>Cadastro</title>
    </head>
    <body>
        <div class="container theme-showcase" role="main" style="padding-top: 100px;">
            <div class="" style="padding-left: 250px;">
                <h2>Banco VSF - Virtude do Sistema Financeiro</h2>
                <div>
                    <form action="Cadastros?action=cadcliente" method="POST">
                        <div class="panel panel-default">
                            <table class="panel-heading">
                                <labe class="panel-title">Abertura de conta corrente</labe>
                                <tbody class="panel-body">
                                    <tr>                                        
                                        <td class="col-md-2">
                                            <label>Nome Completo</label>
                                        </td> 
                                        <td class="col-md-1">
                                            <label>RG</label>
                                        </td> 
                                    </tr>
                                    <tr>                                        
                                        <td class="col-md-2">
                                            <input type="text" maxlength="50" size="50" name="nome"  />
                                        </td>
                                        <td class="col-md-1">                                        
                                            <input type="text" maxlength="9" name="rg"/>
                                        </td>
                                    </tr>
                                    <tr>

                                        <td colspan="2">
                                            <label>Endereço</label>
                                            <input type="text" maxlenght="40" name="endereco"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Número</label>
                                            <input type="text" maxlenght="5" name="endNumero"/>
                                        </td>
                                        <td>
                                            <label>Complemento</label>
                                            <input type="text" maxlenght="15" name="complemento"/>
                                        </td>
                                        <td>
                                            <label>CEP</label>
                                            <input type="text" maxlenght="8" name="cep" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Telefone</label>
                                            <input type="text" maxlenght="11" name="telefone" />
                                        </td>
                                        <td>
                                            <label>Email</label>
                                            <input type="text" maxlenght="25" name="email" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="radio" checked="true" name="tipoConta" value="F" />
                                            <label>Conta Física </label>
                                        </td>
                                        <td class="col-md-2"> 
                                            <label>CPF</label>
                                        </td> 
                                        <td class="col-md-2"> 
                                            <input type="text" maxlength="11" name="cpf" />
                                        </td>
                                        <td>
                                            <input type="radio" name="tipoConta" value="J" />
                                            <label>Conta Jurídica</label>
                                        </td> 
                                        <td class="col-md-2"> 
                                            <label>CNPJ</label>
                                        </td>
                                        <td>
                                            <input type="text" maxlength="14" name="cnpj"  />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">
                                            <label> Insira sua renda mensal, de acordo com ela será definido o seu Cheque Especial(limite)</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Renda</label>
                                            <input type="text" maxlength="18" name="renda"/>
                                        </td>
                                        <td>
                                            <label>Senha</label>
                                            <input type="text" maxlength="10" name="senha"/>
                                        </td>
                                        <td>
                                            <label>Confirma Senha</label>
                                            <input type="text" maxlength="10" name="confirmaSenha"/>
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="reset" class="btn btn-sm btn-default" value="Cancelar" />
                                            <input type="submit" class="btn btn-sm btn-primary" value="Enviar" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div>
                                </form>
                            </div>
                        </div>
                </div>
                </body>
                </html>

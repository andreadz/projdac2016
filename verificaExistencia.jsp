<%-- 
    Document   : verificaExistencia
    Created on : 21/05/2016, 18:48:45
    Author     : André
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Verificacoes?action=verificaExistencia" method="POST">
            <table>                
                <tr>                    
                    <td>
                        <input type="radio" name="tipoPessoa" value="J"  />
                    </td>
                    <td>
                        CNPJ
                    </td>
                    <td>                   
                        <input id="cnpj" type="text" name="cnpj" />           
                    </td>
                    <td>
                        CPF
                    </td>
                    <td>
                        <input type="radio" name="tipoPessoa" value="F"  />
                    </td>
                    <td>
                        <input id="cpf" type="text" name="cpf" />     
                    </td>
                    <td>
                        <input type="submit" value="Verificar"  />
                    </td>
                </tr>
            </table>
        </form>              

    </body>
</html>

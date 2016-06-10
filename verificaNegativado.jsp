<%-- 
    Document   : verificaNegativado
    Created on : 21/05/2016, 01:27:56
    Author     : André
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Verificacoes?action=verificaNegativado" method="POST">
            <table>                
                <tr>                    
                    <td>
                        <input type="radio" name="tipoPessoa" value="J"  />
                    </td>
                    <td>
                        CNPJ
                    </td>
                    <td>                   
                        <input type="text" name="cnpj" />           
                    </td>
                    <td>
                        CPF
                    </td>
                    <td>
                        <input type="radio" name="tipoPessoa" value="F"  />
                    </td>
                    <td>
                        <input type="text" name="cpf" />     
                    </td>
                    <td>
                        <input type="submit" value="Verificar" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

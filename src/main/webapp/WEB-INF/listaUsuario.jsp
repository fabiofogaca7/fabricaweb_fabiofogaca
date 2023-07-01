<%@page import="java.util.List"%>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FabricaWeb</title>
</head>
<body>

<h1>Lista de Usuários</h1>
<%
	List<Usuario> lista = (List<Usuario>)request.getAttribute("listar");

	out.print("<table border=1>");
	for (Usuario u : lista) {
		out.print("<tr>");
		out.print("<td>"+ u.getId() + "</td> <td> " + u.getNome() + "</td>");
		out.print("</tr>");
	}
	out.print("</table>");
%>
</body>
</html>
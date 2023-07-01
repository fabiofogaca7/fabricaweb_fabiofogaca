<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Usuario usuario = (Usuario)request.getAttribute("usuario");
	%>
	<form action="usuarioController" method="post">
		Login: <input type="text" name="id" value="<%=usuario.getId()%>"/>
		Nome: <input type="text" name="nome" value="<%=usuario.getNome()%>"/>
		Login: <input type="text" name="login" value="<%=usuario.getLogin()%>"/>
		Senha: <input type="text" name="senha" value="<%=usuario.getSenha()%>"/>
		<input type="submit" value="Salvar"/>
	</form>
	
</body>
</html>
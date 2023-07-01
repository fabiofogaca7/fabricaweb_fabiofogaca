<%@page import="java.util.List"%>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FabricaWeb</title>

<script type="text/javascript">
function confirmaExclusao(id){
	if(window.confirm('Tem certeza que deseja excluir?')){
		location.href="usuarioController?acao=excluir&id=" + id;
	}
}
</script>

</head>
<body>

<h1>Lista de Usuários</h1>
<%
	List<Usuario> lista = (List<Usuario>)request.getAttribute("listar");
%>
	<table border=1>
	<%for (Usuario u : lista) {%>
		<tr>
		<td><%out.print(u.getId());%></td>
		<td><%=u.getNome()%></td>
		<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)"> Excluir </a> | <a href="usuarioController?acao=alterar&id=<%= u.getId() %>"> Editar </a> </td>
		</tr>
	<%}%>
	</table>
</body>
</html>
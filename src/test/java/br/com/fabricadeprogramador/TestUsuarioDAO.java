package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testExcluir();
	}
	
	public static void testCadastrar() {

		//criando o usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Fabio Fogaça");
		usuario.setLogin("fabio.fogaca10");
		usuario.setSenha("1234");
		
		//cadastrando usuario no banco de dados
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastrar(usuario);

		System.out.println("Cadastrado com sucesso!");
	}
	
	public static void testAlterar() {
		//criando o usuario
		Usuario usuario = new Usuario();
		usuario.setId(4);
		usuario.setNome("Fabio Alves");
		usuario.setLogin("fabio.fogaca");
		usuario.setSenha("1234");
		
		//alterando usuario no banco de dados
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alterar(usuario);

		System.out.println("Alterado com sucesso!");
	}
	
	public static void testExcluir() {
		Usuario usuario = new Usuario();
		usuario.setId(5);
		
		//excluindo usuario no banco de dados
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);

		System.out.println("Excluído com sucesso!");
	}
	
	

}

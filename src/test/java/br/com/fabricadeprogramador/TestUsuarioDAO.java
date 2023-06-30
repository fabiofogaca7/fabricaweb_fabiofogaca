package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testAutenticar();
	}
	
	private static void testBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(2);
		System.out.println(usuario);
	}
	
	private static void testBuscarTodosUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodosUsuarios();
		for (Usuario u : lista) {
			System.out.println(u);
		}
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
		usuario.setId(6);
		
		//excluindo usuario no banco de dados
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);

		System.out.println("Excluído com sucesso!");
	}
	
	public static void testSalvar() {
		Usuario usuario = new Usuario();
		//usuario.setId(3);
		usuario.setNome("Theodoro");
		usuario.setLogin("theo20");
		usuario.setSenha("1234");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	public static void testAutenticar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario.setLogin("fabiofogaca7");
		usuario.setSenha("1234");
		
		Usuario usuarioAutenticado = usuarioDAO.autenticar(usuario);
		
		System.out.println(usuarioAutenticado);
	}
		

}

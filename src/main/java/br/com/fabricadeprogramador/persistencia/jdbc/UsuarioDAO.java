package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection conn = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usuario) {
		String sql = "insert into usuario (nome, login, senha) values (?, ? ,?)";
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			//substituindo o '?'
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			//executando o comando sql no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void alterar(Usuario usuario) {
		String sql = "update usuario set nome =?, login=?, senha=? where id =?";
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			//substituindo o '?'
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			//executando o comando sql no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void excluir(Usuario usuario) {
		String sql = "delete from usuario where id =?";
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			//substituindo o '?'
			preparador.setInt(1, usuario.getId());
			//executando o comando sql no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void salvar(Usuario usuario) {
		if(usuario.getId() != null) {
			alterar(usuario);
			System.out.println("Alterado com sucesso!");
		} else {
			cadastrar(usuario);
			System.out.println("Cadastrado com sucesso!" );
		}
	}
	
	/**
	 * Busca de um registro no bando de dados pelo número do id do usuário
	 * @param id É um inteiro que representa o número do id do usuário a ser buscado
	 * @return Um objeto usuário quando encontra um registro ou null quando não encontra
	 */
	public Usuario buscarPorId(Integer id) {
		String sql = "select * from usuario where id = ?";
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			preparador.setInt(1, id);
			preparador.executeQuery();
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Realiza a busca de todos os registros da tabela de usuários
	 * @return Uma lista de objetos Usuario vazia, quando não tiver registros, ou n elementos quando tiver resultado
	 */
	public List<Usuario> buscarTodosUsuarios() {
		String sql = "select * from usuario";
		List<Usuario> lista = new ArrayList<Usuario>();
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				lista.add(usuario);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Usuario autenticar(Usuario usuario) {
		String sql = "Select * from usuario where login = ? and senha = ?";
		
		try (PreparedStatement preparador = conn.prepareStatement(sql)){
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()) {
				Usuario usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(resultado.getInt("id"));
				usuarioAutenticado.setNome(resultado.getString("nome"));
				usuarioAutenticado.setLogin(resultado.getString("login"));
				usuarioAutenticado.setSenha(resultado.getString("senha"));
				
				return usuarioAutenticado;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

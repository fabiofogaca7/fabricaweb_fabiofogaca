package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

// http://localhost:8080/fabricaweb/usuariocontroller
@WebServlet("/usuarioController")
public class UsuarioController extends HttpServlet {

	public UsuarioController() {
		System.out.println("Construtor");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("chamou o init");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		if (acao.equals("excluir")) {
			String id = req.getParameter("id");

			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			resp.sendRedirect("usuarioController?acao=listar");
		} else if (acao.equals("listar")) {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = usuarioDAO.buscarTodosUsuarios();
			
			req.setAttribute("listar", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaUsuario2.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("alterar")) {
			String id = req.getParameter("id");
		
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscarPorId(Integer.parseInt(id));
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formUsuario.jsp");
			dispatcher.forward(req, resp);
		}else if (acao.equals("cadastrar")) {
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formUsuario.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usuario = new Usuario();
		if (id != null) {
			usuario.setId(Integer.parseInt(id));
		}
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		resp.sendRedirect("usuarioController?acao=listar");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(id));

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);

		resp.getWriter().print("Excluído com sucesso");
	}

	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
	}
}

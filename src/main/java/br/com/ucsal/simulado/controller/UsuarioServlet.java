package br.com.ucsal.simulado.controller;

import java.io.IOException;
import java.util.List;

import br.com.ucsal.simulado.model.Papel;
import br.com.ucsal.simulado.model.Usuario;
import br.com.ucsal.simulado.service.PapelService;
import br.com.ucsal.simulado.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();
    private PapelService papelService = new PapelService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equalsIgnoreCase(action)) {
            // Excluir usuário
            String idParam = request.getParameter("id");
            int id = Integer.parseInt(idParam);
            usuarioService.excluirUsuario(id);
            response.sendRedirect("UsuarioServlet?action=list");
        } else if ("edit".equalsIgnoreCase(action)) {
            // Editar usuário
            String idParam = request.getParameter("id");
            int id = Integer.parseInt(idParam);
            List<Papel> papeis = papelService.listarPapeis();
            request.setAttribute("papeis", papeis);
            Usuario usuario = usuarioService.obterUsuarioPorId(id);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("usuarioform.jsp").forward(request, response);
        } else if ("new".equalsIgnoreCase(action)) {
            // Novo usuário
            List<Papel> papeis = papelService.listarPapeis();
            request.setAttribute("papeis", papeis);
            request.getRequestDispatcher("usuarioform.jsp").forward(request, response);
        } else {
            // Listar usuários
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("usuariolist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {
            // Criar novo usuário
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            int papelId = Integer.parseInt(request.getParameter("papelId"));
            Usuario usuario = new Usuario(0, nome, email, new Papel(papelId, null));
            usuarioService.criarUsuario(usuario);
            response.sendRedirect("UsuarioServlet?action=list");

        } else if ("update".equalsIgnoreCase(action)) {
            // Atualizar usuário existente
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            int papelId = Integer.parseInt(request.getParameter("papelId"));
            Usuario usuario = new Usuario(id, nome, email, new Papel(papelId, null));
            usuarioService.atualizarUsuario(usuario);
            response.sendRedirect("UsuarioServlet?action=list");

        } else {
            // Ação desconhecida
            response.sendRedirect("UsuarioServlet?action=list");
        }
    }
}

package br.com.ucsal.simulado.service;

import java.util.List;

import br.com.ucsal.simulado.model.Usuario;
import br.com.ucsal.simulado.persistencia.UsuarioPersistence;

public class UsuarioService {

    private UsuarioPersistence persistence = new UsuarioPersistence();

    public List<Usuario> listarUsuarios() {
        return persistence.listarUsuarios();
    }

    public Usuario obterUsuarioPorId(int id) {
        return persistence.obterUsuarioPorId(id);
    }

    public void criarUsuario(Usuario usuario) {
        persistence.adicionarUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        persistence.atualizarUsuario(usuario);
    }

    public void excluirUsuario(int id) {
        persistence.excluirUsuario(id);
    }


}

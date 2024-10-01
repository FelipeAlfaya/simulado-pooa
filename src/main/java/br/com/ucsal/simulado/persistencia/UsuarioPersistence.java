package br.com.ucsal.simulado.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.simulado.model.Papel;
import br.com.ucsal.simulado.model.Usuario;
import br.com.ucsal.simulado.util.DatabaseUtil;


	public class UsuarioPersistence {

	    public List<Usuario> listarUsuarios() {
	        List<Usuario> usuarios = new ArrayList<>();
	        String query = "SELECT id, nome, email, papel_id FROM usuario";

	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Usuario usuario = new Usuario(
	                        rs.getInt("id"),
	                        rs.getString("nome"),
	                        rs.getString("email"),
	                        new Papel(rs.getInt("papel_id"),null)
	                );
	                usuarios.add(usuario);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return usuarios;
	    }

	    public Usuario obterUsuarioPorId(int id) {
	        String query = "SELECT id, nome, email, papel_id FROM usuario WHERE id = ?";
	        Usuario usuario = null;

	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {

	                if (rs.next()) {
	                    usuario = new Usuario(
	                            rs.getInt("id"),
	                            rs.getString("nome"),
	                            rs.getString("email"),
	                            new Papel(rs.getInt("papel_id"),null)
	                    );
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return usuario;
	    }

	    public void adicionarUsuario(Usuario usuario) {
	        String query = "INSERT INTO usuario (nome, email, papel_id) VALUES (?, ?, ?)";

	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	            stmt.setString(1, usuario.getNome());
	            stmt.setString(2, usuario.getEmail());
	            stmt.setInt(3, usuario.getPapel().getId());
	            stmt.executeUpdate();

	            // Obter o ID gerado
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    usuario.setId(generatedKeys.getInt(1));
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void atualizarUsuario(Usuario usuario) {
	        String query = "UPDATE usuario SET nome = ?, email = ?, papel_id = ? WHERE id = ?";

	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, usuario.getNome());
	            stmt.setString(2, usuario.getEmail());
	            stmt.setInt(3, usuario.getPapel().getId());
	            stmt.setInt(4, usuario.getId());
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void excluirUsuario(int id) {
	        String query = "DELETE FROM usuario WHERE id = ?";

	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setInt(1, id);
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

    


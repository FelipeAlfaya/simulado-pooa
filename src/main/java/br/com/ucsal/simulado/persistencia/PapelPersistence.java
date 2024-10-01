package br.com.ucsal.simulado.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.simulado.model.Papel;
import br.com.ucsal.simulado.util.DatabaseUtil;

public class PapelPersistence {

    public List<Papel> listarPapeis() {
        List<Papel> papeis = new ArrayList<>();
        String query = "SELECT id, nome FROM papel";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Papel papel = new Papel(rs.getInt("id"), rs.getString("nome"));
                papeis.add(papel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return papeis;
    }

    public Papel obterPapelPorId(int id) {
        String query = "SELECT id, nome FROM papel WHERE id = ?";
        Papel papel = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    papel = new Papel(rs.getInt("id"), rs.getString("nome"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return papel;
    }

}

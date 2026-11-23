package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Usuario;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository 
@Profile("jdbc")
public class UsuarioRepositoryJdbc implements IUsuarioRepository {
    private final DataSource dataSource;

    public UsuarioRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, email) VALUES (?, ?)";

        try (Connection conexao = this.dataSource.getConnection()) {
            PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.executeUpdate();

            try (ResultSet rs = comando.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }

            return usuario;

        } catch (SQLException erro) {
            throw new IllegalStateException("Falha ao salvar o usuario", erro);
        }
    }

    @Override 
    public Optional<Usuario> buscarPorId(Long id) {
        return this.buscarUm("SELECT * FROM usuario WHERE id = ?", id);
    }

    @Override 
    public Optional<Usuario> buscarPorEmail(String email) {
        return this.buscarUm("SELECT * FROM usuario WHERE LOWER(email) = LOWER(?)", email);
    }

    @Override 
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuario ORDER BY nome";

        try (Connection conexao = this.dataSource.getConnection()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();

            while(resultado.next()) {
                usuarios.add(this.mapearUsuarios(resultado));
            }

            return usuarios;

        } catch (SQLException erro) {
            throw new IllegalStateException("Falha ao listar usuário", erro);
        }
    }

    private Optional<Usuario> buscarUm(String sql, Object parametro) {
        try (Connection conexao = this.dataSource.getConnection()) {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setObject(1, parametro);

            try (ResultSet rs = comando.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(this.mapearUsuarios(rs));
                }

                return Optional.empty();
            }
        
        } catch (SQLException erro) {
            throw new IllegalStateException("Falha ao consultar usuário", erro);
        }
    }

    private Usuario mapearUsuarios(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario(resultado.getString("nome"), resultado.getString("email"));
        usuario.setId(resultado.getLong("id"));
        return usuario;
    }
}

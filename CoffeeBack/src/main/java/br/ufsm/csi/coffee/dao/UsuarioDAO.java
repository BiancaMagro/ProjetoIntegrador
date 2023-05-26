package br.ufsm.csi.coffee.dao;

import br.ufsm.csi.coffee.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<Usuario> getUsers(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM usuarios";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setNome(this.resultSet.getString("nome"));
                usuario.setCpf(this.resultSet.getString("cpf"));
                usuario.setLogin(this.resultSet.getString("login"));
                usuario.setSenha(this.resultSet.getString("senha"));
                usuario.setFuncao(new FuncaoDAO().getFuncao(this.resultSet.getInt("funcao")));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void setUser(Usuario usuario) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO usuarios (nome, cpf, login, senha, funcao) VALUES (?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getCpf());
            this.preparedStatement.setString(3, usuario.getLogin());
            this.preparedStatement.setString(4, usuario.getSenha());
            this.preparedStatement.setInt(5, usuario.getFuncao().getCodigo());
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario getUser(String login) {
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM usuarios WHERE login = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, login);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setLogin(this.resultSet.getString("login"));
                usuario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));
                usuario.setFuncao(new FuncaoDAO().getFuncao(this.resultSet.getInt("funcao")));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario getUsuario(String login) {
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM usuarios WHERE login = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, login);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setNome(this.resultSet.getString("nome"));
                usuario.setCpf(this.resultSet.getString("cpf"));
                usuario.setLogin(this.resultSet.getString("login"));
                usuario.setSenha(this.resultSet.getString("senha"));
                usuario.setFuncao(new FuncaoDAO().getFuncao(this.resultSet.getInt("funcao")));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editar(Usuario usuario, String login) {

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update usuarios set nome = ?, cpf = ?, login = ?, senha = ?, funcao = ?  where login = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getCpf());
            this.preparedStatement.setString(3, usuario.getLogin());
            this.preparedStatement.setString(4, usuario.getSenha());
            this.preparedStatement.setInt(5, usuario.getFuncao().getCodigo());
            this.preparedStatement.setString(6, login);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String login) {
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "delete from usuarios where login = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, login);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

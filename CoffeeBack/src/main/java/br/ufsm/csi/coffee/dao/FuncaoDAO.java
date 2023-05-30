package br.ufsm.csi.coffee.dao;

import br.ufsm.csi.coffee.model.Funcao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncaoDAO {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public Funcao getFuncao(int codigo) {
        Funcao funcao = new Funcao();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select * from funcao where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                funcao.setCodigo(this.resultSet.getInt("codigo"));
                funcao.setFuncao(this.resultSet.getString("funcao"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return funcao;
    }

    public ArrayList<Funcao> getFuncoes(){
        ArrayList<Funcao> funcoes = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM funcao";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcao funcao = new Funcao();
                funcao.setCodigo(this.resultSet.getInt("codigo"));
                funcao.setFuncao(this.resultSet.getString("funcao"));
                funcoes.add(funcao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcoes;
    }
}
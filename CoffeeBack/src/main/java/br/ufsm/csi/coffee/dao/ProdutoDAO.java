package br.ufsm.csi.coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufsm.csi.coffee.model.Produto;

public class ProdutoDAO {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<Produto> getProdutos(){
        ArrayList<Produto> produtos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM produtos where ativo = true";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Produto produto = new Produto();
                produto.setId(this.resultSet.getInt("codigo"));
                produto.setNome(this.resultSet.getString("nome"));
                produto.setDescricao(this.resultSet.getString("descricao"));
                produto.setPreco(this.resultSet.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto getById(int id){
        Produto produto = new Produto();
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select * from produtos where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                produto.setId(this.resultSet.getInt("codigo"));
                produto.setNome(this.resultSet.getString("nome"));
                produto.setDescricao(this.resultSet.getString("descricao"));
                produto.setPreco(this.resultSet.getDouble("preco"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    public ArrayList<Produto> getAllProdutos(){
        ArrayList<Produto> produtos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM produtos";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Produto produto = new Produto();
                produto.setId(this.resultSet.getInt("codigo"));
                produto.setNome(this.resultSet.getString("nome"));
                produto.setDescricao(this.resultSet.getString("descricao"));
                produto.setPreco(this.resultSet.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void addProduto(Produto produto){
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "insert into produtos (nome, descricao, preco, ativo) values (?, ?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome());
            this.preparedStatement.setString(2, produto.getDescricao());
            this.preparedStatement.setDouble(3, produto.getPreco());
            this.preparedStatement.setBoolean(4, produto.isAtivo());
            this.preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editaProduto(Produto produto, int id){
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "update produtos set nome = ?, descricao = ?, preco = ? where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, produto.getNome());
            this.preparedStatement.setString(2, produto.getDescricao());
            this.preparedStatement.setDouble(3, produto.getPreco());
            this.preparedStatement.setInt(4, id);
            this.preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduto(int id){
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "Update produtos set ativo = false where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

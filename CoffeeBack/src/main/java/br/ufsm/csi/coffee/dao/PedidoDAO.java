package br.ufsm.csi.coffee.dao;

import br.ufsm.csi.coffee.model.LogPedidos;
import br.ufsm.csi.coffee.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<LogPedidos> getLogPedidos(){
        ArrayList<LogPedidos> logPedidos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM comanda";

            this.preparedStatement = connection.prepareStatement(this.sql); 
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                LogPedidos logPedido = new LogPedidos();
                logPedido.setPedido(new PedidoDAO().getPedido(this.resultSet.getInt("pedido")));
                //logPedido.setProduto(new ProdutoDAO().getProduto(this.resultSet.getInt("produto")));
                logPedidos.add(logPedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logPedidos;
    }

    public ArrayList<Pedido> getPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM pedidos where status != 3";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Pedido pedido = new Pedido();
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
                pedido.setDescricao(this.resultSet.getString("descricao"));
                pedido.setNome(this.resultSet.getString("nome"));
                pedido.setMesa(this.resultSet.getInt("mesa"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public ArrayList<Pedido> getCozinha(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM pedidos where status = 1";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Pedido pedido = new Pedido();
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
                pedido.setDescricao(this.resultSet.getString("descricao"));
                pedido.setNome(this.resultSet.getString("nome"));
                pedido.setMesa(this.resultSet.getInt("mesa"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void setPedido(Pedido pedido) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO pedidos (mesa, descricao, nome, status) VALUES (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, pedido.getMesa());
            this.preparedStatement.setString(2, pedido.getDescricao());
            this.preparedStatement.setString(3, pedido.getNome());
            this.preparedStatement.setInt(4, pedido.getStatus().getCodigo());
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pedido getPedido(int codigo) {
        Pedido pedido = new Pedido();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select * from pedidos where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setMesa(this.resultSet.getInt("mesa"));
                pedido.setDescricao(this.resultSet.getString("descricao"));
                pedido.setNome(this.resultSet.getString("nome"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    public void editar(Pedido pedido, int id) {

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update pedidos set descricao = ?, status = ?, nome = ?  where codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, pedido.getDescricao());
            this.preparedStatement.setInt(2, pedido.getStatus().getCodigo());
            this.preparedStatement.setString(3, pedido.getNome());
            this.preparedStatement.setInt(4, id);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection connection = new ConectaDB().getConexao()) {

            this.sql = "delete from pedidos where codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

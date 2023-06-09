package br.ufsm.csi.coffee.dao;

import br.ufsm.csi.coffee.model.Comanda;
import br.ufsm.csi.coffee.model.LogView;
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

    public ArrayList<Pedido> getPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM pedido where status != 3 order by codigo desc";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Pedido pedido = new Pedido();
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setComanda(this.resultSet.getInt("comanda"));
                pedido.setProduto(new ProdutoDAO().getById(this.resultSet.getInt("produto")));
                pedido.setQuantidade(this.resultSet.getInt("quantidade"));
                pedido.setDataPedido(this.resultSet.getString("data_pedido"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
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
            this.sql = "SELECT * FROM pedido where status in (1,2) order by codigo asc";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Pedido pedido = new Pedido();
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setComanda(this.resultSet.getInt("comanda"));
                pedido.setProduto(new ProdutoDAO().getById(this.resultSet.getInt("produto")));
                pedido.setQuantidade(this.resultSet.getInt("quantidade"));
                pedido.setDataPedido(this.resultSet.getString("data_pedido"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void setPedido(Pedido pedido) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO pedido (data_pedido, quantidade, produto, comanda, status) VALUES (?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
        
            this.preparedStatement.setString(1, pedido.getDataPedido());
            this.preparedStatement.setInt(2, pedido.getQuantidade());
            this.preparedStatement.setInt(3, pedido.getProduto().getId());
            this.preparedStatement.setInt(4, pedido.getComanda());
            this.preparedStatement.setInt(5, pedido.getStatus().getCodigo());

            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pedido getPedido(int codigo) {
        Pedido pedido = new Pedido();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select * from pedido where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setComanda(this.resultSet.getInt("comanda"));
                pedido.setProduto(new ProdutoDAO().getById(this.resultSet.getInt("produto")));
                pedido.setQuantidade(this.resultSet.getInt("quantidade"));
                pedido.setDataPedido(this.resultSet.getString("data_pedido"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    public void editar(Pedido pedido, int id) {

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update pedido set quantidade = ?, status = ? where codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, pedido.getQuantidade());
            this.preparedStatement.setInt(2, pedido.getStatus().getCodigo());
            this.preparedStatement.setInt(3, id);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "delete from pedido where codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Comanda> getComandas(){
        ArrayList<Comanda> comandas = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM comanda where ativo = true order by codigo desc";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Comanda comanda = new Comanda();
                comanda.setCodigo(this.resultSet.getInt("codigo"));
                comanda.setMesa(this.resultSet.getInt("mesa"));
                comanda.setCliente(this.resultSet.getString("cliente"));
                comanda.setData_criada(this.resultSet.getString("data_criada"));
                comanda.setAtivo(this.resultSet.getBoolean("ativo"));
                comandas.add(comanda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comandas;
    }

    public void addComanda(Comanda comanda){
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO comanda (mesa, cliente, data_criada, ativo) VALUES (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, comanda.getMesa());
            this.preparedStatement.setString(2, comanda.getCliente());
            this.preparedStatement.setString(3, comanda.getData_criada());
            this.preparedStatement.setBoolean(4, true);
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pedido> getPedidos(int idComanda){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select * from pedido where comanda = ? order by codigo desc";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, idComanda);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodigo(this.resultSet.getInt("codigo"));
                pedido.setComanda(this.resultSet.getInt("comanda"));
                pedido.setProduto(new ProdutoDAO().getById(this.resultSet.getInt("produto")));
                pedido.setQuantidade(this.resultSet.getInt("quantidade"));
                pedido.setDataPedido(this.resultSet.getString("data_pedido"));
                pedido.setStatus(new StatusDAO().getStatus(this.resultSet.getInt("status")));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public ArrayList<LogView> getLogView(){
        ArrayList<LogView> logViews = new ArrayList<>();
        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select c.cliente as cliente, p.nome as produto, ped.data_pedido as data, ped.quantidade as quantidade, ped.quantidade * p.preco as total from pedido ped inner join produtos p on ped.produto = p.codigo inner join comanda c on ped.comanda = c.codigo where ped.status = 4 order by ped.codigo desc";
            System.out.println("LOG DAO");
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                LogView logView = new LogView();
                logView.setCliente(this.resultSet.getString("cliente"));
                logView.setProduto(this.resultSet.getString("produto"));
                logView.setData(this.resultSet.getString("data"));
                logView.setQuantidade(this.resultSet.getInt("quantidade"));
                logView.setValor(this.resultSet.getDouble("total"));
                logViews.add(logView);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logViews;
    }

    public void fechaComanda(int codigo){
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update comanda set ativo = false where codigo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

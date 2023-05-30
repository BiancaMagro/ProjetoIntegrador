package br.ufsm.csi.coffee.dao;

import br.ufsm.csi.coffee.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDAO {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public Status getStatus(int codigo) {
        Status status = new Status();

        try(Connection connection = new ConectaDB().getConexao()){
            this.sql = "select * from status where codigo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                status.setCodigo(this.resultSet.getInt("codigo"));
                status.setStatus(this.resultSet.getString("status"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public ArrayList<Status> getStatuses(){
        ArrayList<Status> statuses = new ArrayList<>();
        try (Connection connection = new ConectaDB().getConexao()){
            this.sql = "SELECT * FROM status";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Status status = new Status();
                status.setCodigo(this.resultSet.getInt("codigo"));
                status.setStatus(this.resultSet.getString("status"));
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}

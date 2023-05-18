package br.ufsm.csi.coffee.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/ProjetoJava";
    private static final String USER = "postgres";
    private static final String SENHA = "1234";

    public Connection getConexao(){
        Connection con = null;
        try {Class.forName(ConectaDB.DRIVER);
            con = DriverManager.getConnection(ConectaDB.URL, ConectaDB.USER, ConectaDB.SENHA);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}

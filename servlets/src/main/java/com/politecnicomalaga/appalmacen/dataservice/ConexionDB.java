package com.politecnicomalaga.appalmacen.dataservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://192.168.1.6:3307/almacen";
    private static final String USER = "almacen_user";
    private static final String PASSWORD = "onlyforyoureyes";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

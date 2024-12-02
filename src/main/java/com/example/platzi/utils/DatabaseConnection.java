package com.example.platzi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/project";
  private static final String USER = "root";
  private static final String PWD = "admin";
  private static Connection connection = null;

  private DatabaseConnection() {
  }

  public static Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
      }
    } catch (ClassNotFoundException | SQLException e) {
      connection = null;
    }

    return connection;
  }
}

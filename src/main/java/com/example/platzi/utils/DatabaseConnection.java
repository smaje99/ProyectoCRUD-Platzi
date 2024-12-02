package com.example.platzi.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/project";
  private static final String USER = "root";
  private static final String PWD = "admin";
  private static BasicDataSource pool = null;

  private DatabaseConnection() {
  }

  private static BasicDataSource getPool() {
    if (pool == null || pool.isClosed()) {
      pool = new BasicDataSource();
      pool.setUrl(URL);
      pool.setUsername(USER);
      pool.setPassword(PWD);

      pool.setInitialSize(3);
      pool.setMinIdle(2);
      pool.setMaxIdle(10);
      pool.setMaxTotal(10);
    }

    return pool;
  }

  public static Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      return getPool().getConnection();
    } catch (SQLException | ClassNotFoundException e) {
      return null;
    }
  }
}

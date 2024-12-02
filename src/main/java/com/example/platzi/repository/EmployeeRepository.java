package com.example.platzi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.platzi.models.Employee;

public class EmployeeRepository implements Repository<Employee> {
  private static final String SELECT_EMPLOYEES = """
    SELECT id, first_name, pa_surname, ma_surname, email, salary, curp
    FROM employees;
  """;
  private static final String SELECT_EMPLOYEE = """
    SELECT id, first_name, pa_surname, ma_surname, email, salary, curp
    FROM employees
    WHERE id = ?;
  """;
  private static final String INSERT_EMPLOYEE = """
    INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp)
    VALUES (?, ?, ?, ?, ?, ?);
  """;
  private static final String UPDATE_EMPLOYEE = """
    UPDATE employees
    SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ?, curp = ?
    WHERE id = ?;
  """;
  private static final String DELETE_EMPLOYEE = """
    DELETE FROM employees
    WHERE id = ?;
  """;

  private final Connection connection;

  public EmployeeRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Employee findById(final Integer id) {
    Employee employee = null;

    try (
      PreparedStatement statement = this.connection.prepareStatement(SELECT_EMPLOYEE);
    ) {
      statement.setInt(1, id);

      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          employee = mapEmployee(resultSet);
        }
      }
    } catch (SQLException e) {
      employee = null;
    }

    return employee;
  }

  @Override
  public List<Employee> findAll() {
    final List<Employee> employees = new ArrayList<>();

    try (
      Statement statement = this.connection.createStatement();
      ResultSet resultSet = statement.executeQuery(SELECT_EMPLOYEES);
    ) {
      while (resultSet.next()) {
        employees.add(mapEmployee(resultSet));
      }
    } catch (SQLException e) {
      employees.clear();
    }

    return employees;
  }

  @Override
  public boolean save(final Employee employee) {
    try (
      PreparedStatement statement = this.connection.prepareStatement(INSERT_EMPLOYEE);
    ) {
      statement.setString(1, employee.firstName());
      statement.setString(2, employee.paSurname());
      statement.setString(3, employee.maSurname());
      statement.setString(4, employee.email());
      statement.setFloat(5, employee.salary());

      var result = statement.executeUpdate();

      return result == 1;
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public boolean update(final Employee t) {
    try(
      PreparedStatement statement = this.connection.prepareStatement(UPDATE_EMPLOYEE);
    ) {
      statement.setString(1, t.firstName());
      statement.setString(2, t.paSurname());
      statement.setString(3, t.maSurname());
      statement.setString(4, t.email());
      statement.setFloat(5, t.salary());
      statement.setInt(6, t.id());

      var result = statement.executeUpdate();

      return result == 1;
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public boolean delete(final Employee t) {
    try (
      PreparedStatement statement = this.connection.prepareStatement(DELETE_EMPLOYEE);
    ) {
      statement.setInt(1, t.id());

      var result = statement.executeUpdate();

      return result == 1;
    } catch (SQLException e) {
      return false;
    }
  }

  private Employee mapEmployee(final ResultSet resultSet) {
    try {
      return new Employee(
        resultSet.getInt("id"),
        resultSet.getString("first_name"),
        resultSet.getString("pa_surname"),
        resultSet.getString("ma_surname"),
        resultSet.getString("email"),
        resultSet.getFloat("salary"),
        resultSet.getString("curp")
      );
    } catch (SQLException e) {
      return null;
    }
  }
}

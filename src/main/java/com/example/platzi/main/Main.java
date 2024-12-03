package com.example.platzi.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.platzi.jpa.entity.EmployeeEntity;
import com.example.platzi.jpa.util.UtilEntity;
import com.example.platzi.models.Employee;
import com.example.platzi.repository.EmployeeRepository;
import com.example.platzi.repository.Repository;
import com.example.platzi.utils.DatabaseConnection;

import jakarta.persistence.EntityManager;

public class Main {
  public static void main(String[] args) {
    // doEmployeesFindAll();
    // doEmployeeFindById();
    // doEmployeeSave();
    // doEmployeeUpdate();
    // doEmployeeDelete();
    // doTransaction();
    // doEmployeesFindAllWithPool();
    doEmployeesOperationsWithJPA();
  }

  private static void doEmployeesFindAll() {
    /* Connection connection = DatabaseConnection.getConnection();
    Repository<Employee> repository = new EmployeeRepository(connection); */

    Repository<Employee> repository = new EmployeeRepository();

    List<Employee> employees = repository.findAll();
    employees.forEach(System.out::println);
  }

  private static void doEmployeeFindById() {
    /* Connection connection = DatabaseConnection.getConnection();
    Repository<Employee> repository = new EmployeeRepository(connection); */

    Repository<Employee> repository = new EmployeeRepository();

    Employee employee = repository.findById(6);
    System.out.println(employee);
  }

  private static void doEmployeeSave() {
    /* Connection connection = DatabaseConnection.getConnection();
    Repository<Employee> repository = new EmployeeRepository(connection); */

    Repository<Employee> repository = new EmployeeRepository();

    Employee employee = new Employee(
      null,
      "Juan",
      "Pérez",
      "Gómez",
      "j.perez@email.com",
      100_000.0f,
      "NENFEUHF43843FJFJ"
    );

    boolean isCreated = repository.save(employee);
    System.out.println(isCreated ? "Employee created" : "Employee not created");
  }

  private static void doEmployeeUpdate() {
    /* Connection connection = DatabaseConnection.getConnection();
    Repository<Employee> repository = new EmployeeRepository(connection); */

    Repository<Employee> repository = new EmployeeRepository();

    Employee employee = repository.findById(6);
    Employee newEmployee = new Employee(
      employee.id(),
      employee.firstName(),
      employee.paSurname(),
      "Franco",
      "smajefranco@gmail.com",
      1_000_000.0f,
      "NEN487HF43843FJFJ"
    );

    boolean isUpdated = repository.update(newEmployee);
    System.out.println(isUpdated ? "Employee updated" : "Employee not updated");
  }

  private static void doEmployeeDelete() {
    /* Connection connection = DatabaseConnection.getConnection();
    Repository<Employee> repository = new EmployeeRepository(connection); */

    Repository<Employee> repository = new EmployeeRepository();

    Employee employee = repository.findById(8);
    boolean isDeleted = repository.delete(employee);
    System.out.println(isDeleted ? "Employee deleted" : "Employee not deleted");
  }

  private static void doTransaction() {
    Connection connection = DatabaseConnection.getConnection();

    try {
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      /* Repository<Employee> repository = new EmployeeRepository(connection); */
      Repository<Employee> repository = new EmployeeRepository();

      Employee employee = new Employee(
        null,
        "America",
        "Lopez",
        "Villa",
        "ame2@example.com",
        3_000f,
        "AMEC376EF542EFTRMJ"
      );

      repository.save(employee);

      connection.commit();
    } catch (SQLException e) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          System.out.println("Error trying to rollback");
        }
    }
  }

  private static void doEmployeesFindAllWithPool() {
    // Implementación del Pool de Conexiones
    Repository<Employee> repository = new EmployeeRepository();

    System.out.println("All employees");
    repository.findAll().forEach(System.out::println);

    System.out.println("Employee with id 6");
    System.out.println(repository.findById(6));
  }

  private static void doEmployeesOperationsWithJPA() {
    EntityManager entityManager = UtilEntity.getEntityManager();

    // Find all employees
    System.out.println("All employees");

    List<EmployeeEntity> employees = entityManager.createQuery(
      "SELECT e FROM EmployeeEntity e",
      EmployeeEntity.class
    ).getResultList();

    employees.forEach(System.out::println);

    // Find employee by id
    System.out.println("Employee with id 6");
    int employeeId = 6;
    EmployeeEntity employee = entityManager.find(EmployeeEntity.class, employeeId);
    System.out.println(employee);

    // Save new employee
    /* EmployeeEntity newEmployee = new EmployeeEntity(
      "America",
      "Lopez",
      "Villa",
      "america.lopez@mail.com",
      3_000f
    );
    entityManager.getTransaction().begin();
    entityManager.persist(newEmployee);
    entityManager.getTransaction().commit();

    System.out.println(newEmployee); */

    // Update employee
    /* int employeeToUpdateId = 9;
    EmployeeEntity employeeToUpdate = entityManager.find(
      EmployeeEntity.class,
      employeeToUpdateId
    );
    System.out.println("Employee to update: " + employeeToUpdate);

    employeeToUpdate.setSalary(5_000f);

    entityManager.getTransaction().begin();
    entityManager.merge(employeeToUpdate);
    entityManager.getTransaction().commit();

    System.out.println("Employee updated: " + employeeToUpdate); */

    // Delete employee
    int employeeToDeleteId = 9;
    EmployeeEntity employeeToDelete = entityManager.find(
      EmployeeEntity.class,
      employeeToDeleteId
    );
    System.out.println("Employee to delete: " + employeeToDelete);

    entityManager.getTransaction().begin();
    entityManager.remove(employeeToDelete);
    entityManager.getTransaction().commit();
    entityManager.close();

    System.out.println("Employee deleted: " + employeeToDelete);
  }
}

package com.example.platzi.main;

public class Main {
  public static void main(String[] args) {
    /*
     * Connection connection = DatabaseConnection.getConnection();
     * Repository<Employee> repository = new EmployeeRepository(connection);
     * List<Employee> employees = repository.findAll();
     * employees.forEach(System.out::println);
     */

    /*
     * Employee employee = repository.findById(6);
     * System.out.println(employee);
     */

    /*
     * Employee employee = new Employee(
     * null,
     * "Juan",
     * "Pérez",
     * "Gómez",
     * "j.perez@email.com",
     * 100_000.0f
     * );
     * var isCreated = repository.save(employee);
     * System.out.println(isCreated ? "Employee created" : "Employee not created");
     */

    /*
     * var employee = repository.findById(6);
     * var newEmployee = new Employee(
     * employee.id(),
     * employee.firstName(),
     * employee.paSurname(),
     * "Franco",
     * "smajefranco@gmail.com",
     * 1_000_000.0f
     * );
     * var isUpdated = repository.update(newEmployee);
     * System.out.println(isUpdated ? "Employee updated" : "Employee not updated");
     */

    /*
     * var employee = repository.findById(8);
     * var isDeleted = repository.delete(employee);
     * System.out.println(isDeleted ? "Employee deleted" : "Employee not deleted");
     */

    /* Connection connection = DatabaseConnection.getConnection();

    try {
      if (connection.getAutoCommit()) {
        connection.setAutoCommit(false);
      }

      Repository<Employee> repository = new EmployeeRepository(connection);

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
    } */

    
  }
}

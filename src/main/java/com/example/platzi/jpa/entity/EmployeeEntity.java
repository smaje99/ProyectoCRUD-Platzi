package com.example.platzi.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "pa_surname")
  private String paSurname;

  @Column(name = "ma_surname")
  private String maSurname;

  @Column(name = "email")
  private String email;

  @Column(name = "salary")
  private Float salary;

  public EmployeeEntity() {
  }

  public EmployeeEntity(
    String firstName,
    String paSurname,
    String maSurname,
    String email,
    Float salary
  ) {
    this.firstName = firstName;
    this.paSurname = paSurname;
    this.maSurname = maSurname;
    this.email = email;
    this.salary = salary;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPaSurname() {
    return paSurname;
  }

  public void setPaSurname(String paSurname) {
    this.paSurname = paSurname;
  }

  public String getMaSurname() {
    return maSurname;
  }

  public void setMaSurname(String maSurname) {
    this.maSurname = maSurname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Float getSalary() {
    return salary;
  }

  public void setSalary(Float salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", paSurname='" + paSurname + '\'' +
      ", maSurname='" + maSurname + '\'' +
      ", email='" + email + '\'' +
      ", salary=" + salary +
      '}';
  }
}

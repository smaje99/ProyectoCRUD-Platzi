package com.example.platzi.models;

public record Employee(
  Integer id,
  String firstName,
  String paSurname,
  String maSurname,
  String email,
  Float salary,
  String curp
) {
}

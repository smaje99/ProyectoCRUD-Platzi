package com.example.platzi.repository;

import java.util.List;

public interface Repository<T> {
  T findById(final Integer id);
  List<T> findAll();
  boolean save(final T t);
  boolean update(final T t);
  boolean delete(final T t);
}

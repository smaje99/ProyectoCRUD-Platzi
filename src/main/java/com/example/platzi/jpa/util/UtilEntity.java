package com.example.platzi.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtilEntity {
  private UtilEntity() {
    // Private constructor to hide the implicit public one
  }
  private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

  private static EntityManagerFactory buildEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("PersistenceUnit");
  }

  public static EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();
  }
}

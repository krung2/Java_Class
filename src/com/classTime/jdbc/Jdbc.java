package com.classTime.jdbc;

public class Jdbc {

  public static void main(String[] args) {

    try {

      Class.forName("org.mariadb.jdbc.Driver");
      System.out.println("Success!");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}

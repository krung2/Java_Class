package com.classTime.variable;

public class VariableStudy {


  static String name;

  public void method1 (String param) {
    int local = 3;

    if (3 == local) {
      int a = 1;
    } else {
      int a = 2;
    }

    int a = 1;
  }

  public void method2 (String param) {
    int local = 5;
  }

  public static void main(String[] args) {
    VariableStudy study1 = new VariableStudy();
    VariableStudy study2 = new VariableStudy();

    VariableStudy.name = "홍길동";
    study2.name = "이순신";

    System.out.println(study1.name);
  }
}

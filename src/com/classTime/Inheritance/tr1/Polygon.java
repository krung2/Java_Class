package com.classTime.Inheritance.tr1;

public abstract class Polygon {

  public abstract double getArea();

  public abstract String getName();

  public void printArea() {
    System.out.println(getName() + "의 면적 : " + getArea());
  }

}

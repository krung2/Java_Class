package com.classTime.Inheritance.tr1;

public class Circle extends Polygon{

  private double radius;
  private final double PI = 3.141592;

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double getPI() {
    return PI;
  }

  @Override
  public double getArea() {
    return radius * radius * PI;
  }

  @Override
  public String getName() {
    return "원";
  }

  public static void main(String[] args) {

    Scanner_ scanner_ = new Scanner_();
    Circle circle = new Circle();

    System.out.println("반지름의 길이를 입력해 주세요");
    circle.setRadius(scanner_.getValue());

    circle.printArea();
  }

}

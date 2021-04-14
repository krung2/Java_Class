package com.classTime.Inheritance.tr1;

public class Triangle extends Polygon {

  private double bottom;
  private double height;

  public double getBottom() {
    return bottom;
  }

  public void setBottom(double bottom) {
    this.bottom = bottom;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public double getArea() {
    return bottom * height / 2;
  }

  @Override
  public String getName() {
    return "삼각형";
  }


  public static void main(String[] args) {

    Scanner_ scanner_ = new Scanner_();
    Triangle triangle = new Triangle();

    System.out.println("밑변의 길이를 입력해 주세요");
    triangle.setBottom(scanner_.getValue());
    System.out.println("높이의 길이를 입력해 주세요");
    triangle.setHeight(scanner_.getValue());

    triangle.printArea();
  }
}

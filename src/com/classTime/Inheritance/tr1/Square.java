package com.classTime.Inheritance.tr1;

public class Square extends Polygon{

  private int bottom;
  private int height;

  public int getBottom() {
    return bottom;
  }

  public void setBottom(int bottom) {
    this.bottom = bottom;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public double getArea() {
    return bottom * height;
  }

  @Override
  public String getName() {
    return "사각형";
  }

  public static void main(String[] args) {

    Scanner_ scanner_ = new Scanner_();
    Square square = new Square();

    System.out.println("밑변의 길이를 입력해주세요");
    square.setBottom(scanner_.getValue());
    System.out.println("높이의 길이를 입력해주세요");
    square.setHeight(scanner_.getValue());

    square.printArea();
  }
}

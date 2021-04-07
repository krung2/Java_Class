package com.classTime.Inheritance;

public class Animal {

  protected String name;
  private String food;

  public void eat() {
    System.out.println(getName() + "이(가) " + getFood() + "을(를) 먹는다");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public static void main(String[] args) {

    Animal cat = new Animal();
    cat.setName("고양이");
    cat.setFood("고기");
  }
}

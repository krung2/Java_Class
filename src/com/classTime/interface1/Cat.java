package com.classTime.interface1;

public class Cat implements Animal {

  @Override
  public void eat() {

    System.out.println("생선을 먹습니다.");
  }

  @Override
  public void makeSound() {

    System.out.println("야옹");
  }
}

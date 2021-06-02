package com.classTime.interface1;

public class Dog implements Animal {

  @Override
  public void eat () {
    System.out.println("사료");
  }

  @Override
  public void makeSound () {
    System.out.println("멍멍");
  }
}
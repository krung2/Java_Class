package com.classTime.abstractClass;

public abstract class Animal {

  public String getName() {
    return "동물";
  }

  public abstract int getCountOfLegs();

  public static void main(String[] args) {
    Animal animal = new Dog();

    animal.getName();
    animal.getCountOfLegs();
  }
}

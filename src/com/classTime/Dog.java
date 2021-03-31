package com.classTime;

public class Dog {

  String name;
  int age;
  String gender;

  public Dog() {}

  public Dog(String name) {
    this.name = name;
  }

  public Dog(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Dog(String name, int age, String gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public void bark() {

  }

  public void run() {

  }

  public void eat(String food) {
    System.out.println(name + "가 " + food + "를 먹습니다");
  }

  public static void main(String[] args) {

    Dog mesi = new Dog();
    Dog snoopy = new Dog();
    Dog nardo = new Dog("호날두");

    mesi.name = "mesi";
    mesi.age = 3;
    mesi.gender = "F";

    snoopy.name = "snoopy";
    snoopy.age = 10;
    snoopy.gender = "M";

    mesi.eat("사료");
    snoopy.eat("고기");
    nardo.eat("골");

  }
}

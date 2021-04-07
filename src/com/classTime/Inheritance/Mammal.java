package com.classTime.Inheritance;

public class Mammal extends Animal {

  public void birthBaby() {
    System.out.println(getName() + "이(가) 새끼를 낳는다.");
  }

  public void eat() {
    System.out.println(getName() + "이(가) 아기 때에는 젖을 먹습니다.");
    super.eat();
  }


  public static void main(String[] args) {

    Mammal hippo = new Mammal();
    hippo.setName("하마");
    hippo.setFood("물");

    hippo.eat();

    Animal dog = new Mammal();
//    Mammal mouse = new Animal();  허용 안됨

//    dog.birthBaby();  Animal타입에는 birthBaby라는 메소드가 없음
      dog.eat();
  }
}

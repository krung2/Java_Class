package com.classTime.array;

import com.classTime.Dog;

import java.util.Random;

public class ArrayStudy {

  public static void main(String[] args) {

    int[] array = new int[5];
    array[0] = 3;
    array[3] = 5;
//    array[5] = 8;

    System.out.println(array[1]);
    System.out.println("배열의 크기 : " + array.length + "\n");

    Random random = new Random();

    for (int i = 0; i < array.length;i ++) {
      array[i] = random.nextInt(1000);
    }

    for (int value : array) {
      System.out.println(value);
    }

    Dog[] dogArray = new Dog[7];

    System.out.println(dogArray[0]);
    dogArray[0] = new Dog();
  }

}

package com.classTime.array;

import java.util.Random;

public abstract class Sort {

  private static final int SIZE = 100;

  protected int[] array = new int[SIZE];


  public void fillValues() {
    Random random = new Random();

    for (int i = 0;i < array.length; i ++) {
      array[i] = random.nextInt(10000);
    }
  }

  public void printArray() {
      for (int i = 0;i < array.length; i ++) {
        System.out.println(i + " : " + array[i]);
      }
  }

  protected void swap (int index1,int index2) {
    int tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

  public abstract void sort();

}

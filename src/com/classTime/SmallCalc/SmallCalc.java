package com.classTime.SmallCalc;

public class SmallCalc {

  int[] values;
  int arraySize;

  public SmallCalc (int arraySize) {
    this.values = new int[arraySize];
    this.arraySize = arraySize;
  }

  public void addNumber (int scanValue, int array) {
    this.values[array] = scanValue;
  }

  public void avg () {
    float sum = 0;

    for(int i = 0;i < arraySize;i ++) {
      sum += values[i];
    }

    System.out.println("avg is " + sum / arraySize);
  }

  public void big () {
    int big = values[0];

    for(int i = 1;i < arraySize - 1;i ++) {
      if (big < values[i]) {
        big = values[i];
      }
    }

    System.out.println("largest is " + big);
  }

  public void small () {
    int small = values[0];

    for (int i = 1;i < arraySize - 1;i ++) {
      if (small > values[i]) {
        small = values[i];
      }
    }

    System.out.println("smallest is " + small);
  }

}

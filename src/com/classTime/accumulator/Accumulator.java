package com.classTime.accumulator;

public class Accumulator {

  private int sum;

  public Accumulator() {
    sum = 0;
  }

  public void add(int value) {
    this.sum += value;
  }

  public void display() {
    System.out.println("누적값 : " + sum);
    System.out.println();
  }

}

package com.classTime.accumulator;

public class AccumulatorRunner {

  public static void main(String[] args) {
      Accumulator accumulator = new Accumulator();

      accumulator.add(3);
      accumulator.display();

      accumulator.add(8);
      accumulator.display();

      accumulator.add(-5);
      accumulator.display();
  }



}

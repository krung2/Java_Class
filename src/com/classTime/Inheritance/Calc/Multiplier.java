package com.classTime.Inheritance.Calc;

public class Multiplier extends Adder{

  @Override
  public int calculate() {
    return getOperand1() * getOperand2();
  }

  @Override
  public String getOperator() {
    return "-";
  }

  public static void main(String[] args) {
    Multiplier multiplier = new Multiplier();

    multiplier.mainFunc();
  }
}

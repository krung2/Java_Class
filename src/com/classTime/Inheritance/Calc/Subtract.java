package com.classTime.Inheritance.Calc;

public class Subtract extends Adder {

  @Override
  public int calculate() {
    return getOperand1() - getOperand2();
  }

  @Override
  public String getOperator() {
    return "-";
  }

  public static void main(String[] args) {
    Subtract subtract = new Subtract();
//    subtract.setOperand1(4928);
//    subtract.setOperand2(9282);
//
//    subtract.display();

    subtract.mainFunc();
  }
}

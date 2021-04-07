package com.classTime.Inheritance.Calc;

import java.util.Scanner;

public class Adder {

  private int operand1;
  private int operand2;
  Scanner scanner = new Scanner(System.in);

  public int calculate() {
    return operand1 + operand2;
  }

  public void display() {
    String str = String.format("%d %s %d = %d",
            operand1, getOperator(), operand2, calculate());

    System.out.println(str);
  }

  public String getOperator() {
    return "+";
  }

  public int scan() {
    return scanner.nextInt();
  }

  protected void mainFunc() {

    while (true) {

      setOperand1(scan());
      setOperand2(scan());

      if (0 == operand1 && 0 == operand2) {
        break;
      }

      display();
    }
  }

  public int getOperand1() {
    return operand1;
  }

  public void setOperand1(int operand1) {
    this.operand1 = operand1;
  }

  public int getOperand2() {
    return operand2;
  }

  public void setOperand2(int operand2) {
    this.operand2 = operand2;
  }

  public static void main(String[] args) {
    Adder adder = new Adder();
    adder.setOperand1(4928);
    adder.setOperand2(9282);

    adder.display();

    adder.mainFunc();
  }
}

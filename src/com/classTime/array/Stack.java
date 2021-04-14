package com.classTime.array;

public class Stack {

  private int top = 0;

  private static final int SIZE = 100;
  public String[] stack = new String[SIZE];

  public void push(String value) {
    stack[top++] = value;
  }

  public void pop() {
    System.out.println(stack[-- top] + " 제거 \n");
  }

  public void display() {
    for (int i = 0; i < top; i ++) {
      System.out.println(stack[i]);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Stack stack = new Stack();

    stack.push("어벤져스");
    stack.push("인터스텔라");

    stack.display();
    stack.pop();
    stack.display();
  }

}

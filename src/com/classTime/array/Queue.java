package com.classTime.array;

public class Queue {


  private int front = 0;
  private int rear = 0;

  private static final int SIZE = 100;
  public String[] queue = new String[SIZE];

  public void enqueue (String value) {
    queue[front ++] = value;
  }

  public void dequeue () {
    System.out.println(queue[rear ++] + " 제거 \n");
  }

  public void display() {
    for (int i = rear; i < front; i ++) {
      System.out.println(queue[i]);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Queue queue = new Queue();

    queue.enqueue("어벤져스");
    queue.enqueue("인터스텔라");

    queue.display();
    queue.dequeue();
    queue.display();
  }

}

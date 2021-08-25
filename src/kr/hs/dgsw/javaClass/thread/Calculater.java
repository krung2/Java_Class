package kr.hs.dgsw.javaClass.thread;

import java.util.ArrayList;
import java.util.List;

public class Calculater {

  private int sum = 0;

  private List<String> list = new ArrayList<>();

  public void add (int value) {

    synchronized (list) {
      int a = sum + value;
      sum = a;
    }

  }

  public void method1() {

    synchronized (list) {
      list.add("123");
    }
  }

  public int getSum() {
    return sum;
  }
}

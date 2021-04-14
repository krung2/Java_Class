package com.classTime.array;

import java.util.Arrays;

public class BestSort extends Sort{

  @Override
  public void sort() {
    Arrays.sort(array);
  }

  public static void main(String[] args) {
    Sort sort = new BestSort();

    sort.fillValues();

    System.out.println(" 정렬 전");
    sort.printArray();

    long before = System.currentTimeMillis();
    sort.sort();
    long after = System.currentTimeMillis();

    System.out.println("\n\n 정렬 후");
    sort.printArray();

    System.out.println("\n경과 시간 : " + (double)(after - before) / 1000 + "초");
  }
}

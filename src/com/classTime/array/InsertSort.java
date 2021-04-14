package com.classTime.array;

public class InsertSort extends Sort {

  @Override
  public void sort() {
    for (int i = 1;i < array.length; i ++) {
      int key = array[i];
      int j;

      for (j = i - 1; j >= 0 && array[j] > key;j --) {
        array[j + 1] = array[j];
      }

      array[j + 1] = key;
    }
  }

  public static void main(String[] args) {
    Sort sort = new InsertSort();

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

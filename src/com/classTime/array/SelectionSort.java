package com.classTime.array;

public class SelectionSort extends Sort {

  @Override
  public void sort() {
    for (int i = 0; i < array.length; i ++) {
      int minIndex = findMinimumIndex(i);

      if (i != minIndex) {
        swap (i, minIndex);
      }
    }
  }

  private int findMinimumIndex(int index) {
    int result = index;

    for (int i = index + 1; i < array.length; i ++) {

      if (array[result] > array[i]) {
        result = i;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Sort sort = new SelectionSort();

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

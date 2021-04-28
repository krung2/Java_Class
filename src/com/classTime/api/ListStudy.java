package com.classTime.api;

import java.util.List;
import java.util.ArrayList;

public class ListStudy {

  public static void printAll(List<String> list) {

    for (int i = 0; i < list.size(); i ++) {

      System.out.println(i + " : " + list.get(i));
    }
  }

  public static void main(String[] args) {

    List<String> list  = new ArrayList<String>();

    list.add("대소고");
    list.add("2학년 2반");
    list.add("자바 수업");
    list.add("수요일");
    printAll(list);

    System.out.println(" --------- ");

    list.add(2, "데이터베이스");
    list.remove(3);
    printAll(list);
  }
}

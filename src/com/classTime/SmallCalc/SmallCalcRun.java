package com.classTime.SmallCalc;

import java.util.Scanner;

public class SmallCalcRun {

  public static void main(String[] args) {

    ScannerFuc scannerFuc = new ScannerFuc();

    int arraySize;

    System.out.println("입력할 숫자의 개수를 입력해 주세요");
    arraySize = scannerFuc.scan();
    System.out.println();

    SmallCalc calc = new SmallCalc(arraySize);

    for(int i = 0;i < arraySize;i ++) {

      System.out.println("숫자를 입력해주세요");

      int value = scannerFuc.scan();

      calc.addNumber(value, i);

      System.out.println();
    }

    System.out.println("1");
    calc.big();
    calc.small();
    calc.avg();

  }

}

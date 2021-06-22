package com.classTime.assessment.baseball;

import java.util.Random;
import java.util.Scanner;

public class Baseball {

  private int[] arr;

  public Baseball() {

    this.arr = new int[3];
    setting();
  }

  public boolean check(int[] inputNum) {

    int strike = 0;
    int ball = 0;
//    int[] checkArr = {firstNum, secondNum, thirdNum};

    for (int i = 0;i < 3; i ++) {
      for (int j = 0;j < 3; j ++) {

        if (inputNum[i] == this.arr[j] && i == j) {

          strike ++;
        } else if (inputNum[i] == this.arr[j]) {

          ball ++;
        }
      }
    }

    if (strike == 3) {

      System.out.println("축하합니다. 숫자를 맞췄습니다");
      return false;
    }

    if (strike == 0 && ball == 0) {

      System.out.println("아웃");
      return true;
    }

    System.out.println(strike + "S" + ball + "B입니다.");

    return true;
  }

  private void setting() {

    Random rand = new Random();

    boolean[] check = new boolean[10];

    int i = 0;
    while (i < 3) {

      int newNum = rand.nextInt(10);
      if (check[newNum] == false) {

        check[newNum] = true;
        arr[i] = newNum;
        i ++;
      }
    }
  }

  public void start() {

    Scanner sc = new Scanner(System.in);

    boolean checker = true;
    int[] inputNum = new int[3];

    while (checker) {

      System.out.println("숫자를 맞춰보세요.");

      for (int i = 0; i < 3; i ++) {
        inputNum[i] = sc.nextInt();
      }

      checker = this.check(inputNum);
    }
  }

  public static void main(String[] args) {

    Baseball baseball = new Baseball();
    baseball.start();
  }
}

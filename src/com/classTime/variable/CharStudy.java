package com.classTime.variable;

public class CharStudy {

  public static void main(String[] args) {

    char a = 65;
    char b = 'a';
    char d = '가';

    System.out.println(a);
    System.out.println(b + " " + (int)b);
    System.out.println(d + " " + (int)d);

    for (char i = '가'; i < '나'; i ++) {
      System.out.println(i + " " + (int)i);
    }

    System.out.println("지원되는 한글의 개수 : " + ('힣' -'가' + 1));

    char hanja = '天';

    System.out.println(hanja + " " + (int) hanja);
  }
}

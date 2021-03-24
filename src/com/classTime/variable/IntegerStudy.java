package com.classTime.variable;

public class IntegerStudy {

  public static void main(String[] args) {

    byte byteValue; // 1 바이트
    short shortValue; // 2 바이트
    int intValue; // 4 바이트
    long longValue; // 8 바이트
    char charValue; // 2 바이트

    System.out.println("Byte : " + Byte.BYTES);
    System.out.println("Short : " + Short.BYTES);
    System.out.println("Integer : " + Integer.BYTES);
    System.out.println("Long : " + Long.BYTES);
    System.out.println("Char : " + Character.BYTES);

    System.out.println();

    System.out.println("Byte Range : " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
    System.out.println("Short Range : " + Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
    System.out.println("Integer Range : " + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
    System.out.println("Long Range : " + Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
    System.out.println("Char Range : " + Character.MIN_VALUE + " ~ " + Character.MAX_VALUE);

    System.out.println();

    byteValue = (byte)128;
    System.out.println("byteValue : " + byteValue);
    intValue = 123456780;
    longValue = 1234567890000L;

    intValue = 0xFF;
    byteValue = 0b1001011;
    shortValue = 0232;
    System.out.println(intValue);
    System.out.println(byteValue);
    System.out.println(shortValue);
  }
}

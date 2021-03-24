package com.classTime.variable;

import java.util.Scanner;

public class Hex {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int intValue = scanner.nextInt();

    String hex = convertToHexStringT(intValue);
    System.out.println(intValue + " -> " + hex);

    scanner.close();
  }

  private static String convertToHexString (int value) {
    String result = "";

    int divValue;

    while (value != 0) {
      divValue = value % 16;
      value /= 16;

      switch (divValue) {
        case 10:
          result = "A" + result;
        case 11:
          result = "B" + result;
          break;
        case 12:
          result = "C" + result;
          break;
        case 13:
          result = "D" + result;
          break;
        case 14:
          result = "E" + result;
          break;
        case 15:
          result = "F" + result;
          break;
        default:
          result = divValue + result;
      }
    }

    return result;
  }

  private static String convertToHexStringCase (int value) {
    switch (value) {
      case 10:
        return "A";
      case 11:
        return "B";
      case 12:
        return "C";
      case 13:
        return "D";
      case 14:
        return "E";
      case 15:
        return "F";
      default:
        return value + "";
    }
  }

  private static String convertToHexStringT (int value) {

    String result = "";

    while (value > 0) {
      int remainder = value % 16;
      String char1 = convertToHexStringCase(remainder);
      result = char1 + result;

      value /= 16;
    }

    return result;
  }
}

package com.classTime.tryCatch;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TryStudy {

  public static int divide() {

    Scanner scanner = new Scanner(System.in);

    try {
      System.out.println(" 두 정수를 입력하세요.");
      int value1 = scanner.nextInt();
      int value2 = scanner.nextInt();

      String printFormat = String.format("%d / %d = %d",
              value1, value2, (value1 / value2));

      System.out.println(printFormat);

      return value1 / value2;
    } catch (ArithmeticException e) {

      System.out.println("0으로 나눌 수 없습니다.");
      e.printStackTrace();;
    } catch (InputMismatchException e) {

      System.out.println("정수를 입력하세요");
      e.printStackTrace();
    } catch (Exception err) {

      System.out.println("잘못된 입력입니다.");
      err.printStackTrace();
    } finally {

      scanner.close();
    }

    throw new RuntimeException();
  }

  public static void readFile()
    throws FileNotFoundException, IOException {

    try {
      File file = new File("/Users/krung2/Documents/Document/JavaClass/a.txt");

      FileReader fileReader = new FileReader(file);
      BufferedReader reader = new BufferedReader(fileReader);

      String firstLine = reader.readLine();
      System.out.println(firstLine);

      reader.close();
    } catch (FileNotFoundException err) {


    } catch (IOException e) {


    }
  }

  public static void method1() throws Exception {

    throw new Exception("샘플 예외");
  }

  public static void method2() throws RuntimeException {

    throw new RuntimeException();
  }

  public static void main(String[] args) {
    divide();

//    method1();
    method2();
  }
}

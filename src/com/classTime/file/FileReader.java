package com.classTime.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileReader {

public static void main(String[] args) {

  String baseURL = "/Users/krung2/Desktop/java/file/";

    try {

      File file = new File(baseURL + "abc.txt");
      InputStream is = new FileInputStream(file);

      while (is.available() > 0) {
        int value = is.read();
        char ch = (char)value;
        System.out.println(value + " " + ch);
      }

      is.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

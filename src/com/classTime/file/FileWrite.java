package com.classTime.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileWrite {

  public static void main(String[] args) {

    String baseURL = "/Users/krung2/Desktop/java/file/";

    try {
      File file = new File(baseURL + "zzz.txt");
      OutputStream os = new FileOutputStream(file);

      os.write(49);
      os.write(65);
      os.write(105);

      os.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

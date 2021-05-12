package com.classTime.file;

import java.io.File;

public class FileStudy {

  public static void main(String[] args) {

    File file = new File("/Users/krung2/Desktop/java/abc.txt");

    boolean exist = file.exists();
    System.out.println("exist : " + exist);
  }
}

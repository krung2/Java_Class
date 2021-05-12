package com.classTime.file;

import java.io.File;
import java.util.Date;

public class FileStudy {

  public static void main(String[] args) {

    String baseURL = "/Users/krung2/Desktop/java/file/";

    try {
      File file = new File(baseURL + "abc.txt");

      boolean exist = file.exists();
      System.out.println("exist : " + exist);

      file = new File(baseURL + "가나다.txt");
      boolean result = file.createNewFile();
      System.out.println("createNewFile : " + result);

      System.out.println("deleteFile : " + file.delete() + "\n");

      File dir = new File(baseURL + "sub");
      result = dir.mkdir();
      System.out.println("mkdir : " + result);
      System.out.println("rm : " + dir.delete() + "\n");

      File hello = new File(baseURL + "hello");
      System.out.println("isFile : " + hello.isFile());
      System.out.println("isDirectory : " + hello.isDirectory());

      System.out.println("getName : " + hello.getName());
      System.out.println("getName : " + hello.getAbsolutePath());
      System.out.println("length : " + hello.length());
      System.out.println("lastModified : " + hello.lastModified());

      long lastModified = hello.lastModified();
      Date date = new Date(lastModified);
      System.out.println("lastModified : " + date.toString());

    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}

package com.classTime.file;

import java.io.*;

public class FileCopy {

  String baseURL = "/Users/krung2/Desktop/java/file/asdf.jpeg";
  String baseImage = "/Users/krung2/Desktop/java/file/123.jpeg";

  public void copy(String src, String target)
    throws Exception {
      File srcFile = new File(src);
      File targetFile = new File(target);

    InputStream is = new FileInputStream(srcFile);
    OutputStream os = new FileOutputStream(targetFile);

    while (is.available() > 0) {
      int value = is.read();
      os.write(value);
    }

    is.close();
    os.close();
  }

  public static void main(String[] args) {
    FileCopy copy = new FileCopy();

    try {
      copy.copy(copy.baseImage, copy.baseURL);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

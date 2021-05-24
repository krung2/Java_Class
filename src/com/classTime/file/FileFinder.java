package com.classTime.file;

import java.io.File;
import java.util.regex.Pattern;

public class FileFinder {

  public void showFile (String dirpath) throws Exception {
    File dir = new File(dirpath);
    File[] files = dir.listFiles();

    String pattern = "^\\\\S+.(?i)(ts)$";

    for (File file: files) {
      if (file.isDirectory()) {
        showFile(file.getPath());
      } else {
        if (Pattern.matches(pattern, file.getName())) {
          System.out.println(file);
        }
      }
    }
  }

  public static void main(String[] args) {

    FileFinder fileFinder = new FileFinder();
    String baseURL = "/Users/krung2/Desktop/java/file/";

    try {
      fileFinder.showFile("/Users");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

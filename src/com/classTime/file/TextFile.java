package com.classTime.file;

import java.io.*;
import java.io.FileReader;

public class TextFile {

  public String read (String filePath) throws Exception {

    File file = new File(filePath);
    java.io.FileReader fileReader = new FileReader(file);
    BufferedReader reader = new BufferedReader(fileReader);

    String result = "";
    while (true) {

      String line = reader.readLine();

      if (line == null) {
        break ;
      }

      result += line + "\n";
    }

    reader.close();

    return result;
  }

  public void write(String filePath, String content) throws Exception {

    File file = new File(filePath);
    FileWriter fileWriter = new FileWriter(file);
    BufferedWriter writer = new BufferedWriter(fileWriter);

    writer.write(content);

    writer.close();
  }

  public static void main(String[] args) {

    try {

      String baseURL = "/Users/krung2/Desktop/java/file/hello";

      TextFile textFile = new TextFile();

      String contents = textFile.read(baseURL);
      System.out.println(contents);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

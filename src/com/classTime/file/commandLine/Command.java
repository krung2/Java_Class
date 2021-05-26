package com.classTime.file.commandLine;

import java.io.File;
import java.util.Scanner;

public class Command {

  private String dirpath = "/Users/krung2";

  public void ls () throws Exception {
    File dir = new File(this.dirpath);
    File[] files = dir.listFiles();

    for (File file: files) {
      System.out.println(file);
    }
  }

  public static void main(String[] args) {

    Command commandLine = new Command();
    Scanner scanner = new Scanner(System.in);

    try {

      while (true) {

        String command = scanner.nextLine();

        switch (command) {
          case "ls":
            commandLine.ls();
            break;
          default :
            break;
        }

      }
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}

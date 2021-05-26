package com.classTime.file.commandLine;

import java.io.File;
import java.util.Scanner;

public class Command {

  private String dirpath = "/Users/krung2/";

  public void nowDir () throws Exception {

    System.out.print(this.dirpath + " >  ");
  }

  public void ls () throws Exception {
    File dir = new File(this.dirpath);
    File[] files = dir.listFiles();

    for (File file: files) {
      System.out.println(file);
    }
  }

  public void cd (String path) throws Exception{
    if (path.equals("..")) {

      System.out.println(this.dirpath.substring((this.dirpath.lastIndexOf("/"))));
      this.dirpath = this.dirpath.substring(0, this.dirpath.lastIndexOf("/"));
      return;
    }

    String newPath = this.dirpath + path + "/";
    File dir = new File(newPath);

    if (dir.isDirectory()) {
      this.dirpath = newPath;
    } else {
      System.out.println("없는 경로입니다");
    }
  }

  public static void main(String[] args) {

    Command commandLine = new Command();
    Scanner scanner = new Scanner(System.in);

    try {

      while (true) {

        commandLine.nowDir();

        String command = scanner.nextLine();
        String[] commandSplit = command.split(" ");

        switch (commandSplit[0]) {
          case "ls":
            commandLine.ls();
            break;
          case "cd":
            commandLine.cd(commandSplit[1]);
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

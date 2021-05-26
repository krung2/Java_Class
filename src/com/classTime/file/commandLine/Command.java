package com.classTime.file.commandLine;

import java.io.File;
import java.rmi.server.ExportException;
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

  public void mkdir (String path) throws Exception {

    File dir = new File(this.dirpath + path);

    if (dir.exists()) {

      System.out.println("이미 같은 이름의 폴더가 있습니다");
    } else {

      dir.mkdir();
    }
  }

  public void rmdir (String path) throws Exception {

    File dir = new File(this.dirpath + path);

    if (dir.exists()) {

      dir.delete();
    } else {

      System.out.println("없는 폴더입니다");
    }
  }

  public void touch (String fileName) throws Exception {

    File file = new File(this.dirpath + fileName);

    if (file.exists()) {

      System.out.println("이미 있는 파일입니다");
    } else {

      file.createNewFile();
    }
  }

  public void rm (String fileName) throws Exception {

    File file = new File(this.dirpath + fileName);

    if (file.exists()) {

      file.delete();
    } else {

      System.out.println("없는 파일입니다");
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
          case "mkdir":
            commandLine.mkdir(commandSplit[1]);
            break;
          case "rmdir":
            commandLine.rmdir(commandSplit[1]);
            break;
          case "touch":
            commandLine.touch(commandSplit[1]);
            break;
          case "rm":
            commandLine.rm(commandSplit[1]);
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

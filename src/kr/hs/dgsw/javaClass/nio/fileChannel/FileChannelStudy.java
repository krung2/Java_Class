package kr.hs.dgsw.javaClass.nio.fileChannel;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelStudy {

  public static void studyWrite() throws Exception {
    Path path = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/read.txt");
    FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

    String data = "대구소프트웨어마이스터고등학교";
    byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
    ByteBuffer buffer = ByteBuffer.wrap(bytes);

    channel.write(buffer);

    channel.close();
  }

  public static void studyRead() throws Exception {
    Path path = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/read.txt");
    FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);

    ByteBuffer buffer = ByteBuffer.allocate(100);
    byte[] bytes = new byte[100];
    String data = "";

    while (true) {
      int cnt = channel.read(buffer);

      if (cnt < 0) break;

      buffer.flip();

      buffer.get(bytes);
      data += new String(bytes, 0, cnt, StandardCharsets.UTF_8);
      buffer.clear();
    }

    channel.close();
    System.out.println(data);
  }

  public static void copy() throws Exception {
    Path src = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/favicon.ico");
    Path target = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/favicon.ico");
  }

  public static void main(String[] args) {
    try {
      studyWrite();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

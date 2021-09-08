package kr.hs.dgsw.javaClass.nio.charBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferStudy {

  public static void studyBuffer() throws Exception {
    ByteBuffer buffer1 = ByteBuffer.allocate(10);

    byte[] bytes = new byte[] {1, 2, 3, 4, 5};
    ByteBuffer buffer2 = ByteBuffer.wrap(bytes);

    printStatus(buffer1, "buffer1 생성");
    printStatus(buffer2, "buffer2 생성");

    buffer1.put((byte) 55);
    buffer1.put((byte) -12);
    printStatus(buffer1, "buffer1에 2바이트 추가");

    buffer1.put(new byte[] {15, 16, 17, 18, 19});
    printStatus(buffer1, "buffer1에 4바이트 추가");

    byte value = buffer2.get();
    System.out.println("읽은 값 : " + value);
    printStatus(buffer2, "buffer2에서 1바이트 읽음");

    byte[] bytes1 = new byte[3];
    buffer2.get(bytes1);
    printStatus(buffer2, "buffer2에서 3바이트 읽음");

    printStatus(buffer1, "buffer1 flip() 실행전");
    buffer1.flip();
    printStatus(buffer1, "buffer1 flip() 실행후");
    value = buffer1.get();
    System.out.println("읽은 값 : " + value);
    printStatus(buffer1, "buffer1에서 1바이트 읽음");

    printStatus(buffer1, "buffer1 rewind() 실행전");
    buffer1.rewind();
    printStatus(buffer1, "buffer1 rewind() 실행후");

    buffer1.get(bytes1);
    printStatus(buffer1, "buffer1 상태");
    buffer1.mark();

    buffer1.get();
    printStatus(buffer1, "buffer1 reset() 수행 전");
    buffer1.reset();
    printStatus(buffer1, "buffer1 reset() 수행 후");

    printStatus(buffer1, "buffer1 compact() 수행 전");
    buffer1.compact();
    printStatus(buffer1, "buffer1 compact() 수행 후");
  }

  public static void printStatus(Buffer buffer, String note) {
    System.out.println(String.format("%s : %d %d %d", note, buffer.position(), buffer.limit(), buffer.capacity()));
  }

  public static void main(String[] args) {
    try {
      studyBuffer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

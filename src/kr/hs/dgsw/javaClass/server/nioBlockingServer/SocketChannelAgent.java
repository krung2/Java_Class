package kr.hs.dgsw.javaClass.server.nioBlockingServer;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SocketChannelAgent {

  private SocketChannel socketChannel;

  private Thread listnerThread;

  public SocketChannelAgent (SocketChannel socketChannel) {
    this.socketChannel = socketChannel;
  }

  public void init() throws Exception {
    listnerThread = new Thread(new Listener());
    listnerThread.setDaemon(true);
    listnerThread.start();
  }

  public void receive (String message) throws Exception {
    try {
      send(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void send(String message) throws Exception {
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer buffer = charset.encode(message);
    buffer.flip();

    socketChannel.write(buffer);
  }

  private class Listener implements Runnable {

    @Override
    public void run() {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      byte[] bytes = new byte[1024];
      int length;

      try {
        while (true) {
          length = socketChannel.read(buffer);

          if (length < 0) break;

          buffer.flip();
          Charset charset = StandardCharsets.UTF_8;
          String message = charset.decode(buffer).toString();
          receive(message);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

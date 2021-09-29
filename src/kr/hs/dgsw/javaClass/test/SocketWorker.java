package kr.hs.dgsw.javaClass.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class SocketWorker {

  protected Socket socket;

  protected InputStream is;

  protected OutputStream os;

  protected Thread listenerThread;

  public void setSocket(Socket socket) {

    this.socket = socket;
  }

  public void prepareTalking() throws IOException {

    this.is = socket.getInputStream();
    this.os = socket.getOutputStream();

    Listener listener = new Listener();
    listenerThread = new Thread(listener);
    listenerThread.setDaemon(true);
    listenerThread.start();
  }

  public abstract void startTalking() throws IOException;

  public abstract void listen(byte[] buffer, int length) throws IOException;

  public void disconnect() throws IOException {

    if (is != null) is.close();
    if (os != null) os.close();
    if (socket != null) {
      socket.close();
    }
  }

  public String sendMessage(ChattingCommand chattingCommand) throws IOException {
    return chattingCommand + "0000";
  }

  public String sendMessage(ChattingCommand chattingCommand, String payload) throws IOException {
    return chattingCommand + String.format("%04d", payload.getBytes(StandardCharsets.UTF_8).length) + payload;
  }

  protected List<String> getUserInfo (String idWithName) {
    List<String> list = new ArrayList<>();
    list.add(idWithName.substring(0, 4));
    list.add(idWithName.substring(4));
    return list;
  }

  private class Listener implements Runnable {

    @Override
    public void run() {

      byte[] buffer = new byte[1024];
      int length;

      try {

        while (true) {

          length = is.read(buffer);
          if (length < 0) {
            disconnect();
            break;
          }
          listen(buffer, length);
        }
      } catch (Exception e) {

        e.printStackTrace();
      }
    }
  }
}

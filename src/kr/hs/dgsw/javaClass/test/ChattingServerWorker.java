package kr.hs.dgsw.javaClass.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChattingServerWorker extends SocketWorker {


  private static List<Socket> socketList = new ArrayList<>();

  @Override
  public void startTalking() throws IOException {

    System.out.println(this.socket.getInetAddress().toString() + "connet");
    socketList.add(this.socket);
  }

  @Override
  public void listen(byte[] buffer, int length) throws IOException {

    if (length == -1) disconnect();

    for (Socket socket: socketList) {
      if (socket == this.socket) continue;
      OutputStream os = socket.getOutputStream();
      String sendMessage = this.socket.getInetAddress().toString() + new String(buffer, 0, length);
      os.write(sendMessage.getBytes());
   }
  }

  @Override
  public void disconnect() throws IOException {

    if (is != null) is.close();
    if (os != null) os.close();
    if (socket != null) {
      socket.close();
      socketList.remove(socket);
    }
  }
}
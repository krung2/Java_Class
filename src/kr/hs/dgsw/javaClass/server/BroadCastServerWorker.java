package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BroadCastServerWorker extends SocketWorkerAdapter {

  private static List<Socket> socketList = new ArrayList<>();

  @Override
  public void startTalking() throws IOException {

    socketList.add(this.socket);
  }

  @Override
  public void listen(String message) throws IOException {

    for (Socket socket: socketList) {
      OutputStream os = socket.getOutputStream();
      os.write(message.getBytes());
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

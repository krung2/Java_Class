package kr.hs.dgsw.javaClass.test;

import java.net.ServerSocket;
import java.net.Socket;

public class ChattingServer {

  private ServerSocket serverSocket;

  public void startServer () {

    try {

      serverSocket = new ServerSocket(1200);
      System.out.println("Server start");

      while (true) {
        Socket socket = serverSocket.accept();
        Agent agent = new Agent(socket);

        new Thread(agent).start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private class Agent implements Runnable {

    private final Socket socket;

    public Agent (Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {

      try {

        SocketWorker socketWorker = new ChattingServerWorker();
        socketWorker.setSocket(socket);
        socketWorker.prepareTalking();
        socketWorker.startTalking();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    try {

      ChattingServer server = new ChattingServer();
      server.startServer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

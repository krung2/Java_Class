package kr.hs.dgsw.javaClass.server;

import java.net.ServerSocket;
import java.net.Socket;

public class CommonServer {

  private ServerSocket serverSocket;

  public void startServer(int port) {

    try {

      serverSocket = new ServerSocket(port);
      System.out.println("서버 시작");

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

    public Agent(Socket socket) {

      this.socket = socket;
    }

    @Override
    public void run() {

      try {

        SocketWorker socketWorker = new BroadCastServerWorker();
        socketWorker.setSocket(socket);
        socketWorker.prepareTalking();
        socketWorker.startTalking();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {

    try{

      CommonServer server = new CommonServer();
      server.startServer(8001);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

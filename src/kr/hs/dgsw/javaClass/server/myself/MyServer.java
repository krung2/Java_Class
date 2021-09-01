package kr.hs.dgsw.javaClass.server.myself;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

  ServerSocket serverSocket;

  public static final String SERVER_ADDRESS = "127.0.0.1";

  public static final int PORT = 8001;

  public void start() throws IOException {

    serverSocket = new ServerSocket(PORT);
    System.out.println("SERVER is running at " + PORT);

    while (true) {

      System.out.println("waiting...");
      Socket client = serverSocket.accept();
      System.out.println("client is Connect : " + client.getInetAddress().toString());
      startTalk(client);
    }
  }

  public void startTalk(Socket socket) {

    try {
      while (true) {

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        byte[] bytes = new byte[9999];
        int length = is.read(bytes);

        String clientMessage = new String(bytes, 0, length);
        System.out.println("client's message : " + clientMessage);

        String serverMessage = clientMessage + " 수신 완료";
        os.write(serverMessage.getBytes());
      }

    } catch (Exception e) {

      System.out.println("client is disconnect");
//      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    try {

      MyServer myServer = new MyServer();
      myServer.start();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}

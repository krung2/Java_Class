package kr.hs.dgsw.javaClass.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

  private ServerSocket serverSocket;

  public static final String SERVER_ADDRESS = "127.0.0.1";

  public static final int PORT = 8002;

  public void startServer() throws IOException {

    serverSocket = new ServerSocket(PORT);
    System.out.println("서버 소켓이 생성되었습니다.");

    while (true) {

      System.out.println("클라이언트 접속 기다림");
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트 접속됨 : " + socket.getInetAddress().toString());
      startTalking(socket);
    }
  }

  public void startTalking (Socket socket) {

    try {

      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();

      byte[] bytes = new byte[4096];

      while (true) {

        int length = is.read(bytes);

        String message = new String(bytes, 0, length);
        System.out.println("클라이언트 메시지 : " + message);

        int index = message.indexOf(",");
        String sValue1 = message.substring(0, index);
        String sValue2 = message.substring(index + 1);
        int value1 = Integer.parseInt(sValue1);
        int value2 = Integer.parseInt(sValue2);
        int sum = value1 + value2;

        os.write((sum + "").getBytes());
      }
    } catch (Exception e) {

      System.out.println("클라이언트 연결 종료");
//      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

    try {

      SimpleServer server = new SimpleServer();
      server.startServer();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}
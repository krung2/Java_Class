package kr.hs.dgsw.javaClass.server.myself;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

  Socket socket;

  InputStream is;

  OutputStream os;

  private final Scanner scanner;

  private static final String SERVER_ADDRESS = "127.0.0.1";

  private static final int PORT = 8001;

  MyClient () {
    this.scanner = new Scanner(System.in);
  }

  public void connect () throws IOException {

    socket = new Socket(SERVER_ADDRESS, PORT);

    System.out.println("connecting " + socket.getInetAddress().toString());
  }

  public void prepareTalk () throws Exception {
    is = socket.getInputStream();
    os = socket.getOutputStream();
  }

  public void talking () throws Exception {

    while (true) {

      System.out.println("메시지를 입력후 Enter를 눌러주세요 (exit입력시 종료)");
      String message = scanner.nextLine();

      if (message.equals("exit")){
        System.out.println("서버와의 접속을 종료합니다");
        disconnect();
        break;
      }

      os.write(message.getBytes());

      byte[] bytes = new byte[2048];
      int length = is.read(bytes);

      System.out.println("서버 메시지 : " + new String(bytes, 0, length));
    }
  }

  public void disconnect () throws Exception {

    if (is != null) {
      is.close();
    }

    if (os != null) {
      os.close();
    }

    if (socket != null) {
      socket.close();
    }
  }

  public static void main(String[] args) {

    try {

      MyClient myClient = new MyClient();
      myClient.connect();
      myClient.prepareTalk();
      myClient.talking();
    } catch (Exception e) {

      e.printStackTrace();
    }
  }
}

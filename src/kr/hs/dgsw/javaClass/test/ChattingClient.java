package kr.hs.dgsw.javaClass.test;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChattingClient extends SocketWorker {

  private Scanner scanner;

  public void connect (String address, int port) throws IOException {

    this.socket = new Socket(address, port);
  }

  @Override
  public void startTalking() throws IOException {

    scanner = new Scanner(System.in);

    while (true) {
      String line = scanner.nextLine();

      if ("quit".equals(line)) break;

      this.os.write(line.getBytes());
    }

    scanner.close();
    disconnect();
  }

  @Override
  public void listen(byte[] buffer, int length) throws IOException {
    if (length == -1) {
      scanner.close();
      disconnect();
    }

    System.out.println(new String(buffer, 0, length));
  }

  public static void main(String[] args) {

    try {

      ChattingClient client = new ChattingClient();
      client.connect("127.0.0.1", 1200);
      client.prepareTalking();
      client.startTalking();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

package kr.hs.dgsw.javaClass.test;


import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ChattingClient extends SocketWorker {

  private Scanner scanner;

  private HashMap<String, String> userMap = new HashMap<>();

  public void connect (String address, int port) throws IOException {

    this.socket = new Socket(address, port);
  }

  @Override
  public void startTalking() throws IOException {

    scanner = new Scanner(System.in);

    System.out.println("id와 이름을 입력해주요 ex) 2210 신중빈");
    String idName = scanner.nextLine();
    sendMessage(ChattingCommand.ID, idName);

    while (true) {
      String line = scanner.nextLine();

      if ("quit".equals(line)) break;

      sendMessage(ChattingCommand.GM, line);
    }

    scanner.close();
    disconnect();
  }

  @Override
  public void listen(byte[] buffer, int length) throws IOException {
    if (length == -1) {
      System.out.println("서버와 연결이 끊켰습니다");
      scanner.close();
      disconnect();
    }

    Message message = new Message(buffer, length);

    switch (message.getCommand()) {
      case UR:
        List<String> connectusers = List.of(message.getReceiveMessage().split(","));
        connectusers.forEach(this::saveUserInfo);
        System.out.println(message.getReceiveMessage());
        break;

      case GR:
        List<String> userInfo = getUserInfo(message.getReceiveMessage());
        String user = userMap.get(userInfo.get(0));
        System.out.println(user + " :" +userInfo.get(1));
        break;

      case JR:
        saveUserInfo(message.getReceiveMessage());
        System.out.println(message.getReceiveMessage() + "님이 접속하였습니다");
        break;
      case DR:
        System.out.println("중복된 아이디입니다");
        disconnect();
        return;
      default:
        break;
    }
  }

  @Override
  public String sendMessage(ChattingCommand chattingCommand, String payload) throws IOException {
    String message = super.sendMessage(chattingCommand, payload);
    this.os.write(message.getBytes(StandardCharsets.UTF_8));
    return "";
  }

  private List<String> getUserInfo (String idWithName) {
    List<String> list = new ArrayList<>();
    list.add(idWithName.substring(0, 4));
    list.add(idWithName.substring(4));
    return list;
  }

  private void saveUserInfo (String idName) {
    userMap.put(idName.substring(0, 4), idName.substring(4));
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

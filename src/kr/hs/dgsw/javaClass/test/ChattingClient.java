package kr.hs.dgsw.javaClass.test;


import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
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

      if (line.startsWith("/")) {
        List<String> message = List.of(line.split(" "));

        switch (message.get(0)) {
          case "/귓속말":
            List<String> messages = new ArrayList<>();
            for (int i = 2;i < message.size();i ++) {
              messages.add(message.get(i));
            }
            sendMessage(ChattingCommand.SM, message.get(1) + " " + String.join(" ", messages));
            break;

          case "/추방":
            sendMessage(ChattingCommand.WD, message.get(1));
            break;

          default:
            System.out.println("없는 명령어입니다 다시 시도해주세요");
            break;
        }

      } else {

        if (line == "quit") break;

        sendMessage(ChattingCommand.GM, line);
      }
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
        System.out.println(user + " :" + userInfo.get(1));
        break;

      case JR:
        saveUserInfo(message.getReceiveMessage());
        System.out.println(message.getReceiveMessage() + "님이 접속하였습니다");
        break;

      case DR:
        System.out.println("중복된 아이디입니다");
        disconnect();

        return;

      case SR:
        List<String> whisperUserInfo = getUserInfo(message.getReceiveMessage());
        String whisperUser = userMap.get(whisperUserInfo.get(0));
        System.out.println("귓속말) " + whisperUserInfo.get(0) + " " + whisperUser + " " +
                ":" + whisperUserInfo.get(1));
        break;

      case DC:
        String quitUser = userMap.get(message.getReceiveMessage());
        System.out.println(message.getReceiveMessage() + " " + quitUser + "가 퇴실했습니다");
        break;

      case WR:
        System.out.println("추방당했습니다");
        disconnect();
        return;

        case WA:
        String kickeduser = userMap.get(message.getReceiveMessage());
        System.out.println(message.getReceiveMessage() + " " + kickeduser + "가 추방당했습니다");
        break;

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

package kr.hs.dgsw.javaClass.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChattingServerWorker extends SocketWorker {

  private String idName;
  private String id;
  private static List<ChattingServerWorker> userList = new ArrayList<>();

  @Override
  public void startTalking() throws IOException {

    System.out.println(this.socket.getInetAddress().toString() + " connet");
    userList.add(this);
  }

  @Override
  public void listen(byte[] buffer, int length) throws IOException {

    if (length < 0) disconnect();

    Message message = new Message(buffer, length);

    switch (message.getCommand()) {
      case ID:
        saveId(message.getReceiveMessage());
        break;

      case GM:
        String sendMessage = sendMessage(ChattingCommand.GR, this.id + " " + message.getReceiveMessage());
        sendMessageToEveryOne(sendMessage);
        break;
    }
  }

  public void sendMessageToEveryOne (String message) throws IOException {
    for (ChattingServerWorker user: userList) {
      if (user.id.equals(this.id)) continue;
      user.socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
    }
  }

  @Override
  public void disconnect() throws IOException {

    if (is != null) is.close();
    if (os != null) os.close();
    if (socket != null) {
      socket.close();
      sendMessageToEveryOne(sendMessage(ChattingCommand.DC, this.id));
      userList.remove(this);
    }
  }

  public void saveId(String idName) throws IOException{
    try {
      List<String> userNameList = new ArrayList<>();

      String savingId = idName.substring(0, 4);

      for (ChattingServerWorker user: userList) {

        userNameList.add(user.idName);
        if (savingId.equals(user.id)) {
          this.os.write(sendMessage(ChattingCommand.DR).getBytes(StandardCharsets.UTF_8));
          return;
        }
      }

      this.idName = idName;
      this.id = savingId;
      userNameList.add(idName);
      userNameList.remove(null);

      String sendingMessage = sendMessage(ChattingCommand.UR, userNameList.toString()
              .replace("[", "")
              .replace("]", "")
              .replace(", ", ","));
      this.os.write(sendingMessage.getBytes(StandardCharsets.UTF_8));
      sendMessageToEveryOne(sendMessage(ChattingCommand.JR, this.idName));
    } catch (Exception e) {
      disconnect();
      e.printStackTrace();
    }
  }
}
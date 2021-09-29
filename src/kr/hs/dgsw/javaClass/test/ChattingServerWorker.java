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

      case SM:
        List<String> userInfo = getUserInfo(message.getReceiveMessage());
        String sendWhisperMessage = sendMessage(ChattingCommand.SR, this.id + " " + userInfo.get(1));
        sendMessageToOne(userInfo.get(0), sendWhisperMessage);
        break;

      case WD:
        if (this.id != userList.get(0).id) break;
        kickUser(message.getReceiveMessage());

      default:
        break;
    }
  }

  public void sendMessageToEveryOne (String message) throws IOException {
    for (ChattingServerWorker user: userList) {
      if (user.id == null) continue;
      if (user.id.equals(this.id)) continue;
      user.socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
    }
  }

  private void sendMessageToOne (String userId, String message) throws IOException {
    for (ChattingServerWorker user: userList) {
      if (user.id.equals(userId)) {
        user.socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
        break;
      }
    }
  }

  private void kickUser (String userId) throws IOException {
    ChattingServerWorker kickUser = null;
    for (ChattingServerWorker user: userList) {
      if (user.id.equals(userId)) {
        user.socket.getOutputStream().write(
                sendMessage(ChattingCommand.WR)
                        .getBytes(StandardCharsets.UTF_8)
        );
        kickUser = user;
      } else {
        user.socket.getOutputStream().write(
                sendMessage(ChattingCommand.WA, userId)
                        .getBytes(StandardCharsets.UTF_8)
        );
      }
    }
    disconnectToObject(kickUser);
  }

  @Override
  public void disconnect() throws IOException {
    sendMessageToEveryOne(sendMessage(ChattingCommand.DC, this.id));

    if (is != null) is.close();
    if (os != null) os.close();
    if (socket != null) {
      socket.close();
      userList.remove(this);
    }
  }

  private void disconnectToObject(ChattingServerWorker kickUser) throws IOException {
    if (kickUser.is != null) is.close();
    if (kickUser.os != null) os.close();
    if (kickUser.socket != null) {
      kickUser.socket.close();
      userList.remove(kickUser);
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

      String sendingMessage = sendMessage(ChattingCommand.UR, String.join(",", userNameList).replace("null,", "").replace("null", ""));
      this.os.write(sendingMessage.getBytes(StandardCharsets.UTF_8));
      sendMessageToEveryOne(sendMessage(ChattingCommand.JR, this.idName));
    } catch (Exception e) {
      disconnect();
      e.printStackTrace();
    }
  }
}
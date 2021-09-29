package kr.hs.dgsw.javaClass.test;

public class Message {

  private ChattingCommand command;
  private int payloadLength;
  private String receiveMessage;

  public ChattingCommand getCommand() {
    return command;
  }

  public int getPayloadLength() {
    return payloadLength;
  }

  public String getReceiveMessage() {
    return receiveMessage;
  }

  public Message (byte[] buffer, int length) {
    this.command = ChattingCommand.valueOf(new String(buffer, 0, 2));
    this.payloadLength = Integer.parseInt(new String(buffer, 2, 4));
    this.receiveMessage = new String(buffer, 6, payloadLength);
  }
}

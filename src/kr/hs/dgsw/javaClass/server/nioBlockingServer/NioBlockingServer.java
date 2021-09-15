package kr.hs.dgsw.javaClass.server.nioBlockingServer;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioBlockingServer {

  private ServerSocketChannel serverSocketChannel;

  public void start(int port) throws Exception {

    this.serverSocketChannel = ServerSocketChannel.open();
    // ServerSocket이 사용할 포트 지정해주기
    this.serverSocketChannel.bind(new InetSocketAddress((port)));
    // default value = false (false = nonBlockingServer, true = BlockingServer)
    this.serverSocketChannel.configureBlocking(true);

    while (true) {
      try {
        SocketChannel socketChannel = serverSocketChannel.accept();

        SocketChannelAgent agent = new SocketChannelAgent(socketChannel);
        agent.init();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}
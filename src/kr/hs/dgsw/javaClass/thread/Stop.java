package kr.hs.dgsw.javaClass.thread;

public class Stop extends Thread {

//  @Override
//  public void run() {
//
//    try {
//      Thread.sleep(Long.MAX_VALUE);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }

  @Override
  public void run() {

    while (true) {}
  }

  public static void main(String[] args) {
    Stop t1 = new Stop();
    t1.start();
    System.out.println("쓰레드를 시작했습니다.");

    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t1.interrupt();
    t1.stop();
    System.out.println("쓰레드를 종료했습니다.");
  }
}

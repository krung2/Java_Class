package kr.hs.dgsw.javaClass.thread;

public class MainClass {

  public static void main(String[] args) {

    MyThread t1 = new MyThread();
    t1.setName("쓰레드 1");
    t1.setPriority(Thread.MAX_PRIORITY);
    t1.start();

    System.out.println("t1.getId() : " + t1.getId());
    System.out.println("t1.getName() : " + t1.getName());

    Runnable r1 = new MyRunnable();
    Thread t2 = new Thread(r1);
    t2.setPriority(Thread.MIN_PRIORITY);
    t2.start();

    System.out.println("t2.getId() : " + t2.getId());

    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.getStackTrace();
    }

    for (int i = 0; i < 100;i ++) {

      System.out.println("Main : " + i);
    }

    System.out.println("t1.isAlive() : " + t1.isAlive());
  }
}

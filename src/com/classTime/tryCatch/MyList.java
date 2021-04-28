package com.classTime.tryCatch;

public class MyList {

  private Node head;

  public void insert (String value) {

    Node newNode = new Node();
    newNode.setData(value);

    if (head == null) {

      head = newNode;
    } else {

      Node pointer = head;
      while (true) {

        if (pointer.getNext() == null) {

          pointer.setNext(newNode);
          break;
        } else {

          pointer = pointer.getNext();
        }
      }
    }
  }

  public Node getNode (int index) {

    Node node = head;

    if (node == null || index < 0) {

      throw new ArrayIndexOutOfBoundsException();
    }


    for (int i = 0;i < index; i ++) {

      node = node.getNext();
      if (node == null) {

        throw new ArrayIndexOutOfBoundsException();
      }
    }

    return node;
  }

  public String read (int index) {

    Node node = this.getNode(index);

    return node.getData();
  }

  public void delete (int index) {

    if (index > 0) {

      Node prevNode = getNode(index - 1);
      Node node = prevNode.getNext();
      if (node == null) {

        prevNode.setNext(null);
      } else {

        prevNode.setNext(node.getNext());
      }
    } else {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  public int length () {

    Node pointer = head;
    int count = 0;

    while (pointer != null) {
      count ++;
      pointer = pointer.getNext();
    }

    return count;
  }

  public static void main(String[] args) {
    MyList list = new MyList();

    System.out.println("리스트의 길이 : " + list.length());

    list.insert("자바");
    list.insert("네트워크");
    list.insert("데이터베이스");

    System.out.println("리스트의 길이 : " + list.length());
    System.out.println(list.read(1));

    list.delete(2);

    System.out.println("리스트의 길이 : " + list.length());
    System.out.println(list.read(2)); // ArrayIndexOutOfBoundsException 발생
    System.out.println(list.read(-1)); // ArrayIndexOutOfBoundsException 발생
  }
}

package com.classTime.api.PhoneBook;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Book {

  private final Map<String, Data> map = new HashMap<>();

  public void insert (String name, String phoneNum, String address) {
    Data data = new Data(phoneNum, address);

    map.put(name, data);
  }

  public void remove (String name) {
    map.remove(name);
  }

  public void displayName () {

    System.out.println("등록된 이름은 : \n");

    Set<String> names = map.keySet();
    for (String name: names) {
      System.out.println(name);
    }
  }

  public void display () {

    System.out.println("\n등록된 정보 : ");

    Set<String> names = map.keySet();
    for (String name: names) {

      System.out.print(name + " : ");

      Data value = map.get(name);
      value.display();
    }
  }

  public static void main(String[] args) {
    Book book = new Book();

    book.insert("신중빈", "010-2395-4337", "부산광역시");
    book.insert("손민재", "010-1234-5678", "대구광역시");
    book.insert("전해윤", "010-6543-0987", "대구광역시");

    book.display();

    book.remove("전해윤");

    book.display();
  }
}

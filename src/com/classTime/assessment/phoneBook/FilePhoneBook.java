package com.classTime.assessment.phoneBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePhoneBook implements PhoneBook {

  private static final String FILE_PATH = "/Users/krung2/Documents/Github/JAVA_Class/src/com/classTime/assessment/phoneBook/";
  public static final String FILE_NAME = "phoneBook.dat";

  private File file;
  
  // 이 문자열은 무조건 바꿔주세요!!
  private String splitMsg = "     ";

  public FilePhoneBook() {
    file = new File(FILE_PATH + FILE_NAME);
  }


  @Override
  public List<NameCard> getList() {

    try {

      List<NameCard> cardList = new ArrayList<NameCard>();

      BufferedReader br = new BufferedReader(new FileReader(file));

      String line = null;
      int cnt = 1;
      while ((line = br.readLine()) != null) {

        String[] cardInfo = line.split(splitMsg);

        NameCard nameCard = new NameCard();

        nameCard.setId(cnt);
        nameCard.setName(cardInfo[0]);
        nameCard.setPhoneNumber(cardInfo[1]);
        nameCard.setAddress(cardInfo[2]);

        cardList.add(nameCard);
        cnt ++;
      }

      br.close();

      return cardList;

    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  @Override
  public NameCard getCard(int id) {

    List<NameCard> cardList  = this.getList();

    for (NameCard nameCard : cardList) {

      if (nameCard.id == id) {

        return nameCard;
      }
    }

    System.out.println("찾는 카드id는 존재하지 않습니");
    return null;
  }

  @Override
  public NameCard getCard(String name) {

    List<NameCard> cardList  = this.getList();

    for (NameCard nameCard : cardList) {

      if (nameCard.name.equals(name)) {

        return nameCard;
      }
    }

    System.out.println("찾는 이름은 존재하지 않습니다");
    return null;
  }

  @Override
  public int addCard(String name, String phoneNumber, String address) {

    try {

      BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

      bw.write(name + splitMsg + phoneNumber + splitMsg + address +"\n");
      bw.close();

      return 1;

    } catch (Exception e) {

      e.printStackTrace();
      return 0;
    }

  }

  @Override
  public boolean removeCard(int id) {

    List<NameCard> cardList = this.getList();

    file.delete();

    for (NameCard nameCard: cardList) {

      if (nameCard.id == id) {

        cardList.remove(nameCard);
        break;
      }
    }

    addNameCard(cardList);

    return false;
  }

  @Override
  public void updateCard(int id, String name, String phoneNumber, String address) {

    List<NameCard> cardList = this.getList();

    file.delete();

    for (NameCard nameCard: cardList) {

      if (nameCard.id == id) {

        nameCard.setName(name);
        nameCard.setPhoneNumber(phoneNumber);
        nameCard.setAddress(address);
        break;
      }
    }

    addNameCard(cardList);
  }

  @Override
  public int size() {

    List<NameCard> cardList = this.getList();

    return cardList.size();
  }

  private void addNameCard (List<NameCard> cardList) {

    for (NameCard nameCard: cardList) {

      this.addCard(
              nameCard.getName(),
              nameCard.getPhoneNumber(),
              nameCard.getAddress()
      );
    }
  }
}

package com.classTime.assessment.newPhoneBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBPhoneBook implements PhoneBook {

  private Connection getConnection() throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
//    return DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "1234");
    return DriverManager.getConnection("jdbc:mariadb://211.53.209.159/dgsw_java", "dgsw_student", "1234");
  }

  private NameCard setNameCard (ResultSet rs) throws Exception{

    NameCard card = new NameCard();

    int idx = rs.getInt("id");
    String name = rs.getString("name");
    String phoneNumber = rs.getString("phone_number");
    String address = rs.getString("address");

    card.setId(idx);
    card.setName(name);
    card.setPhoneNumber(phoneNumber);
    card.setAddress(address);

    return card;
  }

  @Override
  public List<NameCard> getList() {

    try {

      Connection con = this.getConnection();

      String sql = "SELECT * FROM phone_book";
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      List<NameCard> cardList = new ArrayList<NameCard>();

      while (rs.next()) {

        NameCard card = this.setNameCard(rs);

        cardList.add(card);
      }

      rs.close();
      pstmt.close();
      con.close();

      return cardList;
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  @Override
  public NameCard getCard(int id) {

    try {

      Connection con = this.getConnection();

      String sql = "SELECT * FROM phone_book WHERE id = ?";
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, id);

      ResultSet rs = pstmt.executeQuery();

      NameCard nameCard = null;

      if (rs.next()) {

        nameCard = this.setNameCard(rs);
      }

      rs.close();
      pstmt.close();
      con.close();

      return  nameCard;

    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  @Override
  public NameCard getCard(String name) {

    try {

      Connection con = this.getConnection();

      String sql = "SELECT * FROM phone_book WHERE name = ?";
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, name);

      ResultSet rs = pstmt.executeQuery();

      NameCard nameCard = null;

      if (rs.next()) {

        nameCard = this.setNameCard(rs);
      }

      rs.close();
      pstmt.close();
      con.close();

      return  nameCard;

    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  @Override
  public int addCard(String name, String phoneNumber, String address) {

    try {

      Connection con = this.getConnection();

      StringBuilder sql = new StringBuilder();

      sql.append(" INSERT INTO phone_book ");
      sql.append(" (name, phone_number, address) ");
      sql.append(" VALUES ");
      sql.append(" (?, ?, ?) ");

      PreparedStatement pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, name);
      pstmt.setString(2, phoneNumber);
      pstmt.setString(3, address);

      pstmt.executeUpdate();

      pstmt.close();
      con.close();

      return 1;

    } catch (Exception e) {

      e.printStackTrace();
    }

    return 0;
  }

  @Override
  public boolean removeCard(int id) {

    try {

      Connection con = this.getConnection();

      StringBuilder sql = new StringBuilder();

      sql.append(" DELETE FROM phone_book ");
      sql.append(" WHERE ");
      sql.append(" id = ? ");

      PreparedStatement pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, id);

      pstmt.executeUpdate();

      pstmt.close();
      con.close();

      return true;

    } catch (Exception e) {

      e.printStackTrace();
    }

    return false;
  }

  @Override
  public void updateCard(int id, String name, String phoneNumber, String address) {

    try {

      Connection con = this.getConnection();

      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE phone_book ");
      sql.append(" SET ");
      sql.append(" name = ?, ");
      sql.append(" phone_number = ?, ");
      sql.append(" address = ? ");
      sql.append(" WHERE id = ? ");

      PreparedStatement pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, name);
      pstmt.setString(2, phoneNumber);
      pstmt.setString(3, address);
      pstmt.setInt(4, id);

      pstmt.executeUpdate();

      pstmt.close();
      con.close();

    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  @Override
  public int size() {

    return this.getList().size();
  }

  @Override
  public List<NameCard> search(String keyword) {

    try {

      Connection con = this.getConnection();

      String sql = "SELECT * FROM phone_book WHERE name LIKE ?";
      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, "%" + keyword + "%");
      ResultSet rs = pstmt.executeQuery();

      List<NameCard> cardList = new ArrayList<NameCard>();

      while (rs.next()) {

        NameCard card = this.setNameCard(rs);

        cardList.add(card);
      }

      rs.close();
      pstmt.close();
      con.close();

      return cardList;
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  @Override
  public void start() {
    Scanner scanner = new Scanner(System.in);

    while (true) {

      System.out.println("-------------------- 주소록 --------------------");
      System.out.println("1. 연락처 등록");
      System.out.println("2. 모든 연락처 조회");
      System.out.println("3. 연락처 검색");
      System.out.println("4. 특정 이름으로 연락처 검색");
      System.out.println("5. 이름으로 연락처 삭제");
      System.out.println("0. 종료\n");


      System.out.println("----------------------------------------------");
      System.out.print("메뉴를 입력해주세요 : ");

      int menu = scanner.nextInt();
      scanner.nextLine();

//      System.out.println("\n");

      switch (menu) {
        case 1:
          System.out.print("이름 : ");
          String name = scanner.nextLine();

          System.out.print("휴대폰 번호 : ");
          String phoneNumber = scanner.nextLine();

          System.out.print("주소 : ");
          String address = scanner.nextLine();

          this.addCard(name, phoneNumber, address);

          break;
        case 2:
          System.out.println("\n");
          List<NameCard> cards = this.getList();

          for (NameCard card : cards) {
            System.out.println(card.toString());
          }
          break;

        case 3:
          System.out.print("검색할 이름을 입력하세요 : ");
          String keyword = scanner.nextLine();
          List<NameCard> searchCard = this.search(keyword);

          for (NameCard card : searchCard) {
            System.out.println(card.toString());
          }

          break;

        case 4:
          System.out.print("정확한 이름을 입력하세요 : ");
          String searchName = scanner.nextLine();

          System.out.print(this.getCard(searchName).toString());
          break;

        case 5:
          System.out.print("삭제할 사람의 정확한 이름을 입력하세요 : ");
          String searchName1 = scanner.nextLine();

          NameCard searchCard1 = this.getCard(searchName1);

          if (searchCard1 == null) {

            System.out.println("입력한 이름은 없습니다");
            break;
          }

          this.removeCard(searchCard1.getId());
          break;

        case 0:
          System.out.println("프로그램을 종료합니다");
          return;
        default:
          System.out.println("다시 입력해주세요\n");
          continue;
      }

      System.out.println("\n\n\n");
    }

  }

  public static void main(String[] args) {
    DBPhoneBook dbPhoneBook = new DBPhoneBook();

    dbPhoneBook.start();
  }
}

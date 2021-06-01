package com.classTime.jdbc;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudy {

  public NameCard get(int id) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mariadb://211.53.209.159/dgsw_java", "dgsw_student", "1234");

    String sql = "SELECT * FROM phone_book WHERE id = " + id;
    PreparedStatement pstmt = con.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    NameCard nameCard = null;
    if (rs.next()) {

      int idx = rs.getInt("id");
      String name = rs.getString("name");
      String phoneNumber = rs.getString("phone_number");
      String address = rs.getString("address");

      nameCard = new NameCard();
      nameCard.setId(idx);
      nameCard.setName(name);
      nameCard.setPhoneNumber(phoneNumber);
      nameCard.setAddress(address);
    }

    rs.close();
    pstmt.close();

    con.close();

    return nameCard;
  }

  public NameCard get(String name) throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mariadb://211.53.209.159/dgsw_java", "dgsw_student", "1234");

    String sql = "SELECT * FROM phone_book WHERE name=" + name;
    PreparedStatement pstmt = con.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    NameCard nameCard = null;
    if (rs.next()) {

      int idx = rs.getInt("id");
      String dbName = rs.getString("name");
      String phoneNumber = rs.getString("phone_number");
      String address = rs.getString("address");

      nameCard = new NameCard();
      nameCard.setId(idx);
      nameCard.setName(dbName);
      nameCard.setPhoneNumber(phoneNumber);
      nameCard.setAddress(address);
    }

    rs.close();
    pstmt.close();

    con.close();

    return nameCard;
  }

  public List<NameCard> getList() throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mariadb://211.53.209.159/dgsw_java", "dgsw_student", "1234");

    String sql = "SELECT * FROM phone_book";
    PreparedStatement pstmt = con.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    List<NameCard> cardList = new ArrayList<>();
    while (rs.next()){

      NameCard nameCard = new NameCard();

      int idx = rs.getInt("id");
      String dbName = rs.getString("name");
      String phoneNumber = rs.getString("phone_number");
      String address = rs.getString("address");

      nameCard = new NameCard();
      nameCard.setId(idx);
      nameCard.setName(dbName);
      nameCard.setPhoneNumber(phoneNumber);
      nameCard.setAddress(address);

      cardList.add(nameCard);
    }

    rs.close();
    pstmt.close();

    con.close();

    return cardList;
  }

  public static void main(String[] args) {

    try {

      JdbcStudy jdbcStudy = new JdbcStudy();
      NameCard card = jdbcStudy.get(30);
      NameCard nameCard = jdbcStudy.get("'사승은'");
      List<NameCard> cardList = jdbcStudy.getList();

      if (card == null) {

        System.out.println("데이터가 존재하지 않습니다");
        return;
      }

      System.out.println(
        card.getId() + "  " +
        card.getName() + "  " +
        card.getPhoneNumber() + "  " +
        card.getAddress() + "  "
      );

      if (nameCard == null) {

        System.out.println("데이터가 존재하지 않습니다");
        return;
      }

      System.out.println(
        nameCard.getId() + "  " +
        nameCard.getName() + "  " +
        nameCard.getPhoneNumber() + "  " +
        nameCard.getAddress() + "  "
      );

      for (NameCard card1 : cardList) {

        System.out.println(
          card1.getId() + "  " +
          card1.getName() + "  " +
          card1.getPhoneNumber() + "  " +
          card1.getAddress() + "  "
        );
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

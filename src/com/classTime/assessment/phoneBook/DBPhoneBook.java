package com.classTime.assessment.phoneBook;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}

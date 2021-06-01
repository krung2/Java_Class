package com.classTime.jdbc;

import javax.naming.Name;
import java.lang.constant.Constable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudy {

  private Connection getConnection() throws Exception {

    Class.forName("org.mariadb.jdbc.Driver");
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

  public NameCard get(int id) throws Exception {

    Connection con = this.getConnection();

    String sql = "SELECT * FROM phone_book WHERE id = ? ";
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

    return nameCard;
  }

  public NameCard get(String name) throws Exception {

    Connection con = this.getConnection();

    String sql = "SELECT * FROM phone_book WHERE name= ?";
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

    return nameCard;
  }

  public List<NameCard> getList() throws Exception {

    Connection con = this.getConnection();

    String sql = "SELECT * FROM phone_book";
    PreparedStatement pstmt = con.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    List<NameCard> cardList = new ArrayList<NameCard>();
    while (rs.next()){

      NameCard nameCard = this.setNameCard(rs);

      cardList.add(nameCard);
    }

    rs.close();
    pstmt.close();
    con.close();

    return cardList;
  }

  public void insert(String name, String phoneNumber, String address) throws Exception {

    Connection con = this.getConnection();

    StringBuilder sql = new StringBuilder();

    sql.append("INSERT INTO phone_book ");
    sql.append(" (name, phone_number, address) ");
    sql.append("VALUES ");
    sql.append(" (?, ?, ?) ");

    PreparedStatement pstmt = con.prepareStatement(sql.toString());
    pstmt.setString(1, name);
    pstmt.setString(2, phoneNumber);
    pstmt.setString(3, address);

    pstmt.executeUpdate();

    pstmt.close();
    con.close();
  }

  public void update (int id, String name, String phoneNumber, String address) throws Exception {

    Connection con = this.getConnection();

    StringBuilder sql = new StringBuilder();
    sql.append("UPDATE phone_book SET ");
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
  }

  public void delete (int id) throws Exception {

    Connection con = this.getConnection();

    StringBuilder sql = new StringBuilder();
    sql.append("DELETE FROM phone_book ");
    sql.append(" WHERE id=?");

    PreparedStatement pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, id);

    pstmt.executeUpdate();

    pstmt.close();
    con.close();
  }

  public static void main(String[] args) {

    try {

      JdbcStudy jdbcStudy = new JdbcStudy();
      NameCard card = jdbcStudy.get(30);
      NameCard nameCard = jdbcStudy.get("사승은");
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

//      jdbcStudy.insert("신중빈", "010-1234-5678", "투르크메니스탄");
//      jdbcStudy.update(55, "윤석현", "010-123-4533", "키르기스스탄");
      jdbcStudy.delete(51);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

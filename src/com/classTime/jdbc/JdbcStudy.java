package com.classTime.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

  public static void main(String[] args) {

    try {

      JdbcStudy jdbcStudy = new JdbcStudy();
      NameCard card = jdbcStudy.get(30);

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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

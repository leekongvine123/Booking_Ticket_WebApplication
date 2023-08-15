/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seat;

import db.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class seatDAO extends DBUtils {

    public List<seatDTO> list(int seatID) {

        ArrayList<seatDTO> list;
        list = new ArrayList<seatDTO>();

        String sql = "select seatID, seatNum from seat ";

        String where = "";
        String whereJoinWord = " where ";

        if (seatID != 0) {
            where += whereJoinWord;
            where += " seatID = ? ";
            whereJoinWord = " and ";
        }

        try {
            sql += where;
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            if (seatID != 0) {
                ps.setInt(index, seatID);

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new seatDTO(
                        rs.getInt("seatID"),
                        rs.getString("seatNum")
                ));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public List<seatDTO> listSeatOrder(int orderID) {

        ArrayList<seatDTO> list2;
        list2 = new ArrayList<seatDTO>();

        String sql = "select seatID from OrderDetail od join  [Order] o on od.orderID = o.orderID where o.orderID = ?";

        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                seatDTO s = list(rs.getInt("seatID")).get(0);
                list2.add(s);
            }

            return list2;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    /*
    Load student with id
     */
    public static void main(String[] args) {
        seatDAO s = new seatDAO();
        List<seatDTO> list = s.listSeatOrder(1);
        for (seatDTO dTO : list) {
            System.out.println(dTO.getSeatNum());
        }
    }
}

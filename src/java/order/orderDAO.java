/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import db.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import journey.journeyDAO;
import seat.seatDAO;
import seat.seatDTO;
import user.userDAO;

/**
 *
 * @author Admin
 */
public class orderDAO {

    public List<orderDTO> listSort(String condition, int orderID) {
        ArrayList<orderDTO> list;
        list = new ArrayList<orderDTO>();

        String sql = "select r.[orderID], r.[quantity], r.totalPrice, r.userID, r.journeyID, r.status, r.orderDob from [Order] r ";
        String where = "";
        if (orderID != 0) {
            where += " JOIN Journey j ON r.journeyID = j.journeyID where j.routeID = ? ";
        }
        if (condition != null && !condition.trim().isEmpty() && condition.equals("old")) {
            where += " ORDER BY r.orderID ASC ";
        }
        if (condition != null && !condition.trim().isEmpty() && condition.equals("new")) {
            where += " ORDER BY r.orderID DESC";
        }

        try {
            sql += where;
            System.out.println("" + sql);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            if (orderID != 0) {
                ps.setInt(1, orderID);

            }

            ResultSet rs = ps.executeQuery();
            userDAO u = new userDAO();
            journeyDAO j = new journeyDAO();
            seatDAO s = new seatDAO();
            while (rs.next()) {

                list.add(new orderDTO(rs.getInt("orderID"), u.list(rs.getInt("userID"), null, null, null, false).get(0), j.list(rs.getInt("journeyID"), 0, 0, null, null, null, null, null).get(0), s.listSeatOrder(rs.getInt("orderID")), rs.getInt("quantity"), rs.getDouble("totalPrice"), rs.getString("status"), rs.getDate("orderDob")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage() + ex.getErrorCode());
        }
        return null;	// your code

        //
    }

    public List<orderDTO> list(int orderID, int quanity, double totalPrice, int userID, int journeyID, int seatID, String status, Date orderDob) {

        ArrayList<orderDTO> list;
        list = new ArrayList<orderDTO>();

        String sql = "select orderID, [quantity], totalPrice, userID, journeyID, status, orderDob from [Order] ";
        String where = "";
        String whereJoinWord = " where ";

        if (orderDob != null) {
            where += whereJoinWord;
            where += " orderDob = ? ";
            whereJoinWord = " and ";
        }

        if (orderID != 0) {
            where += whereJoinWord;
            where += " orderID = ? ";
            whereJoinWord = " and ";
        }
        if (userID != 0) {

            where += whereJoinWord;
            where += " userID = ? ";
            whereJoinWord = " and ";

        }
        if (quanity != 0) {

            where += whereJoinWord;
            where += " quanity = ? ";
            whereJoinWord = " and ";

        }
        if (totalPrice != 0) {

            where += whereJoinWord;
            where += " totalPrice = ? ";
            whereJoinWord = " and ";

        }
        if (journeyID != 0) {

            where += whereJoinWord;
            where += " journeyID = ? ";
            whereJoinWord = " and ";

        }
        if (status != null && status != "") {

            where += whereJoinWord;
            where += " status = ? ";
            whereJoinWord = " and ";

        }
        try {
            sql += where;

            if ((seatID != 0 && journeyID != 0) && ((orderID == 0) && (userID == 0) && (quanity == 0) && (totalPrice == 0) && (orderDob == null) && (status == null))) {
                sql = "select o.orderID , o.quantity, o.totalPrice ,o.userID, o.journeyID, o.status, o.orderDob from OrderDetail od join [Order] o on od.orderID = o.orderID "
                        + "where o.journeyID = ? and od.seatID = ?";
            }
            if ((seatID != 0 && journeyID != 0 && orderID != 0) && ((userID == 0) && (quanity == 0) && (totalPrice == 0) && (orderDob == null) && (status == null))) {
                sql = "select o.orderID , o.quantity, o.totalPrice ,o.userID, o.journeyID, o.status, o.orderDob from OrderDetail od join [Order] o on od.orderID = o.orderID "
                        + "where  o.orderID = ? and o.journeyID = ? and od.seatID = ? ";
            }

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;

            if (orderDob != null) {
                ps.setDate(index, orderDob);
                index++;
            }

            if (orderID != 0) {
                ps.setInt(index, orderID);
                index++;
            }
            if (userID != 0) {
                ps.setInt(index, userID);
                index++;
            }
            if (journeyID != 0) {
                ps.setInt(index, journeyID);
                index++;
            }
            if (quanity != 0) {
                ps.setInt(index, quanity);
                index++;
            }
            if (totalPrice != 0) {
                ps.setDouble(index, totalPrice);
                index++;
            }
            if (status != null && status != "") {
                ps.setString(index, status);
                index++;
            }
            if ((seatID != 0 && journeyID != 0) && (orderID == 0) && (userID == 0) && (quanity == 0) && (totalPrice == 0) && (orderDob == null) && (status == null)) {
                ps.setInt(index, seatID);
                index++;
            }
            if ((seatID != 0 && journeyID != 0 && orderID != 0) && (userID == 0) && (quanity == 0) && (totalPrice == 0) && (orderDob == null) && (status == null)) {
                ps.setInt(index, seatID);
                index++;
            }

            ResultSet rs = ps.executeQuery();
            userDAO u = new userDAO();
            journeyDAO j = new journeyDAO();
            seatDAO s = new seatDAO();
            while (rs.next()) {

                list.add(new orderDTO(rs.getInt("orderID"), u.list(rs.getInt("userID"), null, null, null, false).get(0), j.list(rs.getInt("journeyID"), 0, 0, null, null, null, null, null).get(0), s.listSeatOrder(rs.getInt("orderID")), rs.getInt("quantity"), rs.getDouble("totalPrice"), rs.getString("status"), rs.getDate("orderDob")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage() + ex.getErrorCode());
        }
        return null;	// your code

        //
    }

    public boolean insertOrder(orderDTO o) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([userID]\n"
                + "           ,[journeyID]\n"
                + "            ,[totalPrice]\n"
                + "           ,[quantity]\n"
                + "           ,[status],orderDob)\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?,?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getUserID().getUserID());
            ps.setInt(2, o.getJourneyID().getJourneyID());
            ps.setDouble(3, o.getTotalPrice());
            ps.setInt(4, o.getQuantity());
            ps.setString(5, o.getStatus());
            ps.setDate(6, o.getOrderDob());
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertOrderDetail(int orderID, int seatID) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([orderID]\n"
                + "           ,[seatID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.setInt(2, seatID);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOrder(orderDTO order) {
        String sql = "DELETE FROM [Order] WHERE orderID = ? AND [status] = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getOrderID());
            ps.setString(2, "p");

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOrderM(int order) {
        String sql = "DELETE FROM [Order] WHERE orderID = ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOrderDetailM(int order) {
        String sql = "DELETE FROM [OrderDetail] WHERE orderID = ?  ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOrderDetail(orderDTO order) {
        String sql = "DELETE FROM [OrderDetail] WHERE orderID = (select orderID from [Order] WHERE orderID = ? AND [status] = ? ) ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getOrderID());
            ps.setString(2, "p");

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateOrder(orderDTO order) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [status] = ?\n"
                + " WHERE orderID = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "d");
            ps.setInt(2, order.getOrderID());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateOrderAdmin(int orderID, int journeyID, double totalPrice, int quanity) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET "
                + "      [journeyID] = ?\n"
                + "      ,[totalPrice] = ?\n"
                + "      ,[quantity] = ?\n"
                + " WHERE orderID =?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, journeyID);
            ps.setDouble(2, totalPrice);
            ps.setInt(3, quanity);
            ps.setInt(4, orderID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public static void main(String[] args) {
        orderDAO o = new orderDAO();

        List<orderDTO> list = o.listSort("new", 3);

        for (orderDTO dTO : list) {
            System.out.println(dTO);
        }
    }

}

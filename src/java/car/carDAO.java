/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

import db.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seat.seatDTO;

public class carDAO {

    public List<carDTO> list(int carID, String carType, String cabin, String wifi, boolean status) {

        ArrayList<carDTO> list;
        list = new ArrayList<carDTO>();

        String sql = " select carID, carType, cabin, wifi, licensePlate,[status] from Car ";

        String where = "";
        String whereJoinWord = " where ";

        if (status != false) {
            where += whereJoinWord;
            where += " [status] = ? ";
            whereJoinWord = " and ";
        }
        if (carID != 0) {
            where += whereJoinWord;
            where += " carID = ? ";
            whereJoinWord = " and ";
        }
        if (carType != null && carType != "") {
            where += whereJoinWord;
            where += "  carType = ?  ";
            whereJoinWord = " and ";
        }
        if (cabin != null && cabin != "") {
            where += whereJoinWord;
            where += "  cabin = ?  ";
            whereJoinWord = " and ";
        }
        if (wifi != null && wifi != "") {
            where += whereJoinWord;
            where += "  wifi = ?  ";
            whereJoinWord = " and ";
        }

        try {
            sql += where;
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            if (status != false) {
                ps.setBoolean(index, status);
                index++;
            }

            if (carID != 0) {
                ps.setInt(index, carID);
                index++;

            }

            if (carType != null && carType != "") {
                ps.setString(index, carType);
                index++;

            }
            if (cabin != null && cabin != "") {
                ps.setString(index, cabin);
                index++;

            }
            if (wifi != null && wifi != "") {
                ps.setString(index, wifi);
                index++;

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new carDTO(
                        rs.getInt("carID"),
                        rs.getString("carType"),
                        rs.getString("cabin"),
                        rs.getString("wifi"),
                        rs.getString("licensePlate"),
                        rs.getBoolean("status")
                ));

            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public boolean carUpdate(String carType, String cabin, String wifi, int carID) {
        String sql = "UPDATE [dbo].[Car]\n"
                + "   SET [carType] = ?\n"
                + "      ,[cabin] =   ?\n"
                + "      ,[wifi] =   ?\n"
                + " WHERE [carID] =   ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, carType);
            ps.setString(2, cabin);
            ps.setString(3, wifi);
            ps.setInt(4, carID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean carUpdateStatus(int carID, boolean status) {
        String sql = "UPDATE [dbo].[Car]\n"
                + "   SET [status] = ?\n"
                + " WHERE [carID] =   ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, carID);;
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return false;
    }

    public boolean carInsert(String carTpe, String carCabin, String carWifi, String license, boolean status) {
        String sql = "INSERT INTO [dbo].[Car]\n"
                + "           ([carType]\n"
                + "           ,[cabin]\n"
                + "           ,[wifi]\n"
                + "           ,[licensePlate]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            System.out.println("" + sql);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, carTpe);
            ps.setString(2, carCabin);
            ps.setString(3, carWifi);
            ps.setString(4, license);
            ps.setBoolean(5, status);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        carDAO c = new carDAO();
        for (carDTO object : c.list(0, null, null, null, true)) {
            System.out.println(object);
        }
      

    }

}

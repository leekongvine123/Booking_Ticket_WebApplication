/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package location;

import db.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Admin
 */
public class locationDAO {
    public static void main(String[] args) {
        locationDAO l = new locationDAO();
         List<locationDTO> list = l.search("K");
         System.out.println("" + list);
    }
    public List<locationDTO> search(String name) {
        List<locationDTO> list = new ArrayList<>();
        String sql = "SELECT [locationID], [locationCity], [locationStation], [locationImg],[status] FROM [Location] WHERE [locationCity] LIKE ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] locationImg = rs.getBytes("locationImg");
                String base64Image = Base64.getEncoder().encodeToString(locationImg);

                list.add(new locationDTO(rs.getInt("locationID"), rs.getString("locationCity"), rs.getString("locationStation"), base64Image, rs.getBoolean("status"))
                );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<locationDTO> list(int locationID, boolean status) {

        ArrayList<locationDTO> list;
        list = new ArrayList<locationDTO>();

        String sql = "select locationID, locationCity, locationStation, locationImg,[status] from Location ";

        String where = "";
        String whereJoinWord = " where ";
        if (status != false) {
            where += whereJoinWord;
            where += " [status] = ? ";
            whereJoinWord = " and ";
        }
        if (locationID != 0) {
            where += whereJoinWord;
            where += " locationID = ? ";
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
            if (locationID != 0) {
                ps.setInt(index, locationID);
                index++;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] locationImg = rs.getBytes("locationImg");
                String base64Image = Base64.getEncoder().encodeToString(locationImg);

                list.add(new locationDTO(rs.getInt("locationID"), rs.getString("locationCity"), rs.getString("locationStation"), base64Image, rs.getBoolean("status"))
                );
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public static String removeLastComma(String input) {
        int lastIndex = input.lastIndexOf(",");
        if (lastIndex >= 0) {
            return input.substring(0, lastIndex) + input.substring(lastIndex + 1);
        } else {
            return input;
        }
    }

    public boolean insertLocation(String locationCity, String locationStation, byte[] imageData) {
        String sql = "INSERT INTO [dbo].[Location]\n"
                + "           ([locationCity]\n"
                + "           ,[locationStation]\n"
                + "           ,[locationImg]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "             ,?)";
        try {
            System.out.println("" + sql);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, locationCity);
            ps.setString(2, locationStation);
            ps.setBytes(3, imageData);
            ps.setBoolean(4, true);
            ResultSet rs = ps.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("Loi o insert location o locationDAO" + e.getMessage());
        }
        return false;
    }

    public boolean locationStatusUpdate(boolean status, int locationID) {
        String sql = "UPDATE [dbo].[Location]\n"
                + "   SET [status] = ? WHERE [locationID] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, locationID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return false;
    }

    public boolean updateLocation(int locationID, String locationCity, String locationStation, byte[] imageData) {
        String sql = "UPDATE [dbo].[Location]\n"
                + "   SET ";
        String where = "";
        String whereJoinWord = " WHERE ";
        if (locationCity != null & !locationCity.trim().isEmpty()) {
            sql += "[locationCity] = ?" + ",";
        }
        if (locationStation != null & !locationStation.trim().isEmpty()) {
            sql += "[locationStation] = ?" + ",";
        }
        if (imageData != null && !imageData.equals("")) {
            sql += "[locationImg] = ?" + ",";
        }
        if (locationID != 0) {
            where += whereJoinWord;
            where += " [locationID] = ? ";
        }
        try {
            sql += where;
            sql = removeLastComma(sql);
            System.out.println("" + sql);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int index = 1;
            if (locationCity != null & !locationCity.trim().isEmpty()) {
                ps.setString(index, locationCity);
                index++;
            }
            if (locationStation != null & !locationStation.trim().isEmpty()) {
                ps.setString(index, locationStation);
                index++;
            }
            if (imageData != null && !imageData.equals("")) {
                ps.setBytes(index, imageData);
                index++;
            }
            if (locationID != 0) {
                ps.setInt(index, locationID);
                index++;

            }
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("locationDAO UPDATE" + e.getMessage());
        }
        return false;
    }

    /*
    Load student with id
//     */
//    public static void main(String[] args) {
//        locationDAO s = new locationDAO();
//         << << << < HEAD
//        
//        List<locationDTO> list = s.listLocation(null, null, 0);
//         == == ==
//                = List < locationDTO > list = s.list(0, false);
//         >>> >>> > 2e6d12c56ad3a83e87f53bb931e16da42a14d904
//        for (locationDTO dTO : list) {
//            System.out.println(dTO.getLocationID());
//        }
//    }
}

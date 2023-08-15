/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.PrintWriter;
import db.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Admin
 */
public class userDAO {

    public void insertUser(userDTO dto) {
        String query = "INSERT INTO [dbo].[User]\n"
                + "           ([roleID]\n"
                + "           ,[userName]\n"
                + "           ,[userPassword]\n"
                + "           ,[userPhoneNumber]\n"
                + "           ,[userMail]\n"
                + "           ,[userFirstName]\n"
                + "           ,[userLastName]\n"
                + "           ,[userDob]\n"
                + "           ,[userGender],[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?,?)";
        try {
            Connection con = db.util.DBUtils.getConnection();
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, dto.getRoleID());
            pr.setString(2, dto.getUserName());
            pr.setString(3, dto.getUserPassword());
            pr.setString(4, dto.getUserPhoneNumber());
            pr.setString(5, dto.getUserMail());
            pr.setString(6, dto.getUserFirstName());
            pr.setString(7, dto.getUserLastName());

            java.util.Date userDob = dto.getUserDob();
            if (userDob != null) {
                java.sql.Date sqlUserDob = new java.sql.Date(userDob.getTime());
                pr.setDate(8, sqlUserDob);
            } else {
                pr.setNull(8, java.sql.Types.DATE);
            }

            pr.setString(9, dto.getUserGender());
            pr.setBoolean(10, dto.isStatus());
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("Loi o insertUser class userDAO" + e.getMessage());
        }
    }

    public void updateUser(String userName, String userPassword, String userPhoneNumber, int userID, String userFirstName, String userLastName, Date userDob1, String userGender) {
        String query = "UPDATE [dbo].[User]\n"
                + "SET                     ";

        String where = "";
        String whereJoinWord = " where ";

        if (userName != null && !userName.trim().isEmpty()) {
            query += "[userName] =  ?" + ",";
        }
        if (userFirstName != null && !userFirstName.trim().isEmpty()) {
            query += "[userFirstName] =  ?" + ",";
        }
        if (userLastName != null && !userLastName.trim().isEmpty()) {
            query += "[userLastName] =  ?" + ",";
        }
        if (userDob1 != null) {
            query += "[userDob] =  ?" + ",";
        }
        if (userGender != null && !userGender.trim().isEmpty()) {
            query += "[userGender] =  ?" + ",";
        }
        if (userPassword != null && !userPassword.trim().isEmpty()) {
            query += "[userPassword] =  ?" + ",";
        }

        if (userPhoneNumber != null && !userPhoneNumber.trim().isEmpty()) {
            query += "[userPhoneNumber] =  ?" + ",";
        }

        if (userID != 0) {
            where += whereJoinWord;
            where += " [userID] = ? ";
        }
        try {

            query += where;
            query = removeLastComma(query);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            int index = 1;

            if (userName != null && !userName.trim().isEmpty()) {
                ps.setString(index, userName);
                index++;
            }
            if (userFirstName != null && !userFirstName.trim().isEmpty()) {
                ps.setString(index, userFirstName);
                index++;
            }
            if (userLastName != null && !userLastName.trim().isEmpty()) {
                ps.setString(index, userLastName);
                index++;
            }
            if (userDob1 != null) {
                java.util.Date userDob = userDob1;
                java.sql.Date sqlUserDob = new java.sql.Date(userDob.getTime());
                ps.setDate(index, sqlUserDob);
                index++;
            }
            if (userGender != null && !userGender.trim().isEmpty()) {
                ps.setString(index, userGender);
                index++;
            }
            if (userPassword != null && !userPassword.trim().isEmpty()) {
                ps.setString(index, userPassword);
                index++;
            }
            if (userPhoneNumber != null && !userPhoneNumber.trim().isEmpty()) {
                ps.setString(index, userPhoneNumber);
                index++;
            }
            if (userID != 0) {
                ps.setInt(index, userID);
                index++;
            }
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Loi o updateUser " + e.getMessage());
        }

    }
// Function to remove the last comma from a string

    public static String removeLastComma(String input) {
        int lastIndex = input.lastIndexOf(",");
        if (lastIndex >= 0) {
            return input.substring(0, lastIndex) + input.substring(lastIndex + 1);
        } else {
            return input;
        }
    }

    public List<userDTO> list(int userID, String userName, String userPassword, String userMail, boolean status) {
        ArrayList<userDTO> list;
        list = new ArrayList<userDTO>();

        String sql = "select userID, roleID, userName, userPassword, userPhoneNumber,userMail, userFirstName, userLastName, userDob, userGender,[status] from [User] ";

        String where = "";
        String whereJoinWord = " where ";

        if (status != false) {
            where += whereJoinWord;
            where += " [status] = ? ";
            whereJoinWord = " and ";
        }

        if (userID != 0) {
            where += whereJoinWord;
            where += " userID = ? ";
            whereJoinWord = " and ";
        }
        if (userName != null) {
            where += whereJoinWord;
            where += " userName = ? ";
            whereJoinWord = " and ";
        }
        if (userPassword != null) {
            where += whereJoinWord;
            where += " userPassword = ? ";
            whereJoinWord = " and ";
        }
        if (userMail != null) {
            where += whereJoinWord;
            where += " userMail = ? ";
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

            if (userID != 0) {
                ps.setInt(index, userID);
                index++;
            }

            if (userName != null) {
                ps.setString(index, userName);
                index++;
            }

            if (userPassword != null) {
                ps.setString(index, userPassword);
                index++;
            }

            if (userMail != null) {
                ps.setString(index, userMail);
                index++;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new userDTO(rs.getInt("userID"), rs.getInt("roleID"), rs.getString("userName"), rs.getString("userPassword"),
                        rs.getString("userPhoneNumber"), rs.getString("userMail"), rs.getString("userFirstName"),
                        rs.getString("userLastName"), rs.getDate("userDob"), rs.getString("userGender"), rs.getBoolean("status")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public static void main(String[] args) {
        userDAO udao = new userDAO();


    }

}

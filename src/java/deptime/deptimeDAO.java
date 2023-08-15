/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deptime;

import db.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seat.seatDTO;

/**
 *
 * @author Admin
 */
public class deptimeDAO {

    public List<deptimeDTO> list(int depID, int depTime) {

        ArrayList<deptimeDTO> list;
        list = new ArrayList<deptimeDTO>();

        String sql = "select depID, depTime from deptime ";

        String where = "";
        String whereJoinWord = " where ";

        if (depID != 0) {
            where += whereJoinWord;
            where += " depID = ? ";
            whereJoinWord = " and ";
        }
        if (depTime != 0) {
            where += whereJoinWord;
            where += "  depTime = ? ";
            whereJoinWord = " and ";
        }
        try {
            sql += where;
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            if (depID != 0) {
                ps.setInt(index, depID);
                index++;

            }

            if (depTime != 0) {
                ps.setInt(index, depTime);
                index++;

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new deptimeDTO(
                        rs.getInt("depID"),
                        rs.getInt("depTime")
                ));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public static void main(String[] args) {
        deptimeDAO d = new deptimeDAO();
        List<deptimeDTO> list = d.list(0, 5);
        for (deptimeDTO dTO : list) {
            System.out.println(dTO.getDepTime());
        }
    }
}

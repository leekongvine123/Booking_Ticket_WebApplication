/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route;

import db.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import location.locationDAO;
import location.locationDTO;

/**
 *
 * @author Admin
 */
public class routeDAO {

    public List<routeChartDTO> listcharttop2() {

        ArrayList<routeChartDTO> list = new ArrayList<>();

        String sql = "select top 2   count(ood.orderID) as number, j.routeID from (select o.orderID,o.journeyID from [Order] o join [OrderDetail] od on o.orderID = od.orderID ) ood join Journey j on ood.journeyID =j.journeyID group by j.routeID\n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            locationDAO l = new locationDAO();
            routeDAO rdao = new routeDAO();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routeChartDTO rcdto = new routeChartDTO(rdao.list(rs.getInt("routeID"), 0, 0, false).get(0), 0, rs.getInt("number"));
                list.add(rcdto);
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    
    
    
    
    
    
    
    
    
    public List<routeDTO> list(int routeID, int start, int dest, boolean status) {

        ArrayList<routeDTO> list;
        list = new ArrayList<routeDTO>();

        String sql = "select routeID, routeStartLocation, routeDestLocation, routeTime, routePrice, routeLenght, [status] from Route ";

        String where = "";
        String whereJoinWord = " where ";

        if (status != false) {
            where += whereJoinWord;
            where += " [status] = ? ";
            whereJoinWord = " and ";
        }
        if (routeID != 0) {
            where += whereJoinWord;
            where += " routeID = ? ";
            whereJoinWord = " and ";
        }
        if (dest != 0) {
            where += whereJoinWord;
            where += " routeStartLocation = ? ";
            whereJoinWord = " and ";
        }
        if (start != 0) {
            where += whereJoinWord;
            where += " routeDestLocation = ? ";
            whereJoinWord = " and ";
        }

        try {
            sql += where;
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            locationDAO l = new locationDAO();
            int index = 1;

            if (status != false) {
                ps.setBoolean(index, status);
                index++;
            }
            if (routeID != 0) {
                ps.setInt(index, routeID);
                index++;
            }
            if (start != 0) {
                ps.setInt(index, start);
                index++;
            }
            if (dest != 0) {
                ps.setInt(index, dest);
                index++;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new routeDTO(rs.getInt("routeID"), l.list(rs.getInt("routeStartLocation"), false).get(0), l.list(rs.getInt("routeDestLocation"), false).get(0), rs.getInt("routeTime"), rs.getDouble("routePrice"), rs.getInt("routeLenght"), rs.getBoolean("status")));
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public boolean updateRoute(int start, int dest, int time, int price, int lenght, int routeId) {
        String sql = "UPDATE [dbo].[Route]\n"
                + "   SET [routeStartLocation] = ?"
                + "      ,[routeDestLocation] = ?"
                + "      ,[routeTime] = ?"
                + "      ,[routePrice] = ?\n"
                + "      ,[routeLenght] = ?\n"
                + " WHERE [routeID] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, dest);
            ps.setInt(3, time);
            ps.setInt(4, price);
            ps.setInt(5, lenght);
            ps.setInt(6, routeId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return false;
    }

    public List<routeChartDTO> listchart() {

        ArrayList<routeChartDTO> list = new ArrayList<>();

        String sql = "select SUM(totalPrice) as total,j.routeID from [Order] o join Journey j on o.journeyID = j.journeyID group by j.routeID";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            locationDAO l = new locationDAO();
            routeDAO rdao = new routeDAO();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routeChartDTO rcdto = new routeChartDTO(rdao.list(rs.getInt("routeID"), 0, 0, false).get(0), rs.getDouble("total"), 0);
                list.add(rcdto);
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public List<routeChartDTO> listcharttop3() {

        ArrayList<routeChartDTO> list = new ArrayList<>();

        String sql = "select top 3   count(ood.orderID) as number, j.routeID from (select o.orderID,o.journeyID from [Order] o join [OrderDetail] od on o.orderID = od.orderID ) ood join Journey j on ood.journeyID =j.journeyID group by j.routeID\n";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            locationDAO l = new locationDAO();
            routeDAO rdao = new routeDAO();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                routeChartDTO rcdto = new routeChartDTO(rdao.list(rs.getInt("routeID"), 0, 0, false).get(0), 0, rs.getInt("number"));
                list.add(rcdto);
            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public boolean updateRouteStatus(int routeStart, int routeDest, boolean status) {
        String sql = "UPDATE [dbo].[Route]\n"
                + "   SET [status] = ?\n";

        String where = "";
        String whereJoinWord = " WHERE ";

        if (routeStart != 0) {
            where += whereJoinWord;
            where += "[routeStartLocation] = ? ";
        }
        if (routeDest != 0) {
            where += whereJoinWord;
            where += "[routeDestLocation] = ? ";
        }
        try {
            sql += where;
            System.out.println("" + sql);
            int index = 1;
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (routeStart != 0) {
                ps.setInt(index, routeStart);
                index++;
            }
            if (routeDest != 0) {
                ps.setInt(index, routeDest);
                index++;
            }
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateListRouteStatus(int routeId, boolean status) {
        String sql = "UPDATE [dbo].[Route]\n"
                + "   SET [status] = ?\n";

        String where = "";
        String whereJoinWord = " WHERE ";
        if (routeId != 0) {
            where += whereJoinWord;
            where += "[routeId] = ? ";
        }

        try {
            sql += where;
            System.out.println("" + sql);
            int index = 1;
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, routeId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //select top 3 count(ood.orderID), j.routeID from (select o.orderID,o.journeyID from [Order] o join [OrderDetail] od on o.orderID = od.orderID ) ood join Journey j on ood.journeyID =j.journeyID group by j.routeID

    //select SUM(totalPrice), j.routeID from [Order] o join Journey j on o.journeyID = j.journeyID group by j.routeID
    public boolean insertRoute(int start, int dest, int time, int price, int length) {
        String sql = "INSERT INTO [dbo].[Route]\n"
                + "           ([routeStartLocation]\n"
                + "           ,[routeDestLocation]\n"
                + "           ,[routeTime]\n"
                + "           ,[routePrice]\n"
                + "           ,[routeLenght]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            System.out.println("" + sql);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, dest);
            ps.setInt(3, time);
            ps.setInt(4, price);
            ps.setInt(5, length);
            ps.setBoolean(6, true);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return false;
    }

    /*
    Load student with id
     */
    public static void main(String[] args) {
        routeDAO r = new routeDAO();

        for (routeChartDTO dTO : r.listcharttop3()) {
            System.out.println("" + dTO.getNumber());
        }

    }

}

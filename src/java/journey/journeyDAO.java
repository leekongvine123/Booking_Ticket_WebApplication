package journey;

import java.sql.Date;

import car.carDAO;
import db.util.DBUtils;
import deptime.deptimeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import order.orderDAO;
import route.routeDAO;
import seat.seatDAO;
import seat.seatDTO;
import static user.userDAO.removeLastComma;

/**
 *
 * @author Admin
 */
public class journeyDAO {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
    
    public List<journeyDTO> list(int journeyID, int routeID, int carID, Date journeyDepDay, String journeyStatus, String sortPrice, String sortTime, String sortCar) {

        ArrayList<journeyDTO> list;
        list = new ArrayList<journeyDTO>();

        String sql = "select j.journeyID, j.routeID, j.depID,j.carID, j.journeyDepDay, j.journeyStatus, j.price from Journey j";

        String where = "";
        String whereJoinWord = " where ";

        if (sortPrice != null && sortPrice != "") {
            sql += " join  Route r on j.routeID = r.routeID ";
        }
        // 0:6
        if (sortTime != null && sortTime != "") {
            where += whereJoinWord;
            Pattern p = Pattern.compile(":");
            String[] str = p.split(sortTime);
            int time1 = Integer.parseInt(str[0]);
            int time2 = Integer.parseInt(str[1]);

            where += " j.depID in ( select j.depID from Journey j join DepTime d on j.depID = d.depID where d.depTime >= " + time1 + " and d.depTime <= " + time2 + " ) ";
            whereJoinWord = " and ";
        }

        if (journeyID != 0) {
            where += whereJoinWord;
            where += " j.journeyID = ? ";
            whereJoinWord = " and ";
        }
        if (routeID != 0) {
            where += whereJoinWord;
            where += " j.routeID = ? ";
            whereJoinWord = " and ";
        }

        if (carID != 0) {
            where += whereJoinWord;
            where += " j.carID = ? ";
            whereJoinWord = " and ";
        }

        if (journeyDepDay != null) {
            where += whereJoinWord;
            where += " j.journeyDepDay = ? ";
            whereJoinWord = " and ";
        }
        if (journeyStatus != null) {
            where += whereJoinWord;
            where += " j.journeyStatus = ? ";
            whereJoinWord = " and ";
        }
        if (sortCar != null && sortCar != "") {
            where += whereJoinWord;
            where += " j.carID in( select j.carID  from Journey j join car c on j.carID = c.carID where c.carType = ? )";
            whereJoinWord = " and ";
        }

        try {
            sql += where;
            if (sortPrice != null && sortPrice != "") {
                sql += " ORDER BY j.price " + sortPrice;
            }
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            if (journeyID != 0) {
                ps.setInt(index, journeyID);
                index++;

            }
            if (routeID != 0) {
                ps.setInt(index, routeID);
                index++;

            }

            if (carID != 0) {
                ps.setInt(index, carID);
                index++;

            }

            if (journeyDepDay != null) {
                ps.setDate(index, (java.sql.Date) journeyDepDay);
                index++;

            }
            if (journeyStatus != null) {
                ps.setString(index, journeyStatus);
                index++;

            }
            if (sortCar != null && sortCar != "") {
                ps.setString(index, sortCar);
                index++;

            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                seatDAO s = new seatDAO();
                List<seatDTO> listseat = s.list(0);
                orderDAO o = new orderDAO();
                journeyDAO j = new journeyDAO();
                journeyDTO jdto = j.return1j(rs.getInt("journeyID"));
                List<seatDTO> list2 = new ArrayList<>();
                if (jdto.getCarID().getCarType().contains("1F")) {

                    for (int i = 0; i < 17; i++) {
                        list2.add(listseat.get(i));
                    }
                    list.removeAll(listseat);
                    listseat = list2;
                }

                route.routeDAO r = new routeDAO();
                route.routeDTO rdto = r.list(rs.getInt("routeID"), 0, 0, false).get(0);
                deptime.deptimeDAO d = new deptimeDAO();
                deptime.deptimeDTO ddto = d.list(rs.getInt("depID"), 0).get(0);
                car.carDAO c = new carDAO();
                car.carDTO cdto = c.list(rs.getInt("carID"), null, null, null, false).get(0);

                list.add(new journeyDTO(rs.getInt("journeyID"), rdto, ddto, cdto, rs.getDate("journeyDepDay"), rs.getString("journeyStatus"), rs.getDouble("price"), listseat, 0));

            }
            return list;

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }
        return null;	// your code
        //
    }

    public journeyDTO return1j(int journeyID) {
        String sql = "select journeyID, carID from Journey where journeyID = ? ";

        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, journeyID);
            ResultSet rs = ps.executeQuery();
            car.carDAO c = new carDAO();

            if (rs.next()) {
                car.carDTO cdto = c.list(rs.getInt("carID"), null, null, null, false).get(0);
                journeyDTO j = new journeyDTO(journeyID, null, null, cdto, null, null, 0, null, 0);
                return j;

            }

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }

        return null;
    }

    public boolean insert(journeyDTO journey) {
        String sql = "INSERT INTO [dbo].[Journey]\n"
                + "           ([routeID]\n"
                + "           ,[depID]\n"
                + "           ,[carID]\n"
                + "           ,[journeyDepDay]\n"
                + "           ,[journeyStatus]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, journey.getRouteID().getRouteID());
            ps.setInt(2, journey.getDepID().getDepID());
            ps.setInt(3, journey.getCarID().getCarID());
            ps.setDate(4, (Date) journey.getJourneyDepDay());
            ps.setString(5, journey.getJourneyStatus());
            ps.setDouble(6, journey.getPrice());

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Query Student error!" + ex.getMessage());
        }

        return false;

    }

    public boolean update(int journeyID, int routeID, int depID, int carID, double price, Date journeyDepDay) {
        String sql = "UPDATE [dbo].[Journey]\n"
                + "   SET ";
        String where = "";
        String whereJoinWord = " WHERE ";
        if (routeID != 0) {
            sql += "[routeID] = ?" + ",";
        }
        if (depID != 0) {
            sql += "[depID] = ?" + ",";
        }
        if (carID != 0) {
            sql += "[carID] = ?" + ",";
        }
        if (journeyDepDay != null) {
            sql += "[journeyDepDay] = ?" + ",";
        }
        if (price != 0) {
            sql += "[price] = ?" + ",";
        }
        if (journeyID != 0) {
            where += whereJoinWord;
            where += " [journeyID] = ? ";
        }
        try {
            sql += where;
            sql = removeLastComma(sql);
            System.out.println("" + sql);
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            if (routeID != 0) {
                ps.setInt(index, routeID);
                index++;
            }
            if (depID != 0) {
                ps.setInt(index, depID);
                index++;
            }
            if (carID != 0) {
                ps.setInt(index, carID);
                index++;
            }
            if (journeyDepDay != null) {
                ps.setDate(index, journeyDepDay);
                index++;

            }
            if (price != 0) {
                ps.setDouble(index, price);
                index++;
            }

            if (journeyID != 0) {
                ps.setInt(index, journeyID);
                index++;
            }
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("journeyDAO UPDATE" + e.getMessage());
        }
        return false;

    }

    public boolean delete(int journeyID) {
        String sql = "DELETE FROM [dbo].[Journey]\n"
                + "      WHERE  journeyID = ?";

        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, journeyID);

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }

        } catch (Exception e) {
        }

        return false;
    }

    /*
    Load student with id
     */
//    public static void main(String[] args) {
//        journeyDAO s = new journeyDAO();
//        routeDAO r = new routeDAO();
//        deptimeDAO d = new deptimeDAO();
//        carDAO c = new carDAO();
//        List<journeyDTO> list1 = s.list(0, 0, 0, null, null, null, null, null);
////   
////        
////        
////        List<journeyDTO> list = s.list(0, 1, 0, 0, (java.sql.Date)list1.get(0).getJourneyDepDay(), null,null);
//
//        List<journeyDTO> list2 = s.list(0, 0, 0, null, null, null, null, null);
//
//        for (journeyDTO dTO : list2) {
//
//        }
//        journeyDTO journey = new journeyDTO();
//
//        journey.setRouteID(r.list(0, 0, 0).get(0));
//        journey.setDepID(d.list(0, 0).get(0));
//        journey.setCarID(c.list(0, null, null, null).get(0));
//        journey.setJourneyDepDay(Date.valueOf("2023-07-11"));
//        journey.setJourneyStatus("T");
//        journey.setPrice(10.5);
//        System.out.println(s.insert(journey));
//    }
    public static void main(String[] args) {

        journey.journeyDAO jd = new journeyDAO();
        List<journeyDTO> lDto = jd.list(0, 0, 0, null, null, null, null, null);
        for (journeyDTO dTO : lDto) {
            if (dTO.getRouteID().getRouteStartLocation().getLocationID() == 3 || dTO.getRouteID().getRouteDestLocation().getLocationID() == 3) {
                java.util.Date currentDate = new java.util.Date();
                int bool = dTO.getJourneyDepDay().compareTo(currentDate);
                if (bool > 0) {
                    System.out.println("Ngu");
                }
            }
        }

        journeyDAO s = new journeyDAO();
        routeDAO r = new routeDAO();
        deptimeDAO d = new deptimeDAO();
        carDAO c = new carDAO();
        List<journeyDTO> list1 = s.list(0, 0, 0, null, null, null, null, null);
//   
//        
//        
//        List<journeyDTO> list = s.list(0, 1, 0, 0, (java.sql.Date)list1.get(0).getJourneyDepDay(), null,null);

        List<journeyDTO> list2 = s.list(0, 0, 0, null, null, null, null, null);

        for (journeyDTO dTO : list2) {

            System.out.println(dTO);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Date;
import java.util.List;
import journey.journeyDTO;
import seat.seatDTO;
import user.userDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/**
 *
 * @author Admin
 */
public class orderDTO {

    private int orderID;
    private userDTO userID;
    private journeyDTO journeyID;
    private List<seatDTO> listSeat;
    private int quantity;
    private double totalPrice;
    private String status;
    private Date orderDob;

}

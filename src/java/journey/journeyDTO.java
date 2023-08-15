package journey;

import car.carDTO;
import deptime.deptimeDTO;
import java.util.Date;
import java.util.List;
import route.routeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import seat.seatDTO;

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
public class journeyDTO {

    private int journeyID;
    private routeDTO routeID;
    private deptimeDTO depID;
    private carDTO carID;
    private Date journeyDepDay;
    private String journeyStatus;

    private double Price;
    private List<seatDTO> listSeat;
    private int seatLeft;

}

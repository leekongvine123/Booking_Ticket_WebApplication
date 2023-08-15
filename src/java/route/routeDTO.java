/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route;

import location.locationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

/**
 *
 * @author Admin
 */
public class routeDTO {

    private int routeID;
    private locationDTO routeStartLocation;
    private locationDTO routeDestLocation;
    private int routeTime;
    private double routePrice;
    private int routeLength;
    private boolean status;

}

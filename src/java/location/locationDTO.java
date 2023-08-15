/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package location;

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
public class locationDTO {

    private int locationID;
    private String locationCity;
    private String locationStation;
    private String locationImg;
    private boolean status;

}

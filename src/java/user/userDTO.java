/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.Date;
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
public class userDTO {

    private int userID;
    private int roleID;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;
    private String userMail;
    private String userFirstName;
    private String userLastName;
    private Date userDob;
    private String userGender;
    private boolean status;

}

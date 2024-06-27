package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String username;
    private String passwordEncoding;
    private String fullName;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private Date birthDay;
    private boolean isVerify;
    private String role;
    private String avatar;
    private String tokenVerify;
    private Timestamp tokenVerifyTime;
    private String tokenResetPassword;
    private Timestamp tokenResetPasswordTime;

}

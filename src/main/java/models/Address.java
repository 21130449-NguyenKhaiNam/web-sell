package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private int id;
    private int userId;
    private String provinceId;
    private String districtId;
    private String wardId;
    private String detail;
    private String provinceName;
    private String districtName;
    private String wardName;

    public String exportAddressString(){
        return String.format("%s, %s, %s, %s", detail, wardName, districtName, provinceName);
    }
}

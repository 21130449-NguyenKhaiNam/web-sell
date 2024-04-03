package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private int province;
    private int district;
    private int ward;
    private String detail;
    private String provinceName;
    private String districtName;
    private String wardName;

    public String exportAddressString(){
        return String.format("%s, %s, %s, %s", detail, wardName, districtName, provinceName);
    }
}

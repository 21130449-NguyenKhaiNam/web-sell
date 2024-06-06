package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private Integer id;
    private Integer userId;
    private String detail;
    private String province;
    private String district;
    private String ward;

    public String exportAddressString() {
        return String.format("%s, %s, %s, %s", detail, ward, district, province);
    }
}
package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Parameter {
    private int id;
    private String name;
    private double minValue;
    private double maxValue;
    private String unit;
    private int categoryId;
    private String guideImg;

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", unit='" + unit + '\'' +
                ", categoryId=" + categoryId +
                ", guideImg='" + guideImg + '\'' +
                '}';
    }
}

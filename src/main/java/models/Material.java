package models;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Material {
    private int id;
    private CategoryMaterial categoryMaterial;
    private int remain;
    private LocalDate createdAt;
}

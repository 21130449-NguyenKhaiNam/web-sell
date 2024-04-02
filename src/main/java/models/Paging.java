package models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Paging<T> {
    private int start;
    private int length;
    private List<T> list;
}

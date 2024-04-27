package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Log {
    private int id;
    private String ip;
    private String level;
    private String location;
    private String resource;
    private String previousValue;//JSON
    private String currentValue;//JSON
}

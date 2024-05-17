package models;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
public class Log {
    private int id;
    private String ip;
    private String level;
    private String resource;
    private Date dateCreated;
    private String previous; // json
    private String current; // json
}

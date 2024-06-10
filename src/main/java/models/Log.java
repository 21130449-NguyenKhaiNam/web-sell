package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Log {
    private int id;
    private String ip;
    private String level;
    private String resource;
    private Date dateCreated;
    private String previous; // json
    private String current; // json
}

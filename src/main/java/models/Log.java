package models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Data
@Getter
@Setter@NoArgsConstructor
public class Log {
    private int id;
    private String ip;
    private String level;
    private String resource;
    private Date dateCreated;
    private String previous;
    private String current;
}

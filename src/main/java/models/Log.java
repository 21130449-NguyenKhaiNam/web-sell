package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
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

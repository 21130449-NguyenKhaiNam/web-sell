package models;

import java.sql.Date;

public class Log {
    private int id;
    private String ip;
    private String level;
    private String resource;
    private Date dateCreated;
    private String previous;
    private String current;

    public Log() {}

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getLevel() {
        return level;
    }

    public String getResource() {
        return resource;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getPrevious() {
        return previous;
    }

    public String getCurrent() {
        return current;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}

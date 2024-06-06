package logging;

public enum LogLevel {
    INFO("1"),
    ALERT("2"),
    WARNING("3"),
    DANGER("4");
    private String level;

    LogLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

package config;

public enum ImagePath {
    CATEGORY("size_table"),
    PARAMETER("parameter_guide"),
    PRODUCT("product");

    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

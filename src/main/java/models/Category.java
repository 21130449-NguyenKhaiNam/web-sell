package models;

public class Category {
    private int id;
    private String nameType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nameType='" + nameType + '\'' +
                '}';
    }
}

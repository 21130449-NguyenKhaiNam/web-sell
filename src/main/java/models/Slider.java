package models;

public class Slider {
    private int id;
    private String nameSlide;
    private String nameImage;
    private boolean visibility;

    public Slider() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSlide() {
        return nameSlide;
    }

    public void setNameSlide(String nameSlide) {
        this.nameSlide = nameSlide;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}

package models;

public class Sliders {
    private int id;
    private String image;
    private boolean visibility;

    public Sliders() {
    }

    public Sliders(int id, String image, boolean visibility) {
        this.id = id;
        this.image = image;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}

package models;

public class Image {
    private int id;
    private String nameImage;
    private int productId;

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", nameImage='" + nameImage + '\'' +
                ", productId=" + productId +
                '}';
    }
}

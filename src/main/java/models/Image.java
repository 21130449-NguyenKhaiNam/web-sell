package models;

import services.image.CloudinaryUploadServices;

import java.util.HashMap;
import java.util.Objects;

public class Image {
    private int id;
    private String nameImage;
    private int productId;

    public Image() {
    }

    public Image(int id, String nameImage, int productId){
        this.id = id;
        this.nameImage = nameImage;
        this.productId = productId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameImage() {
        return CloudinaryUploadServices.getINSTANCE().getImage("product_img", this.nameImage);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return productId == image.productId && Objects.equals(nameImage, image.nameImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameImage, productId);
    }
}

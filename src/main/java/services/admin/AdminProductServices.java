package services.admin;

import dao.*;
import models.Color;
import models.Image;
import models.Product;
import models.Size;
import services.image.UploadImageServices;
import utils.Comparison;

import javax.servlet.http.Part;
import java.sql.Date;
import java.util.*;

public class AdminProductServices {
    private static final int LIMIT = 15;
    private static AdminProductServices INSTANCE;
    ProductDao productDAO = new ProductDao();
    ColorDAO colorDAO = new ColorDAO();
    ImageDAO imageDAO = new ImageDAO();
    SizeDAO sizeDAO = new SizeDAO();
    ProductCardDAO productCardDAO = new ProductCardDAO();

    private AdminProductServices() {
    }

    public static AdminProductServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminProductServices();
        return INSTANCE;
    }

    public int addProduct(Product product) {
        List<Product> productList = productDAO.getIdProductByName(product.getName());
        if (!productList.isEmpty()) {
            System.out.println("Không thể thêm sản phẩm cùng tên");
            return 0;
        }
        productDAO.addProduct(product);
        return productDAO.getIdProductByName(product.getName()).get(0).getId();
    }

    public void addColor(String[] codeColors, int productId) {
        Color[] colors = new Color[codeColors.length];
        for (int i = 0; i < codeColors.length; i++) {
            colors[i] = new Color();
            colors[i].setCodeColor(codeColors[i]);
            colors[i].setProductId(productId);
        }
        colorDAO.addColors(colors);
    }

    public void addImages(List<String> nameImages, int productId) {
        List<Image> imageList = new ArrayList<>();
        for (int i = 0; i < nameImages.size(); i++) {
            Image image = new Image();
            if (nameImages.get(i) == null)
                continue;
            image.setNameImage(nameImages.get(i));
            image.setProductId(productId);
            imageList.add(image);
        }
        imageDAO.addImages(imageList);
    }

    public void addSize(String[] nameSizes, double[] sizePrices, int productId) {
        Size[] sizes = new Size[nameSizes.length];
        for (int i = 0; i < sizes.length; i++) {
            Size size = new Size();
            size.setNameSize(nameSizes[i]);
            size.setSizePrice(sizePrices[i]);
            size.setProductId(productId);

            sizes[i] = size;
        }
        sizeDAO.addSizes(sizes);
    }

    public List<Product> filter(List<Integer> listId, int pageNumber) {
        List<Product> productList = productCardDAO.pagingAndFilter(listId, pageNumber, LIMIT);
        return productList;
    }

    public int getQuantityPage() {
        double quantityPage = Math.ceil(Double.parseDouble(productCardDAO.getQuantityProduct() + "") / LIMIT);
        return (int) quantityPage;
    }

    public int getQuantityPage(List<Integer> listId) {
        double quantityPage = Math.ceil(Double.parseDouble(productCardDAO.getQuantityProduct(listId) + "") / LIMIT);
        return (int) quantityPage;
    }

    public List<Integer> getProductByName(String name) {
        List<Product> listProduct = productCardDAO.getIdProductByName(name);
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p : listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }


    public List<Integer> getProductByTimeCreated(Date dateBegin, Date dateEnd) {
        List<Product> listProduct = productCardDAO.getProductByTimeCreated(dateBegin, dateEnd);
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }

    public List<Product> getProducts(int numberPage) {
        List<Product> productList = productCardDAO.getProducts(numberPage, LIMIT);
        return productList;
    }


    public boolean isContain(Product product) {
        List<Product> productList = productDAO.getIdProductByName(product.getName());
        return !productList.isEmpty();
    }

    //Sản phẩm không có hiệu chỉnh -> Không cập nhập
    public void updateProduct(Product product, Product productOther, int id) {
        if (product != null && productOther != null && !product.equals(productOther)) {
//Trích xuất ra những điểm khác nhau giữa 2 obj
            Product productUpdate = Comparison.different2Product(product, productOther);
            productDAO.updateProduct(productUpdate, id);
        }
    }

    public void updateColors(String[] codeColors, int productId) {
        Color[] colors = new Color[codeColors.length];
        for (int i = 0; i < colors.length; i++) {
            Color color = new Color();
            color.setCodeColor(codeColors[i]);
            color.setProductId(productId);
            colors[i] = color;
        }
//        update
        List<Color> listColorId = colorDAO.getIdColorByProductId(productId);
        int index = Math.min(listColorId.size(), colors.length);
        for (int i = 0; i < index; i++) {
            colorDAO.updateColor(colors[i], listColorId.get(i).getId());
        }
//       delete
        if (listColorId.size() > index) {
            List<Color> colorsDelete = listColorId.subList(index, listColorId.size());
            List<Integer> listIdDelete = (List<Integer>) colorsDelete.stream().map(Color::getId);
            colorDAO.deleteColorList(listIdDelete);
        }
//       create
        if (listColorId.size() < index) {
//            int update = index - listSizeId.size();
            Color[] colorsAdd = Arrays.copyOfRange(colors, index, colors.length);
            colorDAO.addColors(colorsAdd);
        }
    }

    public void updateSizes(String[] nameSizes, String[] sizePrices, int productId) {
        if (nameSizes.length != sizePrices.length) return;
//        Create sizes obj
        Size[] sizes = new Size[nameSizes.length];
        for (int i = 0; i < sizes.length; i++) {
            Size size = new Size();
            size.setNameSize(nameSizes[i]);
            size.setSizePrice(Double.parseDouble(sizePrices[i]));
            size.setProductId(productId);
            sizes[i] = size;
        }
//        Lấy ra các id size thuôc về product đó đang có trong cửa hàng
        List<Size> listSizeId = sizeDAO.getIdSizeByProductId(productId);
//       listSizeId < sizes
        int index = Math.min(listSizeId.size(), sizes.length);
        for (int i = 0; i < index; i++) {
            sizeDAO.updateSize(sizes[i], listSizeId.get(i).getId());
        }
//       delete
        if (listSizeId.size() > index) {
            List<Size> sizesDelete = listSizeId.subList(index, listSizeId.size());
            List<Integer> listIdDelete = (List<Integer>) sizesDelete.stream().map(Size::getId);
            sizeDAO.deleteSizeList(listIdDelete);
        }
//       create
        if (sizes.length > index) {
//            int update = index - listSizeId.size();
            Size[] sizesAdd = Arrays.copyOfRange(sizes, index, sizes.length);
            sizeDAO.addSizes(sizesAdd);
        }
    }

    private List<String> getNameImages(int quantityFromRightToLeft, int productId) {
        List<Image> imageList = imageDAO.getNameImages(productId);
        Collections.reverse(imageList);

        List<Image> imageDelete = imageList.subList(0, quantityFromRightToLeft);
        for (int i = 0; i < imageDelete.size(); i++) {
            if (keepImageAvailable(imageList, imageDelete.get(i)) > 1) {
                imageDelete.remove(imageDelete.get(i));
            }
        }

        List<String> nameImageList = new ArrayList<>();
        for (Image img : imageDelete) {
            nameImageList.add("product_img/" + img.getNameImage());
        }

        return nameImageList;
    }

    public int keepImageAvailable(List<Image> imageList, Image image){
        int count = 0;
        for (Image img : imageList){
            if(img.equals(image)){
                count++;
            }
        }
        return count;
    }

    private List<Integer> getIdImages(int quantityImgDelete, int productId) {
        List<Image> imageList = imageDAO.getIdImages(productId);
        List<Integer> nameImageList = new ArrayList<>();
        for (Image image : imageList) {
            nameImageList.add(image.getId());
        }
        return nameImageList.subList(imageList.size() - quantityImgDelete, imageList.size());
    }

    private void deleteImages(List<Integer> nameImages) {
        imageDAO.deleteImages(nameImages);
    }

    public void updateImages(UploadImageServices uploadImageServices, Collection<Part> images, int quantityImgDelete, int productId) throws Exception {
        if (quantityImgDelete != 0) {
            List<String> nameImages = getNameImages(quantityImgDelete, productId);
            List<Integer> imageId = getIdImages(quantityImgDelete, productId);
            uploadImageServices.deleteImages(nameImages);//delete in cloud
            deleteImages(imageId);//delete in db
        }
        else{
            uploadImageServices.addImages(images);//add in cloud
            List<String> nameImagesAdded = uploadImageServices.getNameImages();
            addImages(nameImagesAdded, productId);//add in db
        }
    }

    public boolean updateVisibility(int productId, boolean visibility) {
        if (productCardDAO.isVisibility(productId).isEmpty() || visibility == productCardDAO.isVisibility(productId).get(0).isVisibility()) {
            return false;
        }
        productCardDAO.updateVisibility(productId, visibility);
        return true;
    }

    public List<Product> getAll() {
        List<Product> productList = productCardDAO.getAll();
        return productList;
    }
}

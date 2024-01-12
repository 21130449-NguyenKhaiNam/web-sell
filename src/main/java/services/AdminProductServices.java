package services;

import dao.*;
import models.*;
import utils.Comparison;

import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        if (!productList.isEmpty()) return 0;
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
            image.setNameImage(nameImages.get(i));
            image.setProductId(productId);
            imageList.add(image);
        }
        imageDAO.addImages(imageList);
    }

    public void addSize(String[] nameSizes, double[] sizePrices, int productId) {
        Size[] sizes = new Size[nameSizes.length];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = new Size();
            sizes[i].setNameSize(nameSizes[i]);
            sizes[i].setSizePrice(sizePrices[i]);
            sizes[i].setProductId(productId);
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
        for (Product p :
                listProduct) {
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
        List<String> nameImageList = new ArrayList<>();
        for (Image image : imageList
        ) {
            nameImageList.add(image.getNameImage());
        }
        return nameImageList.subList(imageList.size() - quantityFromRightToLeft, imageList.size());
    }

    private List<Integer> getIdImages(int quantityImgDelete, int productId) {
        List<Image> imageList = imageDAO.getIdImages(productId);
        List<Integer> nameImageList = new ArrayList<>();
        for (Image image : imageList
        ) {
            nameImageList.add(image.getId());
        }
        return nameImageList.subList(imageList.size() - quantityImgDelete, imageList.size());
    }

    private void deleteImages(List<Integer> nameImages) {
        imageDAO.deleteImages(nameImages);
    }

    public void updateImages(UploadImageServices uploadImageServices, Collection<Part> images, int quantityImgDelete, int productId) {
        if (quantityImgDelete != 0) {
            List<String> nameImages = getNameImages(quantityImgDelete, productId);
            List<Integer> imageId = getIdImages(quantityImgDelete, productId);
            uploadImageServices.deleteImages(nameImages);//delete in local
            deleteImages(imageId);//delete in db
        }
        try {
            uploadImageServices.addImages(images);//add in local
            List<String> nameImagesAdded = uploadImageServices.getNameImages();
            addImages(nameImagesAdded, productId);//add in db
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

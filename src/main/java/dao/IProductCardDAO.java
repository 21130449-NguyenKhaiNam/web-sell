package dao;

import models.Category;
import models.Parameter;
import models.Product;
import utils.MoneyRange;

import java.sql.Date;
import java.util.List;

public interface IProductCardDAO extends IDAO {
    //    Lấy ra danh sách sản phẩm theo trạng thái hiển thị
    //    Sử dụng để phân trang
    List<Product> getProducts(int pageNumber, int limit, boolean visibility);

    //    Lấy ra danh sách sản phẩm bao gôm cả trạng thái hiển thị và ẩn
    //    Dùng để lấy danh sách sản phẩm trong trang admin product
    List<Product> getProducts(int pageNumber, int limit);

    //    Lấy ra số lượng sản phẩm có trong hệ thống
    //    Dùng để tính số trang có thể phân trang (admin product)
    int getQuantityProduct();

    //    Lấy ra số lượng sản phẩm có trong hệ thống dựa vào trạng thái hiển thị
    //    Dùng để tính số trang có thể phân trang (product buying)
    int getQuantityProduct(boolean visibility);

    //    Lấy ra số lượng sản phẩm có trong hệ thống dựa vào danh sách id sản phẩm và trạng thái hiển thị
    //    Dùng để phân trang kết hợp với lọc sản phẩm
    //    Cách lọc: lấy ra id của các sản phẩm thỏa mãn các yêu cầu -> Lấy ra các sản phẩm đó thông qua id
    int getQuantityProduct(List<Integer> listId, boolean visibility);

    //    Tương tự như trên nhưng không lấy cả ẩn + ko ẩn
    int getQuantityProduct(List<Integer> listId);

    //    Lấy ra danh sách sản phẩm dựa vào danh sách id sản phẩm
    //    pageNumber = trang
    //    limit = số lượng sản phẩm trên 1 trang
    List<Product> pagingAndFilter(List<Integer> listId, int pageNumber, int limit);


    //    Tương tự như trên nhưng có thêm trạng thái hiển thị
    List<Product> pagingAndFilter(List<Integer> listId, int pageNumber, int limit, boolean visibility);

    //      Lấy ra danh sách id sản phẩm dựa vào danh sách id thể loại
    List<Product> getIdProductByCategoryId(List<String> listIdCategory);


    //    --------------
    //    Các hàm sau phục vụ cho việc lọc sản phẩm theo nhiều tiêu chí trong productBuying
    //    Lấy ra danh sách id sản phẩm dựa vào danh sách id màu
    List<Product> getIdProductByColor(List<String> listCodeColor);

    //    Lấy ra danh sách id sản phẩm dựa vào danh sách id size
    List<Product> getIdProductBySize(List<String> listSize);

    //    Lấy ra danh sách id sản phẩm dựa vào danh sách mức giá
    List<Product> getIdProductByMoneyRange(List<MoneyRange> moneyRangeList);

    //    Lấy ra danh sách sản phẩm dựa vào danh sách id thể loại
    List<Product> getProductByCategoryId(int categoryId);

    //    Lấy ra danh sách id sản phẩm dựa theo tên sản phẩm
    List<Product> getIdProductByName(String name);

    //    Lấy ra danh sách id sản phẩm dựa theo thời gian tạo
    List<Product> getProductByTimeCreated(Date dateBegin, Date dateEnd);

    //    Lấy ra tên thể loại dựa vào id sản phẩm
    List<Category> getNameCategoryById(int id);

    //    Lấy ra danh sách thể loại dựa vào id sản phẩm
    List<Category> getCategoryByProductId(int id);

    //    Lấy ra danh sách thông số dựa vào id sản phẩm
    List<Parameter> getParametersByProductId(int id);

    //    Lấy ra danh sách tên sản phẩm dựa vào id sản phẩm
    List<Product> getNameProductById(int id);

    //    Lấy ra danh sách trạng thai sản phẩm dựa vào id sản phẩm
    List<Product> isVisibility(int id);

    //    Cập nhập trạng thái ẩn/hiện sản phẩm dựa vào id sản phẩm
    void updateVisibility(int productId, boolean visibility);

    //    Lấy ra danh sách tên sản phẩm dựa theo id chi tiết đơn hàng
    List<Product> getNameProductByIdOrderDetail(int orderDetailId);
}

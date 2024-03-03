# web-sell

## 1. Mở đầu
- Đồ án trang web bán đồ gia dụng
- Đồ án là một yêu cầu bắt buộc hoàn thành của môn học
- Tổ chức nhóm gồm: 3 thành viên
- Các công nghệ chính sử dụng: HTML, CSS, JS, Java. Ngoài ra còn một số thư viện khác như: Boostrap, Ajax, JDBI,...
## 2. Phân chia
- Thư viện icon: Fonawesome,  flag-icon
- Giao diện: sử dụng các thư viện có thể và chỉ làm xoay quanh HTML, CSS, JS (MetroUI, Boostrap, SweetAlert2, Tippy, JQuery, Ajax)
- Xử lý: sử dụng Java (cụ thể JSP, Servlet)
- Cơ sở dữ liệu: MySQL
## 3. Yêu cầu
- Đầy đủ giao diện
- Xử lý đầy đủ chức năng
- Xử lý không reload ở một số vị trí
- Tối ưu cho web
## 4. Quy tắc
- Tên đặt cho một DOM trong html được viết hoa chữ cái đầu để phân biệt với tên của các thư viện
- Đặt tên cho một phần tử trong html: (thư mục chứa file css)_(tên phần tử)
- Yêu cầu chú thích cơ bản
- Áp dụng mô hình MVC cho Java
- Ngôn ngữ sử dụng để viết code là tiếng anh, commmit là tiếng việt
- Commit phải có ý nghĩa
- Mọi công việc đều phải có issue
- Chỉ được phép pull request và merge branch khi đã được review
- Tên thư mục sản phẩm chính là id của sản phẩm đó
- Sử dụng quy tắc đặt tên: [Trang web hướng dẫn đặt tên cho phù hợp](https://viblo.asia/p/naming-rules-cac-quy-tac-vang-trong-lang-dat-ten-ByEZkMXE5Q0)

***

# Hướng dẫn chức năng của các file
- Giải thích từ khó xuống dễ hiểu
- Nếu custom lại phải chú thích rõ tại vị trí tương ứng

## ***Phần js***:

## **/action**
### access.js
- Xử lý các vấn về bằng js cho các file thuộc thư mục html/access
### layoutCountries.js
- Dùng cho việc đổ dữ liệu các quốc gia ra giao dạng dạng popup
### footer.js
- Dùng xử lý cho mỗi footer.html
### header.js
- Xử lý nhiều vấn đề liên quan đến header.js

## **/data**
- Cũng được viết bằng js, trong tương lai sẽ lấy dữ liệu bằng Ajax và đổ vào đây
### categories.js
- Lấy dữ liệu loại sản phẩm, có function getCategories lấy danh sách dưới dạng DOM
### countries.js
- Cũng khá giống categories.js, sở hữu function getCountries lấy danh sách quốc gia
### locations.js
- Lấy vị trí, khác với quốc gia ở chỗ có thể rộng hơn và getLocations trả về dạng tag select DOM

## ***Phần html***:

## **/access**
- Chứa các lớp hiển thị liên quan đến truy cập của người dùng
+ confirm.html: Dùng khi người dùng chọn quên mật khâủ hoặc yêu cầu nhập mã bảo mật, nhận mã code và truyền về server -> Có thể thay thế bằng dialog
+ forget.html: Trang quên mật khẩu, nhập email và nhập mã tại trang confirm.html
+ login.html: Trang đăng nhập, cho phép đăng nhập bằng email hoặc sdt
+resgister.html: Đăng ký một tài khoản mới trong hệ thống 

## **/footer**
- Chứa trang footer.html giúp hiển thị giao diện phần chân trang

## **/header**
- Gồm trang header.html là phần header của mọi trang public

## **/product**
- Gồm các trang:
+ product.html: Hiển thị các sản phẩm tại trang index.html
+ productDetail.html: Chi tiết của một sản phẩm

## ***Phần css***:

## **/access**
- Thiết lập style cho các file liên quan đến access

## **/footer**
- Trang điểm cho footer

## **/general**
- Chứa các file có thể css chung:
+ index.css: Đặt biến chú thích chung
+ reset.css: Đặt lại thông số một số thuộc tính

## **/header**
- Cải thiện giao diện cho header

## **/product**
- Làm nổi bật sản phẩm hơn

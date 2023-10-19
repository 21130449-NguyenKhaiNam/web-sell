# Đề án: Website đặt may đồ theo yêu cầu

## I. Bài toán

Xây dựng website đặt may đồ, cung cấp cho người dùng khả năng điều chỉnh (custom) quần áo theo sở thích và đặt may.

## II. Chức năng

- Tùy chỉnh quần áo:
    - Màu sắc áo.
    - Thêm chữ cách điệu và tùy chỉnh chữ.
    - Thêm ảnh và tùy chỉnh kích thước ảnh.
- Dự tính giá cả và định trước khoảng thời gian quần áo.

## III. Ngôn ngữ/kỹ thuật sử dụng

### 1. Ngôn ngữ

- HTML
- CSS
- JS
- Java (Servlet)

### 2. Kỹ thuật

- Quy chuẩn đặt tên class/id BEM: Block-Element-Modifier.
- Phân chia html, css, js thành các module ứng với từng page, chức năng.
- Áp dụng mô hình Scrum/Agile trong việc quản lý và vận hành nhóm.

## IV. Quy trình làm việc

- Trưởng nhóm tạo task thông qua trello.
- Các thành viên tiến hành nhận task thông qua trello. Sau khi hoàn thành thì phải cập nhập tiến độ trên trello.
- Nếu có vấn đề hay thắc mắc về task phải báo cho các thành viên khác để cùng nhau giải quyết.
- Mỗi thành viên khi nhận công việc sẽ tạo ra 1 branch riêng để bắt đầu công việc.
- Sau khi hoàn thành công việc, các thành viên bắt đầu commit và push lên branch của công việc đó.
- Muốn merge vào banch chính (main), thành viên bắt buộc phải tạo ***pull reques*t**.
- Cuối mỗi tuần sẽ có meeting nhằm tổng kết công việc.
- Các thành viên sẽ liên lạc và họp thông qua zalo.

## V. Git

### 1. Branch

- Đặt tên branch theo chức năng. Hạn chế đặt tên sai
- Sau khi hoàn thành và merge thành công chức năng đó. Tiến hành tạo 1 branch mới từ main và xây dựng chức năng mới.
- Quy trình rename branch:

  > Giả sử branch cũ là A (branch cần đổi tên), branch mới là B (tên branch A muốn đổi thành)

    - Rename branch A -> B trên local: `git branch -m A_branch B_branch`
    - Push branch B (branch A đã đổi tên) lên remote: `git push -u origin B_branch`

  > Khi này trên remote sẽ có cả branch A và branch B
    - Xóa branch A trên remote: `git push origin --delete A_branch`

  > Lưu ý, commit trên branch A sẽ được sao chép hoàn toàn sang branch B. Khi xóa branch A thì commit bên branch
  > B ko bị ảnh hưởng (cả local và remote)`

### 2. Merge

- Bắt buộc tạo **pull request**.
- Test kỹ càng các trường hợp có thể phát sỉnh ra lỗi
- Các thành viên sẽ tiến hành review code trước khi push.
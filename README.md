# HỆ THỐNG QUẢN LÝ ĐIỂM CỦA SINH VIÊN TRƯỜNG CNTT

## I. GIỚI THIỆU DỰ ÁN
Đây là dự án **Quản lý điểm của sinh viên Trường Công nghệ Thông tin**, được xây dựng nhằm hỗ trợ giảng viên và nhà trường trong việc quản lý, nhập liệu và tra cứu điểm của sinh viên một cách hiệu quả, nhanh chóng và trực quan.

### CÁC THÀNH VIÊN TRONG NHÓM
1. Nguyễn Trọng Huy  
2. Bùi Thế Phương

### Bài Tập Lớn: Ứng dụng Quản Lý Hệ Thống Điểm Của Sinh Viên Khoa CNTT

### Yêu cầu chính

- Giao diện sử dụng **Java + Spring Boot**
- Hệ thống gồm các chức năng quản lý sinh viên, điểm số và học phần

### Các chức năng chính

**Quản lý điểm của sinh viên**
- ✅ Thêm, sửa, xóa điểm của sinh viên
- ✅ Liệt kê thông tin điểm của sinh viên

**Quản lý sinh viên**
- ✅ Thêm mới sinh viên
- ✅ Sửa thông tin sinh viên
- ✅ Xóa sinh viên

**Gán điểm cho sinh viên**
- ✅ Hỗ trợ chức năng gán điểm cho từng sinh viên theo môn học
- ✅ Tự động cập nhật vào danh sách điểm tổng hợp

**Công nghệ sử dụng**

Java 17, Spring Boot

Thymeleaf (Giao diện web)

MySQL / Aiven (Cơ sở dữ liệu)

HTML/CSS

Dự án được phát triển trong học phần **Lập trình hướng đối tượng** và phù hợp để triển khai trong môi trường học đường.

## II. Thiết kế các đối tượng

### Lớp Student (Sinh viên)

**1. Giới thiệu:**

Lớp `Student` đại diện cho sinh viên trong hệ thống. Mỗi sinh viên có mã số, họ tên, giới tính, ngày sinh, email và lớp học. Đây là lớp trung tâm liên kết với điểm và các thao tác quản lý sinh viên.

**2. Thuộc tính:**

| Tên thuộc tính | Kiểu dữ liệu | Mô tả                          |
|----------------|--------------|--------------------------------|
| `id`           | String       | Mã sinh viên                   |
| `fullName`     | String       | Họ và tên                      |
| `gender`       | String       | Giới tính                      |
| `dob`          | Date         | Ngày sinh                      |
| `email`        | String       | Email liên hệ                  |
| `className`    | String       | Tên lớp                        |
| `khoa`         | String       | Khoa đang theo học             |

**3. Phương thức:**

| Tên phương thức   | Mô tả                                       |
|-------------------|---------------------------------------------|
| `get` / `set`     | Truy xuất và cập nhật thuộc tính            |
| `toString()`      | Hiển thị thông tin sinh viên                |

### Lớp MonHoc (Môn học)

**1. Giới thiệu:**

Lớp `MonHoc` đại diện cho thông tin các môn học trong hệ thống quản lý điểm. Mỗi môn học có mã riêng, tên môn, số tín chỉ, số tiết lý thuyết và thực hành, đồng thời được phân theo học kỳ.

**2. Thuộc tính:**

| Tên thuộc tính   | Kiểu dữ liệu | Mô tả |
|------------------|--------------|-------|
| `maMon`          | String       | Mã môn học |
| `tenMon`         | String       | Tên môn học |
| `soTinChi`       | int          | Số tín chỉ |
| `soTietLT`       | String       | Số tiết lý thuyết |
| `soTietTH`       | String       | Số tiết thực hành |
| `hocKy`          | String       | Học kỳ |

**3. Phương thức:**

| Tên phương thức | Mô tả |
|------------------|------|
| `get` / `set`     | Truy xuất và cập nhật thuộc tính |
| `toString()`      | Hiển thị thông tin môn học |

### Lớp Mark (Điểm)

**1. Giới thiệu:**

Lớp `Mark` đại diện cho điểm số mà sinh viên đạt được ở một môn học cụ thể. Mỗi điểm gồm mã sinh viên, mã học phần và điểm số tương ứng.

**2. Thuộc tính:**

| Tên thuộc tính | Kiểu dữ liệu | Mô tả                        |
|----------------|--------------|-------------------------------|
| `id`           | String       | Mã điểm (duy nhất)           |
| `studentId`    | String       | Mã sinh viên                  |
| `subject`      | String       | Tên môn học                  |
| `score`        | Float        | Điểm số                      |

**3. Phương thức:**

| Tên phương thức   | Mô tả                             |
|-------------------|------------------------------------|
| `get` / `set`     | Truy xuất và cập nhật thuộc tính   |
| `toString()`      | Xuất thông tin điểm                |

### Lớp User (Người dùng)

**1. Giới thiệu:**

Lớp `User` đại diện cho tài khoản đăng nhập vào hệ thống, dùng để phân quyền và bảo mật.

**2. Thuộc tính:**

| Tên thuộc tính | Kiểu dữ liệu | Mô tả              |
|----------------|--------------|---------------------|
| `username`     | String       | Tên đăng nhập       |
| `password`     | String       | Mật khẩu truy cập   |

**3. Phương thức:**

| Tên phương thức   | Mô tả                              |
|-------------------|-------------------------------------|
| `get` / `set`     | Truy cập và cập nhật tài khoản      |

### Interface CoId

**1. Giới thiệu:**

Interface `CoId` được dùng để đảm bảo các lớp có thể thao tác thống nhất thông qua thuộc tính `id`.

**2. Phương thức:**

| Phương thức       | Mô tả                      |
|-------------------|-----------------------------|
| `getId()`         | Trả về mã định danh         |
| `setId(String)`   | Gán mã định danh            |

### Các phương thức hoạt động chính của ứng dụng

| STT | Tên Controller        | Chức năng chính                          | Đối tượng liên quan      | Ghi chú                      |
|-----|-----------------------|-------------------------------------------|---------------------------|------------------------------|
| 1   | `StudentController`   | Quản lý sinh viên                         | Student                   | Chức năng chính              |
| 2   | `MarkController`      | Quản lý điểm số                           | Mark, Student             | Gắn kết điểm với sinh viên   |
| 3   | `LoginController`     | Quản lý đăng nhập                         | User                      | Bảo mật, phân quyền          |

## III. Công nghệ sử dụng

#### Frontend (Giao diện người dùng)

Giao diện được xây dựng sử dụng engine **Thymeleaf**, tích hợp trực tiếp với **Spring Boot**. Cách tiếp cận này giúp kết nối chặt chẽ giữa giao diện và logic xử lý phía server, đồng thời thuận tiện trong việc mở rộng giao diện người dùng sau này.

#### Backend (Xử lý nghiệp vụ)

Phần backend được phát triển bằng **Spring Boot**, áp dụng kiến trúc **MVC (Model - View - Controller)**. Kiến trúc này giúp tổ chức mã nguồn một cách rõ ràng, dễ bảo trì, dễ mở rộng, và đảm bảo phân tách hợp lý giữa các lớp xử lý dữ liệu, giao diện và điều hướng logic.

#### Cơ sở dữ liệu

Hệ thống sử dụng **MySQL** làm hệ quản trị cơ sở dữ liệu. Dữ liệu như: thông tin sinh viên, điểm số, tài khoản đăng nhập... được lưu trữ trong các bảng có thiết kế chuẩn hóa, đảm bảo tính toàn vẹn dữ liệu và hiệu suất truy vấn.

#### Công cụ build

Dự án sử dụng **Maven** để quản lý thư viện, các phụ thuộc và quy trình build. Maven giúp tự động hóa các bước biên dịch, đóng gói, kiểm thử và triển khai, đảm bảo quy trình phát triển phần mềm diễn ra đồng bộ và hiệu quả.
****

## IV. Mô hình và chức năng

### Mô hình kiến trúc

Hệ thống được phát triển theo mô hình **MVC (Model - View - Controller)**, giúp phân tách rõ ràng các thành phần trong ứng dụng:
- **Model:** Chứa các lớp biểu diễn dữ liệu như `Student`, `Mark`, `User`…
- **Database:** Quản lý việc lưu trữ dữ liệu thực tế trong hệ quản trị MySQL.
- **Controller:** Chịu trách nhiệm xử lý logic nghiệp vụ, tiếp nhận yêu cầu từ giao diện và điều phối dữ liệu giữa Model và View.

Mô hình MVC giúp tăng khả năng mở rộng, bảo trì dễ dàng và tái sử dụng mã nguồn hiệu quả.

### Các chức năng chính của hệ thống

- **Quản lý tài khoản người dùng:**  
  Người dùng có thể đăng ký và đăng nhập hệ thống thông qua `LoginController`.

- **Quản lý sinh viên:**  
  Cho phép thêm, sửa, xóa và tìm kiếm sinh viên theo mã số hoặc tên lớp.

- **Quản lý điểm sinh viên:**  
  Lưu trữ và xử lý điểm số của từng sinh viên theo từng học phần. Hệ thống hỗ trợ các thao tác thêm, cập nhật, xóa và lọc điểm.

- **Giao diện người dùng (UI):**  
  Được xây dựng bằng **Thymeleaf** kết hợp với **Spring Boot**, mang lại trải nghiệm đơn giản, trực quan và dễ sử dụng.

## V. Sơ đồ khối
<img src ="img/Screenshot 2025-07-05 154221.png">

## VI. Thông tin dữ liệu mySQL

Ứng dụng giúp quản lý điểm, sinh viên, môn học và người dùng trong hệ thống giáo dục, sử dụng Spring Boot + MySQL.

```sql
CREATE DATABASE quanlydiem;

USE quanlydiem;

CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO login (username, password) VALUES ('admin', '111111');

CREATE TABLE student (
    msv VARCHAR(20) PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(100),
    dob DATE,
    khoa VARCHAR(50),
    classname VARCHAR(50)
);

INSERT INTO student (msv, fullname, gender, email, dob, khoa, classname) VALUES
('23010199', 'Nguyễn Văn A', 'Nam', 'nva@gmail.com', '2002-05-14', 'CNTT', 'CNTT1');

CREATE TABLE monhoc (
    ma_mon VARCHAR(20) PRIMARY KEY,
    ten_mon VARCHAR(100) NOT NULL,
    so_tin_chi INT NOT NULL,
    so_tiet_lt VARCHAR(10),
    so_tiet_th VARCHAR(10),
    hoc_ky VARCHAR(10)
);

INSERT INTO monhoc (ma_mon, ten_mon, so_tin_chi, so_tiet_lt, so_tiet_th, hoc_ky) VALUES
('FS001', 'Lập trình Java', 3, '30', '15', '3');

CREATE TABLE diem (
    msv VARCHAR(20) NOT NULL,
    maMon VARCHAR(20) NOT NULL,
    diemMon DOUBLE,
    diemHe4 DOUBLE,
    diemChu VARCHAR(5),
    danhGia VARCHAR(20),
    PRIMARY KEY (msv, maMon),
    FOREIGN KEY (msv) REFERENCES student(msv) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (maMon) REFERENCES monhoc(ma_mon) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO diem (msv, maMon, diemMon, diemHe4, diemChu, danhGia) VALUES
('23010199', 'FS001', 8.5, 3.5, 'B+', 'Đạt');
```
## VII. Giao diện hệ thống

### Giao diện đăng nhập
<img src ="img/Screenshot 2025-07-06 093944.png">

### Giao diện đăng ký
<img src ="img/Screenshot 2025-07-06 093958.png">

### Giao diện chính
<img src ="img/Screenshot 2025-07-06 093823.png">

### Giao diện quản lý sinh viên
<img src ="img/Screenshot 2025-07-06 093845.png">

### Giao diện quản lý môn học
<img src ="img/Screenshot 2025-07-06 093856.png">

### Giao diện quản lý điểm
<img src ="img/Screenshot 2025-07-06 093924.png">


# Tạo và sử dụng CSDL Quản lý điểm

```sql
-- Tạo cơ sở dữ liệu
CREATE DATABASE quanlydiem;

-- Sử dụng cơ sở dữ liệu
USE quanlydiem;

SHOW TABLES;
```

## Bảng `login`

```sql
-- Tạo bảng login
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Mô tả bảng login
DESCRIBE login;

-- Thêm tài khoản mặc định
INSERT IGNORE INTO login (username, password) VALUES ('admin', '111111');
```

## Bảng `student`

```sql
-- Tạo bảng student
CREATE TABLE student (
    msv VARCHAR(20) PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(100),
    dob DATE,
    khoa VARCHAR(50),
    classname VARCHAR(50)
);

-- Thêm dữ liệu mẫu sinh viên
INSERT INTO student (msv, fullname, gender, email, dob, khoa, classname) VALUES
('23010199', 'Nguyễn Văn A', 'Nam', 'nva@gmail.com', '2002-05-14', 'CNTT', 'CNTT1');
```

## Bảng `monhoc`

```sql
-- Tạo bảng môn học
CREATE TABLE monhoc (
    ma_mon VARCHAR(20) PRIMARY KEY,
    ten_mon VARCHAR(100) NOT NULL,
    so_tin_chi INT NOT NULL,
    so_tiet_lt VARCHAR(10),
    so_tiet_th VARCHAR(10),
    hoc_ky VARCHAR(10)
);

-- Thêm dữ liệu mẫu môn học
INSERT INTO monhoc (ma_mon, ten_mon, so_tin_chi, so_tiet_lt, so_tiet_th, hoc_ky) VALUES
('FS001', 'Lập trình Java', 3, '30', '15', '3');
```

## Bảng `diem`

```sql
-- Tạo bảng điểm
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

-- Thêm dữ liệu mẫu điểm
INSERT INTO diem (msv, maMon, diemMon, diemHe4, diemChu, danhGia) VALUES
('23010199', 'FS001', 8.5, 3.5, 'B+', 'Đạt');
```

## Truy vấn dữ liệu

```sql
SELECT * FROM login;
SELECT * FROM student;
SELECT * FROM monhoc;
SELECT * FROM diem;
```

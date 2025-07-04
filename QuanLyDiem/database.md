-- Tạo cơ sở dữ liệu
CREATE DATABASE quanlydiem;

-- Sử dụng cơ sở dữ liệu
USE quanlydiem;

-- Hiển thị các bảng trong cơ sở dữ liệu
SHOW TABLES;

-- Tạo bảng đăng nhập
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thêm tài khoản mặc định
INSERT IGNORE INTO login (username, password) 
VALUES ('admin', '111111');

-- Tạo bảng sinh viên
CREATE TABLE student (
    msv VARCHAR(20) PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(100),
    dob DATE,
    khoa VARCHAR(50),
    classname VARCHAR(50)
);

-- Tạo bảng môn học
CREATE TABLE monhoc (
    ma_mon VARCHAR(20) PRIMARY KEY,
    ten_mon VARCHAR(100) NOT NULL,
    so_tin_chi INT NOT NULL,
    so_tiet_lt VARCHAR(10),
    so_tiet_th VARCHAR(10),
    hoc_ky VARCHAR(10)
);

-- Tạo bảng điểm
CREATE TABLE diem (
    msv VARCHAR(20) NOT NULL,
    maMon VARCHAR(20) NOT NULL,
    diemMon DOUBLE,
    diemHe4 DOUBLE,
    diemChu VARCHAR(5),
    danhGia VARCHAR(20),
    PRIMARY KEY (msv, maMon),
    FOREIGN KEY (msv) REFERENCES student(msv),
    FOREIGN KEY (maMon) REFERENCES monhoc(ma_mon)
);

-- Hiển thị dữ liệu bảng login
SELECT * FROM login;

-- Hiển thị dữ liệu bảng student
SELECT * FROM student;

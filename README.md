# HỆ THỐNG QUẢN LÝ ĐIỂM CỦA SINH VIÊN KHOA CNTT
# GIỚI THIỆU DỰ ÁN
Đây là dự án quản lý điểm của sinh viên khoa cntt
# CÁC THÀNH VIÊN TRONG NHÓM
1. Nguyễn Trọng Huy
2. Bùi Thế Phương
# Project: point management
# Bài Tập Lớn: Ứng dụng Quản Lý Hệ Thống Điểm Của Sinh Viên Khoa CNTT
## Yêu cầu chính:
- Giao diện Java Spring Boot

## Các chức năng chính

### 1. Quản lý điểm của sinh viên
- ✅ Thêm, sửa, xóa điểm của sinh viên
- ✅ Liệt kê thông tin điểm của sinh viên, lọc điểm theo từng sinh viên

### 2. Quản lý sinh viên
- ✅ Thêm, sửa, xóa sinh viên

### 3. Gán điểm cho sinh viên
- ✅ Hỗ trợ chức năng gán điểm cho từng sinh viên

# Object: sinh viên, môn học, lớp học, điểm
Sinh viên: bao gồm tất cả thông tin liên quan đến sinh viên

Class Sinhvien{

       int SinhvienID;

       String Tensinhvien;

       int Tuoisinhvien;

   }

Môn học: bao gồm tất cả các thông tin liên quan đến môn học

Class Monhoc{

        int MonhocID;

        String Tenmonhoc;

        int sotinchi;

}

Sinhvien_Diem: lưu giữ sinh viên và điểm cụ thể của từng sinh viên

Class Sinhvien_Diem{ 

        String Sinhvien_DiemID;

        String MonhocID;

        int Diem;

}

# HỆ THỐNG QUẢN LÝ ĐIỂM CỦA SINH VIÊN KHOA CNTT
# GIỚI THIỆU DỰ ÁN
Đây là dự án quản lý điểm của các sinh viên khoa cntt
# CÁC THÀNH VIÊN TRONG NHÓM
1. Nguyễn Trọng Huy
2. Bùi Thế Phương
# Project: point management
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

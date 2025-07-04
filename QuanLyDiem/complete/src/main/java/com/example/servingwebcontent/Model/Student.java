package com.example.servingwebcontent.Model;

public class Student {
    private String msv;
    private String fullname;
    private String gender;
    private String email;
    private String dob;
    private String khoa;
    private String classname;

    public Student() {}

    public Student(String msv, String fullname, String gender, String email, String dob, String khoa, String classname) {
        this.msv = msv;
        this.fullname = fullname;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.khoa = khoa;
        this.classname = classname;
    }

    // Getter/setter cho tất cả trường
    public String getMsv() { return msv; }
    public void setMsv(String msv) { this.msv = msv; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getKhoa() { return khoa; }
    public void setKhoa(String khoa) { this.khoa = khoa; }

    public String getClassname() { return classname; }
    public void setClassname(String classname) { this.classname = classname; }
}

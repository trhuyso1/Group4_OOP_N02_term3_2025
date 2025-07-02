package Model;

public class Student {
    private String msv;
    private String fullName;
    private String gender;
    private String email;
    private String dob;
    private String khoa;
    private  String className;
    
    public Student() {
    }

    public Student(String msv, String fullName, String gender, String email, String dob, String khoa,
            String className) {
        this.msv = msv;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.khoa = khoa;
        this.className = className;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-20s | %-6s | %-22s | %-12s | %-8s | %-10s",
            msv, fullName, gender, email, dob, khoa, className);
    }

    

}

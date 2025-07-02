import test.TestStudent;
import test.TestMonhoc;
import test.TestDiem;

public class App {
    public static void main(String[] args) throws Exception {
        TestStudent tst = new TestStudent();
        tst.dsMauStudent();
        tst.inStudent();
        TestMonhoc tmon = new TestMonhoc();
        tmon.dsMauMonhoc();
        tmon.inMonhoc();
        TestDiem tdiem = new TestDiem();
        tdiem.dsMauDiem(tst.getStudentList(), tmon.getMonhocList()); // Sửa dòng này
        tdiem.inDiem();
    }
}

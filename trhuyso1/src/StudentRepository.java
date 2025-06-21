@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByMajor(String major);
}



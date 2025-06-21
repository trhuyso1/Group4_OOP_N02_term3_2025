@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public void saveStudent(Student s) {
        studentRepo.save(s);
    }

    public void deleteStudent(String id) {
        studentRepo.deleteById(id);
    }

    public Optional<Student> getStudent(String id) {
        return studentRepo.findById(id);
    }
}

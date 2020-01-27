import model.Student;
import repository.StudentRepository;
import util.StudentUtil;
import repository.StudentRepositoryImpl;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        StudentUtil studentUtil = new StudentUtil(studentRepository);
        List<Student> studentsList = studentRepository.getAllStudents();

        studentsList.forEach(student -> student.setStudentFinalGrade(studentUtil.calculateFinalGrade(student)));
        studentRepository.updateStudents(studentsList);

        studentsList = studentRepository.getStudentsByFinalGrades();

        studentsList.forEach(student -> System.out.println(student.getStudentFirstName() + " " + student.getStudentLastName() + " got grade: "+ student.getStudentFinalGrade()));



    }
}

package repository;

import model.Student;

import java.util.List;

public interface StudentRepository {
    public Student getStudentById(int studentId);

    public List<Student> getAllStudents();

    public List<Student> getStudentsByFinalGrades();

    public void updateStudents(List<Student> studentList);
}

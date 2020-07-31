package repository;

import model.Student;

import java.util.*;

public class StudentRepositoryImpl implements StudentRepository {

    private List<Student> studentList;
    private Map<Integer, Student> studentMap;

    /*public StudentRepositoryImpl(){
        studentList = new ArrayList<>();
        studentMap = new HashMap<>();
        this.studentList.add(new Student(100, "Jan", "Kowalski", Arrays.asList(3,4,5,4,3,5)));
        this.studentList.add(new Student(101, "Anna", "Nowak", Arrays.asList(5,4,5,4,5,5)));
        this.studentList.add(new Student(102, "Adam", "Małysz", Arrays.asList(3,3,3,4,3,5)));
        this.studentList.add(new Student(103, "Michał", "Pszczoliński", Arrays.asList(2,3,2,2,3,4)));
        studentList.forEach(student -> studentMap.put(student.getStudentId(), student));
    }*/

    public Student getStudentById(int studentId){
        return studentMap.get(studentId);
    }

    public List<Student> getAllStudents(){
        return studentList;
    }

    public List<Student> getStudentsByFinalGrades(){
        List<Student> listByGrades = studentList;
        Comparator<Student> comparator = Comparator.comparingDouble(Student::getStudentFinalGrade);
        listByGrades.sort(comparator);
        return listByGrades;
    }

    public void updateStudents(List<Student> studentList){
        this.studentList =  studentList;
    }


}

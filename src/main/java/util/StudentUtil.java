package util;

import model.Student;
import repository.StudentRepository;
import repository.StudentRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class StudentUtil {

    private final StudentRepository studentRepository;

    public StudentUtil(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    //Test nr #2 - obliczanie średniej ocen
    public double calculateFinalGrade(Student student){
        int sum = student.getStudentGrades().stream().mapToInt(integer -> integer).sum();
        return countTwoDigitsAverage(sum, student.getStudentGrades().size());
    }

    //Test nr #3 - obliczanie średnich każdej osoby w klasie
    public void calculateWholeClassGrades(List<Student> students){
        students.forEach(student -> student.setStudentFinalGrade(calculateFinalGrade(student)));
        studentRepository.updateStudents(students);
    }

    //Test nr #4 - wyszukiwanie studentów którzy oblali (średnia poniżej 3.0)
    public List<Student> findStudentsWhoFailed(){
        List<Student> failedStudents = new ArrayList<>();
        studentRepository.getAllStudents().forEach(student -> {
            if (student.getStudentFinalGrade() < 3.0){
                failedStudents.add(student);
            }
        });
        return failedStudents;
    }

    //Test nr #5 - sprawdzanie czy student jest lepszy od średniej klasowej
    public boolean isStudentBetterThanAverage(int studentId){
        Student student = studentRepository.getStudentById(studentId);
        double classAverage = calculateClassAverage();
        double studentGrade = calculateFinalGrade(student);
        if (studentGrade > classAverage)
            return true;
        else return false;
    }


    //Sprawdzane przy okazji testu nr #5 - obliczanie średniej klasowej nad postawie ostatecznych ocen każdego studenta
    public double calculateClassAverage(){
        List<Student> allStudents = studentRepository.getAllStudents();
        double sum = allStudents.stream().mapToDouble(student -> student.getStudentFinalGrade()).sum();
        return countTwoDigitsAverage(sum, allStudents.size());
    }

    private double countTwoDigitsAverage(double sum, int size){
        double result = sum / size;
        return Math.round(result*100)/100.0;
    }


}

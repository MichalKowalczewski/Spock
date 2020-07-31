package model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private List<Integer> studentGrades;
    double studentFinalGrade;

    public Student(int studentId, String studentFirstName, String studentLastName, List<Integer> studentGrades) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentGrades = studentGrades;
    }

    //Test nr #1
    public String getStudentFullName(){
        return studentFirstName + " " + studentLastName;
    }
}

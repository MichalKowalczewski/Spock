import model.Student
import repository.StudentRepository
import spock.lang.Specification
import spock.lang.Unroll
import util.StudentUtil

@Unroll
class StudentUtilSpec extends Specification {

    def studentList = [new Student(1, "A","B",[2,2,4])
                       ,new Student(2, "C","D",[4,5,3,2,4])
                       ,new Student(3, "E","F",[2,2,5,4,3,3,5])]

    def studentListWithResults = [new Student(1, "A","B",[2,2,4], 2.67 )
                                  ,new Student(2, "C","D",[4,5,3,2,4],3.6)
                                  ,new Student(3, "E","F",[2,2,5,4,3,3,5], 3.43)]

    def "Checking if Student Full Name displays correctly"(){
        when:
            Student student = new Student(230, "Przykladowy", "Student", [3,4])

        then:
            student.studentFullName == "Przykladowy Student"
            studentList*.studentFullName == ["A B", "C D", "E F"]
    }

    def "Checking if Final Grade calculation is done correctly for student with ID: #student.studentFullName"() {
        given:
            def calculator = new StudentUtil()

        when:
            def result = calculator.calculateFinalGrade(student)

        then:
            result == expectedResult as double


        where:
            student                                         | expectedResult
            new Student(1, "t", "t", [2, 2, 4])             | 2.67
            new Student(2, "s", "s", [4, 5, 3, 2, 4])       | 3.6
            new Student(3, "f", "f", [2, 2, 5, 4, 3, 3, 5]) | 3.43
    }

    def "Mocking data checks"(){
        given : "Creating Mock for the repository"
            def mockedRepository = Mock(StudentRepository.class)
            def studentUtil = new StudentUtil(mockedRepository)

        when : "Calculating grates for the whole class with exemplary data"
            studentUtil.calculateWholeClassGrades(studentList)
        then : "Update students should be called once, with the updated data"
            1*mockedRepository.updateStudents(studentList)
    }

    def "Stubing data checks"(){
        given : "Creating Stub for the repository"
            def stubedRepository = Stub(StudentRepository.class)
            def studentUtil = new StudentUtil(stubedRepository)
            stubedRepository.getAllStudents() >> studentListWithResults

        when : "Calling a tested method"
            def failedStudents = studentUtil.findStudentsWhoFailed()

        then : "checking if the result of the Stub is correct"
            failedStudents == [new Student(3, "E","F",[2,2,5,4,3,3,5], 3.43)]
    }

    def "Mock Stub combination"(){
        given : "Creating Mock for the repository"
            def mockedRepository = Mock(StudentRepository.class)
            def studentUtil = new StudentUtil(mockedRepository)

        when : "Calling a tested method"
            boolean isBetter = studentUtil.isStudentBetterThanAverage(123)

        then : "Stubing called methods, checking number of invocations, checking if the final condition is correct"
            1*mockedRepository.getStudentById(_ as Integer) >> new Student(123, "t","t",[3,3,5],3.66)

        then:
            1*mockedRepository.getAllStudents() >>         studentListWithResults
            isBetter
    }
}

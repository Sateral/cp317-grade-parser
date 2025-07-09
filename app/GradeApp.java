package implementation.app;

import implementation.domain.CourseRecord;
import implementation.domain.Student;
import implementation.io.CourseFileParser;
import implementation.io.CsvWriter;
import implementation.io.NameFileParser;
import implementation.service.StudentAssembler;
import implementation.service.GradeCalculator;

import java.nio.file.Path;
import java.util.List;

public class GradeApp {
  public static void main(String[] args) throws Exception {
    Path namesFilePath = Path.of("NameFile.txt");
    Path courseFilePath = Path.of("CourseFile.txt");
    Path outputFilePath = Path.of("output.csv");

    // Parse names and course records
    List<Student> students = new NameFileParser().parse(namesFilePath);
    List<CourseRecord> records = new CourseFileParser().parse(courseFilePath);

    new StudentAssembler().buildStudents(students, records);

    var calc = new GradeCalculator();
    students.forEach(s -> 
      s.getCourseRecords().forEach(r ->
        r.finalGrade(calc)));

    new CsvWriter().write(outputFilePath, students);

    System.out.println("Grades calculated and written to " + outputFilePath);
  }
}

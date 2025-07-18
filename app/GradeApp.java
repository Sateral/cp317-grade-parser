package implementation.app;

import implementation.domain.CourseRecord;
import implementation.domain.Student;
import implementation.io.CourseFileParser;
import implementation.io.CsvWriter;
import implementation.io.NameFileParser;
import implementation.service.StudentAssembler;

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

    // Merge students with their course records
    new StudentAssembler().buildStudents(students, records);

    // Create the CSV
    new CsvWriter().write(outputFilePath, students);

    System.out.println("Grades calculated and written to " + outputFilePath);
  }
}

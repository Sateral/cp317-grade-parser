package implementation.io;

import implementation.domain.CourseRecord;
import implementation.domain.Student;
import implementation.service.GradeCalculator;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

public class CsvWriter {
  public void write(Path outputPath, List<Student> students) throws Exception {
    // Check that params are valid
    if (students == null) {
      throw new IllegalArgumentException("Students list cannot be null.");
    }

    if (outputPath == null || outputPath.toString().trim().isEmpty()) {
      throw new IllegalArgumentException("Output path cannot be null or blank.");
    }

    // Make sure the original list remains unchanged
    List<Student> sortedStudents = new ArrayList<>(students);
    sortedStudents.sort((s1, s2) -> s1.getStudentId().compareTo(s2.getStudentId()));

    // Write to CSV file
    try (var writer = Files.newBufferedWriter(outputPath)) {
      writer.write("Student ID,Student Name,Course Code,Final Grade");
      writer.newLine();

      GradeCalculator calculator = new GradeCalculator();
      for (Student student : sortedStudents) {
    	for (CourseRecord record : student.getCourseRecords()) {
    		double finalGrade = calculator.calculateFinalGrade(record.getTests(), record.getFinalExam());
    		String line = String.format("%s,%s,%s,%.2f",
    				student.getStudentId(),
    				student.getName(),
    				record.getCourseCode(),
    				finalGrade);
    		writer.write(line);
    		writer.newLine();
    	}
      }
    }
  }
}

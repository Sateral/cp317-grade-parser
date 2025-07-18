package implementation.io;

import implementation.domain.CourseRecord;
import implementation.util.Constants;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;

public class CourseFileParser implements FileParser<CourseRecord> {
  @Override
  public List<CourseRecord> parse(Path filePath) throws Exception {
    // Check if the file path is valid
    if (filePath == null || !filePath.toFile().exists()) {
      throw new IllegalArgumentException("File path is null or does not exist.");
    }

    List<CourseRecord> courseRecords = new ArrayList<>();
    try (var lines = Files.lines(filePath)) {
      int line = 0;
      for (String lineContent : (Iterable<String>) lines::iterator) {
        line++;
        if (lineContent.isBlank()) {
          continue; // Skip empty lines
        }

        // Split the line into student ID, course code, and grades
        String[] data = lineContent.split(",", 6);
        
        // Ensure there are exactly six parts: student ID, course code, and 4 grades
        if (data.length != 6) {
          throw new IllegalArgumentException("Bad course record format at line " + line + ": " + lineContent);
        }

        String studentId = data[0].trim();
        String courseCode = data[1].trim();

        double[] tests = new double[Constants.NUM_TESTS];
        for (int i = 0; i < Constants.NUM_TESTS; i++) {
          try {
            tests[i] = Double.parseDouble(data[i + 2].trim());
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid grade format at line " + line + ": " + lineContent);
          }
        }
        
        double finalExam;
        try {
          finalExam = Double.parseDouble(data[5].trim());
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid final exam format at line " + line + ": " + lineContent);
        }

        // Validate student ID and course code
        if (studentId.isEmpty() || courseCode.isEmpty()) {
          throw new IllegalArgumentException("Invalid student ID or course code at line " + line + ": " + lineContent);
        }

        courseRecords.add(new CourseRecord(studentId, courseCode, tests, finalExam));
      }
    }

    return courseRecords;
  }
}

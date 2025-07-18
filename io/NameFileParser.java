package implementation.io;

import implementation.domain.Student;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NameFileParser implements FileParser<Student> {
  @Override
  public List<Student> parse(Path filePath) throws Exception {
    // Check if the file path is valid
    if (filePath == null || !filePath.toFile().exists()) {
      throw new IllegalArgumentException("File path is null or does not exist.");
    }

    List<Student> students = new ArrayList<>();
    try (var lines = Files.lines(filePath)) {
      int line = 0;
      for (String lineContent : (Iterable<String>) lines::iterator) {
        line++;
        if (lineContent.isBlank()) {
          continue; // Skip empty lines
        }

        // Split the line into student ID and name
        String[] data = lineContent.split(",", 2);
        
        // Ensure there are exactly two parts: student ID and name
        if (data.length != 2) {
          throw new IllegalArgumentException("Bad name format at line " + line + ": " + lineContent);
        }

        String studentId = data[0].trim();
        String name = data[1].trim();

        // Validate student ID and name
        if (studentId.isEmpty() || name.isEmpty()) {
          throw new IllegalArgumentException("Invalid student ID or name at line " + line + ": " + lineContent);
        }

        students.add(new Student(studentId, name));
      }
    }

    return students;
  }
}


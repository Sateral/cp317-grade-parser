package implementation.io;

import implementation.domain.Student;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NameFileParser implements FileParser<Student> {
  @Override
  public List<Student> parse(Path filePath) throws Exception {
    // TODO: read file to create a list of Student objects

    return new ArrayList<>();
  }
}


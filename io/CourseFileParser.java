package implementation.io;

import implementation.domain.CourseRecord;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CourseFileParser implements FileParser<CourseRecord> {
  @Override
  public List<CourseRecord> parse(Path filePath) throws Exception {
    // TODO: read file to create a list of CourseRecord objects

    return new ArrayList<>();
  }
}

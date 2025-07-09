package implementation.io;

import java.nio.file.Path;
import java.util.List;

public interface FileParser<T> {
  List<T> parse(Path filePath) throws Exception;
}

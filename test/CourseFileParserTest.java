package implementation.test;

import implementation.domain.CourseRecord;
import implementation.io.CourseFileParser;

import java.nio.file.Path;
import java.util.List;

public class CourseFileParserTest {

    private final CourseFileParser parser = new CourseFileParser();
    private int passed = 0;
    private int total  = 0;

    // Tests
    private void testParseValidFile() {
        total++;
        try {
            Path file = Path.of("implementation/test/course files/test_courses.txt");
            List<CourseRecord> records = parser.parse(file);

            boolean valid = true;
            valid &= records.size() == 3;
            if (!valid) {
                System.out.println("FAIL testParseValidFile: size was " + records.size());
            }

            CourseRecord first = records.get(0);
            valid &= "12345".equals(first.getStudentId());
            valid &= "CS101".equals(first.getCourseCode());
            valid &= first.getTests()[0] == 85.0;
            valid &= first.getFinalExam() == 92.0;

            if (valid) {
                passed++;   // counts only if all of the checks passed
                System.out.println("PASS testParseValidFile");
            } else {
                System.out.println("FAIL testParseValidFile: values mismatch");
            }
        } catch (Exception e) {
            System.out.println("FAIL testParseValidFile: threw " + e);
        }
    }

    private void testNullPath() {
        total++;
        try {
            parser.parse(null);
            System.out.println("FAIL testNullPath: no exception was thrown");
        } catch (IllegalArgumentException expected) {
            passed++;
            System.out.println("PASS testNullPath");
        } catch (Exception e) {
            System.out.println("FAIL testNullPath: wrong exception " + e);
        }
    }

    private void testMissingFile() {
        total++;
        try {
            parser.parse(Path.of("no_such_file.txt"));
            System.out.println("FAIL testMissingFile: no exception was thrown");
        } catch (IllegalArgumentException expected) {
            passed++;
            System.out.println("PASS testMissingFile");
        } catch (Exception e) {
            System.out.println("FAIL testMissingFile: wrong exception " + e);
        }
    }

    private void testInvalidFileFormat() {
        total++;
        try {
        	
            Path badFile = Path.of("test/resources/invalid_courses.txt");
            parser.parse(badFile);
            System.out.println("FAIL testInvalidFileFormat: no exception was thrown");
        } catch (IllegalArgumentException expected) {
            passed++;
            System.out.println("PASS testInvalidFileFormat");
        } catch (Exception e) {
            System.out.println("FAIL testInvalidFileFormat: wrong exception " + e);
        }
    }

    private void testSkipEmptyLines() {
        total++;
        try {
            Path emptyLineFile = Path.of("implementation/test/course files/empty_lines.txt");
            List<CourseRecord> records = parser.parse(emptyLineFile);

            if (records.size() == 2) {
                passed++;
                System.out.println("PASS testSkipEmptyLines");
            } else {
                System.out.println("FAIL testSkipEmptyLines: size " + records.size());
            }
        } catch (Exception e) {
            System.out.println("FAIL testSkipEmptyLines: threw " + e);
        }
    }

    // Run all tests
    public void runAll() {
        System.out.println("Running CourseFileParser tests…\n");

        testParseValidFile();
        testNullPath();
        testMissingFile();
        testInvalidFileFormat();
        testSkipEmptyLines();

        System.out.printf("%nResult: %d / %d tests passed%n", passed, total);
    }

    // Entry point
    public static void main(String[] args) {
        new CourseFileParserTest().runAll();
    }
}
package implementation.test;

import implementation.domain.Student;
import implementation.io.NameFileParser;

import java.nio.file.Path;
import java.util.List;

public class NameFileParserTest {

    private final NameFileParser parser = new NameFileParser();
    private int passed = 0;
    private int total  = 0;

    // Tests
    private void testParseValidFile() {
        total++;
        try {
        	Path file = Path.of("implementation/test/name files/test_names.txt");
            List<Student> students = parser.parse(file);

            boolean valid = true;
            valid &= students.size() == 3;
            Student first = students.get(0);
            valid &= "12345".equals(first.getStudentId());
            valid &= "John Doe".equals(first.getName());

            if (valid) {
                passed++;
                System.out.println("PASS testParseValidFile");
            } else {
                System.out.println("FAIL testParseValidFile: wrong data or count");
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
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testNullPath");
        } catch (Exception e) {
            System.out.println("FAIL testNullPath: wrong exception " + e);
        }
    }

    private void testMissingFile() {
        total++;
        try {
            parser.parse(Path.of("no_such_names_file.txt"));
            System.out.println("FAIL testMissingFile: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testMissingFile");
        } catch (Exception e) {
            System.out.println("FAIL testMissingFile: wrong exception " + e);
        }
    }

    private void testBadLineFormat() {
        total++;
        try {
            Path badFile = Path.of("implementation/test/name files/bad_names.txt");
            parser.parse(badFile);
            System.out.println("FAIL testBadLineFormat: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testBadLineFormat");
        } catch (Exception e) {
            System.out.println("FAIL testBadLineFormat: wrong exception " + e);
        }
    }

    private void testEmptyStudentId() {
        total++;
        try {
            Path noIdFile = Path.of("implementation/test/name files/bad_names.txt");
            parser.parse(noIdFile);
            System.out.println("FAIL testEmptyStudentId: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testEmptyStudentId");
        } catch (Exception e) {
            System.out.println("FAIL testEmptyStudentId: wrong exception " + e);
        }
    }

    private void testEmptyName() {
        total++;
        try {
            Path noNameFile = Path.of("implementation/test/name files/bad_names.txt");
            parser.parse(noNameFile);
            System.out.println("FAIL testEmptyName: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testEmptyName");
        } catch (Exception e) {
            System.out.println("FAIL testEmptyName - wrong exception " + e);
        }
    }

    private void testSkipEmptyLines() {
        total++;
        try {
        	Path emptyLinesFile = Path.of("implementation/test/name files/empty_lines.txt");
            List<Student> students = parser.parse(emptyLinesFile);

            if (students.size() == 3) {
                passed++;
                System.out.println("PASS testSkipEmptyLines");
            } else {
                System.out.println("FAIL testSkipEmptyLines - count " + students.size());
            }
        } catch (Exception e) {
            System.out.println("FAIL testSkipEmptyLines - threw " + e);
        }
    }

    private void testNameWithCommas() {
        total++;
        try {
            Path nameWithCommasFile = Path.of("implementation/test/name files/name_with_commas.txt");
            List<Student> students = parser.parse(nameWithCommasFile);

            boolean valid = true;
            valid &= students.size() == 2;
            valid &= "\"Smith, Jr., John\"".equals(students.get(0).getName());

            if (valid) {
                passed++;
                System.out.println("PASS testNameWithCommas");
            } else {
                System.out.println("FAIL testNameWithCommas: wrong data");
            }
        } catch (Exception e) {
            System.out.println("FAIL testNameWithCommas: threw " + e);
        }
    }

    // Run all tests
    public void runAll() {
        System.out.println("Running NameFileParser tests…\n");

        testParseValidFile();
        testNullPath();
        testMissingFile();
        testBadLineFormat();
        testEmptyStudentId();
        testEmptyName();
        testSkipEmptyLines();
        testNameWithCommas();

        System.out.printf("%nResult: %d / %d tests passed%n", passed, total);
    }

    // Entry point
    public static void main(String[] args) {
        new NameFileParserTest().runAll();
    }
}
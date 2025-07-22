package implementation.test;

import implementation.domain.CourseRecord;
import implementation.domain.Student;
import implementation.service.StudentAssembler;

import java.util.ArrayList;
import java.util.List;

public class StudentAssemblerTest {

    private final StudentAssembler assembler = new StudentAssembler();
    private int passed = 0;
    private int total  = 0;

    // Helper functions
    private List<Student> makeStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("12345", "John Doe"));
        list.add(new Student("67890", "Jane Smith"));
        list.add(new Student("11111", "Alice Johnson"));
        return list;
    }

    private List<CourseRecord> makeRecords() {
        List<CourseRecord> list = new ArrayList<>();
        list.add(new CourseRecord("12345", "CS101",  new double[]{85, 90, 88}, 92));
        list.add(new CourseRecord("12345", "MATH201",new double[]{95, 92, 90}, 94));
        list.add(new CourseRecord("67890", "CS102",  new double[]{78, 82, 85}, 80));
        list.add(new CourseRecord("11111", "PHYS101",new double[]{88, 86, 90}, 87));
        return list;
    }

    // Tests
    private void testValidData() {
        total++;
        try {
            List<Student> students = makeStudents();
            List<CourseRecord> records = makeRecords();

            List<Student> result = assembler.buildStudents(students, records);

            boolean valid = true;
            // Make sure same instance of original students
            valid &= result == students;
            
            // Make sure size is correct
            valid &= result.size() == 3;
            Student john  = result.get(0);
            Student jane  = result.get(1);
            Student alice = result.get(2);
            valid &= john.getCourseRecords().size() == 2;
            valid &= jane.getCourseRecords().size() == 1;
            valid &= alice.getCourseRecords().size() == 1;
            valid &= "CS101".equals(
                    john.getCourseRecords().get(0).getCourseCode());

            if (valid) {
                passed++;
                System.out.println("PASS testValidData");
            } else {
                System.out.println("FAIL testValidData: wrong mapping");
            }
        } catch (Exception e) {
            System.out.println("FAIL testValidData: threw " + e);
        }
    }

    private void testNullStudents() {
        total++;
        try {
            assembler.buildStudents(null, makeRecords());
            System.out.println("FAIL testNullStudents: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testNullStudents");
        } catch (Exception e) {
            System.out.println("FAIL testNullStudents: wrong exception " + e);
        }
    }

    private void testNullRecords() {
        total++;
        try {
            assembler.buildStudents(makeStudents(), null);
            System.out.println("FAIL testNullRecords: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testNullRecords");
        } catch (Exception e) {
            System.out.println("FAIL testNullRecords: wrong exception " + e);
        }
    }

    private void testStudentNotFound() {
        total++;
        try {
            List<Student> students = makeStudents();
            List<CourseRecord> recs = new ArrayList<>();
            recs.add(new CourseRecord("54321","AB123",new double[]{90,88,92},95));

            assembler.buildStudents(students, recs);
            System.out.println("FAIL testStudentNotFound: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testStudentNotFound");
        } catch (Exception e) {
            System.out.println("FAIL testStudentNotFound: wrong exception " + e);
        }
    }

    private void testEmptyLists() {
        total++;
        try {
            List<Student> students = new ArrayList<>();
            List<CourseRecord> records = new ArrayList<>();
            List<Student> out = assembler.buildStudents(students, records);

            if (out.size() == 0 && out == students) {
                passed++;
                System.out.println("PASS testEmptyLists");
            } else {
                System.out.println("FAIL testEmptyLists: wrong result");
            }
        } catch (Exception e) {
            System.out.println("FAIL testEmptyLists: threw " + e);
        }
    }

    private void testEmptyRecords() {
        total++;
        try {
            List<Student> students = makeStudents();
            List<CourseRecord> records = new ArrayList<>();
            List<Student> out = assembler.buildStudents(students, records);

            boolean valid = out.stream()
                            .allMatch(s -> s.getCourseRecords().isEmpty());

            if (valid) {
                passed++;
                System.out.println("PASS testEmptyRecords");
            } else {
                System.out.println("FAIL testEmptyRecords: records attached");
            }
        } catch (Exception e) {
            System.out.println("FAIL testEmptyRecords: threw " + e);
        }
    }

    private void testEmptyStudents() {
        total++;
        try {
            assembler.buildStudents(new ArrayList<>(), makeRecords());
            System.out.println("FAIL testEmptyStudents: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;
            System.out.println("PASS testEmptyStudents");
        } catch (Exception e) {
            System.out.println("FAIL testEmptyStudents: wrong exception " + e);
        }
    }

    private void testMultipleCoursesSameStudent() {
        total++;
        try {
            List<Student> onlyJohn = new ArrayList<>();
            onlyJohn.add(new Student("12345", "John Doe"));

            List<CourseRecord> records = new ArrayList<>();
            records.add(new CourseRecord("12345","CS101", new double[]{85,90,88},92));
            records.add(new CourseRecord("12345","CS102", new double[]{78,82,85},80));
            records.add(new CourseRecord("12345","MATH201",new double[]{95,92,90},94));

            List<Student> out = assembler.buildStudents(onlyJohn, records);

            if (out.size() == 1 &&
                out.get(0).getCourseRecords().size() == 3) {
                passed++;
                System.out.println("PASS testMultipleCoursesSameStudent");
            } else {
                System.out.println("FAIL testMultipleCoursesSameStudent: wrong count");
            }
        } catch (Exception e) {
            System.out.println("FAIL testMultipleCoursesSameStudent: threw " + e);
        }
    }

    private void testOriginalListsUnchanged() {
        total++;
        try {
            List<Student> students = makeStudents();
            List<CourseRecord> records = makeRecords();
            int sSize = students.size();
            int rSize = records.size();

            assembler.buildStudents(students, records);

            boolean valid = (students.size() == sSize) && (records.size() == rSize);
            valid &= students.stream().anyMatch(s -> !s.getCourseRecords().isEmpty());

            if (valid) {
                passed++;
                System.out.println("PASS testOriginalListsUnchanged");
            } else {
                System.out.println("FAIL testOriginalListsUnchanged: lists modified incorrectly");
            }
        } catch (Exception e) {
            System.out.println("FAIL testOriginalListsUnchanged: threw " + e);
        }
    }

    // Run all tests
    public void runAll() {
        System.out.println("Running StudentAssembler tests…\n");

        testValidData();
        testNullStudents();
        testNullRecords();
        testStudentNotFound();
        testEmptyLists();
        testEmptyRecords();
        testEmptyStudents();
        testMultipleCoursesSameStudent();
        testOriginalListsUnchanged();

        System.out.printf("%nResult: %d / %d tests passed%n", passed, total);
    }

    // Entry point
    public static void main(String[] args) {
        new StudentAssemblerTest().runAll();
    }
}
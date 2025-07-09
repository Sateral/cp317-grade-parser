package implementation.service;

import implementation.domain.CourseRecord;
import implementation.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAssembler {
  public List<Student> buildStudents(List<Student> students, List<CourseRecord> records) {
    // TODO: attach each course record to the corresponding student
    Map<String, Student> studentMap = new HashMap<>();
    students.forEach(s -> studentMap.put(s.getStudentId(), s));
    // Iterate through each course record and add it to the corresponding student
    return students;
  }
}
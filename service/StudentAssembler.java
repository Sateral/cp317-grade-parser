package implementation.service;

import implementation.domain.CourseRecord;
import implementation.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAssembler {
  public List<Student> buildStudents(List<Student> students, List<CourseRecord> records) {
    // Check that params are valid
    if (students == null || records == null) {
      throw new IllegalArgumentException("Students and records cannot be null.");
    }

    Map<String, Student> studentMap = new HashMap<>();
    students.forEach(s -> studentMap.put(s.getStudentId(), s));

    // Iterate through each course record and add it to the corresponding student
    for (CourseRecord record : records) {
      String studentId = record.getStudentId();
      // Check if the student exists in the map
      if (studentMap.containsKey(studentId)) {
        Student student = studentMap.get(studentId);
        student.addCourseRecord(record);
      } else {
        throw new IllegalArgumentException("Student ID " + studentId + " not found in the students list.");
      }
    }

    return students;
  }
}
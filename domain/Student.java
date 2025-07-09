package implementation.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
  private final String studentId;
  private final String name;
  private final List<CourseRecord> courseRecords = new ArrayList<>();

  public Student(String studentId, String name) {
    this.studentId = studentId;
    this.name = name;
  }

  public void addCourseRecord(CourseRecord record) {
    courseRecords.add(record);
  }

  public String getStudentId() {
    return studentId;
  }

  public String getName() {
    return name;
  }

  public List<CourseRecord> getCourseRecords() {
    return Collections.unmodifiableList(courseRecords);
  }
}
package implementation.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
  private final String studentId;
  private final String name;
  private final List<CourseRecord> courseRecords = new ArrayList<>();

  public Student(String studentId, String name) {
    if (studentId == null || studentId.isBlank())
		  throw new IllegalArgumentException("studentId must not be blank");
	
	  if (name == null || name.isBlank())
		  throw new IllegalArgumentException("name must not be blank");
	
    this.studentId = studentId;
    this.name = name;
  }

  public void addCourseRecord(CourseRecord record) {
    if (record == null)
		  throw new IllegalArgumentException("record must not be null");
	
	  boolean isDuplicate = courseRecords.stream()
			.anyMatch(r -> r.getCourseCode()
				.equals(record.getCourseCode()));
	
    if (isDuplicate) {
      throw new IllegalStateException(
        "Duplicate course " + record.getCourseCode() + " for student " + studentId);
    }
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
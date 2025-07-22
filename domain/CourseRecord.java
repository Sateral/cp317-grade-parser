package implementation.domain;

import implementation.service.GradeCalculator;
import util.Constants;

public class CourseRecord {
  private final String studentId;
  private final String courseCode;
  private final double[] tests;
  private final double finalExam;

  public CourseRecord(String studentId, String courseCode, double[] tests, double finalExam) {
    if (studentId == null || studentId.isBlank())
		  throw new IllegalArgumentException("studentId must not be blank");
	
    if (courseCode == null || courseCode.isBlank())
      throw new IllegalArgumentException("courseCode must not be blank");
    
    if (tests.length != Constants.NUM_TESTS || tests == null)
      throw new IllegalArgumentException("must provide exactly 3 tests");
    
    for (double d : tests) {
      if (d < 0 || d > 100)
        throw new IllegalArgumentException("test score out of range: " + d);
    }

    if (finalExam < 0 || finalExam > 100)
      throw new IllegalArgumentException("final exam score out of range: " + finalExam);
    
    this.studentId = studentId;
    this.courseCode = courseCode;
    this.tests = tests;
    this.finalExam = finalExam;
  }

  public String getStudentId() {
    return studentId;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public double[] getTests() {
    return tests;
  }

  public double getFinalExam() {
    return finalExam;
  }

  public double finalGrade(GradeCalculator calculator) {
    return calculator.calculateFinalGrade(tests, finalExam);
  }
}

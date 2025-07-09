package implementation.domain;

import implementation.service.GradeCalculator;

public class CourseRecord {
  private final String studentId;
  private final String courseCode;
  private final double[] tests;
  private final double finalExam;

  public CourseRecord(String studentId, String courseCode, double[] tests, double finalExam) {
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

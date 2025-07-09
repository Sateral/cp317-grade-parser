package implementation.service;

import implementation.util.Constants;

public class GradeCalculator {
  public double calculateFinalGrade(double[] tests, double finalExam) {
    // TODO: do some validation on the inputs

    return (tests[0] + tests[1] + tests[2]) * Constants.TEST_WEIGHT +
           finalExam * Constants.FINAL_EXAM_WEIGHT;
  }
}
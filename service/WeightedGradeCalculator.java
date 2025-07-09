package implementation.service;

import implementation.util.Constants;

public class WeightedGradeCalculator implements GradeCalculator{
  @Override
  public double calculateFinalGrade(double[] tests, double finalExam) {
    // TODO: input validation/rounding
    return (tests[0] + tests[1] + tests[2]) * Constants.TEST_WEIGHT + finalExam * Constants.FINAL_WEIGHT;
  }
}

package implementation.service;

import implementation.util.Constants;

public class GradeCalculator {
  public double calculateFinalGrade(double[] tests, double finalExam) {
    // Check that params are valid
    if (tests == null || tests.length != Constants.NUM_TESTS) {
      throw new IllegalArgumentException("Must provide exactly 3 grades.");
    }

    double weightedTests = 0;
    for (double test : tests) {
      weightedTests += test * Constants.TEST_WEIGHT;
    }

    double weightedFinal = finalExam * Constants.FINAL_EXAM_WEIGHT;

    return weightedTests + weightedFinal;
  }
}
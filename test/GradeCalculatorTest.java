package implementation.test;

import implementation.service.GradeCalculator;
import implementation.util.Constants;

public class GradeCalculatorTest {

    private final GradeCalculator calc = new GradeCalculator();
    private int passed = 0;
    private int total  = 0;

    // Helper function
    private boolean closeEnough(double a, double b) {
        return Math.abs(a - b) < 0.001;
    }

    // Tests
    private void testValidInputs() {
        total++;
        try {
            double[] tests = {85, 90, 88};
            double finalExam = 92;
            double expected = (85 + 90 + 88) * Constants.TEST_WEIGHT
                            + 92 * Constants.FINAL_WEIGHT;
            double actual = calc.calculateFinalGrade(tests, finalExam);

            if (closeEnough(expected, actual)) {
                passed++;  System.out.println("PASS testValidInputs");
            } else {
                System.out.println("FAIL testValidInputs: got " + actual);
            }
        } catch (Exception e) {
            System.out.println("FAIL testValidInputs: threw " + e);
        }
    }

    private void testPerfectScores() {
        total++;
        try {
            double[] tests = {100, 100, 100};
            double expected = 100.0;
            double actual = calc.calculateFinalGrade(tests, 100);

            if (closeEnough(expected, actual)) {
                passed++;  System.out.println("PASS testPerfectScores");
            } else {
                System.out.println("FAIL testPerfectScores: got " + actual);
            }
        } catch (Exception e) {
            System.out.println("FAIL testPerfectScores: threw " + e);
        }
    }

    private void testZeroScores() {
        total++;
        try {
            double[] tests = {0, 0, 0};
            double actual = calc.calculateFinalGrade(tests, 0);

            if (closeEnough(0.0, actual)) {
                passed++;  System.out.println("PASS testZeroScores");
            } else {
                System.out.println("FAIL testZeroScores: got " + actual);
            }
        } catch (Exception e) {
            System.out.println("FAIL testZeroScores: threw " + e);
        }
    }

    private void testMixedScores() {
        total++;
        try {
            double[] tests = {70, 80, 90};
            double finalExam = 85;
            double expected = (70 + 80 + 90) * Constants.TEST_WEIGHT
                            + 85 * Constants.FINAL_WEIGHT;
            double actual = calc.calculateFinalGrade(tests, finalExam);

            if (closeEnough(expected, actual)) {
                passed++;  System.out.println("PASS testMixedScores");
            } else {
                System.out.println("FAIL testMixedScores: got " + actual);
            }
        } catch (Exception e) {
            System.out.println("FAIL testMixedScores: threw " + e);
        }
    }

    private void testNullTestArray() {
        total++;
        try {
            calc.calculateFinalGrade(null, 85);
            System.out.println("FAIL testNullTestArray: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;  System.out.println("PASS testNullTestArray");
        } catch (Exception e) {
            System.out.println("FAIL testNullTestArray: wrong exception " + e);
        }
    }

    private void testWrongArrayLength() {
        total++;
        try {
            calc.calculateFinalGrade(new double[]{85, 90}, 85);  // only 2 tests
            System.out.println("FAIL testWrongArrayLength: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;  System.out.println("PASS testWrongArrayLength");
        } catch (Exception e) {
            System.out.println("FAIL testWrongArrayLength: wrong exception " + e);
        }
    }

    private void testTooManyTests() {
        total++;
        try {
            calc.calculateFinalGrade(new double[]{85, 90, 88, 92}, 85); // 4 tests
            System.out.println("FAIL testTooManyTests: no exception was thrown");
        } catch (IllegalArgumentException ok) {
            passed++;  System.out.println("PASS testTooManyTests");
        } catch (Exception e) {
            System.out.println("FAIL testTooManyTests: wrong exception " + e);
        }
    }

    private void testNegativeGrades() {
        total++;
        try {
            double[] tests = {-10, 90, 88};
            double finalExam = 85;
            double expected = (-10 + 90 + 88) * Constants.TEST_WEIGHT
                            + 85 * Constants.FINAL_WEIGHT;
            double actual = calc.calculateFinalGrade(tests, finalExam);

            if (closeEnough(expected, actual)) {
                passed++;  System.out.println("PASS testNegativeGrades");
            } else {
                System.out.println("FAIL testNegativeGrades: got " + actual);
            }
        } catch (Exception e) {
            System.out.println("FAIL testNegativeGrades: threw " + e);
        }
    }

    private void testHighGrades() {
        total++;
        try {
            double[] tests = {110, 105, 108};
            double finalExam = 102;
            double expected = (110 + 105 + 108) * Constants.TEST_WEIGHT
                            + 102 * Constants.FINAL_WEIGHT;
            double actual = calc.calculateFinalGrade(tests, finalExam);

            if (closeEnough(expected, actual)) {
                passed++;  System.out.println("PASS testHighGrades");
            } else {
                System.out.println("FAIL testHighGrades: got " + actual);
            }
        } catch (Exception e) {
            System.out.println("FAIL testHighGrades: threw " + e);
        }
    }

    // Run all tests
    public void runAll() {
        System.out.println("Running GradeCalculator tests…\n");

        testValidInputs();
        testPerfectScores();
        testZeroScores();
        testMixedScores();
        testNullTestArray();
        testWrongArrayLength();
        testTooManyTests();
        testNegativeGrades();
        testHighGrades();

        System.out.printf("%nResult: %d / %d tests passed%n", passed, total);
    }

    // Entry point
    public static void main(String[] args) {
        new GradeCalculatorTest().runAll();
    }
}
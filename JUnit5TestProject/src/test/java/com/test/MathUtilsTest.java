package com.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MathUtilsTest {

	private MathUtils mathUtils;
	private TestInfo testInfo;
	private TestReporter testReporter;
	
	@BeforeAll
	public static void init() {
		System.out.println("Before all");
	}
	
	@BeforeEach
	public void initBeforeEachTest(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running "+testInfo.getTestMethod().get().getName()+" with tags "+ testInfo.getTags());
	}
	
	@AfterAll
	public static void cleanResources() {
		System.out.println("Clean resource after all test cases");
	}
	
	@Nested
	class AddTest {
		
		@RepeatedTest(3)
		@DisplayName("Testing add positive numbers method")
		void testAddingTwoPositives(RepetitionInfo repetitionInfo) {
			if (repetitionInfo.getCurrentRepetition() == 2) {
				// ...
			}
			assertEquals(2, mathUtils.add(1, 1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		@DisplayName("Testing add negatives numbers method")
		void testAddingTwoNegatives() {
			assertEquals(-2, mathUtils.add(-1, -1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		@Tag("IMPORTANT")
		@DisplayName("Testing add positive and negatives number method")
		void testAddingAPositiveAndANegative() {
			int expected = -2;
			int actual = mathUtils.add(-3, 1);
			assertEquals(expected, actual, () -> "Add method should return "+actual+" the sum of two numbers is "+expected);
		}
	}

	@Test
	@DisplayName("Testing multiply method")
	@Tag("IMPORTANT")
	void testMultiply() {
		boolean isServerUp = true;
		assumeTrue(isServerUp); // Based on conditions or suppiler
		assertAll(
				() -> assertEquals(0, mathUtils.multiply(1, 0)),
				() -> assertEquals(1, mathUtils.multiply(1, 1)),
				() -> assertEquals(6, mathUtils.multiply(2, 3))
				);
	}
	
	@Test
	@Disabled("TDD development - In Progress")
	@EnabledOnOs(OS.WINDOWS)
	@EnabledOnJre(JRE.JAVA_8)
	@EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "DEV")
	@EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Oracle.*")
	@DisplayName("Testing divide method")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), 
				"Divide should throw ArithmeticException when denominator is zero");
	}

	@Test
	@DisplayName("Testing Compute cirle area")
	@Tag("CIRCLE")
	void computeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), 
				"Should return right circle area");
	}
}

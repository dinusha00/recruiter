package com.recruiter.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testEqualRecruitementsForGroupOf1() {
		final Double amount = Calculator.getInstance().calculateFee(1, 0, 5, 200, 10);
		assertEquals(200, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf2() {
		final Double amount = Calculator.getInstance().calculateFee(2, 0, 5, 200, 10);
		assertEquals(400, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf3() {
		final Double amount = Calculator.getInstance().calculateFee(3, 0, 5, 200, 10);
		assertEquals(600, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf4() {
		final Double amount = Calculator.getInstance().calculateFee(4, 0, 5, 200, 10);
		assertEquals(800, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf5() {
		final Double amount = Calculator.getInstance().calculateFee(5, 1, 5, 200, 10);
		assertEquals(1100, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf6() {
		final Double amount = Calculator.getInstance().calculateFee(6, 1, 5, 200, 10);
		assertEquals(1300, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf7() {
		final Double amount = Calculator.getInstance().calculateFee(7, 1, 5, 200, 10);
		assertEquals(1500, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf10() {
		final Double amount = Calculator.getInstance().calculateFee(10, 2, 5, 200, 10);
		assertEquals(2200, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf99() {
		final Double amount = Calculator.getInstance().calculateFee(99, 19, 5, 200, 10);
		assertEquals(21700, amount, 0);
	}

	@Test
	public void testEqualRecruitementsForGroupOf100() {
		final Double amount = Calculator.getInstance().calculateFee(100, 20, 5, 200, 10);
		assertEquals(22000, amount, 0);
	}
}

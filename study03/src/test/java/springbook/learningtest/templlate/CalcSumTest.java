package springbook.learningtest.templlate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calaulator;
	String numFilepath;

	@Before
	public void setUp() {
		this.calaulator = new Calculator();
		this.numFilepath = getClass().getResource("numbers.txt").getPath();
	}


	@Test
	public void sumOfNumbers() throws IOException {
		Calculator calculator = new Calculator();

		int sum = calculator.calcSum(this.numFilepath);
		assertThat(sum, is(10));
	}

	@Test
	public void multiplyOfNumbers() throws IOException {
		Calculator calculator = new Calculator();

		int calcMultiply = calculator.calcMultiply(this.numFilepath);
		assertThat(calcMultiply, is(24));
	}
}

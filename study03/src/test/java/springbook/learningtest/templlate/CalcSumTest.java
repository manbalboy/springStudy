package springbook.learningtest.templlate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilepath;

	@Before
	public void setUp() {
		this.calculator = new Calculator();
		this.numFilepath = getClass().getResource("numbers.txt").getPath();
	}


	@Test
	public void sumOfNumbers() throws IOException {

		int sum = calculator.calcSum(this.numFilepath);
		assertThat(sum, is(10));
	}

	@Test
	public void multiplyOfNumbers() throws IOException {

		int calcMultiply = calculator.calcMultiply(this.numFilepath);
		assertThat(calcMultiply, is(24));
	}

	@Test
	public void concatenateStrings() throws IOException {

		String string = calculator.concatenate(this.numFilepath);
		assertThat(string, is("1234"));
	};
}

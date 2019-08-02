package luizalabs.tddKata;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AppTest
{
	private Calculator calc;
	
	@Before
	public void setUp() {
		calc = new Calculator();
	}
	
	@Test
	public void test_empty_string() {		
		assertThat(calc.add(""), equalTo(0));
	}
	
	@Test
	public void test_one_number() {
		assertThat(calc.add("1"), equalTo(1));
	}
	
	@Test
	public void test_two_numbers() {
		assertThat(calc.add("1,2"), equalTo(3));
	}
	
	@Test
	public void test_many_numbers() {
		assertThat(calc.add("1,2,3,4,5,6,7,8,9"), equalTo(45));
	}
	
	@Test
	public void test_line_between_numbers() {
		assertThat(calc.add("1\n2,3"), equalTo(6));
	}
	
	@Test
	public void test_delimiter() {
		assertThat(calc.add("//;\n1;2"), equalTo(3));
		assertThat(calc.add("//z\n1,2z6"), equalTo(9));
		assertThat(calc.add("//*\n1,2*6\n5"), equalTo(14));
	}
	
	@Test
	public void test_exception_negative_number() {
		String message = "";
		
		try {
			calc.add("-1");
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		assertThat(message, equalTo("negatives not allowed: -1"));
	}
	
	@Test
	public void test_exception_negative_numbers() {
		String message = "";
		
		try {
			calc.add("//=\n1,-2=6,-5\n8=-9");
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		assertThat(message, equalTo("negatives not allowed: -2, -5, -9"));
	}

	@Test
	public void test_numbers_bigger_than_thousand() {
		assertThat(calc.add("2,1001,3"), equalTo(5));
		assertThat(calc.add("//;\n4,1000;6"), equalTo(1010));
	}
	
	@Test
	public void test_any_length_delimiter() {
		assertThat(calc.add("//[***]\n1***2***3"), equalTo(6));
		assertThat(calc.add("//[*[*]]\n4*[*]5*[*]1"), equalTo(10));
		assertThat(calc.add("//[*[*]]\n4*[*]5*[*]1,5\n2*[*]3"), equalTo(20));
	}

	@Test
	public void test_multiple_delimiters() {
		assertThat(calc.add("//[*][%]\n1*2%3"), equalTo(6));
		assertThat(calc.add("//[*12*][%]\n1*12*2%3*12*4"), equalTo(10));
		assertThat(calc.add("//[[]][%]\n1[]2%3[]4"), equalTo(10));
	}
}

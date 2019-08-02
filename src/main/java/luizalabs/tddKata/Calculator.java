package luizalabs.tddKata;

import luizalabs.tddKata.exception.NegativeNumbersException;

public class Calculator {

	public int add(String numbers) {
		if (numbers == "")
			return 0;
		
		if (numbers.startsWith("//")) {
			
			String delimiters = numbers.substring(2, numbers.indexOf("\n"));
			numbers =  numbers.substring(numbers.indexOf("\n") + 1);

			if (delimiters.length() == 1)
				delimiters = "[" + delimiters + "]";

			delimiters = delimiters.substring(1, delimiters.length() - 1);
			for (String delimiter : delimiters.split("\\]\\["))
				numbers = numbers.replace(delimiter, ",");
		}
		
		numbers = numbers.replace('\n', ',');
		
		int sum = 0;
		String negatives = "";
		for (String number : numbers.split(",")) {
			int parsedNumber = Integer.parseInt(number);
			if (parsedNumber < 0)
				negatives += negatives == "" ? parsedNumber : ", " + parsedNumber;
			
			if (parsedNumber <= 1000)
				sum += parsedNumber;
		}

		if (negatives != "")
			throw new NegativeNumbersException("negatives not allowed: " + negatives);

		return sum;
	}

}

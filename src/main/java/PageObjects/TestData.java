package PageObjects;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="InputData")
	public static Object[][] getData()
	{
		Object[][] obj = new Object[][]
				{{"Data Provider"},{"(#@*"}};	//set data in array. when it found there is 2 array, will run the same method twice.
				return obj;
	}

}

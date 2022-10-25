package src.com.action;

import java.util.Calendar;

public class test {

	public static void main(String[] args) {
		long result = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
		String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		
		int ran = (int)(Math.random()*10000);
		
		
		System.out.println(year.substring(2) + month + day + ran);
		
	
	}

}

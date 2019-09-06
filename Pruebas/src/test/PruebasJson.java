package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;

public class PruebasJson {

	public static void main(String[] args) {
//		 Gson gson = new Gson();
//
//		 	String jsonString = "{'employee.name':'Bob','employee.salary':10000}";
//
//		    @SuppressWarnings("rawtypes")
//			Map map = gson.fromJson(jsonString, Map.class);
//		    
//		    System.out.println(map.get("employee.name"));	    
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(2019, 0, 01);
		
		cal.add(Calendar.DAY_OF_YEAR, -7);
		
		Date date = new Date(cal.getTimeInMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM YYYY");
		
		System.out.println(sdf.format(date));

	}
}

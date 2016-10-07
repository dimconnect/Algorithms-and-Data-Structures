package validators;

import java.util.Date;

public class EGNValidator {
	
	public void validate(String egn){
		
		System.out.println("EGN: "+egn);
		
		String birthDay = getBirthDay(egn);
		
		if(checkDate(birthDay)){
			System.out.println("Birthday: "+birthDay);
			System.out.println("Sex: "+getSex(egn));
		}else{
			System.out.println("Inavalide EGN!");
		}
	}
	
	private String getBirthDay(String egn){
		
		/* *********************
		 * forming date from EGN
		 ***********************/
		
		StringBuilder message = new StringBuilder("");
		message.append(egn.substring(4,6)).append(".");
		int month = Integer.parseInt(egn.substring(2, 4));
		String year = egn.substring(0, 2);
		
		if(month >= 21 && month <= 32){
			
			/* *********************************************
			 * check if the person was born before year 1900
			 ***********************************************/
			month = month-20;
			year = 18+year;						
		}else if(month >= 41 && month <= 52){
			
			/* ********************************************
			 * check if the person was born after year 1999
			 **********************************************/
			month = month-40;
			year = 20+year;
		}else if(month >= 1 && month <= 12){
			
			/* ******************************************************
			 * check if the person was born at year between 1900-1999
			 ********************************************************/
			year = 19+year;
		}
		
		if(month < 10)
			//append 0 to get proper date format
			message.append(0);
		message.append(month).append(".").append(year);
		
		return message.toString();
	}
	
	private String getSex(String egn){
		
		/* *****************************************
		 * the ninth digit, referring to person sex
		 * (even number - male, odd number - female) 
		 *******************************************/
		String sex;
		int nine = Integer.parseInt(egn.substring(8,9));
		
		if(nine%2 == 0){
			sex = "male";
		}else{
			sex = "female";
		}
		return sex;
	}
	
	@SuppressWarnings("deprecation")
	private boolean checkDate(String date){
		
		boolean checked = false;
		String presentDate = new Date().toString();
		int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		if(date.length() == 10){
			
			/* ***********************************************************
			 * if month is incorrect, the date length will be less than 10
			 *************************************************************/
			
			int 
				presentYear = Integer.parseInt(presentDate.substring(presentDate.length()-4, presentDate.length())),
				year = Integer.parseInt(date.substring(6, date.length())),
				presentMonth = new Date().getMonth()+1,
				month = Integer.parseInt(date.substring(3, 5)),
				day = Integer.parseInt(date.substring(0, 2)),
				presentDay = new Date().getDate();						
			
			if(presentYear > year || 
					(presentYear == year && 
						(presentMonth > month || 
								(presentMonth == month && presentDay >= day)
						)
					)
				){
				
				/* ************************************************
				 * check if the person isn't born after present day
				 **************************************************/
				
				if(year%4 == 0)
					daysPerMonth[1] = 29;
				
				if(daysPerMonth[month-1] >= day)
					checked = true;
			}
		}
		return checked;
	}
}

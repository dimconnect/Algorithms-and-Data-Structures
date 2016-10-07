package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static validate.IBANValidator.swiftCode;
import static validate.IBANValidator.ibanLength;

public class LoadFromDatabase {
	
	public static void loadData(){
		
		Connection connect = null;
		Statement 
			bank = null,
			length = null;
		
		String 
			getBankBySwift = "select swift_code, bank_name from swift",
			getLengthByCountry = "select country_code, length from length_by_country",
			swift, bankName, countryCode;
		try{
			connect = GetConnect.connect();
			bank = connect.createStatement();
			length = connect.createStatement();						
			
			int countryLength;
			
			ResultSet 
				banks = bank.executeQuery(getBankBySwift),
				countriesLength = length.executeQuery(getLengthByCountry);
			
			while(banks.next()){				
				swift = banks.getString("swift_code");				
				bankName = banks.getString("bank_name");
				swiftCode.put(swift, bankName);
			}
			
			while(countriesLength.next()){
				countryCode = countriesLength.getString("country_code");				
				countryLength = countriesLength.getInt("length");
				ibanLength.put(countryCode, countryLength);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				bank.close();
				length.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

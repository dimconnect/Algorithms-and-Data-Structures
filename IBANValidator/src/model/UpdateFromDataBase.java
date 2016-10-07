package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static validate.IBANValidator.timestamp;
import static validate.IBANValidator.swiftCode;

public class UpdateFromDataBase {
	
	public static void update(){
		
		Connection connect = null;
		java.sql.PreparedStatement st = null;
		String 
			swift, bank,
			sql = "SELECT swift_code, bank_name FROM"
				+ " swift WHERE update_time > ?";
		
		try{
			connect = GetConnect.connect();
			st = connect.prepareStatement(sql);
			st.setString(1, timestamp.toString());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				swift = rs.getString("swift_code");
				bank = rs.getString("bank_name");
				swiftCode.put(swift, bank);
				timestamp = new Timestamp (new Date().getTime());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				st.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}

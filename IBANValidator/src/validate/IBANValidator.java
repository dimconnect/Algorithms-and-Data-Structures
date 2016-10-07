package validate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoadFromDatabase;

/**
 * Servlet implementation class IBANValidator
 */
@WebServlet("/IBANValidator")
public class IBANValidator extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static Map<String, String> swiftCode;
	public static Map<String, Integer> ibanLength;
	public static Timestamp timestamp;
	
    public IBANValidator() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		if(swiftCode == null && ibanLength == null){
			swiftCode = new TreeMap<String, String>();
			ibanLength = new TreeMap<String, Integer>();
			
			LoadFromDatabase.loadData();
			
			timestamp = new Timestamp (new Date().getTime());			
		}
		
		Timer timer = new Timer();
		UpdateDatDaStructure ds = new UpdateDataStructure();
		timer.scheduleAtFixedRate(ds, 10000, 10000);
		
		String iban = request.getParameter("iban").trim();
		StringBuilder message = new StringBuilder("IBAN: ").append(iban).append("@");
		
		if(iban.length() > 0){	
			iban = iban.replace(" ", "");
			
			if(checkLength(iban) && isValid(iban)){	
				message.append("Bank: ").append(getBankName(iban)).append("@");
				message.append("SWIFT: ").append(getSwift(iban));	
			}else{
				message.append("Invalid IBAN!");
			}						
		}
		
		request.setAttribute("message", message.toString());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ValidationForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private boolean checkLength(String iban){
		
		String iso = iban.substring(0, 2);
		
		boolean validLeangth = false;
		
		if(ibanLength.containsKey(iso)){
			
			int length = ibanLength.get(iso);
			
			if(length == iban.length())
				validLeangth = true;
		}
		return validLeangth;
	}
	
	private boolean isValid(String iban){
		
		String digitForm = new StringBuilder(iban.substring(4, iban.length()))
					.append(iban.substring(0, 4)).toString();
		
		char[] array = digitForm.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for(char c : array){
			int i = Character.getNumericValue(c);
			sb.append(i);
		}
		
		digitForm = sb.toString();
		
		int 
			remainder = 0, 
			offset = 9;
		
		boolean hasMoreDigits = true;
		
		while(hasMoreDigits){
			
			if(digitForm.length() <= offset){
				offset = digitForm.length();
				hasMoreDigits = false;
			}
			
			remainder = Integer.parseInt(digitForm.substring(0, offset)) % 97;
			
			digitForm = new String(remainder + digitForm.substring(offset, digitForm.length()));
		}		
		
		if(remainder == 1) return true;
		else return false;		
	}

	
	private String getSwift(String iban){
		 
		return new StringBuilder(iban.substring(4, 8)).append(iban.substring(0, 2)).toString();
	}
	
	private String getBankName(String iban){
		
		String 
			swift = getSwift(iban).substring(0, 4),
			message;
		
		if(swiftCode.containsKey(swift)){
			
			message = swiftCode.get(swift);
		}else{	
			message = "Invalid IBAN!";
		}
		
		return message;
	}

}

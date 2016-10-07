<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Validation</title>
</head>
<body>	
	<center>
		<fieldset>
			<legend>IBAN</legend>
			
			<form method="post" action="IBANValidator">
				<input type="text" name="iban" />
				<br /><br />
				<input type="submit" value="Check" />
			</form>
			
			<%
				String message = (String)request.getAttribute("message");
				if(message != null){
					for(String m : message.split("@")){%>
					<p>
						<%=m %>
					</p>
				<%}
				}
			%>
		</fieldset>
	</center>
</body>
</html>
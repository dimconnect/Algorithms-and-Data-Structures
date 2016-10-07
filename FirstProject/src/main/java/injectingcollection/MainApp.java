package injectingcollection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {
	public static void main(String[] args){
		ApplicationContext context;
		
		try{
			context = new FileSystemXmlApplicationContext(
					"D:/Spring/workspace/FirstProject/src/main/java/injectingcollection/Beans.xml");
			JavaCollection collection = (JavaCollection) context.getBean("javaCollection");
			collection.getAddressList();
			collection.getAddressSet();
			collection.getAddressMap();
			collection.getAddressProp();
			Bean country = (Bean) context.getBean("address1");
			country.setCountry("Poland");
			collection.getAddressList();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

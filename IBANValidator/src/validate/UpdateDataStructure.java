package validate;

import java.util.TimerTask;

import model.UpdateFromDataBase;

public class UpdateDataStructure extends TimerTask{
	
	public void run(){
		UpdateFromDataBase.update();
	}
}

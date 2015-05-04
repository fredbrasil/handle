package havecontrol.com.br.havecontrol.common;

import android.content.Context;
import br.com.havecontrol.entity.Cost;

public class HaveControlManager {

	private static HaveControlManager singleton;
	private static HaveControlMessage message;
	private static Context context;
	private Cost costCurrent;
	
	private HaveControlManager() {
		// TODO Auto-generated constructor stub
		message = HaveControlMessage.getInstance();
	}
	
	public static HaveControlManager getInstance(){
		
		if(singleton == null){
			singleton = new HaveControlManager();
		}
		
		return singleton;
	}
	
	public static HaveControlMessage getMessage() {
		return message;
	}
	
	public void setContext(Context context) {
		HaveControlManager.context = context;
		message.setContext(context);
	}
	
	public boolean showAlertDialogMessage(){
		return message.showAlertDialogMessage();
	}
	
	public boolean showToastMessage(){
		return message.showToastMessage();
	}
	
	public void addMessage(String msg){
		message.addMessage(msg);
	}
	
	public void addMessage(int stringResource){
		message.addMessage(stringResource);
	}

	public Cost getCostCurrent() {
		return costCurrent;
	}

	public void setCostCurrent(Cost costCurrent) {
		this.costCurrent = costCurrent;
	}
	
}

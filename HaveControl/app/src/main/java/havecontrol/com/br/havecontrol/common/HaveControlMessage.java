package havecontrol.com.br.havecontrol.common;

import br.com.havecontrol.common.util.NumberUtil;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class HaveControlMessage {

	private static HaveControlMessage singleton;
	private static StringBuilder message;
	private static String title;
	private static Context context;
	
	private HaveControlMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public static HaveControlMessage getInstance(){
		
		if(singleton == null){
			singleton = new HaveControlMessage();
			message = new StringBuilder();
			title = new String();
		}
		
		return singleton;
	}
	
	protected static boolean showAlertDialogMessage(){
		
		boolean showed = false;
		
    	if(hasMessage()){
    		showed = true;

    		AlertDialog.Builder builder = new AlertDialog.Builder(context);
        	builder.setTitle(title);
        	builder.setMessage(getMessage())
        	       .setCancelable(false)
        	       .setPositiveButton(Constant.OK, new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	                dialog.dismiss();
        	           }
        	       });
        	AlertDialog alert = builder.create();
        	alert.show();
    	}
    	
    	message = new StringBuilder();
    	
    	return showed;
    }	
	
	protected static boolean showToastMessage(){

		boolean showed = false;
		
		if(hasMessage()){
			showed = true;
			Toast.makeText(context, getMessage(), Toast.LENGTH_LONG).show();
    	}
		
		message = new StringBuilder();
		
		return showed;
    }
	
	protected static void addMessage(String msg) {
		HaveControlMessage.message.append(msg).append("\n");
	}
	
	protected static void addMessage(int stringResource) {
		String msg = context.getResources().getString(stringResource);
		HaveControlMessage.message.append(msg).append("\n");
	}
	
	private static String getMessage() {
		return message.toString();
	}
	
	protected static boolean hasMessage() {
		
		if(getMessage().length() > NumberUtil.ZERO){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setContext(Context context) {
		HaveControlMessage.context = context;
	}
}

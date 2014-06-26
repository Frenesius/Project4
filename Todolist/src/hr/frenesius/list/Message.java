package hr.frenesius.list;

import android.content.Context;
import android.widget.Toast;

public class Message{
	/*
	 * This class contains Message
	 * Message is an alternative to Toast
	 * It is an class that helps with debugging and giving messages back
	 */

	public static void message(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();	
	}
}
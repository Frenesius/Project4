package hr.frenesius.todolist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import hr.frenesius.todolist.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartupActivity extends Activity {
	Button b1;
	String name;
	SharedPreferences SHAREDPREFS;
	Calendar SAdate = Calendar.getInstance();
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		MainActivity.MainActivityACTIVITY.finish();	
		b1 = (Button) findViewById(R.id.SAbutton1);
		b1.setOnClickListener(b1listener);
	} 
	
	View.OnClickListener b1listener = new View.OnClickListener() {
		public void onClick(View v) {
			getTextSAeditText1();
			processInput();
			Toast.makeText(getApplicationContext(), "Welkom " + name,
					   Toast.LENGTH_LONG).show();
			finishActivity();
		}
	};
	
	
	private void finishActivity(){
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
	
		//End Main activity wanneer iets ingevuld
		startActivity(i);
		finish();
	}
	private void getTextSAeditText1(){
		EditText i = (EditText) findViewById(R.id.SAeditText1);
		name = i.getText().toString();
	
	}
	private void processInput(){
		//Places everything in 
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		Editor a  = SHAREDPREFS.edit();
		a.putString("Name", name);
		a.commit();
		
		Calendar cal = Calendar.getInstance();
		MainActivity.user.setfirstLogin(cal);	
	}	
}

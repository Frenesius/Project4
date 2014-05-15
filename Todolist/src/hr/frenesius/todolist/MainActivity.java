package hr.frenesius.todolist;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	Button button1; // Button
	Button button2; // Button

	private int dateday; 
	private int datemonth;
	private int dateyear; 
	private String datestring;
	private Editable text;
	private String parsedtext;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		// Buttons voor on click
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(button1listener);
		
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(button2listener);

	}
	public void nextIntent(){
		Intent intent = new Intent();
		intent.setClass(this, ListActivity.class);
		intent.putExtra("EXTRA_ID", "SOME DATAS");
		startActivity(intent);
		
	}
	
	// Onclick listener
	View.OnClickListener button1listener = new View.OnClickListener() {
		public void onClick(View v) {
			//Pakt text van een input en viewt het
			getTexteditText();
			//Pakt datum van input en viewt het in een textview
			setDateDatepicker(); 
		}
	};
	
	View.OnClickListener button2listener = new View.OnClickListener() {
		public void onClick(View v) {
			nextIntent();
			
			Toast.makeText(getApplicationContext(), "Button2 clicked",
					   Toast.LENGTH_LONG).show();
		}
	};

	
	public void setDateDatepicker() { // On click add calendar view
		//Pakt datum
		getDateDatepicker();
		
		//Parse date
		parseDate();
		
		//Zet text in textview
		setDateTextview();
		
		//Geeft een Toast terug
		Toast.makeText(getApplicationContext(), "testDate() triggered",
				Toast.LENGTH_LONG).show(); // Debug Datum
	}
	
	private void getDateDatepicker(){
		DatePicker a2 = (DatePicker) findViewById(R.id.datePicker1); // Maakt Datepicker var aan
		//Get date														
		dateday = a2.getDayOfMonth(); // Pakt datepicker dag
		datemonth = a2.getMonth(); // Pakt datepicker maand
		dateyear = a2.getYear(); // Pakt datepicker jaar	
	}
	
	private void parseDate(){
		String day = String.valueOf(dateday); // Parsed dag in string
		String month = String.valueOf(datemonth); // Parsed maand in string
		String year = String.valueOf(dateyear); // Parsed jaar in string
		
		datestring = day + "-" + month + "-" + year; // Maakt er een DD-MM-YYYY van

	}
	
	private void setDateTextview(){
		TextView tv = (TextView) findViewById(R.id.dateView1); // Pakt textview in een variabele
		tv.setText(datestring); // Zet de datum in textview
	}
	
	// Pakt text van edittext en zet in view
	public void getTexteditText() {
		//Pakt de text uit input
		getTextText1();
		//Parsed Editable text in String
		parseTextText1();
		//Zet Parsed text in Textview
		setTextText1();
	}
	private void getTextText1(){
		EditText i = (EditText) findViewById(R.id.editText1);
		text = i.getText();
	}
	private void parseTextText1(){
		parsedtext = text.toString();
	}
	private void setTextText1(){
		TextView a = (TextView) findViewById(R.id.textView1);
		a.setText(parsedtext);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}

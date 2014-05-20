package hr.frenesius.todolist;

import java.util.Calendar;

import hr.frenesius.list.Habit;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ListActivity extends ActionBarActivity {
	
	Button button1; // Button


	private int day; 
	private int month;
	private int year; 
	private String datestring;
	private Editable text;
	private String parsedtext;
	Habit habit = new Habit();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		// Buttons voor on click
				button1 = (Button) findViewById(R.id.inputbutton1);
				button1.setOnClickListener(button1listener);
				
				
	
	
	
	
	}
	// Onclick listener
		View.OnClickListener button1listener = new View.OnClickListener() {
			public void onClick(View v) {
				//Pakt text van een input en viewt het
				getTexteditText();
				//Pakt datum van input en viewt het in een textview
				setDateDatepicker(); 
				//Koppel habit.class met fields
				testHabit();
			
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
		
		
		
		
		//TEST
		private void testHabit(){
			//Maakt object aan en vult text en datum in
			habit.setText(parsedtext);
			habit.setDate(year, month, day);
		
			//Geeft een Toast terug
			Toast.makeText(getApplicationContext(), "testHabit() triggered",
					Toast.LENGTH_LONG).show(); // Debug Datum
			
		}
		
		//TEST
		
		
		
		private void getDateDatepicker(){
			DatePicker a2 = (DatePicker) findViewById(R.id.inputdatePicker1); // Maakt Datepicker var aan
			//Get date														
			day = a2.getDayOfMonth(); // Pakt datepicker dag
			month = a2.getMonth(); // Pakt datepicker maand
			year = a2.getYear(); // Pakt datepicker jaar	
		}
		
		private void parseDate(){
			String stringday = String.valueOf(day); // Parsed dag in string
			String stringmonth = String.valueOf(month); // Parsed maand in string
			String stringyear = String.valueOf(year); // Parsed jaar in string
			
			datestring = stringday + "-" + stringmonth + "-" + stringyear; // Maakt er een DD-MM-YYYY van

		}
		//FIXEN
		private void setDateTextview(){
			String datetext = "";
			Calendar date = habit.getDate();

			
			TextView tv = (TextView) findViewById(R.id.DatetextView); // Pakt textview in een variabele
		
			
				try{
					datetext = date.toString();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			tv.setText(datetext); // Zet de datum in textview
		}
		
		
		
		
		//FIXED
		
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
			EditText i = (EditText) findViewById(R.id.inputeditText);
			text = i.getText();
		}
		private void parseTextText1(){
			parsedtext = text.toString();
		}
		private void setTextText1(){
			String text = habit.getText();
			TextView a = (TextView) findViewById(R.id.inputtextView);
			a.setText(text);
			
			
		}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

	// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_list, container,
					false);
			return rootView;
		}
	}

}

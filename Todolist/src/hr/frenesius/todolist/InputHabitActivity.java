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

public class InputHabitActivity extends ActionBarActivity {
	
	Button button1; // Button


	private int day; 
	private int month;
	private int year; 
	private String text1;
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
				//GETS
				getDateDatepicker();
				getTexteditText1();
				
				//ASSIGN
				fillHabitObject();
				
				//SET
				setDateTextview();
				setHabitTextview();
				
			}
		};
		
		
		
		//GETS
		private void getDateDatepicker(){
			DatePicker a2 = (DatePicker) findViewById(R.id.inputdatePicker1); // Maakt Datepicker var aan
			//Get date														
			day = a2.getDayOfMonth(); // Pakt datepicker dag
			month = a2.getMonth(); // Pakt datepicker maand
			year = a2.getYear(); // Pakt datepicker jaar	

		}
		private void getTexteditText1(){
			EditText i = (EditText) findViewById(R.id.inputeditText);
			Editable a = i.getText();
			text1 = a.toString();
		}
		//EINDE GETS
		
		//ASSIGN
		private Habit fillHabitObject(){
			habit.setText(text1);
			habit.setDate(year, month, day);
			
			return habit;
		}
		//EINDE ASSIGN
		
		//SETS
		private void setDateTextview(){
			//set date
			TextView tv = (TextView) findViewById(R.id.DatetextView); // Pakt textview in een variabele
			
			//Parse date
			Calendar a = habit.getDate();
			
			int ab = a.get(Calendar.DAY_OF_MONTH);
			int ac = a.get(Calendar.MONTH);
			int ad = a.get(Calendar.YEAR);
			
			String ca = String.valueOf(ab); // Parsed dag in string
			String ba = String.valueOf(ac); // Parsed maand in string
			String aa = String.valueOf(ad); // Parsed jaar in string
			
			String c = ca + "-" + ba + "-" + aa;
		
			//Set date	
			tv.setText(c); // Zet de datum in textview
		}
	
		private void setHabitTextview(){
			String habittext = habit.getText();
			TextView a = (TextView) findViewById(R.id.inputtextView);
			
			a.setText(habittext);	
		}			
		
	//EINDE SETS	
		
		
		


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


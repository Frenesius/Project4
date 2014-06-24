package hr.frenesius.todolist;

import java.util.Calendar;

import hr.frenesius.data.DbDatabaseCreate;
import hr.frenesius.data.DbHelper;
import hr.frenesius.list.Habit;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
	private String title1;
	private String description1;
	private int reward1;
	static int InputHabitActivityCounter;

	DbHelper helper;
	SQLiteDatabase db;
	DbDatabaseCreate entry;
	
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
				getTexttitleText1();
				getTextdescriptionText1();
				
				//Maak object en vul het in
				makeObject();
				
			}
		};
		
//GETS

		private void getTexttitleText1(){
			EditText i = (EditText) findViewById(R.id.titleText1);
			Editable a = i.getText();
			title1 = a.toString();
			
			
			
		}
		private void getTextdescriptionText1(){
			
			EditText i = (EditText) findViewById(R.id.descriptionText1);
			Editable a = i.getText();
			description1 = a.toString();
		}
//EINDE GETS
		
//Nieuwe object creatie
		private void makeObject(){
			
			Habit h = new Habit(title1, description1);
			h.setReward(10);
			reward1 = h.getReward();
			MainActivity.goodHabitTRIGGER = true;
			
			Intent i = new Intent();
			i.setClass(this, MainActivity.class);
			
			//End Main activity wanneer iets ingevuld
			
			placeInDatabase();
			
			MainActivity.MainActivityACTIVITY.finish();
			finish();
			startActivityForResult(i,1);
		}
		private void placeInDatabase(){
			String title = title1;				//title is de string voor title die je in databse moet zetten
			String description = description1;	//description is een string die je in databse moet zetten
			int reward = reward1;	
			//Ook in database maar let op integer!
			
			entry = new DbDatabaseCreate(InputHabitActivity.this);
			helper = new DbHelper(this);
			entry.open();
			
	        entry.createEntryGoodHabit(title, description, reward);
				Toast.makeText(getApplicationContext(), "CreateEntry", Toast.LENGTH_LONG).show();
			entry.close();
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


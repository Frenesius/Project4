package hr.frenesius.todolist;

import hr.frenesius.data.FDatabaseHelper;
import hr.frenesius.list.Habit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.TableRow.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends ActionBarActivity {
//PositiveHabitList.clear() als een reset knop
	 
	Button button1; 									// Button
	Button button2; 									// Button
	LinearLayout ln;									//Lineare Layout
	public static boolean MainActivityTRIGGER = false; 	//Triggerchecker voor onResume
	public static Activity MainActivityACTIVITY;		//Gebruikt in InputHabitActivity.class om MainActivity te finish()
	static List<Habit> PositiveHabitlist 
	= new ArrayList<Habit>();							//List met alle Habit objecten
	int habitcounter = 1;
	//Shared preferences settings 
	final static String PREFS_NAME = "Happits";
	SharedPreferences SHAREDPREFS;
	public static boolean SATRIGGER = false;
		
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		firstLaunch();		//UNCOMMENT WHEN RELEASING
		

		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		// Buttons voor on click
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(button2listener);
		MainActivityACTIVITY = this;

	}

	
	
	private void firstLaunch(){					//Checks if app is launched for first time
		SHAREDPREFS = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		if (SHAREDPREFS.getBoolean("first_run", true)) {
		    intentStartupActiviy();
		    // record the fact that the app has been started at least once
		    SHAREDPREFS.edit().putBoolean("first_run", false).commit(); 
		}
	}
	private void intentStartupActiviy(){
		Intent i = new Intent();
		i.setClass(this, StartupActivity.class);
		startActivity(i);	
	}
	
	protected void onResume(){
		super.onResume();
		
		
		
		
		//Checkt of input getriggerd is
		if(MainActivityTRIGGER == true){
			processObject();
			DATABASETEST();

			MainActivityTRIGGER = false;
				}
		
		try{
			addHabitToDashboard();
		}catch(Exception e){
			}
		getUserName();
		setHabitCounter();
		}
	
	private void DATABASETEST(){
		//Om dit te gebruiken moet je het in mainactivity zetten onder:
		//if(MainActivityTRIGGER == true)  processObject(); onder regel 97
		//Ook eerst een habit toevoegen, daarna pas kan dit uitgevoerd worden
		Habit h = PositiveHabitlist.get(0);
		String title = h.getTitle();				//title is de string voor title die je in databse moet zetten
		String description = h.getDescription();	//description is een string die je in databse moet zetten
		int reward = h.getReward();					//Ook in database maar let op integer!
		
		
		//Probeer eerst de Calendar object date in database te zetten, 
		//Mocht dat niet lukken, heb ik string gemaakt dateString

		//
		//voeg codes die je gaat verwijderen toe in snippet(als het grote code is en niet een int)
		//Voer hier onder je Query's toe
		//V
		
		Databasecreate entry = new Databasecreate(MainActivity.this);
        entry.open();
        entry.createEntry(title, description, reward);
        entry.close();
        
        
        
       // SQLiteDatabase db;
        //db = openOrCreateDatabase(
         //       "Habbitdb.db"
          //      , SQLiteDatabase.CREATE_IF_NECESSARY
           //     , null
            //    );

		
		
		//^
		Toast.makeText(getApplicationContext(), "exec DATABASETEST()", Toast.LENGTH_LONG).show();
	}
	
	private void processObject(){
		//Pakt habit van Activity Input
		Habit h = (Habit)getIntent().getExtras().getParcelable("INPUT_HABIT");
		PositiveHabitlist.add(h);		
	}
	//DASHBOARD RELATED
	private void getUserName(){
		SHAREDPREFS = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		String name = SHAREDPREFS.getString("Name", "Hai");
		TextView i = (TextView) findViewById(R.id.MAtextView1);
		i.setText("Welkom " + name);
	}
	private void setHabitCounter(){
		ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		ln.setOrientation(LinearLayout.VERTICAL); 
		
		TableLayout ll = (TableLayout) findViewById(R.id.DashboardMain);
		TextView tv = new TextView(this);
		//Table layout
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.leftMargin = 10;
		lp.rightMargin = 15;
		lp.bottomMargin = 10;
		ll.setLayoutParams(lp);
		//Set counter 
		int hCounter = habitcounter -1;
		tv.setText("Aantal Habits:" + hCounter);
		ll.addView(tv);	
	}
	
	
	private void addHabitToDashboard(){
		//Variabelen
		int length = PositiveHabitlist.size();
		ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		ln.setOrientation(LinearLayout.VERTICAL); 
		
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		
			for (int i = 0; i < N; i++) {
				//Local vars
				TableLayout ll = (TableLayout) findViewById(R.id.GoodHabitsMain);
				TextView tv = new TextView(this);
				Habit habit = PositiveHabitlist.get(i);
				
				//Table layout
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				lp.leftMargin = 10;
				lp.rightMargin = 15;
				lp.bottomMargin = 10;
				
				ll.setLayoutParams(lp);
				
				//Get strings
				String habitnumber = "Habit Number: " + String.valueOf(habitcounter);
				String Title = habit.getTitle();
				String description = habit.getDescription();
				
				//Set text for the row
				tv.setText(habitnumber+ "\n" + Title + " \n" + description + "\n --------------------");
		
				//Add row in Tableview
				ll.addView(tv);	
				habitcounter++;
//ADD EEN STREEP VIEW HIERONDER 
				
}
	}
	


	
	
	// Onclick listener	
	View.OnClickListener button2listener = new View.OnClickListener() {
		public void onClick(View v) {
			nextIntent();
			
			Toast.makeText(getApplicationContext(), "Button2 clicked",
					   Toast.LENGTH_LONG).show();
		}
	};
	
	//Opent InputHabitActivity Activity 
	public void nextIntent(){
		Intent i = new Intent();
		i.setClass(this, InputHabitActivity.class);
		startActivity(i);
		
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

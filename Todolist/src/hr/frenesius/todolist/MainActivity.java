package hr.frenesius.todolist;


import hr.frenesius.data.DbDatabaseCreate;
import hr.frenesius.data.DbHelper;
import hr.frenesius.list.Habit;
import hr.frenesius.list.Message;
import hr.frenesius.list.User;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
//PositiveHabitList.clear() als een reset knop
	 
	LinearLayout ln;									//Lineare Layout
	public static boolean SATRIGGER = false;
	public static boolean goodHabitTRIGGER = false; 	//Triggerchecker voor onResume
	public static boolean badHabitTRIGGER = false; 	//Triggerchecker voor onResume
	public static User user = new User();				//Een user met zijn eigen attributen
	public static Activity MainActivityACTIVITY;		//Gebruikt in InputHabitActivity.class om MainActivity te finish()
	final static String PREFS_NAME = "Happits";
	
	//Habit lists
	static List<Habit> goodHabitlist 
	= new ArrayList<Habit>();							//List met alle Habit objecten
	static List<Habit> badHabitlist 
	= new ArrayList<Habit>();							//List met alle Habit objecten
	
	int habitcounter = 1;
	//Shared preferences settings 
	SharedPreferences SHAREDPREFS;
	String name1;
	DbHelper helper;
	SQLiteDatabase db;
	DbDatabaseCreate entry;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		firstLaunch();		//Checks if game is launched for first time
		setUser();			//Sets user's name
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		MainActivityACTIVITY = this;
		//VOORBEELD
		helper = new DbHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		//Checkt of input getriggerd is
		processObject();
		addHabitsToDashboard();
		
		DATABASESELECT();
	}
	
	
	protected void onResume(){
		super.onResume();
		
	}
	private void addHabitsToDashboard(){
		try{
			addGoodHabitToDashboard();			//VOEG HIER OOK DE BAD HABIT PROCESS AN TOE
			addBadHabitToDashboard();
		}catch(Exception e){
			}
	}
	
	private void processObject(){
		if(goodHabitTRIGGER == true){
			processGoodHabit();
			
			goodHabitTRIGGER = false;
		}
		if(badHabitTRIGGER == true){
			processBadHabit();
			badHabitTRIGGER = false;
		}
	}
	
	//Database test -> downlaod SQLiteManager plugin eclipse
	private void DATABASETEST(){
		//Om dit te gebruiken moet je het in mainactivity zetten onder:
		//if(MainActivityTRIGGER == true)  processObject(); onder regel 97
		//Ook eerst een habit toevoegen, daarna pas kan dit uitgevoerd worden
		Habit h = goodHabitlist.get(0);
		
		String title = h.getTitle();				//title is de string voor title die je in databse moet zetten
		String description = h.getDescription();	//description is een string die je in databse moet zetten
		int reward = h.getReward();					//Ook in database maar let op integer!
		
		//Probeer eerst de Calendar object date in database te zetten, 
		//Mocht dat niet lukken, heb ik string gemaakt dateString

		//
		//voeg codes die je gaat verwijderen toe in snippet(als het grote code is en niet een int)
		//Voer hier onder je Query's toe
		//V
		entry = new DbDatabaseCreate(MainActivity.this);
		helper = new DbHelper(this);
		entry.open();
        entry.createEntry(title, description, reward);
			Toast.makeText(getApplicationContext(), "CreateEntry", Toast.LENGTH_LONG).show();
		entry.close();
	}
	
	private void DATABASESELECT(){
		entry = new DbDatabaseCreate(MainActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();
		
		String selectQuery = "SELECT "+ DbHelper.KEY_TITLE +" FROM " + DbHelper.DATABASE_TABLE +";";
		String selection[] = {DbHelper.KEY_TITLE};
		
		String a = "";
		
		
		try {
			cursor = db.rawQuery("select title from habit", null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			Toast.makeText(getApplicationContext(), e1.toString(), 1).show();
			
		}
		//HAALT GEGEVENS OP VAN DB
		//ALLEEN VERWERKT/ LAAT HET NIET ZIEN
		//DATABASETEST() WERKT, ROND HET AF
		//MAAK OOK DATABASETEST() VOOR STARTUPSCREEN
		entry.close();
	}
	
		
//START	
//MISC
//	
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
	private void nextIntentBadHabit(){
		Intent i = new Intent();
		i.setClass(this, InputBadHabitActivity.class);
		startActivity(i);	
	}
	private void nextIntent(){
		Intent i = new Intent();
		i.setClass(this, InputHabitActivity.class);
		startActivity(i);
	}
	private void intentRewardActivity(){
		Intent i = new Intent();
		i.setClass(this, RewardActivity.class);
		startActivity(i);
	}
	private void updateScore(){
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + user.getRewardpoint());
	} 
//EINDE	
//MISC
//		
	
//START	
//DASHBOARD RELATED
//	
	private void getUserName(){
		SHAREDPREFS = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		TextView i = (TextView) findViewById(R.id.YourName);
		name1 = SHAREDPREFS.getString("Name", "Hai");
		
		i.setText("Welkom " + user.getName());
		}
	
	private void setUserAantalHabits(){
		int aantalHabits = goodHabitlist.size() + badHabitlist.size();
		user.setAantalHabits(aantalHabits);
	}
	
	private void setUser(){
		SHAREDPREFS = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		name1 = SHAREDPREFS.getString("Name", "Hai");
		user.setName(name1);
		
	}
//EINDE	
//DASHBOARD RELATED
//		
	

	
//START
//HABIT RELATED
//
	private void processGoodHabit(){
		//Pakt habit van Activity Input
		Habit h = (Habit)getIntent().getExtras().getParcelable(InputHabitActivity.goodHabitParcelable);
		goodHabitlist.add(h);
		setUserAantalHabits();	
	}
	private void processBadHabit(){
		//Pakt habit van Activity Input
		Habit h = (Habit)getIntent().getExtras().getParcelable(InputBadHabitActivity.badHabitParcelable);
		badHabitlist.add(h);
		setUserAantalHabits();
		
	}
	
	private void addGoodHabitToDashboard(){
		//Variabelen
		int length = goodHabitlist.size();
		ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		ln.setOrientation(LinearLayout.VERTICAL); 
		
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		
			for (int i = 0; i < N; i++) {
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this); //
				Habit habit = goodHabitlist.get(i); //
				
				//Get strings
				String Title = habit.getTitle(); //
				String description = habit.getDescription(); //
				
				//Set text for the row
				tv.setText(Title + " \n" + description + "\n --------------------"); //
				
				//layouts
				TableLayout ll = (TableLayout) findViewById(R.id.GoodHabitsMain); //
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); //
				LayoutParams lptr = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams lpb1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams lptv = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				//Params
				lptr.weight = 8;				
				lptv.weight = 7;
				lpb1.weight = 1;
				lp.leftMargin = 10; 
				lp.rightMargin = 15; 
				lp.bottomMargin = 10; 
				
				//Delete row
				TableRow r = (TableRow) findViewById(R.id.tableRow75);
				ll.removeView(r);
				//Buttons
				Button b1 = new Button(this);
				b1.setBackgroundResource(R.drawable.button_good);
				
				b1.setLayoutParams(lpb1);
				tv.setLayoutParams(lptv);
				tr.setLayoutParams(lptr);
				ll.setLayoutParams(lp); 
				
				tr.addView(tv);
				tr.addView(b1);
				//Add row in Tableview
				ll.addView(tr);	 
				habitcounter++; 
				
				b1.setOnClickListener(habitB1);
						
//ADD EEN STREEP VIEW HIERONDER 				
} 
	}
	private void addBadHabitToDashboard(){
		//Variabelen
				int length = badHabitlist.size();
				ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
				ln.setOrientation(LinearLayout.VERTICAL); 
				
				//Workaround voor probleem
				final int N = length; // total number of textviews to add
				
					for (int i = 0; i < N; i++) {
						TableRow tr = new TableRow(this);
						TextView tv = new TextView(this); //
						Habit habit = badHabitlist.get(i); //
						
						//Get strings
						String habitnumber = "Habit Number: " + String.valueOf(habitcounter); //
						String Title = habit.getTitle(); //
						String description = habit.getDescription(); //
						
						//Set text for the row
						tv.setText(Title + " \n" + description + "\n --------------------"); //
						
						//layouts
						TableLayout ll = (TableLayout) findViewById(R.id.BadhabitsMain); //
						LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); //
						LayoutParams lptr = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						LayoutParams lpb2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						LayoutParams lptv = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						//Params
						lptr.weight = 8;				
						lptv.weight = 7;
						lpb2.weight = 1;
						lp.leftMargin = 10; 
						lp.rightMargin = 15; 
						lp.bottomMargin = 10; 
						
						//Buttons
						Button b2 = new Button(this);
						
						b2.setBackgroundResource(R.drawable.button_bad);
						
						b2.setLayoutParams(lpb2);
						tv.setLayoutParams(lptv);
						tr.setLayoutParams(lptr);
						ll.setLayoutParams(lp); 
					
						tr.addView(tv);
						tr.addView(b2);
						
						//Add row in Tableview
						ll.addView(tr);	 
						habitcounter++; 
						
						b2.setOnClickListener(habitB2);
					}
	}
//EINDE	
//HABIT RELATED
//	
	
//START	
//ONCLICK LISTENERS
//
	
	View.OnClickListener habitB1 = new View.OnClickListener() {
		public void onClick(View v) {
			int length = goodHabitlist.size();
			final int N = length; // total number of textviews to add
			for (int i = 0; i < N; i++) {
				
				Habit habit = goodHabitlist.get(i);
				user.addRewardPoint(habit.getReward());
				updateScore();
				
			}
		}
	};
		
	View.OnClickListener habitB2 = new View.OnClickListener() {
		public void onClick(View v) {
			int length = goodHabitlist.size();
			final int N = length; // total number of textviews to add
			
			for (int i = 0; i < N; i++) {
				
				Habit habit = goodHabitlist.get(i);
				int negativeReward = 0 - habit.getReward();
				user.addRewardPoint(negativeReward);
				updateScore();
				
			}
		}
	};	
//EINDE
//ONCLICK LISTENERS
//

	
	





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
		
		if (id == R.id.GoodHabitAction) {
			Message.message(getApplicationContext(), "Loading...");
			nextIntent();
			return true;
		}
		
		if (id == R.id.badHabitAction) {
			Message.message(getApplicationContext(), "Loading...");
			nextIntentBadHabit();
			return true;
			
		}
		if (id == R.id.reward_activity) {
			Message.message(getApplicationContext(), "Loading...");
			intentRewardActivity();
			
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
